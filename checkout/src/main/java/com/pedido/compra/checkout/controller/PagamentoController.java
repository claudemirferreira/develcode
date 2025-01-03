package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.service.PagamentoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/pedido/{pedidoId}")
    public Pagamento efetuarPagamento(@PathVariable Long pedidoId) {
        return this.pagamentoService.efetuarPagamento(pedidoId);
    }

}
