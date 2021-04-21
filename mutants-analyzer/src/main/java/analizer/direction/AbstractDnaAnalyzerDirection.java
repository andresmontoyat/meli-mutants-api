package analizer.direction;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class AbstractDnaAnalyzerDirection {

    public static final int ZERO = 0;
    public static final int DEFAULT_MATCH_INITIALIZE = 1;

    protected AtomicInteger matchCount = new AtomicInteger(DEFAULT_MATCH_INITIALIZE);

    protected String[] sequence;

    protected Integer minMatch;

    public AbstractDnaAnalyzerDirection(String[] sequence, Integer minMatch) {
        this.sequence = sequence;
        this.minMatch = minMatch;
    }

    protected boolean isMatchSequence() {
       return (matchCount.get() >= minMatch);
    }

    abstract void matching(int anchor, int cursor);

    abstract boolean isValidPosition(int anchor, int cursor);

    abstract boolean isMatchPosition(int anchor, int cursor);

    protected int sequenceLength() {
        return sequence.length;
    }
}
