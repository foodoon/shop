package guda.shop.core.cache.impl;

import guda.shop.core.cache.CoreCacheSvc;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CoreCacheSvcImpl
        implements CoreCacheSvc {
    private static final String _$3 = "core_website_count";
    private static final String _$2 = "core_website_id";
    private Ehcache _$1;

    @Autowired
    public void setGlobalCache(@Qualifier("global") Ehcache paramEhcache) {
        this._$1 = paramEhcache;
    }

    public void putWebsiteCount(int paramInt) {
        this._$1.put(new Element("core_website_count", Integer.valueOf(paramInt)));
    }

    public Integer getWebsiteCount() {
        Element localElement = this._$1.get("core_website_count");
        if (localElement != null)
            return (Integer) localElement.getValue();
        return null;
    }

    public void putWebsiteId(Long paramLong) {
        this._$1.put(new Element("core_website_id", paramLong));
    }

    public Long getWebsiteId() {
        Element localElement = this._$1.get("core_website_id");
        if (localElement != null)
            return (Long) localElement.getValue();
        return null;
    }

    public boolean removeWebsiteId() {
        return this._$1.remove("core_website_id");
    }
}

