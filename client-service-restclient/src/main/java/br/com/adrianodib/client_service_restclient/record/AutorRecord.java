package br.com.adrianodib.client_service_restclient.record;

import java.util.List;

public record AutorRecord(long autorId, String nome, List<LivroRecord>livros) {}
