package com.meli.mutants.domain.repository;

import com.meli.mutants.domain.model.MutantStats;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public interface MutantRepository {

    /**
     * Seach all stats
     * @return
     */
    MutantStats stats();

    /**
     * Create a new record for dna sequence
     * @param dna
     * @param isHumam
     * @param isMutant
     */
    void register(String[] dna, Boolean isHumam, Boolean isMutant);
}
