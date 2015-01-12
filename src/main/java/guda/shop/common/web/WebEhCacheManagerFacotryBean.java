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
    private final Logger log = LoggerFactory.getLogger(WebEhCacheManagerFacotryBean.class);
    private Resource configLocation;
    private Resource diskStoreLocation;
    private String cacheManagerName;
    private CacheManager cacheManager;

    public void setConfigLocation(Resource paramResource) {
        this.configLocation = paramResource;
    }

    public void setdiskStoreLocation(Resource paramResource) {
        this.diskStoreLocation = paramResource;
    }

    public void setCacheManagerName(String paramString) {
        this.cacheManagerName = paramString;
    }

    public void afterPropertiesSet()
            throws IOException, CacheException {
        this.log.info("Initializing EHCache CacheManager");
        Configuration localConfiguration = null;
        if (this.configLocation != null) {
            localConfiguration = ConfigurationFactory.parseConfiguration(this.configLocation.getInputStream());
            if (this.diskStoreLocation != null) {
                DiskStoreConfiguration localDiskStoreConfiguration = new DiskStoreConfiguration();
                localDiskStoreConfiguration.setPath(this.diskStoreLocation.getFile().getAbsolutePath());
                try {
                    localConfiguration.addDiskStore(localDiskStoreConfiguration);
                } catch (ObjectExistsException localObjectExistsException) {
                    this.log.warn("if you want to config distStore in spring, please remove diskStore in config file!", localObjectExistsException);
                }
            }
        }
        if (localConfiguration != null)
            this.cacheManager = new CacheManager(localConfiguration);
        else
            this.cacheManager = new CacheManager();
        if (this.cacheManagerName != null)
            this.cacheManager.setName(this.cacheManagerName);
    }

    public CacheManager getObject() {
        return this.cacheManager;
    }

    public Class<? extends CacheManager> getObjectType() {
        return this.cacheManager == null ? CacheManager.class : this.cacheManager.getClass();
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() {
        this.log.info("Shutting down EHCache CacheManager");
        this.cacheManager.shutdown();
    }
}

