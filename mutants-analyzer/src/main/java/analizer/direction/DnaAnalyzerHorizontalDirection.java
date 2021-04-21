package analizer.direction;

import analizer.Dna;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class DnaAnalyzerHorizontalDirection implements Callable {

    private Dna dna;
    public DnaAnalyzerHorizontalDirection(Dna dna) {
        this.dna = dna;
    }

    @Override
    public Long call() throws Exception {
        Boolean a = Boolean.FALSE;
        Arrays.stream(dna.getSequence()).forEach(sequence -> {

        });
        return 1L;
    }

    private List<String> transform() {


        return null;
    }
}
