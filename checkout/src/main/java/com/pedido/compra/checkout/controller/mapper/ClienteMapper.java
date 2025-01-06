package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.cliente.CreateClienteRequest;
import com.pedido.compra.checkout.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public Cliente toEntity(CreateClienteRequest createClienteRequest) {
        return Cliente
                .builder()
                .nome(createClienteRequest.nome())
                .saldo(createClienteRequest.saldo())
                .build();
    }
}
