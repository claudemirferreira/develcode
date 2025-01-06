package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.pedido.CreatePedidoRequest;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoMapperTest {

    private PedidoMapper pedidoMapper;

    @BeforeEach
    public void setUp() {
        pedidoMapper = new PedidoMapper();
    }

    @Test
    public void testToEntity() {
        // Arrange
        Long clienteId = 1L;
        Double valor = 100.00;
        CreatePedidoRequest createPedidoRequest = new CreatePedidoRequest(clienteId, valor);

        // Act
        Pedido pedido = pedidoMapper.toEntity(createPedidoRequest);

        // Assert
        assertEquals(clienteId, pedido.getCliente().getId(), "O ID do cliente não foi mapeado corretamente");
        assertEquals(valor, pedido.getValor(), "O valor do pedido não foi mapeado corretamente");
    }
}
