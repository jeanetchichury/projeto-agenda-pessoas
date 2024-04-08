package com.agenda.pessoas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.ReadOnlyProperty;
import java.util.Date;

@Data
@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "o nome não pode ser vazio")
    public String nome;

    @NotBlank(message = "o sobrenome não pode ser vazio")
    public String sobrenome;

    @NotBlank(message = "o nome não pode ser vazio")
    @CPF
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    public String cpf;

    @NotNull(message = "a data de nascimento não pode ser vazio")
    public Date nascimento;

    @NotBlank(message = "o telefone não pode ser vazio")
    public String telefone;

    @NotBlank(message = "o endereço não pode ser vazio")
    public String endereco;

    @NotBlank(message = "o CEP não pode ser vazio")
    public String cep;

    @NotBlank
    @Email(message = "precisa ser um email válido.")
    public String email;

    public Boolean ativo;

}
