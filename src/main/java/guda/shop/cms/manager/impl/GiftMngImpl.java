package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.GiftDao;
iimport guda.shopcms.dao.ShopMemberDao;
imimport guda.shopms.entity.Gift;
impimport guda.shops.entity.ShopMember;
impoimport guda.shop.manager.GiftMng;
imporimport guda.shopon.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GiftMngImpl
  implements GiftMng
{

  @Autowired
  private GiftDao _$2;

  @Autowired
  private ShopMemberDao _$1;

  public Gift deleteById(Long paramLong)
  {
    return this._$2.deleteById(paramLong);
  }

  public Gift[] deleteByIds(Long[] paramArrayOfLong)
  {
    Gift[] arrayOfGift = new Gift[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfGift[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfGift;
  }

  public Gift findById(Long paramLong)
  {
    return this._$2.findById(paramLong);
  }

  public Pagination getPageGift(int paramInt1, int paramInt2)
  {
    return this._$2.getPageGift(paramInt1, paramInt2);
  }

  public Gift save(Gift paramGift)
  {
    return this._$2.save(paramGift);
  }

  public Gift updateByGiftnumb(Long paramLong1, Integer paramInteger, Long paramLong2)
  {
    Gift localGift = this._$2.findById(paramLong1);
    ShopMember localShopMember = this._$1.findById(paramLong2);
    Integer localInteger1 = localGift.getGiftStock();
    Integer localInteger2 = Integer.valueOf(localGift.getGiftScore().intValue() * paramInteger.intValue());
    if (localInteger1.intValue() < paramInteger.intValue())
      return null;
    if (localInteger2.intValue() > localShopMember.getScore().intValue())
      return null;
    localGift.setGiftStock(Integer.valueOf(localInteger1.intValue() - paramInteger.intValue()));
    localShopMember.setScore(Integer.valueOf(localShopMember.getScore().intValue() - localInteger2.intValue()));
    return localGift;
  }

  public Gift updateByUpdater(Gift paramGift)
  {
    Updater localUpdater = new Updater(paramGift);
    return this._$2.updateByUpdater(localUpdater);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.GiftMngImpl
 * JD-Core Version:    0.6.2
 */