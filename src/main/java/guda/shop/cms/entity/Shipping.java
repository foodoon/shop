package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShipping;
import com.jspgou.core.entity.Website;
import java.util.Collection;
import java.util.Iterator;

public class Shipping extends BaseShipping
{
  private static final long serialVersionUID = 1L;
  private Double _$17;
  public static final int UNIFORM = 1;
  public static final int BY_WEIGHT = 2;
  public static final int BY_COUNTRY = 3;

  public Double calPrice(Double paramDouble)
  {
    int i = getMethod().intValue();
    Double localDouble;
    if (i == 1)
      localDouble = getUniformPrice();
    else if (i == 2)
    {
      if (paramDouble.doubleValue() <= 0.0D)
        localDouble = Double.valueOf(0.0D);
      else
        localDouble = byWeight(paramDouble);
    }
    else
      throw new RuntimeException("Shipping method not supported: " + i);
    return localDouble;
  }

  public static Long[] fetchIds(Collection<Shipping> paramCollection)
  {
    if (paramCollection == null)
      return null;
    Long[] arrayOfLong = new Long[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Shipping localShipping = (Shipping)localIterator.next();
      arrayOfLong[(i++)] = localShipping.getId();
    }
    return arrayOfLong;
  }

  public Double byWeight(Double paramDouble)
  {
    Double localDouble1 = getFirstPrice();
    Double localDouble2 = getAdditionalPrice();
    int i = getFirstWeight().intValue();
    int j = getAdditionalWeight().intValue();
    paramDouble = Double.valueOf(paramDouble.doubleValue() - i);
    while (paramDouble.doubleValue() > 0.0D)
    {
      paramDouble = Double.valueOf(paramDouble.doubleValue() - j);
      localDouble1 = Double.valueOf(localDouble1.doubleValue() + localDouble2.doubleValue());
    }
    return localDouble1;
  }

  public Shipping()
  {
  }

  public Shipping(Long paramLong)
  {
    super(paramLong);
  }

  public Shipping(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean)
  {
    super(paramLong, paramWebsite, paramString, paramInteger1, paramInteger2, paramBoolean);
  }

  public Double getPrice()
  {
    return this._$17;
  }

  public void setPrice(Double paramDouble)
  {
    this._$17 = paramDouble;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Shipping
 * JD-Core Version:    0.6.2
 */