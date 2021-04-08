package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.util.FileUtil;

import java.util.Map;

/**
 * @ClassName: Hit
 * @Description:
 * @Author
 * @Date 2021/4/3
 * @Version 1.0
 */

public class Hit extends AbstractHit {
    public Hit(){
        super();
    }

    public Hit(int docId, String docPath){
        super(docId,docPath);
    }

    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> termPostingMapping){
        super(docId,docPath,termPostingMapping);
    }
    @Override
    public int getDocId() {
        return this.docId;
    }

    @Override
    public String getDocPath() {
        return this.docPath;
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
    public double getScore() {
        return this.score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return this.termPostingMapping;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("Hit!:\n");
        res.append("DocId:"+this.docId);
        res.append("  DocPath:"+this.docPath);
        res.append("  Content:"+this.content);
        res.append("  Score:"+this.score+"\n");
        res.append(this.termPostingMapping.toString());
        return res.toString();
    }

    @Override
    public int compareTo(AbstractHit o) {
        return o.getScore()>this.score?1:-1;
    }
}
