package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductTag;
import guda.shop.core.entity.Website;

public class ProductTag extends BaseProductTag
{
  private static final long serialVersionUID = 1L;

  public void increaseCount()
  {
    addCount(1);
  }

  public void reduceCount()
  {
    addCount(-1);
  }

  public void addCount(int paramInt)
  {
    Integer localInteger = getCount();
    if (localInteger != null)
      paramInt += localInteger.intValue();
    if (paramInt < 0)
      paramInt = 0;
    setCount(Integer.valueOf(paramInt));
  }

  public void init()
  {
    setCount(Integer.valueOf(0));
  }

  public ProductTag()
  {
  }

  public ProductTag(Long paramLong)
  {
    super(paramLong);
  }

  public ProductTag(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger)
  {
    super(paramLong, paramWebsite, paramString, paramInteger);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductTag
 * JD-Core Version:    0.6.2
 */