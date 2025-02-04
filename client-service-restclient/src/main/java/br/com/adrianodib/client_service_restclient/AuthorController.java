package br.com.adrianodib.client_service_restclient;

import br.com.adrianodib.client_service_restclient.record.AutorRecord;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("client")
public class AuthorController {

    @Autowired
    private final RestClient restClient; //Forma mais atualizada de fazer uma chamada HTTP a outros serviços com Spring de forma não reativa (síncrono)

    public AuthorController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping(value = "getToken", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Testa o Token", tags = "Autor")
    public String getToken() {
        return restClient.get()
                .uri("http://localhost:8000/sefa/getToken")
                .retrieve()
                .body(String.class);
    }

    @GetMapping(value= "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista todos os autores cadastrados na base de dados", tags = "Autor")
    public ResponseEntity<List<AutorRecord>> findAllAutores() {

        return restClient.get()
                .uri("http://localhost:8000/sefa/api/autor/findAll")
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<AutorRecord>>() {});
    }

    @GetMapping(value = "autor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista um autor pelo id", tags = "Autor")
    public ResponseEntity<AutorRecord> findById(@PathVariable Long id){
        return restClient.get()
                .uri("http://localhost:8000/sefa/api/autor/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AutorRecord.class);
    }

    @PostMapping(value = "autor/cadastra", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um autor na base de dados", tags = "Autor")
    public ResponseEntity<AutorRecord> cadastraAutor(@Validated @RequestBody AutorRecord autor) {
        AutorRecord response = restClient.post()
                .uri("http://localhost:8000/sefa/api/autor/cadastra")
                .body(autor)
                .retrieve()
                .body(AutorRecord.class);

        return ResponseEntity.ok(response);
    }

}
