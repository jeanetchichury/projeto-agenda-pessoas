package com.agenda.pessoas.request;

import lombok.Data;

@Data
public class PessoaUpdateRequest {
    public String telefone;

    public String cep;

    public String endereco;

    public Boolean ativo;

}
