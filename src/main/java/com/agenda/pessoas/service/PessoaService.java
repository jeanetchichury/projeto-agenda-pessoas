package com.agenda.pessoas.service;

import com.agenda.pessoas.exception.BadRequestException;
import com.agenda.pessoas.exception.NotFoundException;
import com.agenda.pessoas.entity.Pessoa;
import com.agenda.pessoas.repository.PessoaRepository;
import com.agenda.pessoas.request.PessoaUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {
    private PessoaRepository repository;

    public Page<Pessoa> getAllPessoas(int number, int size){
        return repository.findAll(PageRequest.of(number, size));
    }

    public Optional<Pessoa> findPessoaById(Long id) throws NotFoundException {
        return repository.findById(id);
    }

    public Boolean existsPessoaByCpf(String cpf) throws NotFoundException {
        return repository.existsByCpf(cpf);
    }

    public Pessoa createPessoa(Pessoa pessoa) throws BadRequestException, IOException, InterruptedException {
        String cpf = pessoa.cpf;
        String cep = pessoa.cep;

        Boolean procuraDuplicidade = this.existsPessoaByCpf(cpf);
        if (procuraDuplicidade) throw new BadRequestException("CPF já cadastrado.");

        String URL_GET = "http://viacep.com.br/ws/"+cep+"/json/";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_GET))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        if(response.statusCode() == 400){
            throw new BadRequestException("cep inválido");
        }

        pessoa.ativo = true;

        return repository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, PessoaUpdateRequest request) throws BadRequestException {
        Pessoa pessoa = this.findPessoaById(id).orElseThrow(() -> new NotFoundException("pessoa não encontrada"));
        if (request.cep != null) {
            pessoa.cep = request.cep;
        }

        if (request.endereco != null) {
            pessoa.endereco = request.endereco;
        }

        if (request.telefone != null) {
            pessoa.telefone = request.telefone;
        }

        if (request.ativo != null){
            pessoa.ativo = request.ativo;
        }

        return repository.save(pessoa);
    }

    public HttpStatus deletePessoa (Long id){
        Pessoa pessoa = this.findPessoaById(id).orElseThrow(() -> new NotFoundException("pessoa não encontrada"));

        PessoaUpdateRequest deletarPessoa = new PessoaUpdateRequest();
        deletarPessoa.ativo = false;

        this.updatePessoa(id,deletarPessoa);

        return HttpStatus.OK;
    }

}
