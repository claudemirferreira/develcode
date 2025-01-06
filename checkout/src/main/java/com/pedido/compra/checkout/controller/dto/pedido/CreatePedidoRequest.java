package com.pedido.compra.checkout.controller.dto.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreatePedidoRequest(
        @NotNull(message = "O valor do pedido é obrigatório.")
        Long clienteId,
        @NotNull(message = "O valor do pedido é obrigatório.")
        @Positive(message = "O valor do pedido deve ser positivo.")
        BigDecimal valor) {
}
