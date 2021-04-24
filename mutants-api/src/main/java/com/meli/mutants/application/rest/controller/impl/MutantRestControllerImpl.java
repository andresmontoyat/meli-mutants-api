package com.meli.mutants.application.rest.controller.impl;

import com.meli.mutants.application.rest.controller.MutantRestController;
import com.meli.mutants.application.rest.model.request.MutantAnalyzerRequest;
import com.meli.mutants.domain.service.MutantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantRestControllerImpl implements MutantRestController {

    private final MutantService mutantService;

    public MutantRestControllerImpl(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("/mutant")
    @Override
    public ResponseEntity isMutant(@RequestBody @Validated MutantAnalyzerRequest mutantAnalyzerRequest, BindingResult result) {
        if(result.hasErrors()) {
            //TODO throws new exception
        }

        mutantService.isMutant(mutantAnalyzerRequest.getDna());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    @Override
    public ResponseEntity stats() {
        mutantService.stats();
        return null;
    }
}
