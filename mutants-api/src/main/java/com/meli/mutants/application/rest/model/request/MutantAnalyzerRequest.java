package com.meli.mutants.application.rest.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Getter
@Setter
public class MutantAnalyzerRequest {

    @NotEmpty
    private String[] dna;
}
