package com.igorgll.h2.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.igorgll.h2.model.Clientes;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientesRepositoryTest {

    private @Autowired ClientesRepository clientesRepository;

    @Test
    @Order(1)
    public void testaBuscaDeClientes() {
        List<Clientes> clientes = clientesRepository.findAll();
        assertEquals(5, clientes.size());
    }

    @Test
    @Order(2)
    public void buscaClientePorNomeRetornaTrue() {
        Optional<Clientes> cliente = clientesRepository.findById(1L);

        assertTrue(cliente.get().getId().equals(1L));
    }

    @AfterAll
    void end() {
        System.out.println("TESTE FINALIZADO!");
    }
}
