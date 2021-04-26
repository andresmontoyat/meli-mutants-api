package com.meli.mutants.application.rest.support.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * RestException is the class of those exceptions that can be thrown of de rest api layer.
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class RestException extends RuntimeException {

    private List errors;

    private HttpStatus status;

    public RestException(String message, HttpStatus status, List errors) {
        super(message);
        this.errors = errors;
        this.status = status;
    }
}