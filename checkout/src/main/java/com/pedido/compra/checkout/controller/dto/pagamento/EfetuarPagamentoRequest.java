package com.pedido.compra.checkout.controller.dto.pagamento;

public record EfetuarPagamentoRequest (
        Long clientId,
        Long pedidoId
) {
}
