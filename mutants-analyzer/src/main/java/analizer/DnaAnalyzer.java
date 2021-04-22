package analizer;

import analizer.direction.*;
import lombok.*;

import java.util.concurrent.*;


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

    public static void main(String[] args) {
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
}
