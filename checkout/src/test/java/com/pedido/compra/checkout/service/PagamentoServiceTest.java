package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.entity.Cliente;
import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.entity.Pedido;
import com.pedido.compra.checkout.enums.StatusPedidoEnum;
import com.pedido.compra.checkout.exception.ServiceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PagamentoApiService pagamentoApiService;

    private Pedido pedido;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação de um cliente e pedido mockados
        cliente = new Cliente();
        cliente.setId(1L);

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setValor(100.00);
        pedido.setStatus(StatusPedidoEnum.PENDENTE);
    }

    @Test
    public void testEfetuarPagamento_Success() {
        // Arrange
        when(pedidoService.buscarPorId(1L)).thenReturn(pedido);  // Mock do PedidoService
        doNothing().when(pagamentoApiService).enviarPagamento(any(BaixarPagamentoRequest.class));  // Mock do PagamentoApiService

        // Act
        Pagamento pagamento = pagamentoService.efetuarPagamento(1L);

        // Assert
        assertNotNull(pagamento);
        assertEquals(pedido, pagamento.getPedido());
        assertEquals(StatusPedidoEnum.PAGO, pedido.getStatus());

        // Verificar interações com as dependências
        verify(pedidoSe
