package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.util.StringSplitter;
import hust.cs.javacourse.search.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static hust.cs.javacourse.search.util.Config.STRING_SPLITTER_REGEX;

/**
 * @ClassName: TermTupleScanner
 * @Description:
 * @Author
 * @Date 2021/4/1
 * @Version 1.0
 */

public class TermTupleScanner extends AbstractTermTupleScanner {
    public TermTupleScanner(BufferedReader input){
        super(input);
    }
    Queue<AbstractTermTuple> termTuples = new LinkedList<>();
    @Override
    public AbstractTermTuple next() throws IOException {
        String Line = input.readLine();
        int pos = 1;
        while(Line!=null){
            Line = Line.trim();
            Line = Line.toLowerCase();
            StringSplitter splitter = new StringSplitter();
            splitter.setSplitRegex(STRING_SPLITTER_REGEX);
            List<String> words = splitter.splitByRegex(Line);
            AbstractTermTuple curTermTuple = new TermTuple();
            for(String word:words){
                AbstractTerm curTerm = new Term();
                curTerm.setContent(word);
                curTermTuple.term = curTerm;
                curTermTuple.curPos = pos++;
                termTuples.add(curTermTuple);
            }
            Line = input.readLine();
        }
        if(termTuples.isEmpty())
            return null;
        else    return termTuples.poll();
    }
}
