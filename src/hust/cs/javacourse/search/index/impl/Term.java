package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName: Term
 * @Description:  Implement of AbstractTerm
 * @Author  jason
 * @Date 2021/3/31
 * @Version 1.0
 */

public class Term extends AbstractTerm {
    public Term(){
        super();
    }

    public Term(String content){
        super(content);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Term) {
            return this.content.equals(((Term) obj).content);
        }
        else    return false;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(AbstractTerm o) {
        return this.content.compareTo(o.getContent());
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try{
            out.writeObject(this.content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try{
            this.content = (String) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
