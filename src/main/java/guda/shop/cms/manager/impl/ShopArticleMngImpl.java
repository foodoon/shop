package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopArticleDao;
iimport guda.shopcms.entity.ShopArticle;
imimport guda.shopms.entity.ShopArticleContent;
impimport guda.shops.entity.ShopChannel;
impoimport guda.shop.manager.ShopArticleContentMng;
imporimport guda.shopmanager.ShopArticleMng;
importimport guda.shopanager.ShopChannelMng;
import import guda.shop.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopArticleMngImpl
  implements ShopArticleMng
{
  private ShopArticleContentMng _$3;
  private ShopChannelMng _$2;
  private ShopArticleDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramLong1, paramLong2, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public Pagination getPageForTag(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2)
  {
    Pagination localPagination;
    if (paramLong2 != null)
      localPagination = this._$1.getPageByChannel(paramLong2, paramInt1, paramInt2, true);
    else
      localPagination = this._$1.getPageByWebsite(paramLong1, paramInt1, paramInt2, true);
    return localPagination;
  }

  public List<ShopArticle> getListForTag(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2)
  {
    List localList;
    if (paramLong2 != null)
      localList = this._$1.getListByChannel(paramLong2, paramInt1, paramInt2, true);
    else
      localList = this._$1.getListByWebsite(paramLong1, paramInt1, paramInt2, true);
    return localList;
  }

  @Transactional(readOnly=true)
  public ShopArticle findById(Long paramLong)
  {
    ShopArticle localShopArticle = this._$1.findById(paramLong);
    return localShopArticle;
  }

  public ShopArticle save(ShopArticle paramShopArticle, Long paramLong, String paramString)
  {
    ShopChannel localShopChannel = this._$2.findById(paramLong);
    paramShopArticle.setChannel(localShopChannel);
    paramShopArticle.init();
    this._$1.save(paramShopArticle);
    if (paramString != null)
      this._$3.save(paramString, paramShopArticle);
    return paramShopArticle;
  }

  public ShopArticle update(ShopArticle paramShopArticle, Long paramLong, String paramString)
  {
    ShopArticle localShopArticle = findById(paramShopArticle.getId());
    ShopArticleContent localShopArticleContent = localShopArticle.getArticleContent();
    if (localShopArticleContent != null)
      localShopArticleContent.setContent(paramString);
    else
      this._$3.save(paramString, localShopArticle);
    if (paramLong != null)
      localShopArticle.setChannel(this._$2.findById(paramLong));
    Updater localUpdater = new Updater(paramShopArticle);
    localShopArticle = this._$1.updateByUpdater(localUpdater);
    return localShopArticle;
  }

  public ShopArticle deleteById(Long paramLong)
  {
    ShopArticle localShopArticle = this._$1.deleteById(paramLong);
    return localShopArticle;
  }

  public ShopArticle[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopArticle[] arrayOfShopArticle = new ShopArticle[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopArticle[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopArticle;
  }

  @Autowired
  public void setDao(ShopArticleDao paramShopArticleDao)
  {
    this._$1 = paramShopArticleDao;
  }

  @Autowired
  public void setShopChannelMng(ShopChannelMng paramShopChannelMng)
  {
    this._$2 = paramShopChannelMng;
  }

  @Autowired
  public void setShopArticleContentMng(ShopArticleContentMng paramShopArticleContentMng)
  {
    this._$3 = paramShopArticleContentMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopArticleMngImpl
 * JD-Core Version:    0.6.2
 */