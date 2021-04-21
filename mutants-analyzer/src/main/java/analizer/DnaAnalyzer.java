package analizer;

import analizer.direction.DnaMutantAnalyzerBottomDiagonalUpDirection;
import analizer.direction.DnaMutantAnalyzerTopDiagonalDownDirection;
import analizer.direction.DnaMutantAnalyzerTopDiagonalUpDirection;

import java.util.concurrent.*;

public class DnaAnalyzer extends DnaAnalyzerContext {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        String[] dna = {"ATGAGA", "CAAGGC", "TAATGC", "AGAACG", "CCCCTA", "TCCCTG"};
        String[][] mutants = {
                {"A", "T", "G", "A", "G", "A"},
                {"C", "A", "A", "T", "G", "C"},
                {"T", "A", "A", "T", "G", "C"},
                {"A", "G", "A", "A", "C", "G"},
                {"C", "C", "C", "C", "T", "A"},
                {"T", "C", "C", "C", "T", "G"}
        };

        ;

        CompletionService service = new ExecutorCompletionService(pool);
        //service.submit(new DnaAnalyzerHorizontalDirection(Dna.builder().sequence(dna).build()));
        // service.submit(new DnaAnalyzerVerticalDirection());
        service.submit(new DnaMutantAnalyzerTopDiagonalUpDirection(dna, 4));
        //service.submit(new DnaMutantAnalyzerBottomDiagonalUpDirection(dna, 4));
        //service.submit(new DnaMutantAnalyzerTopDiagonalUpDirection(dna, 4));

        pool.shutdown();

        try {
            while (!pool.isTerminated()) {
                final Future<Boolean> future = service.take();
                System.out.println("REsult:" + future.get());
            }
        } catch (ExecutionException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
