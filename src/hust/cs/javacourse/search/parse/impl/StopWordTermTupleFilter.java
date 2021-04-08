package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;

import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName: StopWordTermTupleFilter
 * @Description:
 * @Author
 * @Date 2021/4/1
 * @Version 1.0
 */

public class StopWordTermTupleFilter extends AbstractTermTupleFilter {

    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    @Override
    public AbstractTermTuple next() throws IOException {
        AbstractTermTuple curTermTuple= input.next();
        if(curTermTuple==null)  return null;
        String[] stopWords = StopWords.STOP_WORDS;
        while(Arrays.binarySearch(stopWords,curTermTuple.term.getContent())>=0){
            curTermTuple= input.next();
            if(curTermTuple==null)  return null;
        }
        return curTermTuple;
    }
}














