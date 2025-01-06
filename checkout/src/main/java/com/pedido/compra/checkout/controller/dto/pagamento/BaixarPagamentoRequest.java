package com.pedido.compra.checkout.controller.dto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BaixarPagamentoRequest(
        Long pedidoId,
        BigDecimal valor,
        LocalDateTime dataPagamento
) {
}
