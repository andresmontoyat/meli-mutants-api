package com.meli.mutants.domain.service;

import com.meli.mutants.domain.model.MutantStats;

/**
 * Interface that allows to validate if the DNA sequence is valid and if it belongs to a mutant
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public interface MutantService {

    /**
     * Check if it dna sequence is a mutant or human
     *
     * @param dna
     */
    void isMutant(String[] dna);

    /**
     * Return all stats
     *
     * @return stats information
     */
    MutantStats stats();
}
