package guda.shop.core.manager;

import guda.shop.common.page.Pagination;
import com.jspgou.core.entity.Website;
import java.util.List;

public abstract interface WebsiteMng
{
  public abstract Website getWebsite(String paramString);

  public abstract Pagination getAllPage(int paramInt1, int paramInt2);

  public abstract List<Website> getAllList();

  public abstract Website findById(Long paramLong);

  public abstract Website save(Website paramWebsite);

  public abstract Website update(Website paramWebsite);

  public abstract Website deleteById(Long paramLong);

  public abstract Website[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.WebsiteMng
 * JD-Core Version:    0.6.2
 */