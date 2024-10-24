package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.dto.FilmeRequestDTO;
import br.grupointegrado.bootcamp.model.Filme;
import br.grupointegrado.bootcamp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return this.repository.findByNomeContaining(nome);
    }

    @PostMapping
    public Filme save(@RequestBody FilmeRequestDTO dto) {
        Filme filme = new Filme();
        filme.setNome(dto.nome());

        return this.repository.save(filme);
    }

}
