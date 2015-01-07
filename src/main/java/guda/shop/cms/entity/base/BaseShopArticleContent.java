package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopArticleContent;
import java.io.Serializable;

public abstract class BaseShopArticleContent
  implements Serializable
{
  public static String REF = "ShopArticleContent";
  public static String PROP_CONTENT = "content";
  public static String PROP_ID = "id";
  public static String PROP_ARTICLE = "article";
  private int _$4 = -2147483648;
  private Long _$3;
  private String _$2;
  private ShopArticle _$1;

  public BaseShopArticleContent()
  {
    initialize();
  }

  public BaseShopArticleContent(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$3;
  }

  public void setId(Long paramLong)
  {
    this._$3 = paramLong;
    this._$4 = -2147483648;
  }

  public String getContent()
  {
    return this._$2;
  }

  public void setContent(String paramString)
  {
    this._$2 = paramString;
  }

  public ShopArticle getArticle()
  {
    return this._$1;
  }

  public void setArticle(ShopArticle paramShopArticle)
  {
    this._$1 = paramShopArticle;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopArticleContent))
      return false;
    ShopArticleContent localShopArticleContent = (ShopArticleContent)paramObject;
    if ((null == getId()) || (null == localShopArticleContent.getId()))
      return false;
    return getId().equals(localShopArticleContent.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$4)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$4 = str.hashCode();
    }
    return this._$4;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopArticleContent
 * JD-Core Version:    0.6.2
 */