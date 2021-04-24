package com.meli.mutants.application.rest.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MutantAnalyzerRequest {

    @NotEmpty
    private String[] dna;
}
