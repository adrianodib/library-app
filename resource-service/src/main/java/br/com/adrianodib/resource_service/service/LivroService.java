package br.com.adrianodib.resource_service.service;

import br.com.adrianodib.resource_service.entity.Livro;
import br.com.adrianodib.resource_service.exception.BookNotFoundException;
import br.com.adrianodib.resource_service.record.LivroRecord;
import br.com.adrianodib.resource_service.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * LivroService
 * @author adriano.dib
 */
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    /**
     * Salva um livro e retorna
     */
    public LivroRecord save(Livro l)  {

        Livro livroSalvo = livroRepo.save(l);

        return new LivroRecord(livroSalvo.getId(), livroSalvo.getTitulo(), livroSalvo.getDescricao(), livroSalvo.getCategoria());

    }

    /**
     * Consulta um livro cadastrado na base
     */
    public Livro consulta(Livro livro) throws BookNotFoundException {

        Optional<Livro> livroRepoById = livroRepo.findById(livro.getId());

        if (livroRepoById.isEmpty()) {
            throw new BookNotFoundException();
        }

        return livroRepoById.get();
    }

    /**
     * Consulta um livro cadastrado na base pelo titulo
     */
    public LivroRecord consultaByTitulo(String titulo) throws BookNotFoundException {

        Livro livro = livroRepo.findByTitulo(titulo);

        if (livro == null) {
            throw new BookNotFoundException();
        }

        return new LivroRecord(livro.getId(), livro.getTitulo(), livro.getDescricao(), livro.getCategoria());
    }

    /**
     * Deleta um livro cadastrado na base
     */
    public boolean deleta(Long id) throws BookNotFoundException {

        Optional <Livro> byTitulo = livroRepo.findById(id);

        if (byTitulo.isPresent()) {
            livroRepo.delete(byTitulo.get());
            return true;
        } else {
            throw new BookNotFoundException();
        }

    }

}
