package br.com.adrianodib.resource_service.record;

import java.util.List;

public record AutorRecord(long autorId, String nome, List<LivroRecord>livros) {}
