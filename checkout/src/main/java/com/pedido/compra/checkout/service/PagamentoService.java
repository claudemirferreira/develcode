package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoApiResponse;
import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PedidoService pedidoService;
    private final PagamentoApiService pagamentoApiService;

    public PagamentoService(PedidoService pedidoService,
                            PagamentoApiService pagamentoApiService) {
        this.pedidoService = pedidoService;
        this.pagamentoApiService = pagamentoApiService;
    }

    public Pedido efetuarPagamento(Long pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        try {
            PagamentoApiResponse pagamentoApiResponse = pagamentoApiService.enviarPagamento(
                    new BaixarPagamentoRequest(
                            pedido.getId(),
                            pedido.getValor(),
                            LocalDateTime.now()
                    ));
            Pagamento pagamento = Pagamento.builder()
                    .id(pagamentoApiResponse.id())  // Usar o id retornado pela API
                    .pedido(pedido)              // Associar o pagamento ao pedido
                    .build();
            pedido.setStatus(StatusPedidoEnum.PAGO);
            pedido.setPagamento(pagamento);
            pedidoService.salvar(pedido);
            return pedido;
        } catch (Exception e) {
            pedido.setStatus(StatusPedidoEnum.CANCELADO);
            pedidoService.salvar(pedido);
        }
        return pedido;
    }

}
