package com.meli.mutants.infrastructure.adapter;

import com.meli.mutants.domain.model.MutantStats;
import com.meli.mutants.domain.repository.MutantRepository;
import com.meli.mutants.infrastructure.repository.MutantJpaRepository;
import com.meli.mutants.infrastructure.repository.domain.MutantJpaEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Component
public class MutantAdapterRepository implements MutantRepository {

    private final MutantJpaRepository mutantJpaRepository;

    public MutantAdapterRepository(MutantJpaRepository mutantJpaRepository) {
        this.mutantJpaRepository = mutantJpaRepository;
    }

    @Override
    public MutantStats stats() {
        Map result = mutantJpaRepository.findStats();
        return MutantStats.builder()
                .mutants(((BigInteger) result.get("mutants")).intValue())
                .humans(((BigInteger) result.get("humans")).intValue())
                .ratio(((BigDecimal) result.get("ratio")).doubleValue())
                .build();
    }

    @Override
    public void register(String[] dna, Boolean isHumam, Boolean isMutant) {
        mutantJpaRepository.save(MutantJpaEntity.builder()
                .dna(dna)
                .human(isHumam)
                .mutant(isMutant)
                .build());
    }
}
