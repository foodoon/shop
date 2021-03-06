package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.OrderItemDao;
import guda.shop.cms.entity.OrderItem;
import guda.shop.cms.manager.OrderItemMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemMngImpl
        implements OrderItemMng {

    @Autowired
    private OrderItemDao _$1;

    public List<Object[]> profitTop(Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2) {
        return this._$1.profitTop(paramLong1, paramLong2, paramInteger1, paramInteger2);
    }

    public Integer totalCount(Long paramLong1, Long paramLong2) {
        return this._$1.totalCount(paramLong1, paramLong2);
    }

    public OrderItem findById(Long paramLong) {
        return this._$1.findById(paramLong);
    }

    public OrderItem updateByUpdater(OrderItem paramOrderItem) {
        Updater localUpdater = new Updater(paramOrderItem);
        return this._$1.updateByUpdater(localUpdater);
    }

    public Pagination getPageByMember(Integer paramInteger, Long paramLong, int paramInt1, int paramInt2) {
        return this._$1.getPageForMember(paramLong, paramInteger, paramInt1, paramInt2);
    }

    public List<Object[]> getOrderItem() {
        List localList = this._$1.getOrderItem();
        return localList;
    }

    public OrderItem findByMember(Long paramLong1, Long paramLong2) {
        return this._$1.findByMember(paramLong1, paramLong2);
    }

    public Pagination getOrderItem(Integer paramInteger1, Integer paramInteger2, Long paramLong) {
        List localList1 = this._$1.getOrderItemList(paramLong, paramInteger1, paramInteger2);
        List localList2 = this._$1.getOrderItem(paramLong);
        int i = localList2.size();
        Pagination localPagination = new Pagination(paramInteger1.intValue(), paramInteger2.intValue(), i, localList1);
        return localPagination;
    }
}

