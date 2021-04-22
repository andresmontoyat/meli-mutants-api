package analizer.direction;

public class DnaMutantAnalyzerVerticalDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerVerticalDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < sequenceLength(); i++) {
            matching(ZERO, i);

            if (isMatchSequence()) {
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }
        return Boolean.FALSE;
    }

    @Override
    void matching(int anchor, int cursor) {
        if (anchor < sequenceLength()) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
            }

            matching(++anchor, cursor);
        }
    }

    @Override
    boolean isValidPosition(int anchor, int cursor) {
        return ((anchor + 1) < sequenceLength() && (anchor - 1) >= ZERO);
    }

    @Override
    boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor - 1].charAt(cursor)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }
}
