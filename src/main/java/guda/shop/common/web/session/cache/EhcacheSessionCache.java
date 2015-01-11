package guda.shop.common.web.session.cache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EhcacheSessionCache
        implements SessionCache, InitializingBean {
    private Ehcache _$1;

    public Map<String, Serializable> getSession(String paramString) {
        Element localElement = this._$1.get(paramString);
        return localElement != null ? (HashMap) localElement.getValue() : null;
    }

    public void setSession(String paramString, Map<String, Serializable> paramMap, int paramInt) {
        this._$1.put(new Element(paramString, paramMap));
    }

    public Serializable getAttribute(String paramString1, String paramString2) {
        Map localMap = getSession(paramString1);
        return localMap != null ? (Serializable) localMap.get(paramString2) : null;
    }

    public void setAttribute(String paramString1, String paramString2, Serializable paramSerializable, int paramInt) {
        Object localObject = getSession(paramString1);
        if (localObject == null)
            localObject = new HashMap();
        ((Map) localObject).put(paramString2, paramSerializable);
        this._$1.put(new Element(paramString1, localObject));
    }

    public void clear(String paramString) {
        this._$1.remove(paramString);
    }

    public boolean exist(String paramString) {
        return this._$1.isKeyInCache(paramString);
    }

    public void afterPropertiesSet()
            throws Exception {
        Assert.notNull(this._$1);
    }

    public void setCache(Ehcache paramEhcache) {
        this._$1 = paramEhcache;
    }
}

