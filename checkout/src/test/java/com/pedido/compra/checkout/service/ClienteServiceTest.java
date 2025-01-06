package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare a mock Cliente for testing
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("John Doe");
        cliente.setSaldo(new BigDecimal("100.00"));
    }

    @Test
    void deveSalvarClienteComSucesso() {
        // Arrange
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        Cliente result = clienteService.salvar(cliente);

        // Assert
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deveBuscarTodosOsClientesComSucesso() {
        // Arrange
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Jane Doe");
        cliente2.setSaldo(new BigDecimal("100.00"));

        List<Cliente> clientes = List.of(cliente, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        List<Cliente> result = clienteService.buscarTodos();

        // Assert
        assertEquals(clientes, result);
        verify(clienteRepository, times(1)).findAll();
    }

}
