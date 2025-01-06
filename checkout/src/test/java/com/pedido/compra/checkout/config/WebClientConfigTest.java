package com.pedido.compra.checkout.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebClientConfigTest {

    @Autowired
    private WebClient webClient;

    @Test
    void testWebClientBean() {
        // Verifica se o WebClient foi configurado corretamente e não é nulo
        assertNotNull(webClient, "O WebClient não foi configurado corretamente");
    }
}
