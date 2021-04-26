package com.meli.mutants.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutantStats {

    private Integer mutants;

    private Integer humans;

    private Double ratio;
}
