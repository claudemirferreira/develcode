package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoResponse;
import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoMapperTest {

    private PedidoMapper pedidoMapper;

    @BeforeEach
    void setUp() {
        pedidoMapper = new PedidoMapper();
    }

    @Test
    void testToEntity() {
        // Arrange
        Long clienteId = 1L;
        BigDecimal valor = new BigDecimal("100.00");
        CreatePedidoRequest createPedidoRequest = new CreatePedidoRequest(clienteId, valor);

        // Act
        Pedido pedido = pedidoMapper.toEntity(createPedidoRequest);

        // Assert
        assertNotNull(pedido);
        assertEquals(clienteId, pedido.getCliente().getId());
        assertEquals(valor, pedido.getValor());
    }

    @Test
    void testToPagamentoResponse() {
        // Arrange
        Long pedidoId = 1L;
        Long pagamentoId = 2L;
        BigDecimal valor = new BigDecimal("100.00");
        String nomeCliente = "João Silva";

        Cliente cliente = mock(Cliente.class);
        when(cliente.getNome()).thenReturn(nomeCliente);

        Pedido pedido = mock(Pedido.class);
        when(pedido.getId()).thenReturn(pedidoId);
        when(pedido.getValor()).thenReturn(valor);
        when(pedido.getData()).thenReturn(LocalDateTime.now());
        when(pedido.getCliente()).thenReturn(cliente);
        when(pedido.getPagamento()).thenReturn(mock(Pagamento.class));
        when(pedido.getPagamento().getId()).thenReturn(pagamentoId);

        // Act
        PagamentoResponse pagamentoResponse = pedidoMapper.toPagamentoResponse(pedido);

        // Assert
        assertNotNull(pagamentoResponse);
        assertEquals(pagamentoId, pagamentoResponse.pagamentoId());
        assertEquals(pedidoId, pagamentoResponse.pedidoId());
        assertEquals(valor, pagamentoResponse.valor());
        assertNotNull(pagamentoResponse.dataPagamento());
        assertEquals(nomeCliente, pagamentoResponse.clienteNome());
    }
}
