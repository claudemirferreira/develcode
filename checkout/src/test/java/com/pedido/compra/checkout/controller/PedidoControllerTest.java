package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.controller.mapper.PedidoMapper;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
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
        Pedido pedido = new Pedido(1L, 1L, new BigDecimal("100.00"), null); // Ajustar conforme sua entidade

        when(pedidoMapper.toEntity(request)).thenReturn(pedido);
        when(pedidoService.criar(pedido)).thenReturn(pedido);

        // Act
        Pedido result = pedidoController.criar(request);

        // Assert
        assertEquals(ped
