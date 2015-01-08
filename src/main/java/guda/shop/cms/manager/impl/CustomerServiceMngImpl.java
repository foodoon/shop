package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.CustomerServiceDao;
import guda.shop.cms.entity.CustomerService;
import guda.shop.cms.manager.CustomerServiceMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceMngImpl
        implements CustomerServiceMng {

    @Autowired
    private CustomerServiceDao _$1;

    public CustomerService findById(Long paramLong) {
        return this._$1.findById(paramLong);
    }

    public Pagination getPagination(Boolean paramBoolean, int paramInt1, int paramInt2) {
        return this._$1.getPagination(paramBoolean, paramInt1, paramInt2);
    }

    public List<CustomerService> getList() {
        return this._$1.getList(Boolean.valueOf(false));
    }

    public CustomerService update(CustomerService paramCustomerService) {
        Updater localUpdater = new Updater(paramCustomerService);
        CustomerService localCustomerService = this._$1.updateByUpdater(localUpdater);
        return localCustomerService;
    }

    public CustomerService save(CustomerService paramCustomerService) {
        return this._$1.save(paramCustomerService);
    }

    public CustomerService[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        CustomerService[] arrayOfCustomerService = new CustomerService[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCustomerService[i] = findById(paramArrayOfLong[i]);
            arrayOfCustomerService[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfCustomerService;
    }

    public CustomerService[] deleteByIds(Long[] paramArrayOfLong) {
        CustomerService[] arrayOfCustomerService = new CustomerService[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCustomerService[i] = this._$1.deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfCustomerService;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.CustomerServiceMngImpl
 * JD-Core Version:    0.6.2
 */