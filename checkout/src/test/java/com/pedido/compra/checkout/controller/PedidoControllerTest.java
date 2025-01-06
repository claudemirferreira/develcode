package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.controller.mapper.PedidoMapper;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoController pedidoController;

    PedidoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPedidoComSucesso() {
        // Arrange
        CreatePedidoRequest request = new CreatePedidoRequest(1L, new BigDecimal("100.00"));
        Pedido pedido = Pedido
                .builder()
                .id(1L)
                .valor(new BigDecimal("100.00"))
                .data(LocalDateTime.now())
                .cliente(Cliente.builder().id(1L).build())
                .build();

        when(pedidoMapper.toEntity(request)).thenReturn(pedido);
        when(pedidoService.criar(pedido)).thenReturn(pedido);

        // Act
        Pedido result = pedidoController.criar(request);

        // Assert
        assertEquals(pedido, result);
        verify(pedidoService, times(1)).criar(pedido);
        verify(pedidoMapper, times(1)).toEntity(request);
    }

    @Test
    void deveBuscarTodosOsPedidosComSucesso() {
        // Arrange
        Pedido pedido1 = Pedido
                .builder()
                .id(1L)
                .valor(new BigDecimal("100.00"))
                .data(LocalDateTime.now())
                .cliente(Cliente.builder().id(1L).build())
                .build();
        Pedido pedido2 = Pedido
                .builder()
                .id(2L)
                .valor(new BigDecimal("100.00"))
                .data(LocalDateTime.now())
                .cliente(Cliente.builder().id(1L).build())
                .build();
        List<Pedido> pedidos = List.of(pedido1, pedido2);

        when(pedidoService.buscarTodos()).thenReturn(pedidos);

        // Act
        List<Pedido> result = pedidoController.buscarTodos();

        // Assert
        assertEquals(pedidos, result);
        verify(pedidoService, times(1)).buscarTodos();
    }
}
