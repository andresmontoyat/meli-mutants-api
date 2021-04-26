package com.meli.mutants.analizer;

import com.meli.mutants.analizer.direction.*;

import java.util.concurrent.*;


/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public class DnaMutantAnalyzer {

    public Boolean isMutant(String[] dna, Integer minMatch) {
        Boolean result = Boolean.FALSE;

        ExecutorService pool = Executors.newFixedThreadPool(8);
        CompletionService service = new ExecutorCompletionService(pool);
        service.submit(new DnaMutantAnalyzerHorizontalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerVerticalDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerTopDiagonalUpDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerTopDiagonalDownDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerBottomDiagonalUpDirection(dna, minMatch));
        service.submit(new DnaMutantAnalyzerBottomDiagonalDownDirection(dna,  minMatch));
        
        try {
            pool.shutdown();

            while (!pool.isTerminated()) {
                final Future<Boolean> future = service.take();
                result = future.get();

                if(result)
                    break;
            }
        } catch (ExecutionException | InterruptedException ex) {
            System.out.println(ex);
        }

        return result;
    }

}
