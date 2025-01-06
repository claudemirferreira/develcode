package com.pedido.compra.checkout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServiceNotFoundException extends ResponseStatusException {

    public ServiceNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
