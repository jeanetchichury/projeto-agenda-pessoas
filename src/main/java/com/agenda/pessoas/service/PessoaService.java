package com.agenda.pessoas.service;

import com.agenda.pessoas.exception.BadRequestException;
import com.agenda.pessoas.exception.NotFoundException;
import com.agenda.pessoas.entity.Pessoa;
import com.agenda.pessoas.repository.PessoaRepository;
import com.agenda.pessoas.request.PessoaUpdateRequest;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Any;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Pessoa createPessoa(Pessoa pessoa) throws BadRequestException {
        String cpf = pessoa.cpf;

        Boolean procuraDuplicidade = this.existsPessoaByCpf(cpf);
        if (procuraDuplicidade) throw new BadRequestException("CPF já cadastrado.");

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

//    public Any getEndereco(String cep) {
//        String endereco = ViaCep.findCep("01001000");
//    }
}
