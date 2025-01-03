package com.pedido.compra.checkout.repository;

import com.pedido.compra.checkout.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
