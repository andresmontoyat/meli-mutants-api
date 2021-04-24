package com.meli.mutants.infrastructure.repository;

import com.meli.mutants.infrastructure.repository.domain.MutantJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantJpaRepository extends CrudRepository<MutantJpaEntity, Long> {
}
