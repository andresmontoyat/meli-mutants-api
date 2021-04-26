package com.meli.mutants.infrastructure.repository;

import com.meli.mutants.infrastructure.repository.domain.MutantJpaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Interface for CRUD operations.
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Repository
public interface MutantJpaRepository extends CrudRepository<MutantJpaEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT mm.total_mutants AS mutants,  mh.total_humans AS humans, ((mm.total_mutants / mh.total_humans) * 1.0) AS ratio\n" +
            "FROM\n" +
            "(SELECT COUNT(*) AS total_humans\n" +
            "FROM mutants h\n" +
            "WHERE h.human = TRUE AND h.mutant = FALSE) mh,\n" +
            "(SELECT COUNT(*) AS total_mutants\n" +
            "FROM mutants m\n" +
            "WHERE m.human = TRUE AND m.mutant = TRUE) mm")
    Map<String, Object> findStats();
}
