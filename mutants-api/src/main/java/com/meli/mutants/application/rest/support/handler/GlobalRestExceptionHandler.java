package com.meli.mutants.application.rest.support.handler;

import com.meli.mutants.application.rest.support.handler.message.ApiError;
import com.meli.mutants.domain.service.MutantDnaInvalidException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ApiError> resolveRestException(HttpServletRequest request, HttpServletResponse response, RestException e, Locale locale) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiError.builder().code(ApiError.API_ERROR_DEFAULT_CODE).message(e.getMessage()).errors(e.getErrors()).build());
    }

    @ExceptionHandler(MutantDnaInvalidException.class)
    public ResponseEntity<ApiError> resolveMutantDnaInvalidException(HttpServletRequest request, HttpServletResponse response, MutantDnaInvalidException e, Locale locale) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiError.builder().code(ApiError.API_ERROR_DEFAULT_CODE).message(e.getMessage()).build());
    }
}
