package analizer.direction;

import java.util.concurrent.Callable;

/**
 * Dna Analizer for Top diagonal down direction
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
public class DnaMutantAnalyzerBottomDiagonalDownDirection extends AbstractDnaAnalyzerDirection implements Callable {

    public DnaMutantAnalyzerBottomDiagonalDownDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        for (int i = sequenceLength(); i >= 0; i--) {
            /*anchor = i;
            cursor = 0;

            while (anchor >= 0 && cursor < sequenceLength()) {

                if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                    matchCount.incrementAndGet();
                } else {
                    matchCount.set(DEFAULT_MATCH_INITIALIZE);
                }

                anchor--;
                cursor++;
            }

            if (isMatchSequence()) {
                return Boolean.TRUE;
            }
*/
            matchCount.set(DEFAULT_MATCH_INITIALIZE);

        }

        return Boolean.FALSE;
    }

    @Override
    public boolean isValidPosition(int anchor, int cursor) {
        return ((anchor + 1) >= 0 && (cursor + 1) < sequenceLength());
    }

    @Override
    public boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor + 1].charAt(cursor + 1)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }

}
