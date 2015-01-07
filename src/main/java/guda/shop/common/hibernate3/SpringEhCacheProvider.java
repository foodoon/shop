package guda.shop.common.hibernate3;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.hibernate.EhCache;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public final class SpringEhCacheProvider
  implements CacheProvider
{
  private static final Logger _$4 = LoggerFactory.getLogger(SpringEhCacheProvider.class);
  private Resource _$3;
  private Resource _$2;
  private CacheManager _$1;

  public void setConfigLocation(Resource paramResource)
  {
    this._$3 = paramResource;
  }

  public void setDiskStoreLocation(Resource paramResource)
  {
    this._$2 = paramResource;
  }

  public final Cache buildCache(String paramString, Properties paramProperties)
    throws CacheException
  {
    try
    {
      Ehcache localEhcache = this._$1.getEhcache(paramString);
      if (localEhcache == null)
      {
        String str = "Could not find a specific ehcache configuration for cache named [{}]; using defaults.";
        _$4.warn(str, paramString);
        this._$1.addCache(paramString);
        localEhcache = this._$1.getEhcache(paramString);
        _$4.debug("started EHCache region: " + paramString);
      }
      return new EhCache(localEhcache);
    }
    catch (CacheException localCacheException)
    {
      throw new CacheException(localCacheException);
    }
  }

  public final long nextTimestamp()
  {
    return Timestamper.next();
  }

  public final void start(Properties paramProperties)
    throws CacheException
  {
    if (this._$1 != null)
    {
      String localObject = "Attempt to restart an already started EhCacheProvider. Use sessionFactory.close()  between repeated calls to buildSessionFactory. Using previously created EhCacheProvider. If this behaviour is required, consider using SingletonEhCacheProvider.";
      _$4.warn(localObject);
      return;
    }
    Object localObject = null;
    try
    {
      if (this._$3 != null)
      {
        localObject = ConfigurationFactory.parseConfiguration(this._$3.getInputStream());
        if (this._$2 != null)
        {
          DiskStoreConfiguration localDiskStoreConfiguration = new DiskStoreConfiguration();
          localDiskStoreConfiguration.setPath(this._$2.getFile().getAbsolutePath());
          try
          {
            ((Configuration)localObject).addDiskStore(localDiskStoreConfiguration);
          }
          catch (ObjectExistsException localObjectExistsException)
          {
            String str = "if you want to config distStore in spring, please remove diskStore in config file!";
            _$4.warn(str, localObjectExistsException);
          }
        }
      }
    }
    catch (IOException localIOException)
    {
      _$4.warn("create ehcache config failed!", localIOException);
    }
    if (localObject != null)
      this._$1 = new CacheManager((Configuration)localObject);
    else
      this._$1 = new CacheManager();
  }

  public final void stop()
  {
    if (this._$1 != null)
    {
      this._$1.shutdown();
      this._$1 = null;
    }
  }

  public final boolean isMinimalPutsEnabledByDefault()
  {
    return false;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.hibernate3.SpringEhCacheProvider
 * JD-Core Version:    0.6.2
 */