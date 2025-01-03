package com.pedido.compra.checkout.repository;

import com.pedido.compra.checkout.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
