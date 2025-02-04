<h1 align="center">
  Client Credentials
</h1>

Implementar o grant type Client Credentials do Oauth 2.0 com o Spring Security.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Oauth Client](https://docs.spring.io/spring-security/reference/servlet/oauth2/client/index.html)
- [Spring Oauth Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- [Spring Webflux](https://docs.spring.io/spring-framework/reference/web/webflux.html)

## Funcionalidades

- [x] Integração com Keycloak
- [x] Geração de JWT
- [x] Autenticação

## Como executar

### Keycloak

Rodar o comando:
  docker run -p 7080:7080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -e KC_HTTP_PORT=7080 quay.io/keycloak/keycloak:22.0.5 start-dev

Criação do client:
- Client ID: client-service
- Authentication Flow - Service Account Roles (backend chamando backend)
- Gerar a secret e colocar no application-properties do client.
- Spring boot na versão 3.4, o RestClient já tem suporte ao Oauth2 nativo.

  Vai subir em http://localhost:7080/admin/master/console
- User: admin / Senha admin

- Gerar clientId e clientSecret
  
- Configurar Token
  - Access token lifespan: 2 min

  
### Aplicação

- Clonar repositório git:
```
git clone https://github.com/adrianodib
```
- Construir o projeto:
```
cd client-service-restclient
./mvnw clean package

cd ../resource-service
./mvnw clean package
```
- Executar:
```
java -jar ./target/client-service-restclient-0.0.1-SNAPSHOT.jar
```

Utilizar o arquivo `sample.http` para realizar uma requisição autorizada com client credentials.
