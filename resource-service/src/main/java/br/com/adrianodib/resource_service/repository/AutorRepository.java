package br.com.adrianodib.resource_service.repository;

import br.com.adrianodib.resource_service.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AutorRepository
 * @author adriano dib
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNome (String nome);

}

