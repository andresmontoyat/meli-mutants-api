package com.meli.mutants.analizer.direction;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@Getter
@Setter
public abstract class AbstractDnaMutantAnalyzer {

    public static final int ZERO = 0;
    public static final int MATCH_INITIALIZE = 1;

    protected AtomicInteger matchCount;

    protected String[] sequence;

    protected Integer minMatch;

    protected AbstractDnaMutantAnalyzer(String[] sequence, Integer minMatch) {
        this.sequence = sequence;
        this.minMatch = minMatch;
        this.matchCount = new AtomicInteger(MATCH_INITIALIZE);
    }

    public boolean isMatchSequence() {
        return (matchCount.get() >= minMatch);
    }

    abstract void matching(int anchor, int cursor);

    /**
     * Check if it valid position in the array
     *
     * @param anchor
     * @param cursor
     * @return true or false
     */
    abstract boolean isValidPosition(int anchor, int cursor);


    /**
     * Check if it
     *
     * @param anchor
     * @param cursor
     * @return
     */
    abstract boolean isMatchPosition(int anchor, int cursor);

    /**
     * Return lenght of array
     *
     * @return lenght of array
     */
    protected int sequenceLength() {
        return sequence.length;
    }
}
