package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoApiResponse;
import com.pedido.compra.checkout.exception.ErrorServiceException;
import com.pedido.compra.checkout.exception.ServiceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoApiServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private PagamentoApiService pagamentoApiService;
    private BaixarPagamentoRequest baixarPagamentoRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        baixarPagamentoRequest = new BaixarPagamentoRequest(2L, new BigDecimal("100.00"), LocalDateTime.now());
        pagamentoApiService = new PagamentoApiService("http://localhost:3001/pagamento", webClient);
    }

    @Test
    void testEnviarPagamento_Success() {
        // Arrange
        PagamentoApiResponse pagamentoApiResponse = new PagamentoApiResponse(
                1L, 1L, new BigDecimal("100"), LocalDateTime.now());

        // Simulando o comportamento do WebClient
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), eq(BaixarPagamentoRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PagamentoApiResponse.class)).thenReturn(Mono.just(pagamentoApiResponse));

        // Act
        PagamentoApiResponse result = pagamentoApiService.enviarPagamento(baixarPagamentoRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.pedidoId());
    }

    @Test
    void testEnviarPagamento_WebClientResponseException() {
        // Arrange
        String errorMessage = "erro não encontrou a api de pagamento";

        // Simulando erro no WebClient
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), eq(BaixarPagamentoRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PagamentoApiResponse.class)).thenReturn(Mono.error(new WebClientResponseException(
                500, errorMessage, null, null, null
        )));

        // Act & Assert
        assertThrows(ServiceNotFoundException.class, () -> {
            pagamentoApiService.enviarPagamento(baixarPagamentoRequest);
        });
    }

    @Test
    void testEnviarPagamento_GeneralError() {
        // Arrange
        String errorMessage = "erro na api de pagamento";

        // Simulando erro genérico
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), eq(BaixarPagamentoRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PagamentoApiResponse.class)).thenReturn(Mono.error(new RuntimeException(errorMessage)));

        // Act & Assert
        assertThrows(ErrorServiceException.class, () -> {
            pagamentoApiService.enviarPagamento(baixarPagamentoRequest);
        });
    }
}
