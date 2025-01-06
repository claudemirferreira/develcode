package com.pedido.compra.checkout.controller.dto.pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record PagamentoResponse(
        Long pagamentoId,
        Long pedidoId,
        BigDecimal valor,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dataPagamento,
        String clienteNome
) {
}



