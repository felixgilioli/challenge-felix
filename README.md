# Desafio Front e Back end - Senior

Desenvolvi o projeto seguindo o layout e requisitos enviados via e-mail.
O sistema é composto por 3 aplicações, sendo uma delas o banco de dados Postgres, a outra o servidor Java com Spring Boot e por último o front end da aplicação em Angular.


Para facilitar o processo de boot, coloquei as aplicações em containers Docker e criei um script de inicialização utilizando o Docker Compose, para subir o sistema é necessário ter as duas ferramentas instaladas na máquina.

### Subindo com Docker Compose

Primeiramente você deve acessar a pasta raiz do projeto, onde se encontra o arquivo docker-compose.yml, utilizando um terminal, basta rodar o seguinte comando.

```bash
docker-compose up
```
Logo após, você pode acessar o sistema utilizando a URL [http://localhost:4200](http://localhost:4200).
