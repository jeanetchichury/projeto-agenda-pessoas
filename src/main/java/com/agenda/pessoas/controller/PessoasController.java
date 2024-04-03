package com.agenda.pessoas.controller;

import com.agenda.pessoas.Exception.BadRequestException;
import com.agenda.pessoas.Exception.NotFoundException;
import com.agenda.pessoas.entity.Pessoa;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {
    @GetMapping("/")
    public String getAll(){
        return "deu bom!" ;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) throws NotFoundException {
        return "service.getById(id)";
    }

    @PostMapping
    public Pessoa create(@Validated @RequestBody Pessoa pessoa) throws BadRequestException {
        Pessoa infos = pessoa;
        return infos;
    }

    @PutMapping("/{id}")
    public Pessoa update(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return pessoa;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return "deletou";
    }

}
