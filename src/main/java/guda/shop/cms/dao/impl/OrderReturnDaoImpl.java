package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.OrderReturnDao;
import guda.shop.cms.entity.OrderReturn;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderReturnDaoImpl extends HibernateBaseDao<OrderReturn, Long>
        implements OrderReturnDao {
    public Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from OrderReturn bean where 1=1");
        if (paramInteger != null) {
            localFinder.append(" and bean.status=:status");
            localFinder.setParam("status", paramInteger);
        }
        return find(localFinder, paramInt1, paramInt2);
    }

    public OrderReturn findById(Long paramLong) {
        OrderReturn localOrderReturn = (OrderReturn) get(paramLong);
        return localOrderReturn;
    }

    public List<OrderReturn> findByOrderId(Long paramLong) {
        Finder localFinder = Finder.create("from OrderReturn bean where bean.order.id=:orderId");
        localFinder.setParam("orderId", paramLong);
        return find(localFinder);
    }

    public OrderReturn save(OrderReturn paramOrderReturn) {
        getSession().save(paramOrderReturn);
        return paramOrderReturn;
    }

    public OrderReturn deleteById(Long paramLong) {
        OrderReturn localOrderReturn = (OrderReturn) super.get(paramLong);
        if (localOrderReturn != null)
            getSession().delete(localOrderReturn);
        return localOrderReturn;
    }

    protected Class<OrderReturn> getEntityClass() {
        return OrderReturn.class;
    }
}

