package guda.shop.cms.manager;

import guda.shop.cms.entity.CustomerService;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface CustomerServiceMng {
    public abstract Pagination getPagination(Boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract List<CustomerService> getList();

    public abstract CustomerService findById(Long paramLong);

    public abstract CustomerService update(CustomerService paramCustomerService);

    public abstract CustomerService[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);

    public abstract CustomerService[] deleteByIds(Long[] paramArrayOfLong);

    public abstract CustomerService save(CustomerService paramCustomerService);
}

