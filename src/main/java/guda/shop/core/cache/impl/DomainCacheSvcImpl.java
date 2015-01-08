package guda.shop.core.cache.impl;

import guda.shop.core.cache.DomainCacheSvc;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DomainCacheSvcImpl
        implements DomainCacheSvc {
    private Ehcache _$1;

    @Autowired
    public void setDomainCache(@Qualifier("domain") Ehcache paramEhcache) {
        this._$1 = paramEhcache;
    }

    public void put(String paramString, String[] paramArrayOfString, Long paramLong) {
        this._$1.put(new Element(paramString, paramLong));
        if (paramArrayOfString != null)
            for (String str : paramArrayOfString)
                this._$1.put(new Element(str, paramLong));
    }

    public boolean remove(String paramString, String[] paramArrayOfString) {
        if (paramArrayOfString != null)
            for (String str : paramArrayOfString)
                this._$1.remove(str);
        return this._$1.remove(paramString);
    }

    public Long get(String paramString) {
        Element localElement = this._$1.get(paramString);
        if (localElement != null)
            return (Long) localElement.getValue();
        return null;
    }

    public void removeAll() {
        this._$1.removeAll();
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.cache.impl.DomainCacheSvcImpl
 * JD-Core Version:    0.6.2
 */