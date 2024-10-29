package br.grupointegrado.bootcamp.model;

import br.grupointegrado.bootcamp.dto.CategoriaResponseDTO;
import br.grupointegrado.bootcamp.dto.FilmeResponseDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public FilmeResponseDTO toFilmeResponseDTO() {
        CategoriaResponseDTO categoriaDto;
        if (this.categoria != null) {
            categoriaDto =
                    new CategoriaResponseDTO(this.categoria.getNome(), this.categoria.getFaixaEtaria());
            return new FilmeResponseDTO(this.getNome(), categoriaDto);
        }
        return new FilmeResponseDTO(this.getNome(), null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
