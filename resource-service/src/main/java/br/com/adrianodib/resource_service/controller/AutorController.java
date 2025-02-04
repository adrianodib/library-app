package br.com.adrianodib.resource_service.controller;

import br.com.adrianodib.resource_service.entity.Autor;
import br.com.adrianodib.resource_service.record.AutorRecord;
import br.com.adrianodib.resource_service.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Autor Controller
 * @author adriano.dib
 */
@RestController
@RequestMapping("/api/autor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping(value = "/cadastra", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um autor na base de dados", tags = "Autor")
    public ResponseEntity<AutorRecord> cadastra(@RequestBody Autor autor) throws Exception  {

        var saved = autorService.save(autor);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista todos os autores cadastrados na base de dados", tags = "Autor")
    public ResponseEntity<List<AutorRecord>> findAll(){
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista um autor pelo id", tags = "Autor")
    public ResponseEntity<AutorRecord> findById(@PathVariable Long id) {
        var autor = autorService.findById(id);
        return autor != null ? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
    }

}
