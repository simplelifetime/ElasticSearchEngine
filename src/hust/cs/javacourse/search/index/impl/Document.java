package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.util.List;

/**
 * @ClassName: Document
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class Document extends AbstractDocument {
    @Override
    public int getDocId() {
        return this.docId;
    }

    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public String getDocPath() {
        return this.docPath;
    }

    @Override
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    @Override
    public List<AbstractTermTuple> getTuples() {
        return this.tuples;
    }

    @Override
    public void addTuple(AbstractTermTuple tuple) {
        if(!tuples.contains(tuple)){
            tuples.add(tuple);
        }
    }

    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return tuples.contains(tuple);
    }

    @Override
    public AbstractTermTuple getTuple(int index) {
        return tuples.get(index);
    }

    @Override
    public int getTupleSize() {
        return tuples.size();
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        output.append("docId:" + docId + "docPath:" + docPath +"\ntuples:");
        for(AbstractTermTuple abstractTermTuple:tuples){
            output.append("term:");
            output.append(abstractTermTuple.term);
            output.append(" ");
        }
        return null;
    }
}
