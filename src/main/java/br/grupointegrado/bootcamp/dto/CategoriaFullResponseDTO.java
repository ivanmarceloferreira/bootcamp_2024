package br.grupointegrado.bootcamp.dto;

import java.util.List;

public record CategoriaFullResponseDTO(String nome, Integer faixaEtaria, List<String> filmes) {
}
