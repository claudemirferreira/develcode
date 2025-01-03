package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.controller.mapper.PedidoMapper;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @PostMapping
    public Pedido criar(@RequestBody @Valid CreatePedidoRequest request) {
        return this.pedidoService.criar(pedidoMapper.toEntity(request));
    }

    @GetMapping
    public List<Pedido> buscarTodos() {
        return this.pedidoService.buscarTodos();
    }
}
