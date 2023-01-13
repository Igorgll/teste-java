package com.igorgll.h2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.igorgll.h2.model.Enderecos;
import com.igorgll.h2.repository.EnderecosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecosController {

    private @Autowired EnderecosRepository enderecosRepository;

    @GetMapping
    public ResponseEntity<List<Enderecos>> getEndrecos() {
        List<Enderecos> list = enderecosRepository.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enderecos> getById(@PathVariable Long id) {
        Enderecos enderecos = enderecosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco com o id: " + id + " não encontrado."));

        return ResponseEntity.ok().body(enderecos);
    }

    @PostMapping
    public ResponseEntity<Enderecos> createEndereco(@Valid @RequestBody Enderecos enderecos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecosRepository.save(enderecos));
    }

    @PutMapping()
    public ResponseEntity<Enderecos> updateEndereco(@Valid @RequestBody Enderecos enderecos) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecosRepository.save(enderecos));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEndereco(@PathVariable Long id)
            throws ResourceNotFoundException {
        Enderecos enderecos = enderecosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco com o id: " + id + " não existe."));

        enderecosRepository.delete(enderecos);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Endereço excluido com sucesso!", Boolean.TRUE);

        return response;
    }
}
