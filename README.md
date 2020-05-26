# VotoCoop-API
Provedor de APIs para sessões de votação

### Caso queira executar este projeto, siga essas orientações:

Para esta implementação foi utlizado o <a href="https://www.postgresql.org/">Postgresql</a> versão 42.2.12.

Se possível, execute no <a href="https://www.docker.com/products/docker-desktop">Docker</a> com o comando: 
<code>docker run -d --name postgres-server -p 5432:5432 -e POSTGRES_PASSWORD=admin postgres</code>

Conforme comando, a senha padrão para o usuário root, postgres, será admin. 

Com esses dados em mãos, acesse o CLI do seu container criado, postgres-server no nosso caso, 
e digite o seguinte comando: 
<code>psql -U postgres -h localhost</code>, 
caso solicite senha, informe.

Para listar os databases criados, digite <code>\l</code> e caso ainda não exista o banco
 votocoop, digite <code>create database votocoop;</code>
 
### Nuvem
Temos uma aplicação modelo sendo executada no <a href="https://www.heroku.com/">Heroku</a>
e disponibilizada na URL <https://votocoop-api.herokuapp.com/>
 
### Documentação

Para acessar o <a href="https://swagger.io/">swagger</a> da aplicação, <a href="https://votocoop-api.herokuapp.com/swagger-ui.html#/">Clique aqui<a>.

Você também pode importar a collection das apis do projeto no <a href="https://www.postman.com/downloads/">Postman</a> pelo link <https://www.getpostman.com/collections/520c7738fc296eda8261>