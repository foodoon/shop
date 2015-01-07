package guda.shop.dao.impl;

import guda.shop.cms.dao.KeyWordDao;
iimport guda.shopcms.entity.KeyWord;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class KeyWordDaoImpl extends HibernateBaseDao<KeyWord, Integer>
  implements KeyWordDao
{
  public KeyWord findById(Integer paramInteger)
  {
    KeyWord localKeyWord = (KeyWord)get(paramInteger);
    return localKeyWord;
  }

  public List<KeyWord> findKeyWord(Integer paramInteger)
  {
    List localList = getSession().createQuery("from KeyWord bean order by bean.times desc").setMaxResults(paramInteger.intValue()).list();
    return localList;
  }

  public List<KeyWord> getKeyWordContent(String paramString)
  {
    List localList = getSession().createQuery("from KeyWord bean where bean.keyword=:keyword ").setParameter("keyword", paramString).list();
    return localList;
  }

  public KeyWord save(KeyWord paramKeyWord)
  {
    getSession().save(paramKeyWord);
    return paramKeyWord;
  }

  public KeyWord deleteById(Integer paramInteger)
  {
    KeyWord localKeyWord = (KeyWord)super.get(paramInteger);
    if (localKeyWord != null)
      getSession().delete(localKeyWord);
    return localKeyWord;
  }

  protected Class<KeyWord> getEntityClass()
  {
    return KeyWord.class;
  }

  public List<KeyWord> getAllList()
  {
    return null;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.KeyWordDaoImpl
 * JD-Core Version:    0.6.2
 */