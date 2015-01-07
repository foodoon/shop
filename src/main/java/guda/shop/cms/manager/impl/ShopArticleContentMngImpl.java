package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopArticleContentDao;
import guda.shop.cms.entity.ShopArticle;
import guda.shop.cms.entity.ShopArticleContent;
import guda.shop.cms.manager.ShopArticleContentMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopArticleContentMngImpl
  implements ShopArticleContentMng
{
  private ShopArticleContentDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ShopArticleContent findById(Long paramLong)
  {
    ShopArticleContent localShopArticleContent = this._$1.findById(paramLong);
    return localShopArticleContent;
  }

  public ShopArticleContent save(String paramString, ShopArticle paramShopArticle)
  {
    ShopArticleContent localShopArticleContent = new ShopArticleContent();
    localShopArticleContent.setContent(paramString);
    localShopArticleContent.setArticle(paramShopArticle);
    this._$1.save(localShopArticleContent);
    paramShopArticle.setArticleContent(localShopArticleContent);
    return localShopArticleContent;
  }

  public ShopArticleContent update(ShopArticleContent paramShopArticleContent)
  {
    Updater localUpdater = new Updater(paramShopArticleContent);
    ShopArticleContent localShopArticleContent = this._$1.updateByUpdater(localUpdater);
    return localShopArticleContent;
  }

  public ShopArticleContent deleteById(Long paramLong)
  {
    ShopArticleContent localShopArticleContent = this._$1.deleteById(paramLong);
    return localShopArticleContent;
  }

  public ShopArticleContent[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopArticleContent[] arrayOfShopArticleContent = new ShopArticleContent[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopArticleContent[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopArticleContent;
  }

  @Autowired
  public void setDao(ShopArticleContentDao paramShopArticleContentDao)
  {
    this._$1 = paramShopArticleContentDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopArticleContentMngImpl
 * JD-Core Version:    0.6.2
 */