package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.entity.Pagamento;
import com.pedido.compra.checkout.exception.ErrorServiceException;
import com.pedido.compra.checkout.exception.ServiceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PagamentoApiServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private PagamentoApiService pagamentoApiService;

    private BaixarPagamentoRequest baixarPagamentoRequest;

    @BeforeEach
    public void setup() {
        baixarPagamentoRequest = new BaixarPagamentoRequest(1L, 2L, new BigDecimal("100.00"));
    }

    @Test
    public void testEnviarPagamento_Success() {
        // Arrange
        Pagamento pagamentoResponse = new Pagamento();
        pagamentoResponse.setPagamentoId(1L);

        // Simulando o comportamento do WebClient
        Mockito.when(webClient.post()).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.uri(any(String.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToMono(Pagamento.class)).thenReturn(Mono.just(pagamentoResponse));

        // Act
        Pagamento result = pagamentoApiService.enviarPagamento(baixarPagamentoRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getPagamentoId());
    }

    @Test
    public void testEnviarPagamento_NotFound() {
        // Simulando erro WebClientResponseException
        Mockito.when(webClient.post()).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.uri(any(String.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.retrieve()).thenThrow(WebClientResponseException.class);

        // Act & Assert
        ServiceNotFoundException exception = assertThrows(ServiceNotFoundException.class, () -> {
            pagamentoApiService.enviarPagamento(baixarPagamentoRequest);
        });

        assertEquals("erro não encontrou a api de pagamento", exception.getMessage());
    }

    @Test
    public void testEnviarPagamento_Error() {
        // Simulando erro genérico
        Mockito.when(webClient.post()).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.uri(any(String.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestBodySpec);
        Mockito.when(requestBodySpec.retrieve()).thenThrow(RuntimeException.class);

        // Act & Assert
        ErrorServiceException exception = assertThrows(ErrorServiceException.class, () -> {
            pagamentoApiService.enviarPagamento(baixarPagamentoRequest);
        });

        assertEquals("erro na api de pagamento de pagamento", exception.getMessage());
    }
}
