package br.com.adrianodib.client_service_restclient;

import br.com.adrianodib.client_service_restclient.record.AutorRecord;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("client")
public class AuthorController {

    @Autowired
    private final RestClient restClient; //Forma mais atualizada de fazer uma chamada HTTP a outros serviços com Spring de forma não reativa (síncrono)

    public AuthorController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("getToken")
    @Operation(summary = "Testa o Token", tags = "Autor")
    public String getToken() {
        return restClient.get()
                .uri("http://localhost:8000/sefa/getToken")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("findAll")
    @Operation(summary = "Lista todos os autores cadastrados na base de dados", tags = "Autor")
    public String findAllAutores() {
        return restClient.get()
                .uri("http://localhost:8000/sefa/api/autor/findAll")
                .retrieve()
                .body(String.class);
    }

    @GetMapping("cadastraAutor")
    @Operation(summary = "Cadastra um autor na base de dados", tags = "Autor")
    public ResponseEntity<AutorRecord> cadastraAutor(@RequestBody AutorRecord autor) {
        AutorRecord response = restClient.post()
                .uri("http://localhost:8000/sefa/api/autor/cadastra")
                .body(autor)
                .retrieve()
                .body(AutorRecord.class);

        return ResponseEntity.ok(response);
    }

}
