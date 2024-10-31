package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.dto.AtorRequestDTO;
import br.grupointegrado.bootcamp.dto.CategoriaFullResponseDTO;
import br.grupointegrado.bootcamp.dto.CategoriaRequestDTO;
import br.grupointegrado.bootcamp.model.Ator;
import br.grupointegrado.bootcamp.model.Categoria;
import br.grupointegrado.bootcamp.repository.AtorRepository;
import br.grupointegrado.bootcamp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    @Autowired
    private AtorRepository repository;

    @GetMapping
    public List<Ator> findAll() {
        List<Ator> atores = repository.findAll();

        return atores;
    }

    @GetMapping("/{id}")
    public Ator findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado"));
    }

    @PostMapping
    public Ator save(@RequestBody AtorRequestDTO dto) {
        Ator ator = new Ator();
        ator.setNome(dto.nome());
        ator.setOrigem(dto.origem());

        return this.repository.save(ator);
    }

    @PutMapping("/{id}")
    public Ator update(@PathVariable Integer id,@RequestBody AtorRequestDTO dto) {
        Ator ator = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado"));

        ator.setNome(dto.nome());
        ator.setOrigem(dto.origem());

        return this.repository.save(ator);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Ator ator = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado"));

        this.repository.delete(ator);
    }

}
