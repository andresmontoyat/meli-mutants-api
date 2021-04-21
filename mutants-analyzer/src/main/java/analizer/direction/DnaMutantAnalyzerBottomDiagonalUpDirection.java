package analizer.direction;

import java.util.concurrent.Callable;

public class DnaMutantAnalyzerBottomDiagonalUpDirection extends AbstractDnaAnalyzerDirection implements Callable {

    public DnaMutantAnalyzerBottomDiagonalUpDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        for (int i = sequenceLength() - 1; i >= 0; i--) {
            matching(i, sequenceLength() - 1);

            if (isMatchSequence()) {
                return Boolean.TRUE;
            }

            matchCount.set(DEFAULT_MATCH_INITIALIZE);
        }

        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if (anchor < sequenceLength() && cursor >= ZERO) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
            }

            matching(++anchor, --cursor);
        }
    }

    @Override
    public boolean isValidPosition(int anchor, int cursor) {
        return ((anchor - 1) >= 0 && (cursor + 1) < sequenceLength());
    }

    @Override
    public boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor - 1].charAt(cursor + 1)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }
}
