package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopAdminDao;
iimport guda.shopcms.entity.ShopAdmin;
imimport guda.shopms.manager.ShopAdminMng;
impimport guda.shopmmon.hibernate3.Updater;
impoimport guda.shopmon.page.Pagination;
imporimport guda.shop.entity.Admin;
importimport guda.shopentity.User;
import import guda.shopntity.Website;
import cimport guda.shopnager.AdminMng;
import coimport guda.shopager.UserMng;
import com.jspgou.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopAdminMngImpl
  implements ShopAdminMng
{
  private UserMng _$4;
  private WebsiteMng _$3;
  private AdminMng _$2;
  private ShopAdminDao _$1;

  public ShopAdmin getByUserId(Long paramLong1, Long paramLong2)
  {
    Admin localAdmin = this._$2.getByUserId(paramLong1, paramLong2);
    if (localAdmin == null)
      return null;
    return findById(localAdmin.getId());
  }

  public ShopAdmin register(String paramString1, String paramString2, Boolean paramBoolean, String paramString3, String paramString4, boolean paramBoolean1, Long paramLong, ShopAdmin paramShopAdmin)
  {
    Website localWebsite = this._$3.findById(paramLong);
    Admin localAdmin = this._$2.register(paramString1, paramString2, paramString3, paramString4, Boolean.valueOf(paramBoolean1), localWebsite, paramBoolean);
    paramShopAdmin.setAdmin(localAdmin);
    paramShopAdmin.setWebsite(localWebsite);
    return save(paramShopAdmin);
  }

  @Transactional(readOnly=true)
  public Pagination getPage(Long paramLong, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramLong, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ShopAdmin findById(Long paramLong)
  {
    ShopAdmin localShopAdmin = this._$1.findById(paramLong);
    return localShopAdmin;
  }

  public ShopAdmin save(ShopAdmin paramShopAdmin)
  {
    this._$1.save(paramShopAdmin);
    return paramShopAdmin;
  }

  public ShopAdmin update(ShopAdmin paramShopAdmin, String paramString1, Boolean paramBoolean1, String paramString2, Boolean paramBoolean2)
  {
    ShopAdmin localShopAdmin = findById(paramShopAdmin.getId());
    Admin localAdmin = localShopAdmin.getAdmin();
    this._$4.updateUser(localAdmin.getUser().getId(), paramString1, paramString2);
    if (paramBoolean1 != null)
      localAdmin.setDisabled(paramBoolean1);
    localAdmin.setViewonlyAdmin(paramBoolean2);
    Updater localUpdater = new Updater(paramShopAdmin);
    localShopAdmin = this._$1.updateByUpdater(localUpdater);
    return localShopAdmin;
  }

  public ShopAdmin deleteById(Long paramLong)
  {
    ShopAdmin localShopAdmin = this._$1.deleteById(paramLong);
    return localShopAdmin;
  }

  public ShopAdmin[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopAdmin[] arrayOfShopAdmin = new ShopAdmin[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopAdmin[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopAdmin;
  }

  @Autowired
  public void setDao(ShopAdminDao paramShopAdminDao)
  {
    this._$1 = paramShopAdminDao;
  }

  @Autowired
  public void setAdminMng(AdminMng paramAdminMng)
  {
    this._$2 = paramAdminMng;
  }

  @Autowired
  public void setWebsiteMng(WebsiteMng paramWebsiteMng)
  {
    this._$3 = paramWebsiteMng;
  }

  @Autowired
  public void setUserMng(UserMng paramUserMng)
  {
    this._$4 = paramUserMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopAdminMngImpl
 * JD-Core Version:    0.6.2
 */