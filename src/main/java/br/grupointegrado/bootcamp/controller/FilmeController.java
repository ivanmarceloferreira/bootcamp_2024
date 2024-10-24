package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.model.Filme;
import br.grupointegrado.bootcamp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public List<Filme> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Filme findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }

    @GetMapping("/nome/{nome}")
    public List<Filme> findById(@PathVariable String nome) {
        return this.repository.findByNomeLike(nome);
    }

}
