package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.AdvertiseDao;
iimport guda.shopcms.entity.Advertise;
imimport guda.shopms.manager.AdspaceMng;
impimport guda.shops.manager.AdvertiseMng;
impoimport guda.shopmon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertiseMngImpl
  implements AdvertiseMng
{
  private AdspaceMng _$2;
  private AdvertiseDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInteger, paramBoolean, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public List<Advertise> getList(Integer paramInteger, Boolean paramBoolean)
  {
    return this._$1.getList(paramInteger, paramBoolean);
  }

  @Transactional(readOnly=true)
  public Advertise findById(Integer paramInteger)
  {
    Advertise localAdvertise = this._$1.findById(paramInteger);
    return localAdvertise;
  }

  public Advertise save(Advertise paramAdvertise, Integer paramInteger, Map<String, String> paramMap)
  {
    paramAdvertise.setAdspace(this._$2.findById(paramInteger));
    paramAdvertise.setAttr(paramMap);
    paramAdvertise.init();
    this._$1.save(paramAdvertise);
    return paramAdvertise;
  }

  public Advertise update(Advertise paramAdvertise, Integer paramInteger, Map<String, String> paramMap)
  {
    Updater localUpdater = new Updater(paramAdvertise);
    paramAdvertise = this._$1.updateByUpdater(localUpdater);
    paramAdvertise.setAdspace(this._$2.findById(paramInteger));
    paramAdvertise.getAttr().clear();
    paramAdvertise.getAttr().putAll(paramMap);
    return paramAdvertise;
  }

  public Advertise deleteById(Integer paramInteger)
  {
    Advertise localAdvertise = this._$1.deleteById(paramInteger);
    return localAdvertise;
  }

  public Advertise[] deleteByIds(Integer[] paramArrayOfInteger)
  {
    Advertise[] arrayOfAdvertise = new Advertise[paramArrayOfInteger.length];
    int i = 0;
    int j = paramArrayOfInteger.length;
    while (i < j)
    {
      arrayOfAdvertise[i] = deleteById(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfAdvertise;
  }

  public void display(Integer paramInteger)
  {
    Advertise localAdvertise = findById(paramInteger);
    if (localAdvertise != null)
      localAdvertise.setDisplayCount(Integer.valueOf(localAdvertise.getDisplayCount().intValue() + 1));
  }

  public void click(Integer paramInteger)
  {
    Advertise localAdvertise = findById(paramInteger);
    if (localAdvertise != null)
      localAdvertise.setClickCount(Integer.valueOf(localAdvertise.getClickCount().intValue() + 1));
  }

  @Autowired
  public void setAdspaceMng(AdspaceMng paramAdspaceMng)
  {
    this._$2 = paramAdspaceMng;
  }

  @Autowired
  public void setDao(AdvertiseDao paramAdvertiseDao)
  {
    this._$1 = paramAdvertiseDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.AdvertiseMngImpl
 * JD-Core Version:    0.6.2
 */