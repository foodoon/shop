package guda.shop.dao.impl;

import guda.shop.cms.dao.BrandTextDao;
iimport guda.shopcms.entity.BrandText;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BrandTextDaoImpl extends HibernateBaseDao<BrandText, Long>
  implements BrandTextDao
{
  public BrandText save(BrandText paramBrandText)
  {
    getSession().save(paramBrandText);
    return paramBrandText;
  }

  protected Class<BrandText> getEntityClass()
  {
    return BrandText.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.BrandTextDaoImpl
 * JD-Core Version:    0.6.2
 */