package com.igorgll.h2.service;

import java.util.List;
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

    public Optional<Clientes> updateCliente(Clientes clientes, Long id) {
        Optional<Clientes> updateCliente = findClienteById(id);

        updateCliente.get().setNome(clientes.getNome());
        updateCliente.get().setDataNascimento(clientes.getDataNascimento());
        clientesRepository.save(updateCliente.get());

        return updateCliente;
    }

    public List<Clientes> findAllClientes() {
        List<Clientes> list = clientesRepository.findAll();

        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista de clientes está vazia.", null);
        }
        return list;
    }

    public Optional<Clientes> findClienteById(Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);

        if (!cliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com o id: " + id + " não encontrado.",
                    null);
        }
        return cliente;
    }
}
