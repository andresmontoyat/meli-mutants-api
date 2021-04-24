package com.meli.mutants.application.rest.controller;

import com.meli.mutants.application.rest.model.request.MutantAnalyzerRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@Api(tags = "Mutants REST API")
public interface MutantRestController {

    @ApiOperation(value = "Analyze if dna is a mutant")
    ResponseEntity isMutant(MutantAnalyzerRequest mutantAnalyzerRequest, BindingResult result);

    @ApiOperation(value = "Get all stats")
    ResponseEntity stats();

}
