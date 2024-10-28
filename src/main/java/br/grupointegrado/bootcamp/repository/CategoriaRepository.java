package br.grupointegrado.bootcamp.repository;

import br.grupointegrado.bootcamp.model.Categoria;
import br.grupointegrado.bootcamp.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
