package guda.shop.dao.impl;

import guda.shop.cms.dao.LogisticsTextDao;
iimport guda.shopcms.entity.LogisticsText;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class LogisticsTextDaoImpl extends HibernateBaseDao<LogisticsText, Long>
  implements LogisticsTextDao
{
  public LogisticsText save(LogisticsText paramLogisticsText)
  {
    getSession().save(paramLogisticsText);
    return paramLogisticsText;
  }

  protected Class<LogisticsText> getEntityClass()
  {
    return LogisticsText.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.LogisticsTextDaoImpl
 * JD-Core Version:    0.6.2
 */