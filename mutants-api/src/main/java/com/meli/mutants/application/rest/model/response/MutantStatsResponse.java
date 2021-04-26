package com.meli.mutants.application.rest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Getter
@Setter
public class MutantStatsResponse {

    @JsonProperty("count_mutant_dna")
    private Integer mutants;

    @JsonProperty("count_human_dna")
    private Integer humans;

    @JsonProperty("ratio")
    private Double ratio;
}
