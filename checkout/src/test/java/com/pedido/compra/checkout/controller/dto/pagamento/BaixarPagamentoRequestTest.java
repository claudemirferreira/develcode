package com.pedido.compra.checkout.controller.dto.pagamento;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaixarPagamentoRequestTest {

    @Test
    void testBaixarPagamentoRequest() {
        // Arrange
        Long pedidoId = 2L;
        BigDecimal valor = new BigDecimal("100.00");

        // Act
        BaixarPagamentoRequest request = new BaixarPagamentoRequest(pedidoId, valor, LocalDateTime.now());

        // Assert
        assertEquals(pedidoId, request.pedidoId(), "O pedidoId não foi mapeado corretamente");
        assertEquals(valor, request.valor(), "O valor não foi mapeado corretamente");
    }
}
