package com.agenda.pessoas.controller;

import com.agenda.pessoas.exception.BadRequestException;
import com.agenda.pessoas.exception.NotFoundException;
import com.agenda.pessoas.entity.Pessoa;
import com.agenda.pessoas.request.PessoaUpdateRequest;
import com.agenda.pessoas.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoasController {

    private PessoaService service;

    @GetMapping()
    public Page<Pessoa> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return (Page<Pessoa>) service.getAllPessoas(page, size);
    }

//    @GetMapping("/cep")
//    public Any getEndereco(@RequestParam String cep){
//        return service.getEndereco(cep);
//    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getById(@PathVariable Long id) throws NotFoundException {
        return service.findPessoaById(id);
    }

    @PostMapping
    public Pessoa create(@Valid @RequestBody Pessoa pessoa) throws BadRequestException, IOException, InterruptedException {
        return service.createPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa update(@PathVariable Long id, @RequestBody PessoaUpdateRequest pessoa){
        return service.updatePessoa(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        return service.deletePessoa(id);
    }
}
