package com.prueba.nave.commons;

import org.springframework.http.HttpStatus;

public class NaveException extends RuntimeException{

    private HttpStatus httpStatus;

    public NaveException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus =httpStatus;
    }
}
