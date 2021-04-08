package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: Posting
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class Posting extends AbstractPosting {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posting) {
            if (((Posting) obj).getDocId() == this.docId &&
                    ((Posting) obj).getFreq() == this.freq) {
                for (int i = 0; i < this.positions.size(); i++) {       //内容比较
                    if (!this.positions.get(i).equals(((Posting) obj).getPositions().get(i))
                    ) return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("DocId:" + this.docId + "   " + "Freq:" + this.freq + "   ");
        output.append("positions:");
        for (int i : positions) {
            output.append(i);
            output.append("  ");
        }
        return output.toString();
    }

    @Override
    public int getDocId() {
        return this.docId;
    }

    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public int getFreq() {
        return this.freq;
    }

    @Override
    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public List<Integer> getPositions() {
        return this.positions;
    }

    @Override
    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    @Override
    public int compareTo(AbstractPosting o) {
        return this.docId - o.getDocId();
    }

    @Override
    public void sort() {
        Collections.sort(this.positions);
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(this.docId);
            out.writeObject(this.freq);
            out.writeObject(this.positions.size());
            for (Integer i : this.positions)
                out.writeObject(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.docId = (Integer) in.readObject();
            this.freq = (Integer) in.readObject();
            Integer size = (Integer) in.readObject();
            for (int i = 0; i < size; i++)
                this.positions.add((Integer) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
