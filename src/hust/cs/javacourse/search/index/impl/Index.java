package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.*;

import java.io.*;
import java.util.*;

/**
 * AbstractIndex的具体实现类
 */
public class Index extends AbstractIndex {
    /**
     * 返回索引的字符串表示
     *
     * @return 索引的字符串表示
     */
    @Override
    public String toString() {
        StringBuffer curString = new StringBuffer();
        for (AbstractTerm term : termToPostingListMapping.keySet()) {
            curString.append(term.toString());
            curString.append(":");
            curString.append(termToPostingListMapping.get(term).toString());
            curString.append("\n");
        }
        return curString.toString();
    }

    /**
     * 添加文档到索引，更新索引内部的HashMap
     *
     * @param document ：文档的AbstractDocument子类型表示
     */
    @Override
    public void addDocument(AbstractDocument document) {
        docIdToDocPathMapping.put(document.getDocId(), document.getDocPath());
        List<AbstractTermTuple> curTermTuple = document.getTuples();
        for (AbstractTermTuple abstractTermTuple : curTermTuple) {
            Term curTerm = (Term) abstractTermTuple.term;
            PostingList curPostingList;
            if (!termToPostingListMapping.containsKey(curTerm)) {         //判断在倒挂索引中是否已存在该term
                curPostingList = new PostingList();      //若没有，创建一个新的postingList
            } else {   //否则调用原来的Posting List
                curPostingList = (PostingList) termToPostingListMapping.get(curTerm);
            }
            Posting curPosting;
            if (curPostingList.indexOf(document.getDocId()) != -1) {
                curPosting = (Posting) curPostingList.get(curPostingList.indexOf(document.getDocId()));
                curPosting.setFreq(curPosting.getFreq() + 1);
                List<Integer> positions = curPosting.getPositions();
                positions.add(abstractTermTuple.curPos);
            } else {
                curPosting = new Posting();
                curPosting.setDocId(document.getDocId());
                curPosting.setFreq(1);
                List<Integer> positions = new ArrayList<>();
                positions.add(abstractTermTuple.curPos);
                curPosting.setPositions(positions);
            }
            curPostingList.add(curPosting);
            termToPostingListMapping.put(curTerm, curPostingList);
        }
    }

    /**
     * <pre>
     * 从索引文件里加载已经构建好的索引.内部调用FileSerializable接口方法readObject即可
     * @param file ：索引文件
     * </pre>
     */
    @Override
    public void load(File file) throws IOException {
        readObject(new ObjectInputStream(new FileInputStream(file)));
    }

    /**
     * <pre>
     * 将在内存里构建好的索引写入到文件. 内部调用FileSerializable接口方法writeObject即可
     * @param file ：写入的目标索引文件
     * </pre>
     */
    @Override
    public void save(File file) throws IOException {
        writeObject(new ObjectOutputStream(new FileOutputStream(file)));
    }

    /**
     * 返回指定单词的PostingList
     *
     * @param term : 指定的单词
     * @return ：指定单词的PostingList;如果索引字典没有该单词，则返回null
     */
    @Override
    public AbstractPostingList search(AbstractTerm term) {
        return termToPostingListMapping.get(term);
    }

    /**
     * 返回索引的字典.字典为索引里所有单词的并集
     *
     * @return ：索引中Term列表
     */
    @Override
    public Set<AbstractTerm> getDictionary() {
        return termToPostingListMapping.keySet();
    }

    /**
     * <pre>
     * 对索引进行优化，包括：
     *      对索引里每个单词的PostingList按docId从小到大排序
     *      同时对每个Posting里的positions从小到大排序
     * 在内存中把索引构建完后执行该方法
     * </pre>
     */
    @Override
    public void optimize() {
        for (AbstractTerm abstractTerm : this.getDictionary()) {
            PostingList curPostingList = (PostingList) termToPostingListMapping.get(abstractTerm);
            curPostingList.sort();
        }
    }

    /**
     * 根据docId获得对应文档的完全路径名
     *
     * @param docId ：文档id
     * @return : 对应文档的完全路径名
     */
    @Override
    public String getDocName(int docId) {
        return this.docIdToDocPathMapping.get(docId);
    }

    /**
     * 写到二进制文件
     *
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            int size1 = termToPostingListMapping.size();
            out.writeObject(size1);
            int size2 = docIdToDocPathMapping.size();
            out.writeObject(size2);
            for (AbstractTerm term : termToPostingListMapping.keySet()) {
                term.writeObject(out);
                AbstractPostingList abstractPostingList = termToPostingListMapping.get(term);
                abstractPostingList.writeObject(out);
            }
            for (Integer docId : docIdToDocPathMapping.keySet()) {
                out.writeObject(docId);
                out.writeObject(docIdToDocPathMapping.get(docId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从二进制文件读
     *
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            Map<Integer, String> docIdToDocPathMapping = new TreeMap<>();
            Map<AbstractTerm, AbstractPostingList> termToPostingListMapping = new TreeMap<AbstractTerm, AbstractPostingList>();
            Integer size1 = (Integer) in.readObject();
            Integer size2 = (Integer) in.readObject();
            for (int i = 0; i < size1; i++) {
                AbstractTerm curTerm = new Term();
                curTerm.readObject(in);
                AbstractPostingList curPostingList = new PostingList();
                curPostingList.readObject(in);
                termToPostingListMapping.put(curTerm, curPostingList);
            }
            for (int i = 0; i < size2; i++) {
                Integer curDocId = (Integer) in.readObject();
                String curDocPath = (String) in.readObject();
                docIdToDocPathMapping.put(curDocId, curDocPath);
            }
            this.termToPostingListMapping = termToPostingListMapping;
            this.docIdToDocPathMapping = docIdToDocPathMapping;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
