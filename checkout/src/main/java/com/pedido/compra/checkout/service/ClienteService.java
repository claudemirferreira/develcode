package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarTodos() {
        return this.clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }
}
