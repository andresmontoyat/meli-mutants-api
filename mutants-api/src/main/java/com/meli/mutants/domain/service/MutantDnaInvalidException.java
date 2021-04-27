package com.meli.mutants.domain.service;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public class MutantDnaInvalidException extends RuntimeException {

    public MutantDnaInvalidException(String message) {
        super(message);
    }
}
