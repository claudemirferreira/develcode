package com.pedido.compra.checkout.controller.mapper;

import com.pedido.compra.checkout.controller.dto.cliente.CreateClienteRequest;
import com.pedido.compra.checkout.entity.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteMapperTest {

    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Test
    void testToEntity() {
        BigDecimal saldo = new BigDecimal("100");
        // Arrange: Criar um objeto CreateClienteRequest com dados de teste
        CreateClienteRequest createClienteRequest = new CreateClienteRequest("João Silva", saldo);

        // Act: Chamar o método toEntity para mapear CreateClienteRequest para Cliente
        Cliente cliente = clienteMapper.toEntity(createClienteRequest);

        // Assert: Verificar se o mapeamento ocorreu corretamente
        assertEquals("João Silva", cliente.getNome());
        assertEquals(saldo, cliente.getSaldo());
    }
}
