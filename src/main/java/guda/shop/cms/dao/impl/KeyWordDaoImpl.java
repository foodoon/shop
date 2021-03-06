package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.KeyWordDao;
import guda.shop.cms.entity.KeyWord;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeyWordDaoImpl extends HibernateBaseDao<KeyWord, Integer>
        implements KeyWordDao {
    public KeyWord findById(Integer paramInteger) {
        KeyWord localKeyWord = (KeyWord) get(paramInteger);
        return localKeyWord;
    }

    public List<KeyWord> findKeyWord(Integer paramInteger) {
        List localList = getSession().createQuery("from KeyWord bean order by bean.times desc").setMaxResults(paramInteger.intValue()).list();
        return localList;
    }

    public List<KeyWord> getKeyWordContent(String paramString) {
        List localList = getSession().createQuery("from KeyWord bean where bean.keyword=:keyword ").setParameter("keyword", paramString).list();
        return localList;
    }

    public KeyWord save(KeyWord paramKeyWord) {
        getSession().save(paramKeyWord);
        return paramKeyWord;
    }

    public KeyWord deleteById(Integer paramInteger) {
        KeyWord localKeyWord = (KeyWord) super.get(paramInteger);
        if (localKeyWord != null)
            getSession().delete(localKeyWord);
        return localKeyWord;
    }

    protected Class<KeyWord> getEntityClass() {
        return KeyWord.class;
    }

    public List<KeyWord> getAllList() {
        return null;
    }
}

