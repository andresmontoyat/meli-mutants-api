package com.meli.mutants.application.rest.controller.impl;

import com.meli.mutants.application.rest.controller.MutantRestController;
import com.meli.mutants.application.rest.model.MutantModelMapper;
import com.meli.mutants.application.rest.model.request.MutantAnalyzerRequest;
import com.meli.mutants.application.rest.model.response.MutantStatsResponse;
import com.meli.mutants.application.rest.support.handler.RestException;
import com.meli.mutants.domain.model.MutantStats;
import com.meli.mutants.domain.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@RestController
public class MutantRestControllerImpl implements MutantRestController {

    private final MutantService mutantService;

    private final MutantModelMapper mapper;

    public MutantRestControllerImpl(MutantService mutantService) {
        this.mutantService = mutantService;
        this.mapper = MutantModelMapper.INSTANCE;
    }

    @PostMapping("/mutant")
    @Override
    public ResponseEntity isMutant(@RequestBody @Validated MutantAnalyzerRequest mutantAnalyzerRequest, BindingResult result) {
        if(result.hasErrors()) {
            throw new RestException("An error occurred while trying to validate bean information", HttpStatus.UNPROCESSABLE_ENTITY, result.getAllErrors());
        }

        mutantService.isMutant(mutantAnalyzerRequest.getDna());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    @Override
    public ResponseEntity<MutantStatsResponse> stats() {
        MutantStats mutantStats = mutantService.stats();
        return ResponseEntity.ok(mapper.toMutantStatsResponse(mutantStats));
    }
}
