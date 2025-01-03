package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import com.pedido.compra.checkout.exception.NotFoundException;
import com.pedido.compra.checkout.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> buscarTodos() {
        return this.pedidoRepository.findAll();
    }

    public Pedido criar(Pedido pedido) {
        pedido.setData(OffsetDateTime.now());
        pedido.setStatus(StatusPedidoEnum.PENDENTE);
        return salvar(pedido);
    }

    public Pedido salvar(Pedido pedido) {
        return this.pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("não encountrou o pedido " + id));
    }
}
