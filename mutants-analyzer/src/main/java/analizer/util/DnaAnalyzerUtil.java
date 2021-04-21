package analizer.util;

import java.util.stream.IntStream;

public class DnaAnalyzerUtil {

    public static IntStream revRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from - 1);
    }
}
