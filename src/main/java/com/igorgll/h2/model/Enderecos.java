package com.igorgll.h2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_enderecos")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Endereço é obrigatório")
    @Column(name = "endereco")
    private String endereco;

    @NotEmpty(message = "Logradouro é obrigatório")
    @Column(name = "logradouro")
    private String logradouro;

    @NotEmpty(message = "CEP é obrigatório")
    @Column(name = "cep")
    private String cep;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "numero")
    private String numero;

    @NotEmpty(message = "Cidade é obrigatório")
    @Column(name = "cidade")
    private String cidade;

    @NotEmpty(message = "Endereço principal é obrigatório")
    @Size(min = 3, max = 3)
    @Column(name = "endereco_principal")
    private String enderecoPrincipal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties({ "clientes" })
    private Clientes clientes;

    public Enderecos() {
    }

    public Enderecos(Long id, @NotEmpty(message = "Endereço é obrigatório") String endereco,
            @NotEmpty(message = "Logradouro é obrigatório") String logradouro,
            @NotEmpty(message = "CEP é obrigatório") String cep,
            @NotEmpty(message = "Número é obrigatório") String numero,
            @NotEmpty(message = "Cidade é obrigatório") String cidade,
            @NotEmpty(message = "Endereço principal é obrigatório") String enderecoPrincipal, Clientes clientes) {
        this.id = id;
        this.endereco = endereco;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.enderecoPrincipal = enderecoPrincipal;
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

    public String getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(String enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
}
