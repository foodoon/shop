package guda.shop.common.hibernate3;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TreeIntercptor extends EmptyInterceptor
  implements ApplicationContextAware
{
  private static final Logger _$3 = LoggerFactory.getLogger(TreeIntercptor.class);
  private ApplicationContext _$2;
  private SessionFactory _$1;
  public static final String SESSION_FACTORY = "sessionFactory";

  public void setApplicationContext(ApplicationContext paramApplicationContext)
    throws BeansException
  {
    this._$2 = paramApplicationContext;
  }

  protected SessionFactory getSessionFactory()
  {
    if (this._$1 == null)
    {
      this._$1 = ((SessionFactory)this._$2.getBean("sessionFactory", SessionFactory.class));
      if (this._$1 == null)
        throw new IllegalStateException("not found bean named 'sessionFactory',please check you spring config file.");
    }
    return this._$1;
  }

  protected Session getSession()
  {
    return getSessionFactory().getCurrentSession();
  }

  public boolean onSave(Object paramObject, Serializable paramSerializable, Object[] paramArrayOfObject, String[] paramArrayOfString, Type[] paramArrayOfType)
  {
    if ((paramObject instanceof HibernateTree))
    {
      HibernateTree localHibernateTree = (HibernateTree)paramObject;
      Long localLong = localHibernateTree.getParentId();
      String str1 = localHibernateTree.getClass().getName();
      Session localSession = getSession();
      FlushMode localFlushMode = localSession.getFlushMode();
      localSession.setFlushMode(FlushMode.MANUAL);
      String str2;
      Integer localInteger;
      Object localObject;
      if (localLong != null)
      {
        str2 = "select bean." + localHibernateTree.getRgtName() + " from " + str1 + " bean where bean.id=:pid";
        localInteger = Integer.valueOf(((Number)localSession.createQuery(str2).setParameter("pid", localLong).uniqueResult()).intValue());
        localObject = "update " + str1 + " bean set bean." + localHibernateTree.getRgtName() + " = bean." + localHibernateTree.getRgtName() + " + 2 WHERE bean." + localHibernateTree.getRgtName() + " >= :myPosition";
        String str3 = "update " + str1 + " bean set bean." + localHibernateTree.getLftName() + " = bean." + localHibernateTree.getLftName() + " + 2 WHERE bean." + localHibernateTree.getLftName() + " >= :myPosition";
        if (!StringUtils.isBlank(localHibernateTree.getTreeCondition()))
        {
          localObject = (String)localObject + " and (" + localHibernateTree.getTreeCondition() + ")";
          str3 = str3 + " and (" + localHibernateTree.getTreeCondition() + ")";
        }
        localSession.createQuery((String)localObject).setParameter("myPosition", localInteger).executeUpdate();
        localSession.createQuery(str3).setParameter("myPosition", localInteger).executeUpdate();
      }
      else
      {
        str2 = "select max(bean." + localHibernateTree.getRgtName() + ") from " + str1 + " bean";
        if (!StringUtils.isBlank(localHibernateTree.getTreeCondition()))
          str2 = str2 + " where " + localHibernateTree.getTreeCondition();
        localObject = (Number)localSession.createQuery(str2).uniqueResult();
        if (localObject == null)
          localInteger = Integer.valueOf(1);
        else
          localInteger = Integer.valueOf(((Number)localObject).intValue() + 1);
      }
      localSession.setFlushMode(localFlushMode);
      for (int i = 0; i < paramArrayOfString.length; i++)
      {
        if (paramArrayOfString[i].equals(localHibernateTree.getLftName()))
          paramArrayOfObject[i] = localInteger;
        if (paramArrayOfString[i].equals(localHibernateTree.getRgtName()))
          paramArrayOfObject[i] = Integer.valueOf(localInteger.intValue() + 1);
      }
      return true;
    }
    return false;
  }

  public boolean onFlushDirty(Object paramObject, Serializable paramSerializable, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, String[] paramArrayOfString, Type[] paramArrayOfType)
  {
    if (!(paramObject instanceof HibernateTree))
      return false;
    HibernateTree localHibernateTree1 = (HibernateTree)paramObject;
    for (int i = 0; i < paramArrayOfString.length; i++)
      if (paramArrayOfString[i].equals(localHibernateTree1.getParentName()))
      {
        HibernateTree localHibernateTree2 = (HibernateTree)paramArrayOfObject2[i];
        HibernateTree localHibernateTree3 = (HibernateTree)paramArrayOfObject1[i];
        return _$1(localHibernateTree1, localHibernateTree2, localHibernateTree3);
      }
    return false;
  }

  private boolean _$1(HibernateTree paramHibernateTree1, HibernateTree paramHibernateTree2, HibernateTree paramHibernateTree3)
  {
    if (((paramHibernateTree2 == null) && (paramHibernateTree3 == null)) || ((paramHibernateTree2 != null) && (paramHibernateTree3 != null) && (paramHibernateTree2.getId().equals(paramHibernateTree3.getId()))))
      return false;
    String str1 = paramHibernateTree1.getClass().getName();
    if (_$3.isDebugEnabled())
      _$3.debug("update Tree {}, id={}, pre-parent id={}, curr-parent id={}", new Object[] { str1, paramHibernateTree1.getId(), paramHibernateTree2 == null ? null : paramHibernateTree2.getId(), paramHibernateTree3 == null ? null : paramHibernateTree3.getId() });
    Session localSession = getSession();
    FlushMode localFlushMode = localSession.getFlushMode();
    localSession.setFlushMode(FlushMode.MANUAL);
    Integer localInteger1;
    if (paramHibernateTree3 != null)
    {
      str2 = "select bean." + paramHibernateTree1.getLftName() + ",bean." + paramHibernateTree1.getRgtName() + " from " + str1 + " bean where bean.id=:id";
      localObject = (Object[])localSession.createQuery(str2).setParameter("id", paramHibernateTree1.getId()).uniqueResult();
      int i = ((Number)localObject[0]).intValue();
      k = ((Number)localObject[1]).intValue();
      m = k - i + 1;
      _$3.debug("current node span={}", Integer.valueOf(m));
      Object[] arrayOfObject = (Object[])localSession.createQuery(str2).setParameter("id", paramHibernateTree3.getId()).uniqueResult();
      int i1 = ((Number)arrayOfObject[0]).intValue();
      localInteger1 = Integer.valueOf(((Number)arrayOfObject[1]).intValue());
      _$3.debug("current parent lft={} rgt={}", Integer.valueOf(i1), localInteger1);
      str4 = "update " + str1 + " bean set bean." + paramHibernateTree1.getRgtName() + " = bean." + paramHibernateTree1.getRgtName() + " + " + m + " WHERE bean." + paramHibernateTree1.getRgtName() + " >= :parentRgt";
      String str5 = "update " + str1 + " bean set bean." + paramHibernateTree1.getLftName() + " = bean." + paramHibernateTree1.getLftName() + " + " + m + " WHERE bean." + paramHibernateTree1.getLftName() + " >= :parentRgt";
      if (!StringUtils.isBlank(paramHibernateTree1.getTreeCondition()))
      {
        str4 = str4 + " and (" + paramHibernateTree1.getTreeCondition() + ")";
        str5 = str5 + " and (" + paramHibernateTree1.getTreeCondition() + ")";
      }
      localSession.createQuery(str4).setInteger("parentRgt", localInteger1.intValue()).executeUpdate();
      localSession.createQuery(str5).setInteger("parentRgt", localInteger1.intValue()).executeUpdate();
      _$3.debug("vacated span hql: {}, {}, parentRgt={}", new Object[] { str4, str5, localInteger1 });
    }
    else
    {
      str2 = "select max(bean." + paramHibernateTree1.getRgtName() + ") from " + str1 + " bean";
      if (!StringUtils.isBlank(paramHibernateTree1.getTreeCondition()))
        str2 = str2 + " where " + paramHibernateTree1.getTreeCondition();
      localInteger1 = Integer.valueOf(((Number)localSession.createQuery(str2).uniqueResult()).intValue());
      localObject = localInteger1;
      Integer localInteger2 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1);
      _$3.debug("max node left={}", localInteger1);
    }
    String str2 = "select bean." + paramHibernateTree1.getLftName() + ",bean." + paramHibernateTree1.getRgtName() + " from " + str1 + " bean where bean.id=:id";
    Object localObject = (Object[])localSession.createQuery(str2).setParameter("id", paramHibernateTree1.getId()).uniqueResult();
    int j = ((Number)localObject[0]).intValue();
    int k = ((Number)localObject[1]).intValue();
    int m = k - j + 1;
    if (_$3.isDebugEnabled())
      _$3.debug("before adjust self left={} right={} span={}", new Object[] { Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(m) });
    int n = localInteger1.intValue() - j;
    str2 = "update " + str1 + " bean set bean." + paramHibernateTree1.getLftName() + "=bean." + paramHibernateTree1.getLftName() + "+:offset, bean." + paramHibernateTree1.getRgtName() + "=bean." + paramHibernateTree1.getRgtName() + "+:offset WHERE bean." + paramHibernateTree1.getLftName() + " between :nodeLft and :nodeRgt";
    if (!StringUtils.isBlank(paramHibernateTree1.getTreeCondition()))
      str2 = str2 + " and (" + paramHibernateTree1.getTreeCondition() + ")";
    localSession.createQuery(str2).setParameter("offset", Integer.valueOf(n)).setParameter("nodeLft", Integer.valueOf(j)).setParameter("nodeRgt", Integer.valueOf(k)).executeUpdate();
    if (_$3.isDebugEnabled())
      _$3.debug("adjust self hql: {}, offset={}, nodeLft={}, nodeRgt={}", new Object[] { str2, Integer.valueOf(n), Integer.valueOf(j), Integer.valueOf(k) });
    String str3 = "update " + str1 + " bean set bean." + paramHibernateTree1.getRgtName() + " = bean." + paramHibernateTree1.getRgtName() + " - " + m + " WHERE bean." + paramHibernateTree1.getRgtName() + " > :nodeRgt";
    String str4 = "update " + str1 + " bean set bean." + paramHibernateTree1.getLftName() + " = bean." + paramHibernateTree1.getLftName() + " - " + m + " WHERE bean." + paramHibernateTree1.getLftName() + " > :nodeRgt";
    if (!StringUtils.isBlank(paramHibernateTree1.getTreeCondition()))
    {
      str3 = str3 + " and (" + paramHibernateTree1.getTreeCondition() + ")";
      str4 = str4 + " and (" + paramHibernateTree1.getTreeCondition() + ")";
    }
    localSession.createQuery(str3).setParameter("nodeRgt", Integer.valueOf(k)).executeUpdate();
    localSession.createQuery(str4).setParameter("nodeRgt", Integer.valueOf(k)).executeUpdate();
    if (_$3.isDebugEnabled())
      _$3.debug("clear span hql1:{}, hql2:{}, nodeRgt:{}", new Object[] { str3, str4, Integer.valueOf(k) });
    localSession.setFlushMode(localFlushMode);
    return true;
  }

  public void onDelete(Object paramObject, Serializable paramSerializable, Object[] paramArrayOfObject, String[] paramArrayOfString, Type[] paramArrayOfType)
  {
    if ((paramObject instanceof HibernateTree))
    {
      HibernateTree localHibernateTree = (HibernateTree)paramObject;
      String str1 = localHibernateTree.getClass().getName();
      Session localSession = getSession();
      FlushMode localFlushMode = localSession.getFlushMode();
      localSession.setFlushMode(FlushMode.MANUAL);
      String str2 = "select bean." + localHibernateTree.getLftName() + " from " + str1 + " bean where bean.id=:id";
      Integer localInteger = Integer.valueOf(((Number)localSession.createQuery(str2).setParameter("id", localHibernateTree.getId()).uniqueResult()).intValue());
      String str3 = "update " + str1 + " bean set bean." + localHibernateTree.getRgtName() + " = bean." + localHibernateTree.getRgtName() + " - 2 WHERE bean." + localHibernateTree.getRgtName() + " > :myPosition";
      String str4 = "update " + str1 + " bean set bean." + localHibernateTree.getLftName() + " = bean." + localHibernateTree.getLftName() + " - 2 WHERE bean." + localHibernateTree.getLftName() + " > :myPosition";
      if (!StringUtils.isBlank(localHibernateTree.getTreeCondition()))
      {
        str3 = str3 + " and (" + localHibernateTree.getTreeCondition() + ")";
        str4 = str4 + " and (" + localHibernateTree.getTreeCondition() + ")";
      }
      localSession.createQuery(str3).setInteger("myPosition", localInteger.intValue()).executeUpdate();
      localSession.createQuery(str4).setInteger("myPosition", localInteger.intValue()).executeUpdate();
      localSession.setFlushMode(localFlushMode);
    }
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.hibernate3.TreeIntercptor
 * JD-Core Version:    0.6.2
 */