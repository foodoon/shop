package guda.shop.lucene;

import guda.shop.common.page.Pagination;
import java.io.IOException;
import java.util.Date;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;

public abstract interface LuceneProductSvc
{
  public abstract int index(String paramString, Long paramLong, Date paramDate1, Date paramDate2)
    throws CorruptIndexException, LockObtainFailedException, IOException;

  public abstract Pagination search(String paramString1, String paramString2, Long paramLong1, Long paramLong2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
    throws CorruptIndexException, IOException, ParseException;
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneProductSvc
 * JD-Core Version:    0.6.2
 */