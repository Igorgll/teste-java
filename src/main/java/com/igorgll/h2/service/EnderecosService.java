package com.igorgll.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorgll.h2.model.Enderecos;
import com.igorgll.h2.repository.ClientesRepository;

@Service
public class EnderecosService {

    private @Autowired ClientesRepository clientesRepository;

    public Enderecos createNewEndereco(Enderecos endereco, Long id_cliente) {
        endereco.setClientes((clientesRepository.findById(id_cliente)).get());
        return endereco;
    }

}
