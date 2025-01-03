package com.pedido.compra.checkout.controller.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreatePedidoRequest {
    @NotBlank(message = "O clienteId é obrigatório")
    @NotNull(message = "O valor do pedido é obrigatório.")
    private Long clienteId;
    @NotNull(message = "O valor do pedido é obrigatório.")
    @NotBlank(message = "O valor é obrigatório")
    @Positive(message = "O valor do pedido deve ser positivo.")
    private BigDecimal valor;
}

