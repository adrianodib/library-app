package br.com.adrianodib.resource_service.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * Autor Entity
 * @author adriano.dib
 *
 */
@Entity
@Table(name = "autores")
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "autor_id", updatable = false, unique = true, nullable = false, length=16)
    private Long autorId;

    @Column(name = "nome", unique = true, nullable = false, length=100)
    private String nome;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(){}

    public Long getId() {
        return autorId;
    }

    public void setId(Long id) {
        this.autorId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
