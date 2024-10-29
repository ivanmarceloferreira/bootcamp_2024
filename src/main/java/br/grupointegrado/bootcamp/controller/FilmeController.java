package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.dto.FilmeRequestDTO;
import br.grupointegrado.bootcamp.dto.FilmeResponseDTO;
import br.grupointegrado.bootcamp.model.Filme;
import br.grupointegrado.bootcamp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public List<FilmeResponseDTO> findAll() {
        List<Filme> filmes = repository.findAll();

        return filmes.stream().map(Filme::toFilmeResponseDTO).collect(Collectors.toList());
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

    @PutMapping("/{id}")
    public Filme update(@PathVariable Integer id,@RequestBody FilmeRequestDTO dto) {
        Filme filme = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        filme.setNome(dto.nome());

        return this.repository.save(filme);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Filme filme = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        this.repository.delete(filme);
    }

}
