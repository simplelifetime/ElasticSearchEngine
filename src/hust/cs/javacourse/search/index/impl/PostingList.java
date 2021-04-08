package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
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
            output.append("   ");
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
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void sort() {
        list.sort(new Comparator<AbstractPosting>() {
            @Override
            public int compare(AbstractPosting o1, AbstractPosting o2) {
                return o1.getDocId()-o2.getDocId();
            }
        });
        for(AbstractPosting curPosting:this.list){
            curPosting.sort();
        }
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(list.size());
            for(AbstractPosting abstractPosting:this.list)
                abstractPosting.writeObject(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            Integer size = (Integer)in.readObject();
            for(int i=0;i<size;i++) {
                AbstractPosting abstractPosting = new Posting();
                abstractPosting.readObject(in);
                this.list.add(abstractPosting);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
