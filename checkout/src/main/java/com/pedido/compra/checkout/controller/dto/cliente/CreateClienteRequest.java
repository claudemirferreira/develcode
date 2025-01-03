package com.pedido.compra.checkout.controller.dto.cliente;

import java.math.BigDecimal;

public record CreateClienteRequest(String nome,
                                   BigDecimal saldo) {
}
