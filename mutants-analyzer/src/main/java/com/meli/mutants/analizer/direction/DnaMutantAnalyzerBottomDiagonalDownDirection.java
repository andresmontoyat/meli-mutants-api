package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

/**
 * search the sequence through the matrix diagonally down from bottom
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
public class DnaMutantAnalyzerBottomDiagonalDownDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerBottomDiagonalDownDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        log.info("[START] - DnaMutantAnalyzerBottomDiagonalDownDirection");
        try {
            for (int i = sequenceLength() - 1; i >= 0; i--) {
                matching(i, ZERO);

                if (isMatchSequence()) {
                    log.info("[END] - DnaMutantAnalyzerBottomDiagonalDownDirection");
                    return Boolean.TRUE;
                }

                matchCount.set(MATCH_INITIALIZE);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        log.info("[END] - DnaMutantAnalyzerBottomDiagonalDownDirection");
        return Boolean.FALSE;
    }

    @Override
    void matching(int anchor, int cursor) {
        if ((anchor >= 0 && anchor < sequenceLength()) && (cursor >= 0 && cursor < sequenceLength())) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
                if (isMatchSequence()) {
                    return;
                }
            }else {
                matchCount.set(MATCH_INITIALIZE);
            }

            matching(++anchor, ++cursor);
        }
    }

    @Override
    public boolean isValidPosition(int anchor, int cursor) {
        return (((anchor - 1) >= 0 && anchor < sequenceLength())&& ((cursor - 1) >= 0 && (cursor - 1) < sequenceLength()));
    }

    @Override
    public boolean isMatchPosition(int anchor, int cursor) {
        return String.valueOf(sequence[anchor - 1].charAt(cursor - 1)).equalsIgnoreCase(String.valueOf(sequence[anchor].charAt(cursor)));
    }

}
