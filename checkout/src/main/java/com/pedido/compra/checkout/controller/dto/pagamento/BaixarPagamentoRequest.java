package com.pedido.compra.checkout.controller.dto.pagamento;

public record BaixarPagamentoRequest(
        Long clientId,
        Long pedidoId
) {
}
