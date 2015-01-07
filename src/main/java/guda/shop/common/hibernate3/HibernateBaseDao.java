package guda.shop.common.hibernate3;

import guda.shop.common.page.Pagination;
import com.jspgou.common.util.MyBeanUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class HibernateBaseDao<T, ID extends Serializable>
{
  protected Logger log = LoggerFactory.getLogger(getClass());
  protected static final String ORDER_ENTRIES = "orderEntries";
  protected SessionFactory sessionFactory;

  protected T get(ID paramID)
  {
    return get(paramID, false);
  }

  protected T get(ID paramID, boolean paramBoolean)
  {
    Object localObject;
    if (paramBoolean)
      localObject = getSession().get(getEntityClass(), paramID, LockMode.UPGRADE);
    else
      localObject = getSession().get(getEntityClass(), paramID);
    return localObject;
  }

  protected List<T> findByProperty(String paramString, Object paramObject)
  {
    Assert.hasText(paramString);
    return createCriteria(new Criterion[] { Restrictions.eq(paramString, paramObject) }).list();
  }

  protected T findUniqueByProperty(String paramString, Object paramObject)
  {
    Assert.hasText(paramString);
    Assert.notNull(paramObject);
    return createCriteria(new Criterion[] { Restrictions.eq(paramString, paramObject) }).uniqueResult();
  }

  protected int countByProperty(String paramString, Object paramObject)
  {
    Assert.hasText(paramString);
    Assert.notNull(paramObject);
    return ((Number)createCriteria(new Criterion[] { Restrictions.eq(paramString, paramObject) }).setProjection(Projections.rowCount()).uniqueResult()).intValue();
  }

  protected List findByCriteria(Criterion[] paramArrayOfCriterion)
  {
    return createCriteria(paramArrayOfCriterion).list();
  }

  public T updateByUpdater(Updater<T> paramUpdater)
  {
    ClassMetadata localClassMetadata = this.sessionFactory.getClassMetadata(getEntityClass());
    Object localObject1 = paramUpdater.getBean();
    Object localObject2 = getSession().get(getEntityClass(), localClassMetadata.getIdentifier(localObject1, EntityMode.POJO));
    _$1(paramUpdater, localObject2, localClassMetadata);
    return localObject2;
  }

  private void _$1(Updater<T> paramUpdater, T paramT, ClassMetadata paramClassMetadata)
  {
    String[] arrayOfString1 = paramClassMetadata.getPropertyNames();
    String str1 = paramClassMetadata.getIdentifierPropertyName();
    Object localObject1 = paramUpdater.getBean();
    for (String str2 : arrayOfString1)
      if (!str2.equals(str1))
        try
        {
          Object localObject2 = MyBeanUtils.getSimpleProperty(localObject1, str2);
          if (paramUpdater.isUpdate(str2, localObject2))
            paramClassMetadata.setPropertyValue(paramT, str2, localObject2, EntityMode.POJO);
        }
        catch (Exception localException)
        {
          throw new RuntimeException("copy property to persistent object failed: '" + str2 + "'", localException);
        }
  }

  protected Criteria createCriteria(Criterion[] paramArrayOfCriterion)
  {
    Criteria localCriteria = getSession().createCriteria(getEntityClass());
    for (Criterion localCriterion : paramArrayOfCriterion)
      localCriteria.add(localCriterion);
    return localCriteria;
  }

  protected abstract Class<T> getEntityClass();

  protected List find(String paramString, Object[] paramArrayOfObject)
  {
    return createQuery(paramString, paramArrayOfObject).list();
  }

  protected Object findUnique(String paramString, Object[] paramArrayOfObject)
  {
    return createQuery(paramString, paramArrayOfObject).uniqueResult();
  }

  protected Pagination find(Finder paramFinder, int paramInt1, int paramInt2)
  {
    int i = countQueryResult(paramFinder);
    Pagination localPagination = new Pagination(paramInt1, paramInt2, i);
    if (i < 1)
    {
      localPagination.setList(new ArrayList());
      return localPagination;
    }
    Query localQuery = getSession().createQuery(paramFinder.getOrigHql());
    paramFinder.setParamsToQuery(localQuery);
    localQuery.setFirstResult(localPagination.getFirstResult());
    localQuery.setMaxResults(localPagination.getPageSize());
    if (paramFinder.isCacheable())
      localQuery.setCacheable(true);
    List localList = localQuery.list();
    localPagination.setList(localList);
    return localPagination;
  }

  protected List find(Finder paramFinder)
  {
    Query localQuery = getSession().createQuery(paramFinder.getOrigHql());
    paramFinder.setParamsToQuery(localQuery);
    localQuery.setFirstResult(paramFinder.getFirstResult());
    if (paramFinder.getMaxResults() > 0)
      localQuery.setMaxResults(paramFinder.getMaxResults());
    if (paramFinder.isCacheable())
      localQuery.setCacheable(true);
    List localList = localQuery.list();
    return localList;
  }

  protected Query createQuery(String paramString, Object[] paramArrayOfObject)
  {
    Assert.hasText(paramString);
    Query localQuery = getSession().createQuery(paramString);
    if (paramArrayOfObject != null)
      for (int i = 0; i < paramArrayOfObject.length; i++)
        localQuery.setParameter(i, paramArrayOfObject[i]);
    return localQuery;
  }

  protected Pagination findByCriteria(Criteria paramCriteria, int paramInt1, int paramInt2)
  {
    CriteriaImpl localCriteriaImpl = (CriteriaImpl)paramCriteria;
    Projection localProjection = localCriteriaImpl.getProjection();
    ResultTransformer localResultTransformer = localCriteriaImpl.getResultTransformer();
    List localList;
    try
    {
      localList = (List)MyBeanUtils.getFieldValue(localCriteriaImpl, "orderEntries");
      MyBeanUtils.setFieldValue(localCriteriaImpl, "orderEntries", new ArrayList());
    }
    catch (Exception localException1)
    {
      throw new RuntimeException("cannot read/write 'orderEntries' from CriteriaImpl", localException1);
    }
    int i = ((Number)paramCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    Pagination localPagination = new Pagination(paramInt1, paramInt2, i);
    if (i < 1)
    {
      localPagination.setList(new ArrayList());
      return localPagination;
    }
    paramCriteria.setProjection(localProjection);
    if (localProjection == null)
      paramCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    if (localResultTransformer != null)
      paramCriteria.setResultTransformer(localResultTransformer);
    try
    {
      MyBeanUtils.setFieldValue(localCriteriaImpl, "orderEntries", localList);
    }
    catch (Exception localException2)
    {
      throw new RuntimeException("set 'orderEntries' to CriteriaImpl faild", localException2);
    }
    paramCriteria.setFirstResult(localPagination.getFirstResult());
    paramCriteria.setMaxResults(localPagination.getPageSize());
    localPagination.setList(paramCriteria.list());
    return localPagination;
  }

  protected int countQueryResult(Finder paramFinder)
  {
    Query localQuery = getSession().createQuery(paramFinder.getRowCountHql());
    paramFinder.setParamsToQuery(localQuery);
    if (paramFinder.isCacheable())
      localQuery.setCacheable(true);
    return ((Number)localQuery.iterate().next()).intValue();
  }

  @Autowired
  public void setSessionFactory(SessionFactory paramSessionFactory)
  {
    this.sessionFactory = paramSessionFactory;
  }

  protected Session getSession()
  {
    return this.sessionFactory.getCurrentSession();
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.hibernate3.HibernateBaseDao
 * JD-Core Version:    0.6.2
 */