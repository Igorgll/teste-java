package com.igorgll.h2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorgll.h2.exception.ResourceNotFoundException;
import com.igorgll.h2.model.Clientes;
import com.igorgll.h2.repository.ClientesRepository;
import com.igorgll.h2.service.ClientesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientesController {

    private @Autowired ClientesRepository clientesRepository;
    private @Autowired ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Clientes>> getClientes() {
        List<Clientes> list = clientesService.findAllClientes();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clientes>> getById(@PathVariable Long id) {
        Optional<Clientes> cliente = clientesService.findClienteById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Clientes> createCliente(@Valid @RequestBody Clientes clientes) {
        return clientesService.createClientes(clientes).map(resp -> ResponseEntity.status(201).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Clientes>> updateCliente(@PathVariable Long id,
            @Valid @RequestBody Clientes clientes) {
        Optional<Clientes> updateCliente = clientesService.updateCliente(clientes, id);

        return ResponseEntity.ok(updateCliente);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCliente(@PathVariable long id)
            throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com o id: " + id + " não existe."));

        clientesRepository.delete(clientes);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Cliente excluido com sucesso!", Boolean.TRUE);

        return response;
    }
}
