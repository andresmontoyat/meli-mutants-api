package com.meli.mutants.domain.service;

import com.meli.mutants.domain.model.MutantStats;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public interface MutantService {

    void isMutant(String[] dna);

    MutantStats stats();
}
