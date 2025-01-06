package com.pedido.compra.checkout.service;

import com.pedido.compra.checkout.controller.dto.pagamento.BaixarPagamentoRequest;
import com.pedido.compra.checkout.controller.dto.pagamento.PagamentoApiResponse;
import com.pedido.compra.checkout.exception.ErrorServiceException;
import com.pedido.compra.checkout.exception.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PagamentoApiService {

    private final String urlApiPagamento;

    private final WebClient webClient;

    public PagamentoApiService(@Value("${payment.endpoint}") String urlApiPagamento, WebClient webClient) {
        this.urlApiPagamento = urlApiPagamento;
        this.webClient = webClient;
    }

    public PagamentoApiResponse enviarPagamento(BaixarPagamentoRequest request) {
        try {
            PagamentoApiResponse response = webClient.post()
                    .uri(urlApiPagamento)
                    .body(Mono.just(request), BaixarPagamentoRequest.class)
                    .retrieve()
                    .bodyToMono(PagamentoApiResponse.class)
                    .block();
            log.info("{}", response);
            return response;
        } catch (WebClientResponseException e) {
            log.error("Erro HTTP: {}", e.getStatusCode());
            throw new ServiceNotFoundException("erro não encontrou a api de pagamento");
        } catch (Exception e) {
            log.error("Erro HTTP: {}", e.getMessage());
            throw new ErrorServiceException("erro na api de pagamento de pagamento");
        }
    }
}
