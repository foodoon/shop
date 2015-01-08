package guda.shop.cms.action.admin;

import guda.shop.cms.manager.OrderMng;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsJob {

    @Autowired
    private OrderMng manager;

    public void execute() {
/* 12 */
        this.manager.abolishOrder();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.SmsJob
 * JD-Core Version:    0.6.2
 */