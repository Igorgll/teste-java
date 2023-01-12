package com.igorgll.h2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorgll.h2.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    public Optional<Clientes> findByNomeContainingIgnoreCase(String nome);
}
