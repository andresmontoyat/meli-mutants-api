package com.meli.mutants.application.rest.model;

import com.meli.mutants.application.rest.model.response.MutantStatsResponse;
import com.meli.mutants.domain.model.MutantStats;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Mapper
public interface MutantModelMapper {

    MutantModelMapper INSTANCE = Mappers.getMapper(MutantModelMapper.class);

    MutantStatsResponse toMutantStatsResponse(MutantStats mutantStats);
}
