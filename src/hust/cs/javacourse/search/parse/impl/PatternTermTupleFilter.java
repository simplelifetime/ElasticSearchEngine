package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.IOException;

/**
 * @ClassName: PatternTermTupleFilter
 * @Description:
 * @Author
 * @Date 2021/4/1
 * @Version 1.0
 */

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple curTermTuple= input.next();
        if(curTermTuple==null)  return null;
        String pattern = Config.TERM_FILTER_PATTERN;
        String content = curTermTuple.term.getContent();
        while(!content.matches(pattern)){
            curTermTuple= input.next();
            if(curTermTuple==null)  return null;
            content = curTermTuple.term.getContent();
        }
        return curTermTuple;
    }
}
