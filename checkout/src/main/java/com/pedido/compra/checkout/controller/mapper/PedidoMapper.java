package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pedido;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {

    public Pedido toEntity(CreatePedidoRequest createPedidoRequest){
        return Pedido
                .builder()
                .valor(createPedidoRequest.getValor())
                .cliente(Cliente.builder().id(createPedidoRequest.getClienteId()).build())
                .build();
    }
}
