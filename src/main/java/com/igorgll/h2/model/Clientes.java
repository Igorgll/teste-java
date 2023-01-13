package com.igorgll.h2.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotEmpty(message = "Data de nascimento é obrigatório")
    @Column(name = "nascimento")
    private String dataNascimento;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "clientes" })
    private List<Enderecos> enderecos;

    public Clientes() {
    }

    public Clientes(Long id, @NotEmpty(message = "Nome é obrigatório") String nome,
            @NotEmpty(message = "Data de nascimento é obrigatório") String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Enderecos> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Enderecos> enderecos) {
        this.enderecos = enderecos;
    }
}
