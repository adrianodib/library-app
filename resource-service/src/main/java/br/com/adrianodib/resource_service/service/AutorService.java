package br.com.adrianodib.resource_service.service;


import br.com.adrianodib.resource_service.entity.Autor;
import br.com.adrianodib.resource_service.entity.Livro;
import br.com.adrianodib.resource_service.exception.AutorAlreadyExistsException;
import br.com.adrianodib.resource_service.exception.AutorNotFoundException;
import br.com.adrianodib.resource_service.exception.NameMandatoryException;
import br.com.adrianodib.resource_service.record.AutorRecord;
import br.com.adrianodib.resource_service.record.LivroRecord;
import br.com.adrianodib.resource_service.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Transactional
    public Autor save(AutorRecord autorRecord) throws NameMandatoryException, AutorAlreadyExistsException {

        String nomeAutor = autorRecord.nome();

        if (nomeAutor == null || nomeAutor.isBlank()) {
            throw new NameMandatoryException();
        }

        if (autorRepo.findByNome(nomeAutor) != null) {
            throw new AutorAlreadyExistsException();
        }


        Autor autor = new Autor();
        autor.setNome(nomeAutor);


        List<Livro> livros = autorRecord.livros().stream()
                .map(livro -> new Livro(livro.titulo(), livro.descricao(), livro.categoria(), autor))
                .collect(Collectors.toList());

        autor.setLivros(livros);

        return autorRepo.save(autor);
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
