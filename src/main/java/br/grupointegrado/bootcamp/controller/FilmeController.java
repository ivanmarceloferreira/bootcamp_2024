package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.dto.FilmeRequestDTO;
import br.grupointegrado.bootcamp.dto.FilmeResponseDTO;
import br.grupointegrado.bootcamp.model.Ator;
import br.grupointegrado.bootcamp.model.Categoria;
import br.grupointegrado.bootcamp.model.Filme;
import br.grupointegrado.bootcamp.repository.AtorRepository;
import br.grupointegrado.bootcamp.repository.CategoriaRepository;
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

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AtorRepository atorRepository;

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

    @PutMapping("/{id}/categoria/{idCategoria}")
    public Filme addCategoria(@PathVariable Integer id,
                              @PathVariable Integer idCategoria,
                              @RequestBody FilmeRequestDTO dto) {
        Filme filme = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Categoria categoria = this.categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));

        filme.setNome(dto.nome());
        filme.setCategoria(categoria);

        return this.repository.save(filme);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Filme filme = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));

        this.repository.delete(filme);
    }

    @PostMapping("/{id}/add-ator/{idAtor}")
    public Filme addAtor(@PathVariable Integer id, @PathVariable Integer idAtor) {
        Filme filme = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));

        Ator ator = this.atorRepository.findById(idAtor)
                .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado"));

        filme.getAtores().add(ator);
        this.repository.save(filme);

        return filme;
    }

}
