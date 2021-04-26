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
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class MutantServiceImpl implements MutantService {
    private final Pattern dnaPattern = Pattern.compile("[ATCG]");

    private final MutantRepository mutantRepository;

    public MutantServiceImpl(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Override
    public void isMutant(String[] dna) {
        if(!isDnaValid(dna))
            throw new MutantDnaInvalidException("The dna sequence is not valid.");

        Boolean isHumam = Boolean.TRUE;
        Boolean isMutant = Boolean.FALSE;


       DnaMutantAnalyzer dnaMutantAnalyzer = new DnaMutantAnalyzer();
       isMutant = dnaMutantAnalyzer.isMutant(dna, 4);

       mutantRepository.register(dna, isHumam, isMutant);

       if(!isMutant)
           throw new MutantDnaInvalidException("The analyzed DNA is not from a mutant");
    }

    private boolean isDnaValid(String[] dna) {

        for (int i = 0; i < dna.length; i++) {
            System.out.println(dna[i]);
            Matcher matcher = dnaPattern.matcher(dna[i]);

            int matches = 0;
            while (matcher.find()) {
                matches++;
            }

            if(matches < dna[i].length())
                return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public MutantStats stats() {
        return mutantRepository.stats();
    }
}
