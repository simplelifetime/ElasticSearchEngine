package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

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
        Index abstractIndex = new Index();
        List<String> files = FileUtil.list(rootDirectory);
        DocumentBuilder docBuilder = new DocumentBuilder();
        for (String string : files) {
            AbstractDocument abstractDocument = docBuilder.build(docId, string, new File(string));
            abstractIndex.addDocument(abstractDocument);
            docId++;
        }
        abstractIndex.optimize();
        return abstractIndex;
    }
}
