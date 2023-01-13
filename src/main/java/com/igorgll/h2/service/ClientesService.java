package com.igorgll.h2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.igorgll.h2.model.Clientes;
import com.igorgll.h2.repository.ClientesRepository;

@Service
public class ClientesService {

    private @Autowired ClientesRepository clientesReposirtory;

    public Optional<Clientes> createClientes(Clientes clientes) {

        Optional<Clientes> newCliente = clientesReposirtory.findByNomeContainingIgnoreCase(clientes.getNome()); // Executes a query to verify if cliente already exists

        if (newCliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já existe na base de dados.", null);
        } else {
            return Optional.ofNullable(clientesReposirtory.save(clientes));
        }

    }

}
