package com.igorgll.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorgll.h2.model.Enderecos;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {}
