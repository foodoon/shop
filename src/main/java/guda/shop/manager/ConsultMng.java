package guda.shop.manager;

import guda.shop.cms.entity.Consult;
import com.jspgou.common.page.Pagination;
import java.util.Date;
import java.util.List;

public abstract interface ConsultMng
{
  public abstract Consult findById(Long paramLong);

  public abstract Consult saveOrUpdate(Long paramLong1, String paramString, Long paramLong2);

  public abstract Consult update(Consult paramConsult);

  public abstract Consult deleteById(Long paramLong);

  public abstract List<Consult> findByProductId(Long paramLong);

  public abstract Pagination getPage(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, Boolean paramBoolean);

  public abstract Consult[] deleteByIds(Long[] paramArrayOfLong);

  public abstract Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ConsultMng
 * JD-Core Version:    0.6.2
 */