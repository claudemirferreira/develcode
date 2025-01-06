package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoResponse;
import com.pedido.compra.checkout.controller.mapper.PedidoMapper;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private PedidoMapper pedidoMapper;

    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamentoController = new PagamentoController(pagamentoService, pedidoMapper);
    }

    @Test
    void testEfetuarPagamento_Success() {
        // Arrange
        Long pedidoId = 1L;
        Pedido pedidoMock = mock(Pedido.class);
        PagamentoResponse pagamentoResponseMock = new PagamentoResponse(
                2L,
                pedidoId,
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                "João Silva"
        );

        when(pagamentoService.efetuarPagamento(pedidoId)).thenReturn(pedidoMock);
        when(pedidoMapper.toPagamentoResponse(pedidoMock)).thenReturn(pagamentoResponseMock);

        // Act
        ResponseEntity<PagamentoResponse> response = pagamentoController.efetuarPagamento(pedidoId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(pagamentoResponseMock, response.getBody());
        verify(pagamentoService).efetuarPagamento(pedidoId);
        verify(pedidoMapper).toPagamentoResponse(pedidoMock);
    }
}
