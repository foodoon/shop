package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCustomerService;

public class CustomerService extends BaseCustomerService {
    private static final long serialVersionUID = 1L;

    public CustomerService() {
    }

    public CustomerService(Long paramLong) {
        super(paramLong);
    }

    public CustomerService(Long paramLong, String paramString1, String paramString2, Integer paramInteger, Boolean paramBoolean) {
        super(paramLong, paramString1, paramString2, paramInteger, paramBoolean);
    }
}

