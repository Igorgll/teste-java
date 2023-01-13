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

    private @Autowired ClientesRepository clientesRepository;

    public Optional<Clientes> createClientes(Clientes clientes) {

        Optional<Clientes> newCliente = clientesRepository.findByNomeContainingIgnoreCase(clientes.getNome());

        if (newCliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já existe na base de dados.", null);
        }
        return Optional.ofNullable(clientesRepository.save(clientes));
    }

}
