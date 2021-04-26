package com.meli.mutants.infrastructure.repository.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access =AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "mutants")
public class MutantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String[] dna;

    @Column(nullable = false)
    private Boolean human;

    @Column(nullable = false)
    private Boolean mutant;

}
