package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import guda.shop.core.cache.CoreCacheSvc;
import guda.shop.core.cache.DomainCacheSvc;
import guda.shop.core.dao.WebsiteDao;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class WebsiteMngImpl
        implements WebsiteMng {
    private CoreCacheSvc coreCacheSvc;
    private DomainCacheSvc domainCacheSvc;
    private WebsiteDao websiteDao;

    @Transactional(readOnly = true)
    public Website getWebsite(String paramString) {
        Integer localInteger = this.coreCacheSvc.getWebsiteCount();
        int i;
        if (localInteger == null) {
            i = this.websiteDao.countWebsite();
            this.coreCacheSvc.putWebsiteCount(new Integer(i).intValue());
        } else {
            i = localInteger.intValue();
        }
        Long localLong;
        Website localWebsite;
        if (i == 1) {
            localLong = this.coreCacheSvc.getWebsiteId();
            if (localLong != null) {
                localWebsite = findById(localLong);
            } else {
                localWebsite = this.websiteDao.getUniqueWebsite();
                this.coreCacheSvc.putWebsiteId(localWebsite.getId());
            }
        } else if (i > 1) {
            localLong = this.domainCacheSvc.get(paramString);
            if (localLong != null) {
                localWebsite = findById(localLong);
            } else {
                localWebsite = this.websiteDao.findByDomain(paramString);
                if (localWebsite != null)
                    this.domainCacheSvc.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
            }
        } else {
            throw new IllegalStateException("no website data in database, please init database!");
        }
        return localWebsite;
    }

    public Pagination getAllPage(int paramInt1, int paramInt2) {
        return this.websiteDao.getAllPage(paramInt1, paramInt2);
    }

    public List<Website> getAllList() {
        return this.websiteDao.getAllList();
    }

    public Website findById(Long paramLong) {
        return this.websiteDao.findById(paramLong);
    }

    public Website save(Website paramWebsite) {
        Website localWebsite = this.websiteDao.save(paramWebsite);
        _$1(localWebsite);
        return localWebsite;
    }

    public Website update(Website paramWebsite) {
        Website localWebsite = findById(paramWebsite.getId());
        String str = localWebsite.getDomain();
        String[] arrayOfString = localWebsite.getDomainAliasArray();
        localWebsite = this.websiteDao.updateByUpdater(new Updater(paramWebsite));
        _$1(str, arrayOfString, localWebsite);
        return localWebsite;
    }

    public Website deleteById(Long paramLong) {
        Website localWebsite = this.websiteDao.deleteById(paramLong);
        _$2(localWebsite);
        return localWebsite;
    }

    public Website[] deleteByIds(Long[] paramArrayOfLong) {
        Website[] arrayOfWebsite = new Website[paramArrayOfLong.length];
        for (int i = 0; i < paramArrayOfLong.length; i++)
            arrayOfWebsite[i] = this.websiteDao.deleteById(paramArrayOfLong[i]);
        _$1(arrayOfWebsite);
        return arrayOfWebsite;
    }

    private void _$1() {
        List localList = this.websiteDao.getAllList();
        int i = localList.size();
        if (i == 0)
            throw new IllegalStateException("no website data in database, please init database!");
        this.coreCacheSvc.putWebsiteCount(i);
        Website localWebsite;
        if (i == 1) {
            localWebsite = (Website) localList.get(0);
            this.coreCacheSvc.putWebsiteId(localWebsite.getId());
            this.domainCacheSvc.removeAll();
            this.domainCacheSvc.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
        } else {
            this.coreCacheSvc.removeWebsiteId();
            this.domainCacheSvc.removeAll();
            Iterator localIterator = localList.iterator();
            while (((Iterator) localIterator).hasNext()) {
                localWebsite = (Website) ((Iterator) localIterator).next();
                this.domainCacheSvc.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
            }
        }
    }

    private void _$2(String paramString, String[] paramArrayOfString, Website paramWebsite) {
        String str = paramWebsite.getDomain();
        String[] arrayOfString = paramWebsite.getDomainAliasArray();
        if ((!str.equals(paramString)) || (!Arrays.equals(arrayOfString, paramArrayOfString))) {
            this.domainCacheSvc.remove(paramString, paramArrayOfString);
            this.domainCacheSvc.put(str, arrayOfString, paramWebsite.getId());
        }
    }

    private void _$4(Website paramWebsite) {
        _$1();
    }

    private void _$2(Website[] paramArrayOfWebsite) {
        _$1();
    }

    private void _$3(Website paramWebsite) {
        _$1();
    }

    private void _$1(String paramString, String[] paramArrayOfString, Website paramWebsite) {
        _$2(paramString, paramArrayOfString, paramWebsite);
    }

    private void _$2(Website paramWebsite) {
        _$4(paramWebsite);
    }

    private void _$1(Website[] paramArrayOfWebsite) {
        _$2(paramArrayOfWebsite);
    }

    private void _$1(Website paramWebsite) {
        _$3(paramWebsite);
    }

    @Autowired
    public void setCoreCacheSvc(CoreCacheSvc paramCoreCacheSvc) {
        this.coreCacheSvc = paramCoreCacheSvc;
    }

    @Autowired
    public void setDomainCacheSvc(DomainCacheSvc paramDomainCacheSvc) {
        this.domainCacheSvc = paramDomainCacheSvc;
    }

    @Autowired
    public void setDao(WebsiteDao paramWebsiteDao) {
        this.websiteDao = paramWebsiteDao;
    }
}

