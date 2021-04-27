package com.meli.mutants.domain.service.impl;

import com.meli.mutants.analizer.DnaMutantAnalyzer;
import com.meli.mutants.domain.model.MutantStats;
import com.meli.mutants.domain.repository.MutantRepository;
import com.meli.mutants.domain.service.MutantDnaInvalidException;
import com.meli.mutants.domain.service.MutantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that allows to validate if the DNA sequence is valid and if it belongs to a mutant
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class MutantServiceImpl implements MutantService {
    private final Pattern dnaPattern = Pattern.compile("[ATCG]");

    private final MutantRepository mutantRepository;

    private final DnaMutantAnalyzer dnaMutantAnalyzer;

    public MutantServiceImpl(MutantRepository mutantRepository, DnaMutantAnalyzer dnaMutantAnalyzer) {
        this.mutantRepository = mutantRepository;
        this.dnaMutantAnalyzer = dnaMutantAnalyzer;
    }

    @Override
    public void isMutant(String[] dna) {
        if (!isDnaValid(dna)) {
            throw new MutantDnaInvalidException("The dna sequence is not valid.");
        }

        Boolean isHumam = Boolean.TRUE;
        Boolean isMutant = analyze(dna);

        newRecord(dna, isHumam, isMutant);

        if (!isMutant) {
            throw new MutantDnaInvalidException("The analyzed DNA is not from a mutant");
        }
    }

    private void newRecord(String[] dna, Boolean isHumam, Boolean isMutant) {
        mutantRepository.register(dna, isHumam, isMutant);
    }

    private Boolean analyze(String[] dna) {
        // DnaMutantAnalyzer dnaMutantAnalyzer = new DnaMutantAnalyzer();
        return dnaMutantAnalyzer.isMutant(dna, 4);
    }

    /**
     * Check if it dna sequece is valid
     *
     * @param dna
     * @return true or false
     */
    private boolean isDnaValid(String[] dna) {

        for (int i = 0; i < dna.length; i++) {
            System.out.println(dna[i]);
            Matcher matcher = dnaPattern.matcher(dna[i]);

            int matches = 0;
            while (matcher.find()) {
                matches++;
            }

            if (matches < dna[i].length()) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public MutantStats stats() {
        return mutantRepository.stats();
    }
}
