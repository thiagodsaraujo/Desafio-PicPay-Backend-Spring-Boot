<h1 align="center">:file_cabinet: Desafio PicPay :alien: </h1>

## :memo: Descrição

API de fluxo de transferência bancárias entre clientes que faz parte [desse desafio](https://github.com/PicPay/picpay-desafio-backend) para pessoas desenvolvedoras backend júnior, que se candidatam para o PicPay.

## :books: Funcionalidades
* <b>Funcionalidade</b>: Transferência entre contas
## :wrench: Tecnologias utilizadas
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)

## :rocket: Práticas adotadas

- Clean Arquiteture
- API RESTFull
- Notificações de eventos
- Consultas com Spring Data JPA
- Boas práticas
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

### 🔧 Instalação

* Clonar repositório git

```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/desafiopicpay-0.0.1-SNAPSHOT.jar
```

## :soon: Implementação futura

* Implementações de segurança
* Implementações de testes unitários
* Implementações de lock e threads para evitar concorrencia de transações(race conditions)

## :dart: Status do projeto
 * :+1: Finalizado

