package com.meli.mutants.analizer;

import com.meli.mutants.analizer.direction.DnaMutantAnalyzerBottomDiagonalDownDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerBottomDiagonalUpDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerHorizontalDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerTopDiagonalDownDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerTopDiagonalUpDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerVerticalDirection;
import com.meli.mutants.analizer.direction.DnaMutantAnalyzerVerticalDirection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DnaAnalyzer {

    private String[] dna;

    private Integer minMatch;

    public void run() {

        ExecutorService pool = Executors.newFixedThreadPool(8);
        CompletionService service = new ExecutorCompletionService(pool);
        service.submit(new DnaMutantAnalyzerHorizontalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerVerticalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerTopDiagonalUpDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerTopDiagonalDownDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerBottomDiagonalUpDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerBottomDiagonalDownDirection(dna,  minMatch));

        pool.shutdown();

        try {
            while (!pool.isTerminated()) {
                final Future<Boolean> future = service.take();
                System.out.println("Result:" + future.get());
            }
        } catch (ExecutionException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    /*public static void main(String[] args) {
        DnaAnalyzer.builder().dna(null).minMatch(0).build();

        ExecutorService pool = Executors.newFixedThreadPool(8);

        String[] dna = {"ATGAGA", "CAGGGC", "TATTGC", "AGTACG", "GCCCTA", "TCCCTG"};
        Integer minMatch = 4;

        CompletionService service = new ExecutorCompletionService(pool);
        service.submit(new DnaMutantAnalyzerHorizontalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerVerticalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerTopDiagonalUpDirection(dna, 4));
        service.submit(new DnaMutantAnalyzerTopDiagonalDownDirection(dna, 4));
        service.submit(new DnaMutantAnalyzerBottomDiagonalUpDirection(dna, 4));
        service.submit(new DnaMutantAnalyzerBottomDiagonalDownDirection(dna, 4));


    }*/
}
