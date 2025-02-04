package br.com.adrianodib.resource_service.service;


import br.com.adrianodib.resource_service.entity.Autor;
import br.com.adrianodib.resource_service.entity.Livro;
import br.com.adrianodib.resource_service.exception.AutorAlreadyExistsException;
import br.com.adrianodib.resource_service.exception.AutorNotFoundException;
import br.com.adrianodib.resource_service.exception.NameMandatoryException;
import br.com.adrianodib.resource_service.record.AutorRecord;
import br.com.adrianodib.resource_service.record.LivroRecord;
import br.com.adrianodib.resource_service.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AutorService
 * @author adriano.dib
 */
@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepo;

    /**
     * Salva um autor e retorna
     */
    public AutorRecord save(Autor autor) throws NameMandatoryException, AutorAlreadyExistsException {
        String nomeAutor = autor.getNome();

        if (nomeAutor == null || nomeAutor.isBlank()) {
            throw new NameMandatoryException();
        }

        if (autorRepo.findByNome(nomeAutor) != null) {
            throw new AutorAlreadyExistsException();
        }

        if (autor.getLivros() != null) {
            for (Livro livro : autor.getLivros()) {
                livro.setAutor(autor);
            }
        }

        Autor savedAutor = autorRepo.save(autor);

        List<LivroRecord> livros = savedAutor.getLivros().stream()
                .map(livro -> new LivroRecord(
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getDescricao(),
                        livro.getCategoria()
                ))
                .toList();

        return new AutorRecord(savedAutor.getId(), savedAutor.getNome(), livros);

    }

    public List<AutorRecord> findAll(){
        var autores =  autorRepo.findAll();

        return autores.stream()
                .map(a -> new AutorRecord(
                        a.getId(),
                        a.getNome(),
                        a.getLivros().stream()
                                .map(l -> new LivroRecord(l.getId(), l.getTitulo(), l.getDescricao(), l.getCategoria()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

    }

    public AutorRecord findById(Long id){

        Optional<Autor> optionalAutor = autorRepo.findById(id);

        return optionalAutor
                .map(autor -> new AutorRecord(
                        autor.getId(),
                        autor.getNome(),
                        autor.getLivros().stream()
                                .map(livro -> new LivroRecord(livro.getId(), livro.getTitulo(), livro.getDescricao(), livro.getCategoria()))
                                .collect(Collectors.toList())
                ))
                .orElse(null);

    }

    public Autor findByNome(String nome){
        return autorRepo.findByNome(nome);
    }

    public void delete(Long id){
        Optional<Autor> optionalAutor = autorRepo.findById(id);
        optionalAutor.ifPresent(autor -> autorRepo.delete(autor));
    }

    public Autor update(Long id, Autor autor) throws AutorNotFoundException {
        AutorRecord a = this.findById(id);
        if (a != null){
            return autorRepo.save(autor);
        } else {
            throw new AutorNotFoundException();
        }
    }

}
