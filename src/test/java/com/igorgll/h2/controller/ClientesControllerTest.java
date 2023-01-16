package com.igorgll.h2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.igorgll.h2.model.Clientes;
import com.igorgll.h2.repository.ClientesRepository;
import com.igorgll.h2.service.ClientesService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientesControllerTest {

    private @Autowired TestRestTemplate testRestTemplate;
    private @Autowired ClientesService clientesService;
    private @Autowired ClientesRepository clientesRepository;

    @BeforeAll
    void start() {
    }

    @Test
    @Order(1)
    public void deveCriarClienteRetorna201() {
        clientesRepository.deleteAll();

        // GIVEN
        HttpEntity<Clientes> requisicao = new HttpEntity<Clientes>(new Clientes(0L, "Igor Lima", "10/09/2000"));

        // WHEN
        ResponseEntity<Clientes> resposta = testRestTemplate
                .exchange("/api/v1/clientes", HttpMethod.POST, requisicao, Clientes.class);

        // THEN
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    @Order(2)
    public void naoDeveDuplicarClienteRetorna400() {
        clientesRepository.deleteAll();
        clientesService.createClientes(new Clientes(0L, "Maria Santos", "21/05/2005"));

        // GIVEN
        HttpEntity<Clientes> requisicao = new HttpEntity<Clientes>(
                new Clientes(0L, "Maria Santos", "21/05/2005"));

        // WHEN
        ResponseEntity<Clientes> resposta = testRestTemplate.exchange("/api/v1/clientes", HttpMethod.POST, requisicao,
                Clientes.class);

        // THEN
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }

    @Test
    @Order(3)
    public void buscaDeClientesRetorna200() {
        ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/clientes", HttpMethod.GET, null,
                String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @AfterAll
    void end() {
        System.out.println("TESTE FINALIZADO!");
    }
}
