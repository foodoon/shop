
package guda.shop.cms.lucene;



import guda.shop.cms.dao.ProductDao;
import guda.shop.common.page.Pagination;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
























@Service
 public class LuceneProductSvcImpl
 implements LuceneProductSvc
 {
       private ProductDao productDao;



    public int index(String path, Long webId, Date start, Date end)
     throws CorruptIndexException, LockObtainFailedException, IOException
 {

        Directory dir = new SimpleFSDirectory(new File(path));

        IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30),
       true, MaxFieldLength.LIMITED);

        try {

            int count = this.productDao.luceneWriteIndex(writer, webId, start, end);

            writer.optimize();

            return count;

        } finally {

            writer.close();

        }

    }



    public Pagination search(String path, String queryString, Long webId, Long ctgId, Date start, Date end, int pageNo, int pageSize)
     throws CorruptIndexException, IOException, ParseException
 {

        Directory dir = new SimpleFSDirectory(new File(path));

        Searcher searcher = new IndexSearcher(dir);

        try {

            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);

            Query query = LuceneProduct.createQuery(queryString, webId, ctgId, start, end, analyzer);

            TopDocs docs = searcher.search(query, pageNo * pageSize);

            Pagination p = LuceneProduct.getResult(searcher, docs, pageNo, pageSize);

            return p;

        } finally {

            searcher.close();

        }

    }



    @Autowired
 public void setProductDao(ProductDao productDao)
 {

        this.productDao = productDao;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneProductSvcImpl
 * JD-Core Version:    0.6.2
 */