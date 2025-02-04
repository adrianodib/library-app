package br.com.adrianodib.resource_service.repository;

import br.com.adrianodib.resource_service.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LivroRepository
 * @author adriano dib
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByTitulo(String titulo);

}
