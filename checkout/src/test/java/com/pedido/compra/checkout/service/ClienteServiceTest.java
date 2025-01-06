package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

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
        cliente.setSaldo(100.00);
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
        cliente2.setSaldo(200.00);

        List<Cliente> clientes = List.of(cliente, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        List<Cliente> result = clienteService.buscarTodos();

        // Assert
        assertEquals(clientes, result);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        // Arrange
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // Act
        Optional<Cliente> result = clienteService.buscarPorId(1L);

        // Assert
        assertEquals(Optional.of(cliente), result);
        verify(clienteRepository, times(1)).findById(1L);
    }
}
