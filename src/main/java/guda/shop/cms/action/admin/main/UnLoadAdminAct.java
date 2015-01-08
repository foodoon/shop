package guda.shop.cms.action.admin.main;

import guda.shop.cms.AdminMap;
import guda.shop.common.web.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Controller
public class UnLoadAdminAct {
    /* 24 */   private static final Logger log = LoggerFactory.getLogger(UnLoadAdminAct.class);

    @RequestMapping(value = {"/commonAdmin/v_list.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String unLoad(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 28 */
        Map adminMap = AdminMap.adminmap;
/* 29 */
        model.addAttribute("map1", adminMap);
/* 30 */
        Set keySet = adminMap.keySet();
/* 31 */
        for (String username : keySet) {
/* 32 */
            ((Integer) adminMap.get(username)).intValue();
        }

/* 36 */
        return "admin/uplocklist";
    }

    @RequestMapping(value = {"/commonAdmin/v_unlock.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String unlock(HttpServletResponse response, String username) {
/* 41 */
        AdminMap.unLoadAdmin(username);
/* 42 */
        ResponseUtils.renderJson(response, "解锁成功 !");
/* 43 */
        return null;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.UnLoadAdminAct
 * JD-Core Version:    0.6.2
 */