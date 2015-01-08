package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseOrder;
import guda.shop.core.entity.Website;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order extends BaseOrder {
    private static final long serialVersionUID = 1L;

    public Order() {
    }

    public Order(Long paramLong) {
        super(paramLong);
    }

    public Order(Long paramLong1, Website paramWebsite, ShopMember paramShopMember, Payment paramPayment, Shipping paramShipping1, Shipping paramShipping2, Long paramLong2, String paramString, Date paramDate1, Date paramDate2, Double paramDouble1, Integer paramInteger1, Double paramDouble2, Integer paramInteger2) {
        super(paramLong1, paramWebsite, paramShopMember, paramPayment, paramShipping1, paramShipping2, paramLong2.longValue(), paramString, paramDate1, paramDate2, paramDouble1, paramInteger1, paramDouble2);
    }

    public int calWeight() {
        int i = 0;
        Iterator localIterator = getItems().iterator();
        while (localIterator.hasNext()) {
            OrderItem localOrderItem = (OrderItem) localIterator.next();
            i += localOrderItem.getProduct().getWeight().intValue();
        }
        return i;
    }

    public Double getWeightForFreight() {
        Double localDouble = Double.valueOf(0.0D);
        Iterator localIterator = getItems().iterator();
        while (localIterator.hasNext()) {
            OrderItem localOrderItem = (OrderItem) localIterator.next();
            localDouble = Double.valueOf(localDouble.doubleValue() + localOrderItem.getWeightForFreight());
        }
        return localDouble;
    }

    public int getCountForFreight() {
        int i = 0;
        Iterator localIterator = getItems().iterator();
        while (localIterator.hasNext()) {
            OrderItem localOrderItem = (OrderItem) localIterator.next();
            i += localOrderItem.getCountForFreigt();
        }
        return i;
    }

    public void addToItems(OrderItem paramOrderItem) {
        Object localObject = getItems();
        if (localObject == null) {
            localObject = new LinkedHashSet();
            setItems((Set) localObject);
        }
        paramOrderItem.setOrdeR(this);
        ((Set) localObject).add(paramOrderItem);
    }

    public void init() {
        Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
        if (getCreateTime() == null)
            setCreateTime(localTimestamp);
        if (getLastModified() == null)
            setLastModified(localTimestamp);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Order
 * JD-Core Version:    0.6.2
 */