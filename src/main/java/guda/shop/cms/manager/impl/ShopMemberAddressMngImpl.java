package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopMemberAddressDao;
iimport guda.shopcms.entity.Cart;
imimport guda.shopms.entity.ShopMemberAddress;
impimport guda.shops.manager.CartMng;
impoimport guda.shop.manager.ShopMemberAddressMng;
imporimport guda.shopmanager.ShopMemberMng;
import com.jspgou.common.hibernate3.Updater;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopMemberAddressMngImpl
  implements ShopMemberAddressMng
{
  private ShopMemberAddressDao _$3;
  private ShopMemberMng _$2;
  private CartMng _$1;

  @Transactional(readOnly=true)
  public List<ShopMemberAddress> getList(Long paramLong)
  {
    return this._$3.getList(paramLong);
  }

  public ShopMemberAddress findByMemberDefault(Long paramLong, Boolean paramBoolean)
  {
    List localList = this._$3.findByMemberDefault(paramLong, paramBoolean);
    if (localList.size() >= 1)
      return (ShopMemberAddress)localList.get(0);
    return null;
  }

  public List<ShopMemberAddress> findByMemberId(Long paramLong)
  {
    return this._$3.findByMemberDefault(paramLong, null);
  }

  @Transactional(readOnly=true)
  public ShopMemberAddress findById(Long paramLong)
  {
    ShopMemberAddress localShopMemberAddress = this._$3.findById(paramLong);
    return localShopMemberAddress;
  }

  public ShopMemberAddress save(ShopMemberAddress paramShopMemberAddress)
  {
    return this._$3.save(paramShopMemberAddress);
  }

  public ShopMemberAddress updateByUpdater(ShopMemberAddress paramShopMemberAddress)
  {
    Updater localUpdater = new Updater(paramShopMemberAddress);
    return this._$3.updateByUpdater(localUpdater);
  }

  public ShopMemberAddress deleteById(Long paramLong1, Long paramLong2)
  {
    Cart localCart = this._$1.findById(paramLong2);
    ShopMemberAddress localShopMemberAddress = this._$3.deleteById(paramLong1);
    return localShopMemberAddress;
  }

  @Autowired
  public void setDao(ShopMemberAddressDao paramShopMemberAddressDao)
  {
    this._$3 = paramShopMemberAddressDao;
  }

  @Autowired
  public void setShopMemberMng(ShopMemberMng paramShopMemberMng)
  {
    this._$2 = paramShopMemberMng;
  }

  @Autowired
  public void setCartMng(CartMng paramCartMng)
  {
    this._$1 = paramCartMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopMemberAddressMngImpl
 * JD-Core Version:    0.6.2
 */