package com.igorgll.h2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private Clientes cliente;
    private Clientes updateCliente;

    @BeforeAll
    void start() {
        clientesRepository.deleteAll();

        cliente = new Clientes(0L, "Igor Lima", "10/09/2000");
        updateCliente = new Clientes(1L, "Igor Lima Modificado", "10/09/2000");
    }

    @Test
    @Order(1)
    public void deveCriarCliente() {

        // GIVEN
        HttpEntity<Clientes> requisicao = new HttpEntity<Clientes>(
                new Clientes(0l, "Jorge Santos", "12/08/1990"));

        // WHEN
        ResponseEntity<Clientes> resposta = testRestTemplate
                .exchange("/api/v1/clientes", HttpMethod.POST, requisicao, Clientes.class);

        // THEN
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    @Order(2)
    public void naoDeveDuplicarCliente() {
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

}