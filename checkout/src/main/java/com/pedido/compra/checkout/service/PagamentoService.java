package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PedidoService pedidoService;

    public PagamentoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public Pagamento efetuarPagamento(Long pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        pedido.setStatus(StatusPedidoEnum.PAGO);
        pedidoService.salvar(pedido);
        return Pagamento
                .builder()
                .pedido(pedido)
                .build();
    }


}
