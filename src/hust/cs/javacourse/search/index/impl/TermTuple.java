package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;

/**
 * @ClassName: TermTuple
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class TermTuple extends AbstractTermTuple {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TermTuple) {
            return ((TermTuple) obj).curPos == this.curPos &&
                    ((TermTuple) obj).term.equals(this.term);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Content:" + this.term.toString() + " freq:" + this.freq + " pos" + this.curPos;
    }
}
