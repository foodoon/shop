package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.AdspaceDao;
iimport guda.shopcms.entity.Adspace;
imimport guda.shopms.manager.AdspaceMng;
import guda.shop.common.hibernate3.Updater;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdspaceMngImpl
  implements AdspaceMng
{

  @Autowired
  private AdspaceDao _$1;

  public Adspace deleteById(Integer paramInteger)
  {
    return this._$1.deleteById(paramInteger);
  }

  public Adspace[] deleteByIds(Integer[] paramArrayOfInteger)
  {
    Adspace[] arrayOfAdspace = new Adspace[paramArrayOfInteger.length];
    int i = 0;
    int j = paramArrayOfInteger.length;
    while (i < j)
    {
      arrayOfAdspace[i] = deleteById(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfAdspace;
  }

  public Adspace findById(Integer paramInteger)
  {
    return this._$1.findById(paramInteger);
  }

  public List<Adspace> getList()
  {
    return this._$1.getList();
  }

  public Adspace save(Adspace paramAdspace)
  {
    return this._$1.save(paramAdspace);
  }

  public Adspace updateByAdspacenumb(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    return null;
  }

  public Adspace updateByUpdater(Adspace paramAdspace)
  {
    Updater localUpdater = new Updater(paramAdspace);
    return this._$1.updateByUpdater(localUpdater);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.AdspaceMngImpl
 * JD-Core Version:    0.6.2
 */