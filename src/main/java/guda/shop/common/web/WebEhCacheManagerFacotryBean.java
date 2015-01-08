package guda.shop.common.web;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class WebEhCacheManagerFacotryBean
        implements FactoryBean, InitializingBean, DisposableBean {
    private final Logger _$5 = LoggerFactory.getLogger(WebEhCacheManagerFacotryBean.class);
    private Resource _$4;
    private Resource _$3;
    private String _$2;
    private CacheManager _$1;

    public void setConfigLocation(Resource paramResource) {
        this._$4 = paramResource;
    }

    public void setdiskStoreLocation(Resource paramResource) {
        this._$3 = paramResource;
    }

    public void setCacheManagerName(String paramString) {
        this._$2 = paramString;
    }

    public void afterPropertiesSet()
            throws IOException, CacheException {
        this._$5.info("Initializing EHCache CacheManager");
        Configuration localConfiguration = null;
        if (this._$4 != null) {
            localConfiguration = ConfigurationFactory.parseConfiguration(this._$4.getInputStream());
            if (this._$3 != null) {
                DiskStoreConfiguration localDiskStoreConfiguration = new DiskStoreConfiguration();
                localDiskStoreConfiguration.setPath(this._$3.getFile().getAbsolutePath());
                try {
                    localConfiguration.addDiskStore(localDiskStoreConfiguration);
                } catch (ObjectExistsException localObjectExistsException) {
                    this._$5.warn("if you want to config distStore in spring, please remove diskStore in config file!", localObjectExistsException);
                }
            }
        }
        if (localConfiguration != null)
            this._$1 = new CacheManager(localConfiguration);
        else
            this._$1 = new CacheManager();
        if (this._$2 != null)
            this._$1.setName(this._$2);
    }

    public CacheManager getObject() {
        return this._$1;
    }

    public Class<? extends CacheManager> getObjectType() {
        return this._$1 == null ? CacheManager.class : this._$1.getClass();
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() {
        this._$5.info("Shutting down EHCache CacheManager");
        this._$1.shutdown();
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.WebEhCacheManagerFacotryBean
 * JD-Core Version:    0.6.2
 */