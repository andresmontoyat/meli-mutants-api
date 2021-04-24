package com.meli.mutants.infrastructure.adapter;

import com.meli.mutants.domain.repository.MutantRepository;
import com.meli.mutants.infrastructure.repository.MutantJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class MutantAdapterRepository implements MutantRepository {

    private final MutantJpaRepository mutantJpaRepository;

    public MutantAdapterRepository(MutantJpaRepository mutantJpaRepository) {
        this.mutantJpaRepository = mutantJpaRepository;
    }

    @Override
    public void stats() {

    }
}
