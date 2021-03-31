package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;

/**
 * @ClassName: IndexBuilder
 * @Description:
 * @Author
 * @Date 2021/3/31
 * @Version 1.0
 */

public class IndexBuilder extends AbstractIndexBuilder {
    public IndexBuilder(AbstractDocumentBuilder docBuilder) {
        super(docBuilder);
    }

    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        return null;
    }
}
