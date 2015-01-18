package guda.shop.cms.action.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by foodoon on 2015/1/18.
 */
@Controller
public class MarketingAct {

    @RequestMapping({"/marketing/v_list.do"})
    public String marketingLeft(ModelMap model) {

        return "marketing/list";
    }
}
