package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

/**
 * Dna Analizer for Top diagonal down direction
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Slf4j
public class DnaMutantAnalyzerTopDiagonalDownDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerTopDiagonalDownDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() {
        log.info("[START] - DnaMutantAnalyzerTopDiagonalDownDirection");
        for (int i = 0; i < sequenceLength(); i++) {
            matching(i, sequenceLength() - 1);

            if (isMatchSequence()) {
                log.info("[END] - DnaMutantAnalyzerTopDiagonalDownDirection");
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }
        log.info("[END] - DnaMutantAnalyzerTopDiagonalDownDirection");
        return Boolean.FALSE;
    }

    @Override
    void matching(int anchor, int cursor) {
        if (anchor >= 0 && cursor < sequenceLength()) {
            if (isValidPosition(anchor, cursor) && isMatchPosition(anchor, cursor)) {
                matchCount.incrementAndGet();
            }

            matching(--anchor, --cursor);
        }
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
