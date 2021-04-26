package com.meli.mutants.domain.repository;

import com.meli.mutants.domain.model.MutantStats;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public interface MutantRepository {

    MutantStats stats();

    void register(String[] dna, Boolean isHumam, Boolean isMutant);
}
