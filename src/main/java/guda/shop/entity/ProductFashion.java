package guda.shop.entity;

import guda.shop.cms.entity.base.BaseProductFashion;
import com.jspgou.core.entity.Website;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class ProductFashion extends BaseProductFashion
{
  private static final long serialVersionUID = 1L;

  public ProductFashion()
  {
  }

  public ProductFashion(Long paramLong)
  {
    super(paramLong);
  }

  public ProductFashion(Long paramLong, Boolean paramBoolean)
  {
    super(paramLong, paramBoolean);
  }

  public void init()
  {
    if (getSaleCount() == null)
      setSaleCount(Integer.valueOf(0));
    if (getStockCount() == null)
      setStockCount(Integer.valueOf(0));
    if (getMarketPrice() == null)
      setMarketPrice(Double.valueOf(0.0D));
    if (getSalePrice() == null)
      setSalePrice(Double.valueOf(0.0D));
    if (getCostPrice() == null)
      setCostPrice(Double.valueOf(0.0D));
    setCreateTime(new Timestamp(System.currentTimeMillis()));
  }

  public List<String> getPropertysName()
  {
    String str = getFashionStyle();
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = str.split("@");
    for (int i = 0; i < arrayOfString.length; i++)
      if (arrayOfString[i].indexOf("??") != -1)
        localArrayList.add(arrayOfString[i].substring(0, arrayOfString[i].indexOf("??")));
    return localArrayList;
  }

  public List<String> getPropertysValue()
  {
    String str = getFashionStyle();
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = str.split("@");
    for (int i = 0; i < arrayOfString.length; i++)
      if (arrayOfString[i].indexOf("??") != -1)
        localArrayList.add(arrayOfString[i].substring(arrayOfString[i].indexOf("??") + 2));
    return localArrayList;
  }

  public String getFashPic()
  {
    return _$1(getFashionPic());
  }

  private String _$1(String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return null;
    return getProductId().getWebsite().getUploadUrl(paramString);
  }

  public void addTostandards(Standard paramStandard)
  {
    Object localObject = getStandards();
    if (localObject == null)
    {
      localObject = new HashSet();
      setStandards((Set)localObject);
    }
    ((Set)localObject).add(paramStandard);
    paramStandard.addToProductFashions(this);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductFashion
 * JD-Core Version:    0.6.2
 */