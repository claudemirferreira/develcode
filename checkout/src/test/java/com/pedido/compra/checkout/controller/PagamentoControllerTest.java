package com.pedido.compra.checkout.controller;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PagamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
    }

    @Test
    void testEfetuarPagamento() throws Exception {
        // Arrange: Criando um pedido fictício
        Long pedidoId = 1L;
        Pagamento pagamentoMock = Pagamento
                .builder()
                .id(1L)
                .dataPagamento(LocalDateTime.now())
                .build();

        // Simulando o comportamento do serviço
        when(pagamentoService.efetuarPagamento(pedidoId)).thenReturn(pagamentoMock);

        // Act & Assert: Verificando a resposta da requisição POST para efetuar pagamento
        mockMvc.perform(post("/pagamento/pedido/{pedidoId}", pedidoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dataPagamento").exists());

        // Verificando se o método do serviço foi chamado
        verify(pagamentoService, times(1)).efetuarPagamento(pedidoId);
    }

    @Test
    void testEfetuar() throws Exception {
        // Arrange: Criando uma solicitação fictícia
        BaixarPagamentoRequest request = new BaixarPagamentoRequest(1L, 1L, 100.0);
        Pagamento pagamentoMock = Pagamento
                .builder()
                .id(1L)
                .dataPagamento(LocalDateTime.now())
                .build();

        // Simulando a resposta do serviço
        when(pagamentoService.efetuarPagamento(anyLong())).thenReturn(pagamentoMock);

        // Act & Assert: Verificando a resposta da requisição POST
        mockMvc.perform(post("/pagamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientId\":1, \"pedidoId\":1, \"valor\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dataPagamento").exists());

        // Verificando se o serviço foi chamado
        verify(pagamentoService, times(1)).efetuarPagamento(anyLong());
    }
}
