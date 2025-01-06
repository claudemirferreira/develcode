package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoResponse;
import com.pedido.compra.checkout.controller.mapper.PedidoMapper;
import com.pedido.compra.checkout.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final PedidoMapper pedidoMapper;

    public PagamentoController(PagamentoService pagamentoService, PedidoMapper pedidoMapper) {
        this.pagamentoService = pagamentoService;
        this.pedidoMapper = pedidoMapper;
    }

    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<PagamentoResponse> efetuarPagamento(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(
                this.pedidoMapper.toPagamentoResponse(
                        this.pagamentoService.efetuarPagamento(pedidoId)));
    }

}
