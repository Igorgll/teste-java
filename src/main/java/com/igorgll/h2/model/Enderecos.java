package com.igorgll.h2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_enderecos")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Endereço é obrigatório")
    @Column(name = "Endereço")
    private String endereco;

    @NotEmpty(message = "Logradouro é obrigatório")
    @Column(name = "Logradouro")
    private String logradouro;

    @NotEmpty(message = "CEP é obrigatório")
    @Column(name = "CEP")
    private String cep;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "Número")
    private String numero;

    @NotEmpty(message = "Cidade é obrigatório")
    @Column(name = "Cidade")
    private String cidade;

    @ManyToOne
    @JsonIgnoreProperties("enderecos")
    private Clientes clientes;

    public Enderecos() {
    }

    public Enderecos(Long id, @NotEmpty(message = "Endereço é obrigatório") String endereco,
            @NotEmpty(message = "Logradouro é obrigatório") String logradouro,
            @NotEmpty(message = "CEP é obrigatório") String cep,
            @NotEmpty(message = "Número é obrigatório") String numero,
            @NotEmpty(message = "Cidade é obrigatório") String cidade, Clientes clientes) {
        this.id = id;
        this.endereco = endereco;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

}
