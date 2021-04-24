package com.meli.mutants.domain.service.impl;

import com.meli.mutants.analizer.DnaAnalyzer;
import com.meli.mutants.domain.repository.MutantRepository;
import com.meli.mutants.domain.service.MutantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MutantServiceImpl implements MutantService {

    private final MutantRepository mutantRepository;

    public MutantServiceImpl(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Override
    public void isMutant(String[] dna) {
       DnaAnalyzer dnaAnalyzer = DnaAnalyzer.builder()
               .dna(dna)
               .minMatch(4)
               .build();

        dnaAnalyzer.run();
    }

    @Override
    public void stats() {
        mutantRepository.stats();
    }
}
