package com.pedido.compra.checkout.repository;

import com.pedido.compra.checkout.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
