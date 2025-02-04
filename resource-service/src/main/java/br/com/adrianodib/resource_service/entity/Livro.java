package br.com.adrianodib.resource_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Livro Entity
 * @author adriano.dib
 *
 */
@Entity
@Table(name = "livros")
public class Livro implements Serializable {

    @Serial
    private static final long serialVersionUID = 4448827792061032979L;

    public Livro() {}

    public Livro(Long id, String titulo, String descricao, String categoria, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.autor = autor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, unique = true,  length=16)
    private Long id;

    @Column(name = "titulo", unique = true,  length=100)
    private String titulo;

    @Column(name = "descricao", length=150)
    private String descricao;

    @Column(name = "categoria", length=50)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "autor_id" )
    @JsonIgnore
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
