package com.pedido.compra.checkout.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "pagamento_id")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private LocalDateTime dataPagamento;

    @Column(nullable = false)
    private String status; // Ex.: "SUCESSO", "FALHA"

    @Column(nullable = false)
    private Double valorPago;

}
