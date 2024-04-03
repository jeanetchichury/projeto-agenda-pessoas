package com.agenda.pessoas.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private String sobrenome;

    @NonNull
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @NonNull
    @DateTimeFormat
    private Date nascimento;

    @NonNull
    private String telefone;

    @NonNull
    private String endereco;

    @NonNull
    private String email;

}
