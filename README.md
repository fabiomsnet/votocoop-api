# VotoCoop-API
Provedor de APIs para sessões de votação

### Caso queira executar este projeto, siga essas orientações:

Para esta implementação foi utlizado o Postgresql versão 42.2.12.

Se possível, execute no docker com o comando 
<code>docker run -d --name postgres-server -p 5432:5432 -e POSTGRES_PASSWORD=admin postgres</code>


Crie o banco que será utilizado e faça o mapeamento correto no application.properties

Após executar o projeto, terá acesso ao Swagger acessando o link http://localhost:8080/swagger-ui.html#/
