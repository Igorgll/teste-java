package com.igorgll.h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorgll.h2.model.Clientes;
import com.igorgll.h2.repository.ClientesRepository;

@RestController
@RequestMapping("api/v1/clientes")
public class ClientesController {

    public @Autowired ClientesRepository clientesRepository;

    @GetMapping
    public ResponseEntity<List<Clientes>> getClientes() {
        List<Clientes> list = clientesRepository.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Long id) {
        return clientesRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clientes> createCliente(@RequestBody Clientes clientes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesRepository.save(clientes));
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clientesRepository.deleteById(id);
        return "Cliente deletado com sucesso!";
    }
}
