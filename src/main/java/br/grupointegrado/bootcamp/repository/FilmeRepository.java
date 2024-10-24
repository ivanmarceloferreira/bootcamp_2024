package br.grupointegrado.bootcamp.repository;

import br.grupointegrado.bootcamp.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    List<Filme> findByNomeContaining(String nome);
}
