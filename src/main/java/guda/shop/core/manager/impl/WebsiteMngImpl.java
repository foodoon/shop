package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
imimport guda.shop.re.cache.CoreCacheSvc;
impimport guda.shop.e.cache.DomainCacheSvc;
impoimport guda.shop..dao.WebsiteDao;
imporimport guda.shop.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WebsiteMngImpl
  implements WebsiteMng
{
  private CoreCacheSvc _$3;
  private DomainCacheSvc _$2;
  private WebsiteDao _$1;

  @Transactional(readOnly=true)
  public Website getWebsite(String paramString)
  {
    Integer localInteger = this._$3.getWebsiteCount();
    int i;
    if (localInteger == null)
    {
      i = this._$1.countWebsite();
      this._$3.putWebsiteCount(new Integer(i).intValue());
    }
    else
    {
      i = localInteger.intValue();
    }
    Long localLong;
    Website localWebsite;
    if (i == 1)
    {
      localLong = this._$3.getWebsiteId();
      if (localLong != null)
      {
        localWebsite = findById(localLong);
      }
      else
      {
        localWebsite = this._$1.getUniqueWebsite();
        this._$3.putWebsiteId(localWebsite.getId());
      }
    }
    else if (i > 1)
    {
      localLong = this._$2.get(paramString);
      if (localLong != null)
      {
        localWebsite = findById(localLong);
      }
      else
      {
        localWebsite = this._$1.findByDomain(paramString);
        if (localWebsite != null)
          this._$2.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
      }
    }
    else
    {
      throw new IllegalStateException("no website data in database, please init database!");
    }
    return localWebsite;
  }

  public Pagination getAllPage(int paramInt1, int paramInt2)
  {
    return this._$1.getAllPage(paramInt1, paramInt2);
  }

  public List<Website> getAllList()
  {
    return this._$1.getAllList();
  }

  public Website findById(Long paramLong)
  {
    return this._$1.findById(paramLong);
  }

  public Website save(Website paramWebsite)
  {
    Website localWebsite = this._$1.save(paramWebsite);
    _$1(localWebsite);
    return localWebsite;
  }

  public Website update(Website paramWebsite)
  {
    Website localWebsite = findById(paramWebsite.getId());
    String str = localWebsite.getDomain();
    String[] arrayOfString = localWebsite.getDomainAliasArray();
    localWebsite = this._$1.updateByUpdater(new Updater(paramWebsite));
    _$1(str, arrayOfString, localWebsite);
    return localWebsite;
  }

  public Website deleteById(Long paramLong)
  {
    Website localWebsite = this._$1.deleteById(paramLong);
    _$2(localWebsite);
    return localWebsite;
  }

  public Website[] deleteByIds(Long[] paramArrayOfLong)
  {
    Website[] arrayOfWebsite = new Website[paramArrayOfLong.length];
    for (int i = 0; i < paramArrayOfLong.length; i++)
      arrayOfWebsite[i] = this._$1.deleteById(paramArrayOfLong[i]);
    _$1(arrayOfWebsite);
    return arrayOfWebsite;
  }

  private void _$1()
  {
    List localList = this._$1.getAllList();
    int i = localList.size();
    if (i == 0)
      throw new IllegalStateException("no website data in database, please init database!");
    this._$3.putWebsiteCount(i);
    Website localWebsite;
    if (i == 1)
    {
      localWebsite = (Website)localList.get(0);
      this._$3.putWebsiteId(localWebsite.getId());
      this._$2.removeAll();
      this._$2.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
    }
    else
    {
      this._$3.removeWebsiteId();
      this._$2.removeAll();
      Iterator localIterator = localList.iterator();
      while (((Iterator)localIterator).hasNext())
      {
        localWebsite = (Website)((Iterator)localIterator).next();
        this._$2.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
      }
    }
  }

  private void _$2(String paramString, String[] paramArrayOfString, Website paramWebsite)
  {
    String str = paramWebsite.getDomain();
    String[] arrayOfString = paramWebsite.getDomainAliasArray();
    if ((!str.equals(paramString)) || (!Arrays.equals(arrayOfString, paramArrayOfString)))
    {
      this._$2.remove(paramString, paramArrayOfString);
      this._$2.put(str, arrayOfString, paramWebsite.getId());
    }
  }

  private void _$4(Website paramWebsite)
  {
    _$1();
  }

  private void _$2(Website[] paramArrayOfWebsite)
  {
    _$1();
  }

  private void _$3(Website paramWebsite)
  {
    _$1();
  }

  private void _$1(String paramString, String[] paramArrayOfString, Website paramWebsite)
  {
    _$2(paramString, paramArrayOfString, paramWebsite);
  }

  private void _$2(Website paramWebsite)
  {
    _$4(paramWebsite);
  }

  private void _$1(Website[] paramArrayOfWebsite)
  {
    _$2(paramArrayOfWebsite);
  }

  private void _$1(Website paramWebsite)
  {
    _$3(paramWebsite);
  }

  @Autowired
  public void setCoreCacheSvc(CoreCacheSvc paramCoreCacheSvc)
  {
    this._$3 = paramCoreCacheSvc;
  }

  @Autowired
  public void setDomainCacheSvc(DomainCacheSvc paramDomainCacheSvc)
  {
    this._$2 = paramDomainCacheSvc;
  }

  @Autowired
  public void setDao(WebsiteDao paramWebsiteDao)
  {
    this._$1 = paramWebsiteDao;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.WebsiteMngImpl
 * JD-Core Version:    0.6.2
 */