package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.OrderDao;
import guda.shop.cms.entity.Order;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends HibernateBaseDao<Order, Long>
  implements OrderDao
{
  public static final Integer CHECKING = Integer.valueOf(1);
  public static final Integer CHECKED = Integer.valueOf(2);

  public Pagination getPageForMember(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.member.id=:memberId");
    localFinder.setParam("memberId", paramLong);
    localFinder.append(" and bean.status=40");
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Pagination getPageForOrderReturn(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.returnOrder.id is not null");
    if (paramLong != null)
    {
      localFinder.append(" and bean.member.id=:memberId");
      localFinder.setParam("memberId", paramLong);
    }
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Pagination getPageForMember1(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.member.id=:memberId");
    localFinder.setParam("memberId", paramLong);
    localFinder.append(" and bean.status>=10 and bean.status<=19");
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Pagination getPageForMember2(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.member.id=:memberId");
    localFinder.setParam("memberId", paramLong);
    localFinder.append(" and bean.status>=20 and bean.status<=29");
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public List<Order> getlist(Date paramDate)
  {
    Finder localFinder = Finder.create("from Order bean where bean.payment.type=1");
    localFinder.append(" and bean.paymentStatus=:paymentStatus");
    localFinder.append(" and bean.createTime<:endTime");
    localFinder.append(" and (bean.status=:checking or bean.status=:checked)");
    localFinder.setParam("checking", CHECKING);
    localFinder.setParam("checked", CHECKED);
    localFinder.setParam("endTime", paramDate);
    localFinder.setParam("paymentStatus", CHECKING);
    return find(localFinder);
  }

  public List<Order> getlistByforaddressId(Long paramLong)
  {
    Finder localFinder = Finder.create("from Order bean where bean.shopMemberAddress.id=:addressId");
    localFinder.setParam("addressId", paramLong);
    return find(localFinder);
  }

  public Pagination getPageForMember3(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.member.id=:memberId");
    localFinder.setParam("memberId", paramLong);
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Long paramLong5, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where 1=1 ");
    if (paramLong1 != null)
    {
      localFinder.append(" and bean.website.id=:webId");
      localFinder.setParam("webId", paramLong1);
    }
    if (paramLong2 != null)
    {
      localFinder.append(" and bean.member.id=:memberId");
      localFinder.setParam("memberId", paramLong2);
    }
    if (!StringUtils.isBlank(paramString2))
    {
      localFinder.append(" and bean.shopMemberAddress.username like:userName");
      localFinder.setParam("userName", "%" + paramString2 + "%");
    }
    if (!StringUtils.isBlank(paramString1))
    {
      localFinder.append(" and bean.productName like:productName");
      localFinder.setParam("productName", "%" + paramString1 + "%");
    }
    if (paramLong3 != null)
    {
      localFinder.append(" and bean.payment.id=:paymentId");
      localFinder.setParam("paymentId", paramLong3);
    }
    if (paramLong4 != null)
    {
      localFinder.append(" and bean.shipping.id=:shippingId");
      localFinder.setParam("shippingId", paramLong4);
    }
    if (paramDate1 != null)
    {
      localFinder.append(" and bean.createTime>:startTime");
      localFinder.setParam("startTime", paramDate1);
    }
    if (paramDate2 != null)
    {
      localFinder.append(" and bean.createTime<:endTime");
      localFinder.setParam("endTime", paramDate2);
    }
    if (paramDouble1 != null)
    {
      localFinder.append(" and bean.total>=:startOrderTotal");
      localFinder.setParam("startOrderTotal", paramDouble1);
    }
    if (paramDouble2 != null)
    {
      localFinder.append(" and bean.total<=:endOrderTotal");
      localFinder.setParam("endOrderTotal", paramDouble2);
    }
    if (paramInteger1 != null)
      if (paramInteger1.intValue() == 5)
      {
        localFinder.append(" and (bean.status=:checking or bean.status=:checked)");
        localFinder.append(" and bean.paymentStatus=:payment");
        localFinder.setParam("checking", CHECKING);
        localFinder.setParam("checked", CHECKED);
        localFinder.setParam("payment", CHECKING);
      }
      else if (paramInteger1.intValue() == 6)
      {
        localFinder.append(" and ((bean.payment.type=1 and bean.paymentStatus=:payment)or(bean.payment.type=2))");
        localFinder.append(" and bean.status=:checked");
        localFinder.append(" and bean.shippingStatus=:shipping");
        localFinder.setParam("checked", CHECKED);
        localFinder.setParam("shipping", CHECKING);
        localFinder.setParam("payment", CHECKING);
      }
      else
      {
        localFinder.append(" and bean.status=:status");
        localFinder.setParam("status", paramInteger1);
      }
    if (paramInteger2 != null)
    {
      localFinder.append(" and bean.paymentStatus=:paymentStatus");
      localFinder.setParam("paymentStatus", paramInteger2);
    }
    if (paramInteger3 != null)
    {
      localFinder.append(" and bean.shippingStatus=:shippingStatus");
      localFinder.setParam("shippingStatus", paramInteger3);
    }
    if (paramLong5 != null)
    {
      localFinder.append(" and bean.code=:code");
      localFinder.setParam("code", paramLong5);
    }
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger, Long paramLong5, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Order bean where 1=1 ");
    if (paramLong1 != null)
    {
      localFinder.append(" and bean.website.id=:webId");
      localFinder.setParam("webId", paramLong1);
    }
    if (paramLong2 != null)
    {
      localFinder.append(" and bean.member.id=:memberId");
      localFinder.setParam("memberId", paramLong2);
    }
    if (!StringUtils.isBlank(paramString2))
    {
      localFinder.append(" and bean.shopMemberAddress.username like:userName");
      localFinder.setParam("userName", "%" + paramString2 + "%");
    }
    if (!StringUtils.isBlank(paramString1))
    {
      localFinder.append(" and bean.productName like:productName");
      localFinder.setParam("productName", "%" + paramString1 + "%");
    }
    if (paramLong3 != null)
    {
      localFinder.append(" and bean.payment.id=:paymentId");
      localFinder.setParam("paymentId", paramLong3);
    }
    if (paramLong4 != null)
    {
      localFinder.append(" and bean.shipping.id=:shippingId");
      localFinder.setParam("shippingId", paramLong4);
    }
    if (paramDate1 != null)
    {
      localFinder.append(" and bean.createTime>:startTime");
      localFinder.setParam("startTime", paramDate1);
    }
    if (paramDate2 != null)
    {
      localFinder.append(" and bean.createTime<:endTime");
      localFinder.setParam("endTime", paramDate2);
    }
    if (paramDouble1 != null)
    {
      localFinder.append(" and bean.total>=:startOrderTotal");
      localFinder.setParam("startOrderTotal", paramDouble1);
    }
    if (paramDouble2 != null)
    {
      localFinder.append(" and bean.total<=:endOrderTotal");
      localFinder.setParam("endOrderTotal", paramDouble2);
    }
    if (paramInteger != null)
      if (paramInteger.intValue() == 5)
      {
        localFinder.append(" and (bean.status=:checking or bean.status=:checked)");
        localFinder.append(" and bean.paymentStatus=:payment");
        localFinder.setParam("checking", CHECKING);
        localFinder.setParam("checked", CHECKED);
        localFinder.setParam("payment", CHECKING);
      }
      else if (paramInteger.intValue() == 6)
      {
        localFinder.append(" and (bean.status=:checking or bean.status=:checked)");
        localFinder.append(" and bean.shippingStatus=:shipping");
        localFinder.setParam("checking", CHECKING);
        localFinder.setParam("checked", CHECKED);
        localFinder.setParam("shipping", CHECKED);
      }
      else
      {
        localFinder.append(" and bean.status=:status");
        localFinder.setParam("status", paramInteger);
      }
    if (paramLong5 != null)
    {
      localFinder.append(" and bean.code=:code");
      localFinder.setParam("code", paramLong5);
    }
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public List<Object> getTotlaOrder()
  {
    ArrayList localArrayList = new ArrayList();
    Long localLong1 = (Long)getSession().createQuery("select count(*) from Order bean").uniqueResult();
    Long localLong2 = (Long)getSession().createQuery("select count(*) from Order bean where bean.status between 1 and 2").uniqueResult();
    Calendar localCalendar = Calendar.getInstance();
    String str1 = localCalendar.get(2) + 1 + "";
    String str2 = localCalendar.get(1) + "";
    if (str1.length() == 1)
      str1 = "0" + str1;
    else
      str1 = str1;
    String str3 = str2 + "-" + str1;
    Long localLong3 = (Long)getSession().createQuery("select count(*) from Order bean where bean.createTime like :time").setString("time", "%" + str3 + "%").uniqueResult();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String str4 = localSimpleDateFormat.format(new Date());
    Long localLong4 = (Long)getSession().createQuery("select count(*) from Order bean where bean.createTime like :tody").setString("tody", "%" + str4 + "%").uniqueResult();
    Long localLong5 = (Long)getSession().createQuery("select count(*) from Product bean").uniqueResult();
    Long localLong6 = (Long)getSession().createQuery("select count(*) from Product bean where bean.createTime like :time").setString("time", "%" + str3 + "%").uniqueResult();
    Long localLong7 = (Long)getSession().createQuery("select count(*) from Product bean where bean.createTime like :time").setString("time", "%" + str4 + "%").uniqueResult();
    Long localLong8 = (Long)getSession().createQuery("select count(*) from Product bean where bean.onSale=true").uniqueResult();
    Long localLong9 = (Long)getSession().createQuery("select count(*) from ShopMember bean").uniqueResult();
    Long localLong10 = (Long)getSession().createQuery("select count(*) from Member bean where bean.createTime like :time").setString("time", "%" + str3 + "%").uniqueResult();
    Long localLong11 = (Long)getSession().createQuery("select count(*) from Member bean where bean.createTime like :time").setString("time", "%" + str4 + "%").uniqueResult();
    localCalendar.add(5, -7);
    Date localDate = localCalendar.getTime();
    Long localLong12 = (Long)getSession().createQuery("select count(*) from Member bean where bean.createTime >:time").setParameter("time", localDate).uniqueResult();
    Long[] arrayOfLong = new Long[12];
    arrayOfLong[0] = Long.valueOf(localLong1 == null ? 0L : localLong1.longValue());
    arrayOfLong[1] = Long.valueOf(localLong2 == null ? 0L : localLong2.longValue());
    arrayOfLong[2] = Long.valueOf(localLong3 == null ? 0L : localLong3.longValue());
    arrayOfLong[3] = Long.valueOf(localLong4 == null ? 0L : localLong4.longValue());
    arrayOfLong[4] = Long.valueOf(localLong5 == null ? 0L : localLong5.longValue());
    arrayOfLong[5] = Long.valueOf(localLong6 == null ? 0L : localLong6.longValue());
    arrayOfLong[6] = Long.valueOf(localLong7 == null ? 0L : localLong7.longValue());
    arrayOfLong[7] = Long.valueOf(localLong9 == null ? 0L : localLong9.longValue());
    arrayOfLong[8] = Long.valueOf(localLong10 == null ? 0L : localLong10.longValue());
    arrayOfLong[9] = Long.valueOf(localLong11 == null ? 0L : localLong11.longValue());
    arrayOfLong[10] = Long.valueOf(localLong12 == null ? 0L : localLong12.longValue());
    arrayOfLong[11] = Long.valueOf(localLong8 == null ? 0L : localLong8.longValue());
    localArrayList.add(arrayOfLong);
    return localArrayList;
  }

  public BigDecimal getMemberMoneyByYear(Long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    String str = "" + localCalendar.get(1);
    Query localQuery = getSession().createQuery("select sum((bean.salePrice)* bean.count) from OrderItem bean where bean.ordeR.member.id=:id and bean.ordeR.createTime like:time and bean.ordeR.status=4");
    localQuery.setParameter("id", paramLong).setString("time", "%" + str + "%");
    Double localDouble = (Double)localQuery.uniqueResult();
    if (localDouble == null)
      localDouble = Double.valueOf(0.0D);
    return new BigDecimal(localDouble.doubleValue());
  }

  public Integer[] getOrderByMember(Long paramLong)
  {
    Long localLong1 = (Long)getSession().createQuery("select count(*) from Order bean where bean.member.id=:id").setParameter("id", paramLong).uniqueResult();
    Long localLong2 = (Long)getSession().createQuery("select count(*) from Order bean where bean.member.id=:id").setParameter("id", paramLong).uniqueResult();
    Long localLong3 = (Long)getSession().createQuery("select count(*) from Order bean where bean.member.id=:id").setParameter("id", paramLong).uniqueResult();
    Long localLong4 = (Long)getSession().createQuery("select count(*) from Order bean where bean.member.id=:id").setParameter("id", paramLong).uniqueResult();
    Long localLong5 = (Long)getSession().createQuery("select count(*) from Order bean where bean.member.id=:id").setParameter("id", paramLong).uniqueResult();
    Integer[] arrayOfInteger = new Integer[5];
    arrayOfInteger[0] = Integer.valueOf(localLong1.intValue());
    arrayOfInteger[1] = Integer.valueOf(localLong2.intValue());
    arrayOfInteger[2] = Integer.valueOf(localLong3.intValue());
    arrayOfInteger[3] = Integer.valueOf(localLong4.intValue());
    arrayOfInteger[4] = Integer.valueOf(localLong5.intValue());
    return arrayOfInteger;
  }

  public Pagination getOrderByReturn(Long paramLong, Integer paramInteger1, Integer paramInteger2)
  {
    Finder localFinder = Finder.create("from Order bean where bean.member.id=:id and bean.status=41");
    localFinder.setParam("id", paramLong);
    return find(localFinder, paramInteger1.intValue(), paramInteger2.intValue());
  }

  public Order findById(Long paramLong)
  {
    Order localOrder = (Order)get(paramLong);
    return localOrder;
  }

  public Order save(Order paramOrder)
  {
    getSession().save(paramOrder);
    return paramOrder;
  }

  public Order deleteById(Long paramLong)
  {
    Order localOrder = (Order)super.get(paramLong);
    if (localOrder != null)
      getSession().delete(localOrder);
    return localOrder;
  }

  protected Class<Order> getEntityClass()
  {
    return Order.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.OrderDaoImpl
 * JD-Core Version:    0.6.2
 */