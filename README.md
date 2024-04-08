Projeto de agenda de pessoas proposto por Ederrua.

Para gerar o banco de dados, só é mecessário rodar o comando 'docker-compose up'. 
As configurações do banco de dados está toda no arquivo de properties, para evitar ser necessário criar variáveis de hambiente, visto que não estamos trabalhando com arquivos de produção.

Segue abaixo o desafio:

Projeto Java - Agenda
Estamos com a necessidade de fazer os cadastros dos clientes de uma clinica de beleza.

Precisamos que seja coletado os seguintes dados dos clientes, nome com sobrenome, CPF, data de
nascimento, telefones, endereços, e-mail.

Além de fazer os cadastros temos também a necessidade de buscar, editar e excluir os clientes.
Regras:

• Deve ser utilizado no mínimo java 11 e spring 2.7.+ ✔;

• Não podemos cadastrar o mesmo cliente, identificar um dado único para isso ✔;

• Quando excluir que seja feita uma exclusão lógica ✔;

• Validar CPF, e-mail e telefone se são validos ✔;

• Na operação de edição, não poderá ser editado o campo CPF ✔;

• Deve ser respeitado os verbos HTTP ✔;

• Todo endpoint deve possuir um token de autenticação;

• Deve ser feita a validação do CEP na api ViaCEP;

• Deve ser armazenado os dados em banco Postgresql via Docker✔;

• O cadastro deve ser assíncrono, ou seja, deve ser enviado via kafka para ser cadastrado posteriormente;

• A busca do CEP deve ser armazenada em cache de 5 minutos;

• Deve ser criado um JMX para cadastro e outro para consulta do cadastros;

• Deve possuir 100% de cobertura de testes unitários.


Bônus:

• Criar um job scheduled para enviar um e-mail a cada 10 minutos, quando o cadastro for concluído com sucesso (Não deve ser feito o envio, somente logar). Dicas:

• Fazer tratamento de exceções para apresentar uma mensagem amigável.

• Realizar a chamada do ViaCEP via OpenFeign → https://viacep.com.br/

• Executar o kafka localmente → https://www.confluent.io/