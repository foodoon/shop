package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.OrderReturnDao;
import guda.shop.cms.entity.Order;
import guda.shop.cms.entity.OrderReturn;
import guda.shop.cms.manager.OrderReturnMng;
import guda.shop.cms.manager.ShopDictionaryMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderReturnMngImpl
        implements OrderReturnMng {
    private OrderReturnDao _$2;

    @Autowired
    private ShopDictionaryMng _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2) {
        Pagination localPagination = this._$2.getPage(paramInteger, paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public OrderReturn findById(Long paramLong) {
        OrderReturn localOrderReturn = this._$2.findById(paramLong);
        return localOrderReturn;
    }

    public OrderReturn findByOrderId(Long paramLong) {
        List localList = this._$2.findByOrderId(paramLong);
        if (localList.size() > 0)
            return (OrderReturn) localList.get(0);
        return null;
    }

    public OrderReturn save(OrderReturn paramOrderReturn) {
        this._$2.save(paramOrderReturn);
        return paramOrderReturn;
    }

    public OrderReturn save(OrderReturn paramOrderReturn, Order paramOrder, Long paramLong, Boolean paramBoolean, String[] paramArrayOfString1, String[] paramArrayOfString2) {
        paramOrderReturn.setOrder(paramOrder);
        paramOrderReturn.setShopDictionary(this._$1.findById(paramLong));
        if (paramBoolean.booleanValue())
            paramOrderReturn.setReturnType(Integer.valueOf(OrderReturn.OrderReturnStatus.SELLER_NODELIVERY_RETURN.getValue()));
        else
            paramOrderReturn.setReturnType(Integer.valueOf(OrderReturn.OrderReturnStatus.SELLER_DELIVERY_RETURN.getValue()));
        Long localLong = Long.valueOf(new Date().getTime() + paramOrder.getId().longValue());
        paramOrderReturn.setCode(String.valueOf(localLong));
        paramOrderReturn.setStatus(Integer.valueOf(1));
        paramOrderReturn.setCreateTime(new Date());
        if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0)) {
            int i = 0;
            int j = paramArrayOfString1.length;
            while (i < j) {
                if (!StringUtils.isBlank(paramArrayOfString1[i]))
                    paramOrderReturn.addToPictures(paramArrayOfString1[i], paramArrayOfString2[i]);
                i++;
            }
        }
        paramOrderReturn = this._$2.save(paramOrderReturn);
        return paramOrderReturn;
    }

    public OrderReturn update(OrderReturn paramOrderReturn) {
        Updater localUpdater = new Updater(paramOrderReturn);
        OrderReturn localOrderReturn = this._$2.updateByUpdater(localUpdater);
        return localOrderReturn;
    }

    public OrderReturn deleteById(Long paramLong) {
        OrderReturn localOrderReturn = this._$2.deleteById(paramLong);
        return localOrderReturn;
    }

    public OrderReturn[] deleteByIds(Long[] paramArrayOfLong) {
        OrderReturn[] arrayOfOrderReturn = new OrderReturn[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfOrderReturn[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfOrderReturn;
    }

    @Autowired
    public void setDao(OrderReturnDao paramOrderReturnDao) {
        this._$2 = paramOrderReturnDao;
    }
}

