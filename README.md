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
 
### Mensageria
Visando disponibilização do resultado de uma votação em tempo real, a aplicação posta uma mensagem com a apuração dos votos
no tópico votocoop no <a href="https://kafka.apache.org/">Apache Kafka</a>.
Para disponibilizar localmente o kafka, vamos fazer a instalação do mesmo utilizando o <a href="https://docs.docker.com/compose/">Docker Compose</a>, 
basta abrir o terminal na pasta <code>*\votocoop-api\src\main\resources</code> em nosso projeto e executar o comando 
<code>docker-compose up -d</code>, o arquivo <strong>docker-compose.yml</strong> tem todas as configurações necessárias para instalação e 
inicialização do Zookeeper e do Kafka.

Se não houve nenhum problema com a instalação, podemos seguir para ter um consumer das mensagens do nosso tópico.

Baixe os arquivos do Kafka <a href="https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz">clicando aqui</a>,
 descampacte o arquivo e abra o terminal na pasta <code>kafka_2.12-2.5.0\bin\windows</code> caso esteja utilizando o Windows ou <code>kafka_2.12-2.5.0\bin</code> para Linux.
 Para windows digite o comando <code>.\kafka-console-consumer.bat --bootstrap-server localhost:29092 --topic votocoop</code>,
 caso esteja no Linux, utilize o .sh com o comando <code>.\kafka-console-consumer.sh --bootstrap-server localhost:29092 --topic votocoop</code>.
 Pronto, você já está "escutando" nosso tópico e verá as mensagens postadas pela aplicação.
 
### Nuvem
Temos uma aplicação modelo sendo executada no <a href="https://www.heroku.com/">Heroku</a>
e disponibilizada na URL <https://votocoop-api.herokuapp.com/>
 
### Documentação

Para acessar o <a href="https://swagger.io/">swagger</a> da aplicação, <a href="https://votocoop-api.herokuapp.com/swagger-ui.html#/">Clique aqui<a>.

Você também pode importar a collection das apis do projeto no <a href="https://www.postman.com/downloads/">Postman</a> pelo link <https://www.getpostman.com/collections/520c7738fc296eda8261>