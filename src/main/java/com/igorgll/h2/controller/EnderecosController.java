package com.igorgll.h2.controller;

import java.util.HashMap;
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
import com.igorgll.h2.model.Enderecos;
import com.igorgll.h2.repository.EnderecosRepository;
import com.igorgll.h2.service.EnderecosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecosController {

    private @Autowired EnderecosRepository enderecosRepository;
    private @Autowired EnderecosService enderecosService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Enderecos>> getById(@PathVariable Long id) {
        Optional<Enderecos> endereco = enderecosService.findEnderecoById(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping("{id_cliente}")
    public ResponseEntity<Enderecos> createEndereco(@Valid @RequestBody Enderecos enderecos,
            @PathVariable Long id_cliente) {
        enderecos = enderecosService.createNewEndereco(enderecos, id_cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecosRepository.save(enderecos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Enderecos>> updateEndereco(@PathVariable Long id,
            @Valid @RequestBody Enderecos enderecos) {
        Optional<Enderecos> updateEndereco = enderecosService.updateEndereco(enderecos, id);

        return ResponseEntity.ok(updateEndereco);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEndereco(@PathVariable Long id)
            throws ResourceNotFoundException {
        Enderecos enderecos = enderecosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco com o id: " + id + " n??o existe."));

        enderecosRepository.delete(enderecos);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Endere??o excluido com sucesso!", Boolean.TRUE);

        return response;
    }
}
