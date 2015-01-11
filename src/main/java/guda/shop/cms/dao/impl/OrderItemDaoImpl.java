package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.OrderItemDao;
import guda.shop.cms.entity.OrderItem;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class OrderItemDaoImpl extends HibernateBaseDao<OrderItem, Long>
        implements OrderItemDao {
    public static String getHQL(Long paramLong1, Long paramLong2) {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("select bean.product.id, sum(bean.count), sum((bean.finalPrice-bean.costPrice)*bean.count) ");
        localStringBuffer.append(" from OrderItem bean where 1=1 ");
        if (paramLong1 != null)
            localStringBuffer.append(" and bean.product.category.id=:ctgid ");
        if (paramLong2 != null)
            localStringBuffer.append(" and bean.product.type.id=:typeid ");
        localStringBuffer.append(" group by bean.product.id order by sum((bean.finalPrice-bean.costPrice)*bean.count) desc");
        return localStringBuffer.toString();
    }

    public static String getCountHQL(Long paramLong1, Long paramLong2) {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("select count(DISTINCT bean.product.id) ");
        localStringBuffer.append(" from OrderItem bean where 1=1 ");
        if (paramLong1 != null)
            localStringBuffer.append(" and bean.product.category.id=:ctgid ");
        if (paramLong2 != null)
            localStringBuffer.append(" and bean.product.type.id=:typeid ");
        return localStringBuffer.toString();
    }

    protected Class<OrderItem> getEntityClass() {
        return OrderItem.class;
    }

    public List<Object[]> profitTop(Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2) {
        Query localQuery = getSession().createQuery(getHQL(paramLong1, paramLong2));
        if (paramLong1 != null)
            localQuery.setLong("ctgid", paramLong1.longValue());
        if (paramLong2 != null)
            localQuery.setLong("typeid", paramLong2.longValue());
        Iterator localIterator = localQuery.setFirstResult((paramInteger1.intValue() - 1) * paramInteger2.intValue()).setMaxResults(paramInteger2.intValue()).iterate();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
            localArrayList.add((Object[]) localIterator.next());
        return localArrayList;
    }

    public Integer totalCount(Long paramLong1, Long paramLong2) {
        Integer localInteger = Integer.valueOf(0);
        Query localQuery = getSession().createQuery(getCountHQL(paramLong1, paramLong2));
        if (paramLong1 != null)
            localQuery.setLong("ctgid", paramLong1.longValue());
        if (paramLong2 != null)
            localQuery.setLong("typeid", paramLong2.longValue());
        Iterator localIterator = localQuery.iterate();
        if (localIterator.hasNext())
            localInteger = Integer.valueOf(Integer.parseInt(localIterator.next() + ""));
        return localInteger;
    }

    public Pagination getPageForMember(Long paramLong, Integer paramInteger, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("select bean from OrderItem bean");
        localFinder.append(" join bean.ordeR indent");
        localFinder.append(" where indent.member.id=:memberId and indent.status=:status");
        localFinder.setParam("memberId", paramLong).setParam("status", paramInteger);
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<Object[]> getOrderItem() {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(new Date());
        int i = localCalendar.get(7);
        localCalendar.add(5, -i);
        Date localDate1 = localCalendar.getTime();
        String str1 = localSimpleDateFormat.format(localDate1);
        localCalendar.add(5, -7);
        Date localDate2 = localCalendar.getTime();
        String str2 = localSimpleDateFormat.format(localDate2);
        String str3 = "select bean,sum(bean.count) from OrderItem bean where bean.ordeR.createTime<='" + str1 + "' and bean.ordeR.createTime>'" + str2 + "' group by bean.product.id order by sum(bean.count) desc";
        List localList = getSession().createQuery(str3).list();
        return localList;
    }

    public OrderItem findById(Long paramLong) {
        OrderItem localOrderItem = (OrderItem) get(paramLong);
        return localOrderItem;
    }

    public OrderItem findByMember(Long paramLong1, Long paramLong2) {
        String str = "from OrderItem bean where bean.product.id=:productId and bean.ordeR.member.id=:memberId";
        Iterator localIterator = getSession().createQuery(str).setParameter("memberId", paramLong1).setParameter("productId", paramLong2).iterate();
        if (localIterator.hasNext())
            return (OrderItem) localIterator.next();
        return null;
    }

    public List<OrderItem> getOrderItem(Long paramLong) {
        String str = "from OrderItem bean where bean.product.id=:productId";
        List localList = getSession().createQuery(str).setParameter("productId", paramLong).list();
        return localList;
    }

    public List<OrderItem> getOrderItemList(Long paramLong, Integer paramInteger1, Integer paramInteger2) {
        String str = "from OrderItem bean where bean.product.id=:productId";
        List localList = getSession().createQuery(str).setParameter("productId", paramLong).setFirstResult((paramInteger1.intValue() - 1) * paramInteger2.intValue()).setMaxResults(paramInteger2.intValue()).list();
        return localList;
    }
}

