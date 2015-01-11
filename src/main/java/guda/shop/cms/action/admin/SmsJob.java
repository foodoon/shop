package guda.shop.cms.action.admin;

import guda.shop.cms.manager.OrderMng;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsJob {

    @Autowired
    private OrderMng manager;

    public void execute() {

        this.manager.abolishOrder();
    }
}

