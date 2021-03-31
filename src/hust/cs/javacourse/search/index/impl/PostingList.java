package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @ClassName: PostingList
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class PostingList extends AbstractPostingList {
    @Override
    public void add(AbstractPosting posting) {
        if(list.contains(posting))  return;
        this.list.add(posting);
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        for (AbstractPosting abstractPosting : this.list) {         //顺序查找
            output.append(abstractPosting.toString());
        }
        return output.toString();
    }

    @Override
    public void add(List<AbstractPosting> postings) {
        for (AbstractPosting abstractPosting : postings) {
            if(!list.contains(abstractPosting))
                this.add(abstractPosting);
        }
    }

    @Override
    public AbstractPosting get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(AbstractPosting posting) {
        return list.indexOf(posting);
    }

    @Override
    public int indexOf(int docId) {
        for (int i = 0; i < this.list.size(); i++) {        //顺序查找
            if (docId == (this.list
                    .get(i).getDocId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(AbstractPosting posting) {
        return list.contains(posting);
    }


    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void remove(AbstractPosting posting) {
        list.remove(posting);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void sort() {

    }

    @Override
    public void writeObject(ObjectOutputStream out) {

    }

    @Override
    public void readObject(ObjectInputStream in) {

    }
}
