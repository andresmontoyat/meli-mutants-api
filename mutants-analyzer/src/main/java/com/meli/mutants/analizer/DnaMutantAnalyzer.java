package com.meli.mutants.analizer;

import com.meli.mutants.analizer.direction.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


/**
 * Class for dna Analizer with AsyncTaskExecutor
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
public class DnaMutantAnalyzer {

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    public Boolean isMutant(String[] dna, Integer minMatch) {

        List<Future<Boolean>> futures = Arrays.asList(new DnaMutantAnalyzerHorizontalDirection(dna, minMatch),
                new DnaMutantAnalyzerVerticalDirection(dna, minMatch),
                new DnaMutantAnalyzerTopDiagonalUpDirection(dna, minMatch),
                new DnaMutantAnalyzerTopDiagonalDownDirection(dna, minMatch),
                new DnaMutantAnalyzerBottomDiagonalUpDirection(dna, minMatch),
                new DnaMutantAnalyzerBottomDiagonalDownDirection(dna,  minMatch))
                .stream().map(direction -> taskExecutor.submit(direction)).collect(Collectors.toList());

        log.info("Waiting for everything to finish...");
        List<Boolean> results = futures.stream()
                .map(i -> {
                    try {
                        return i.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Boolean.FALSE;
                })
                .collect(Collectors.toList());

        for (Boolean result : results) {
            if(Boolean.TRUE.equals(result))
                return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
