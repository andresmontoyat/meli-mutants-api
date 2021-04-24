package com.meli.mutants.analizer.direction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnaMutantAnalyzerVerticalDirection extends AbstractDnaMutantAnalyzer implements DnaMutantAnalyzeDirection {

    public DnaMutantAnalyzerVerticalDirection(String[] sequence, Integer minMatch) {
        super(sequence, minMatch);
    }

    @Override
    public Boolean call() throws Exception {
        log.info("[START] - DnaMutantAnalyzerVerticalDirection");
        for (int i = 0; i < sequenceLength(); i++) {
            matching(ZERO, i);

            if (isMatchSequence()) {
                log.info("[END] - DnaMutantAnalyzerVerticalDirection");
                return Boolean.TRUE;
            }

            matchCount.set(MATCH_INITIALIZE);
        }
        log.info("[END] - DnaMutantAnalyzerVerticalDirection");
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
