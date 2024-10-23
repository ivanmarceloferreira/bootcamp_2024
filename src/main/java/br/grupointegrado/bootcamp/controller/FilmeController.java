package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.model.Filme;
import br.grupointegrado.bootcamp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping("/filmes")
    public List<Filme> findAll() {
        return repository.findAll();
    }

}
