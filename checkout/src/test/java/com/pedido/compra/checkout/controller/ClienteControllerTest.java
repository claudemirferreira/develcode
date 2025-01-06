package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.cliente.CreateClienteRequest;
import com.pedido.compra.checkout.controller.mapper.ClienteMapper;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteController clienteController;

    ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarClienteComSucesso() {
        // Arrange
        CreateClienteRequest request = new CreateClienteRequest("João", new BigDecimal("100"));
        Cliente cliente = new Cliente(1L, "João",  new BigDecimal("100"), null);
        
        when(clienteMapper.toEntity(request)).thenReturn(cliente);
        when(clienteService.salvar(cliente)).thenReturn(cliente);

        // Act
        Cliente result = clienteController.criar(request);

        // Assert
        assertEquals(cliente, result);
        verify(clienteService, times(1)).salvar(cliente);
        verify(clienteMapper, times(1)).toEntity(request);
    }

    @Test
    void deveBuscarTodosOsClientesComSucesso() {
        // Arrange
        Cliente cliente1 = new Cliente(1L, "João",  new BigDecimal("100"), null);
        Cliente cliente2 = new Cliente(2L, "Maria", new BigDecimal("100"), null);
        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(clienteService.buscarTodos()).thenReturn(clientes);

        // Act
        List<Cliente> result = clienteController.buscarTodos();

        // Assert
        assertEquals(clientes, result);
        verify(clienteService, times(1)).buscarTodos();
    }
}
