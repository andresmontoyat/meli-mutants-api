package analizer.direction;

import java.util.concurrent.Callable;

/**
 * Dna Analizer for Top diagonal up direction
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public class DnaMutantAnalyzerTopDiagonalUpDirection extends AbstractDnaAnalyzerDirection implements Callable {

    public DnaMutantAnalyzerTopDiagonalUpDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        for (int i = 0; i < sequenceLength(); i++) {
            matching(i, ZERO);

            if (isMatchSequence()) {
                return Boolean.TRUE;
            }

            matchCount.set(DEFAULT_MATCH_INITIALIZE);
        }

        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if (anchor >= ZERO && cursor < sequenceLength()) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
            }

            matching(--anchor, ++cursor);
        }
    }

    @Override
    public boolean isValidPosition(int anchor, int cursor) {
        return ((anchor + 1) < sequenceLength() && (cursor - 1) >= ZERO);
    }

    @Override
    public boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor + 1].charAt(cursor - 1)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }

}
