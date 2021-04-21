package analizer.direction;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class DnaAnalyzerVerticalDirection implements Callable {

    String[] sequence;

    @Override
    public Long call() throws Exception {




        return 4L;
    }

    private List<String> tranform() {
        for (int i = 0; i < sequence.length; i++) {


            for (int j = 0; j < sequence.length; j++) {
                sequence[j].charAt(i);
            }
        }
        return null;
    }
}
