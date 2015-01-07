package guda.shop.cms.lucene;

import guda.shop.cms.dao.ProductDao;
import com.jspgou.common.page.Pagination;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

@Service
public class LuceneProductSvcImpl
  implements LuceneProductSvc
{
  private ProductDao productDao;

  public int index(String path, Long webId, Date start, Date end)
    throws CorruptIndexException, LockObtainFailedException, IOException
  {
/* 35 */     Directory dir = new SimpleFSDirectory(new File(path));
/* 36 */     IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), 
/* 37 */       true, IndexWriter.MaxFieldLength.LIMITED);
    try {
/* 39 */       int count = this.productDao.luceneWriteIndex(writer, webId, start, end);
/* 40 */       writer.optimize();
/* 41 */       return count;
    } finally {
      writer.close();
    }
  }

  public Pagination search(String path, String queryString, Long webId, Long ctgId, Date start, Date end, int pageNo, int pageSize)
    throws CorruptIndexException, IOException, ParseException
  {
/* 50 */     Directory dir = new SimpleFSDirectory(new File(path));
/* 51 */     Searcher searcher = new IndexSearcher(dir);
    try {
/* 53 */       Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
/* 54 */       Query query = LuceneProduct.createQuery(queryString, webId, ctgId, start, end, analyzer);
/* 55 */       TopDocs docs = searcher.search(query, pageNo * pageSize);
/* 56 */       Pagination p = LuceneProduct.getResult(searcher, docs, pageNo, pageSize);
/* 57 */       return p;
    } finally {
/* 59 */       searcher.close();
    }
  }

  @Autowired
  public void setProductDao(ProductDao productDao)
  {
/* 67 */     this.productDao = productDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneProductSvcImpl
 * JD-Core Version:    0.6.2
 */