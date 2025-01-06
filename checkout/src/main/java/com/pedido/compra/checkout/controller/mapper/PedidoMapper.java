package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoResponse;
import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pedido;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {

    public Pedido toEntity(CreatePedidoRequest createPedidoRequest) {
        return Pedido
                .builder()
                .valor(createPedidoRequest.valor())
                .cliente(Cliente.builder().id(createPedidoRequest.clienteId()).build())
                .build();
    }

    public PagamentoResponse toPagamentoResponse(Pedido pedido) {
        return new PagamentoResponse(
                pedido.getPagamento().getId(),
                pedido.getId(),
                pedido.getValor(),
                pedido.getData(),
                pedido.getCliente().getNome()
        );

    }
}
