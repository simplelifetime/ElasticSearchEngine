package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PatternTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.*;

/**
 * @ClassName: DocumentBuilder
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class DocumentBuilder extends AbstractDocumentBuilder {
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) throws IOException {
        Document curDoc = new Document();
        curDoc.setDocId(docId);
        curDoc.setDocPath(docPath);
        while (true) {
            TermTuple curTuple = (TermTuple) termTupleStream.next();
            if (curTuple == null) break;
            curDoc.addTuple(curTuple);
        }
        termTupleStream.close();
        return curDoc;
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file) {   //wait to be done
        AbstractTermTupleStream abstractTermTupleStream = null;
        AbstractDocument abstractDocument = null;
        try {
            abstractTermTupleStream = new TermTupleScanner
                    (new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                    );
            abstractTermTupleStream = new PatternTermTupleFilter(abstractTermTupleStream);
            abstractTermTupleStream = new LengthTermTupleFilter(abstractTermTupleStream);
            abstractTermTupleStream = new StopWordTermTupleFilter(abstractTermTupleStream);
            abstractDocument = this.build(docId, docPath, abstractTermTupleStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (abstractTermTupleStream != null) abstractTermTupleStream.close();
        }
        return abstractDocument;
    }
}
