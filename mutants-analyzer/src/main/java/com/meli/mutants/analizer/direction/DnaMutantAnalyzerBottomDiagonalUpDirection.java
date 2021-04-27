package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

/**
 * Search the sequence through the matrix diagonally up from top
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
public class DnaMutantAnalyzerBottomDiagonalUpDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerBottomDiagonalUpDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        log.info("[START] - DnaMutantAnalyzerBottomDiagonalUpDirection");
        for (int i = sequenceLength() - 1; i >= 0; i--) {
            matching(i, sequenceLength() - 1);

            if (isMatchSequence()) {
                log.info("[END] - DnaMutantAnalyzerBottomDiagonalUpDirection");
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }

        log.info("[END] - DnaMutantAnalyzerBottomDiagonalUpDirection");
        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if ((anchor < sequenceLength() && anchor >= 0) && (cursor >= ZERO && cursor < sequenceLength())) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
                if (isMatchSequence()) {
                    return;
                }
            }else {
                matchCount.set(MATCH_INITIALIZE);
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
