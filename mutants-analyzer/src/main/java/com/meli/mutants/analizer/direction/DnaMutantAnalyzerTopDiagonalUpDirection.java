package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

/**
 * Search the sequence through the matrix diagonally up from top
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
public class DnaMutantAnalyzerTopDiagonalUpDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerTopDiagonalUpDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        log.info("[START] - DnaMutantAnalyzerTopDiagonalUpDirection");
        for (int i = 0; i < sequenceLength(); i++) {
            matching(i, ZERO);

            if (isMatchSequence()) {
                log.info("[END] - DnaMutantAnalyzerTopDiagonalUpDirection");
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }
        log.info("[END] - DnaMutantAnalyzerTopDiagonalUpDirection");
        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if (anchor >= ZERO && cursor < sequenceLength()) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
                if (isMatchSequence()) {
                    return;
                }
            }else {
                matchCount.set(MATCH_INITIALIZE);
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
