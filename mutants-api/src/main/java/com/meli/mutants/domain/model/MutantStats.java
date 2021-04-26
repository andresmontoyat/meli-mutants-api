package com.meli.mutants.domain.model;

import lombok.*;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutantStats {

    private Integer mutants;

    private Integer humans;

    private Double ratio;
}
