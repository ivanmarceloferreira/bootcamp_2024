package br.grupointegrado.bootcamp.controller;

import br.grupointegrado.bootcamp.dto.CategoriaFullResponseDTO;
import br.grupointegrado.bootcamp.dto.CategoriaRequestDTO;
import br.grupointegrado.bootcamp.model.Categoria;
import br.grupointegrado.bootcamp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<CategoriaFullResponseDTO> findAll() {
        List<Categoria> categorias = repository.findAll();

        return categorias.stream().map(Categoria::toCategoriaFullDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));
    }

    @PostMapping
    public Categoria save(@RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setFaixaEtaria(dto.faixaEtaria());

        return this.repository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Integer id,@RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));

        categoria.setNome(dto.nome());
        categoria.setFaixaEtaria(dto.faixaEtaria());

        return this.repository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Categoria categoria = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado"));

        this.repository.delete(categoria);
    }

}
