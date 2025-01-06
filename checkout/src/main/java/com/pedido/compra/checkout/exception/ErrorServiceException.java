package com.pedido.compra.checkout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorServiceException extends ResponseStatusException {

    public ErrorServiceException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
