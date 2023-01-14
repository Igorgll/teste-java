package com.igorgll.h2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.igorgll.h2.model.Enderecos;
import com.igorgll.h2.repository.ClientesRepository;
import com.igorgll.h2.repository.EnderecosRepository;

@Service
public class EnderecosService {

    private @Autowired ClientesRepository clientesRepository;
    private @Autowired EnderecosRepository enderecosRepository;

    public Optional<Enderecos> findEnderecoById(Long id) {
        Optional<Enderecos> endereco = enderecosRepository.findById(id);

        if (!endereco.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço com o id: " + id + " não encontrado.",
                    null);
        }
        return endereco;
    }

    public Enderecos createNewEndereco(Enderecos endereco, Long id_cliente) {
        endereco.setClientes((clientesRepository.findById(id_cliente)).get());
        return endereco;
    }

    public Optional<Enderecos> updateEndereco(Enderecos enderecos, Long id) {
        Optional<Enderecos> updateEndereco = findEnderecoById(id);

        updateEndereco.get().setEndereco(enderecos.getEndereco());
        updateEndereco.get().setLogradouro(enderecos.getLogradouro());
        updateEndereco.get().setCep(enderecos.getCep());
        updateEndereco.get().setNumero(enderecos.getNumero());
        updateEndereco.get().setCidade(enderecos.getCidade());
        updateEndereco.get().setEnderecoPrincipal(enderecos.getEnderecoPrincipal());

        enderecosRepository.save(updateEndereco.get());

        return updateEndereco;
    }

}
