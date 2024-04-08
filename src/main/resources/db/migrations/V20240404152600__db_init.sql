CREATE TABLE pessoas (
                           id SERIAL PRIMARY KEY NOT NULL,
                           nome VARCHAR(255) NOT NULL,
                           sobrenome VARCHAR(255) NOT NULL,
                           nascimento DATE NOT NULL,
                           cpf VARCHAR(11) UNIQUE NOT NULL,
                           cep VARCHAR(8) NOT NULL,
                           endereco VARCHAR (255) NOT NULL,
                           telefone VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL NOT NULL
);
