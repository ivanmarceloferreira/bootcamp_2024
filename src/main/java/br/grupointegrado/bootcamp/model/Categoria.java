package br.grupointegrado.bootcamp.model;

import br.grupointegrado.bootcamp.dto.CategoriaFullResponseDTO;
import br.grupointegrado.bootcamp.dto.CategoriaResponseDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column(name = "faixa_etaria")
    private Integer faixaEtaria;

    @OneToMany(mappedBy = "categoria")
    private List<Filme> filmes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public Integer getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(Integer faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public CategoriaFullResponseDTO toCategoriaFullDto() {
        List<String> filmes = this.filmes.stream().map(Filme::getNome).collect(Collectors.toUnmodifiableList());
        return new CategoriaFullResponseDTO(this.nome, this.faixaEtaria, filmes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria filme = (Categoria) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
