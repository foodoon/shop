package guda.shop.cms.dao;

import guda.shop.cms.entity.Order;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract interface OrderDao {
    public abstract Pagination getPageForMember(Long paramLong, int paramInt1, int paramInt2);

    public abstract Pagination getPageForMember1(Long paramLong, int paramInt1, int paramInt2);

    public abstract Pagination getPageForOrderReturn(Long paramLong, int paramInt1, int paramInt2);

    public abstract Pagination getPageForMember2(Long paramLong, int paramInt1, int paramInt2);

    public abstract Pagination getPageForMember3(Long paramLong, int paramInt1, int paramInt2);

    public abstract Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Long paramLong5, int paramInt1, int paramInt2);

    public abstract Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger, Long paramLong5, int paramInt1, int paramInt2);

    public abstract Order findById(Long paramLong);

    public abstract Order save(Order paramOrder);

    public abstract Order updateByUpdater(Updater<Order> paramUpdater);

    public abstract Order deleteById(Long paramLong);

    public abstract List<Object> getTotlaOrder();

    public abstract List<Order> getlistByforaddressId(Long paramLong);

    public abstract List<Order> getlist(Date paramDate);

    public abstract BigDecimal getMemberMoneyByYear(Long paramLong);

    public abstract Integer[] getOrderByMember(Long paramLong);

    public abstract Pagination getOrderByReturn(Long paramLong, Integer paramInteger1, Integer paramInteger2);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.OrderDao
 * JD-Core Version:    0.6.2
 */