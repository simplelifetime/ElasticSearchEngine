package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;

/**
 * @ClassName: LengthTermTupleFilter
 * @Description:
 * @Author
 * @Date 2021/4/1
 * @Version 1.0
 */

public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple abstractTermTuple= input.next();

        return null;
    }

    @Override
    public void close() {

    }
}
