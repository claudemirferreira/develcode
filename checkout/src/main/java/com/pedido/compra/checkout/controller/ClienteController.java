package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.cliente.CreateClienteRequest;
import com.pedido.compra.checkout.controller.mapper.ClienteMapper;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public Cliente criar(@RequestBody @Valid CreateClienteRequest request) {
        return this.clienteService.salvar(clienteMapper.toEntity(request));
    }

    @GetMapping
    public List<Cliente> buscarTodos() {
        return this.clienteService.buscarTodos();
    }

}
