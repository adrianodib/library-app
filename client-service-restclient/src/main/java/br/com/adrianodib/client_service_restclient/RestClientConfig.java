package br.com.adrianodib.client_service_restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient keycloakRestClientOauth(RestClient.Builder builder, OAuth2AuthorizedClientManager authorizedClientManager) {

        //Com a versão 3.4 do Spring Boot o RestClient já tem suporte ao Oauth2 nativo, devendo-se só fazer essa pequena configuração
        //Objeto OAuth2AuthorizedClientManager é montado automaticamente baseado nas propriedades configuradas no application.yml
        //Ele pega o token retornado e faz a chamada usando o token JWT sem precisar de interação humana
        OAuth2ClientHttpRequestInterceptor requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
        requestInterceptor.setClientRegistrationIdResolver((HttpRequest request) -> "keycloak");

        //String token = authorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("keycloak").principal("user").build()).getAccessToken().getTokenValue();
        //System.out.println("Token JWT: " + token);

        return builder.requestInterceptor(requestInterceptor).build();
    }

}
