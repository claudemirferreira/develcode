package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoApiResponse;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PagamentoApiService pagamentoApiService;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Pedido pedido;
    PagamentoApiResponse pagamentoApiResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criar um pedido de exemplo
        pedido = Pedido.builder()
                .id(1L)
                .valor(new BigDecimal("100.00"))
                .status(StatusPedidoEnum.PENDENTE)
                .build();
        pagamentoApiResponse = new PagamentoApiResponse(
                1L, 1L, new BigDecimal("100.00"), LocalDateTime.now()
        );
    }

    @Test
    void efetuarPagamento_DeveAtualizarPedidoParaPagoQuandoSucesso() {

        // Configurar mocks
        when(pedidoService.buscarPorId(1L)).thenReturn(pedido);
        when(pagamentoApiService.enviarPagamento(any(BaixarPagamentoRequest.class)))
                .thenReturn(pagamentoApiResponse);

        // Executar o método
        Pedido resultado = pagamentoService.efetuarPagamento(1L);

        // Verificar comportamento
        verify(pedidoService).buscarPorId(1L);
        verify(pagamentoApiService).enviarPagamento(any(BaixarPagamentoRequest.class));
        verify(pedidoService).salvar(any(Pedido.class));

        // Verificar estado do pedido
        assertEquals(StatusPedidoEnum.PAGO, resultado.getStatus());
        assertNotNull(resultado.getPagamento());
        assertEquals(1L, resultado.getPagamento().getId());
    }

    @Test
    void efetuarPagamento_DeveAtualizarPedidoParaCanceladoQuandoFalha() {
        // Configurar mocks para lançar exceção
        when(pedidoService.buscarPorId(1L)).thenReturn(pedido);
        doThrow(new RuntimeException("Erro ao processar pagamento"))
                .when(pagamentoApiService).enviarPagamento(any(BaixarPagamentoRequest.class));

        // Executar o método
        Pedido resultado = pagamentoService.efetuarPagamento(1L);

        // Verificar comportamento
        verify(pedidoService).buscarPorId(1L);
        verify(pagamentoApiService).enviarPagamento(any(BaixarPagamentoRequest.class));
        verify(pedidoService).salvar(any(Pedido.class));

        // Verificar estado do pedido
        assertEquals(StatusPedidoEnum.CANCELADO, resultado.getStatus());
        assertNull(resultado.getPagamento());
    }
}
