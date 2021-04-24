package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnaMutantAnalyzerHorizontalDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerHorizontalDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() throws Exception {
        log.info("[START] - DnaMutantAnalyzerHorizontalDirection");
        for (int i = ZERO; i < sequenceLength(); i++) {
            matching(i, ZERO);

            if (isMatchSequence()) {
                log.info("[END] - DnaMutantAnalyzerHorizontalDirection");
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }

        log.info("[END] - DnaMutantAnalyzerHorizontalDirection");
        return Boolean.FALSE;
    }

    @Override
    public void matching(int anchor, int cursor) {
        if (cursor < sequenceLength()) {
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
