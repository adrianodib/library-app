package br.com.adrianodib.resource_service.controller;

import br.com.adrianodib.resource_service.entity.Livro;
import br.com.adrianodib.resource_service.exception.BookNotFoundException;
import br.com.adrianodib.resource_service.record.LivroRecord;
import br.com.adrianodib.resource_service.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Livro Controller
 * @author adriano.dib
 */
@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping(value = "/cadastra", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Operation(summary = "Cadastra um livro na base de dados", tags = "Livro")
    public ResponseEntity<LivroRecord> cadastra(@RequestBody Livro livro) throws Exception  {

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
    }

    @GetMapping(value = "/consultabytitulo", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Operation(summary = "Consulta pelo um livro cadastrado na base", tags = "Livro")
    public ResponseEntity<LivroRecord> consulta(String titulo) throws BookNotFoundException {

        LivroRecord livro = livroService.consultaByTitulo(titulo);

        return new ResponseEntity<>(livro, HttpStatus.OK);

    }

    @DeleteMapping(value = "/deleta", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Operation(summary = "Deleta um livro cadastrado na base.", tags = "Livro")
    public ResponseEntity<Void> deleta(Long id) throws BookNotFoundException {
        if (livroService.deleta(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
