package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;

import java.io.File;

/**
 * @ClassName: DocumentBuilder
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class DocumentBuilder extends AbstractDocumentBuilder {
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) {
        Document curDoc = new Document();
        curDoc.setDocId(docId);
        curDoc.setDocPath(docPath);
        while(true){
            TermTuple curTuple = (TermTuple) termTupleStream.next();
            if(curTuple==null)  break;
            curDoc.addTuple(curTuple);
        }
        return curDoc;
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file) {   //wait to be done
        Document curDoc = new Document();
        curDoc.setDocId(docId);
        curDoc.setDocPath(docPath);
        return curDoc;
    }
}
