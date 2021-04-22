package analizer.direction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnaMutantAnalyzerHorizontalDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerHorizontalDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < sequenceLength(); i++) {
            matching(i, ZERO);

            if (isMatchSequence()) {
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }
        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if (anchor >= 0 && cursor < sequenceLength()) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
            }

            matching(anchor, ++cursor);
        }
    }

    @Override
    boolean isValidPosition(int anchor, int cursor) {
        return (cursor - 1 >= 0 && cursor < sequenceLength());
    }

    @Override
    boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor].charAt(cursor - 1)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }
}
