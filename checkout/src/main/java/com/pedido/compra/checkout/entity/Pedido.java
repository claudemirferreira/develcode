package com.pedido.compra.checkout.entity;

import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "pedido_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;

    @Column(nullable = false)
    private OffsetDateTime data;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedidoEnum status;

}
