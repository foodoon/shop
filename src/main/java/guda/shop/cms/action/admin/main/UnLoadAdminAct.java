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
    private static final Logger log = LoggerFactory.getLogger(UnLoadAdminAct.class);

    @RequestMapping(value = {"/commonAdmin/list.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String unLoad(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Map adminMap = AdminMap.adminmap;

        model.addAttribute("map1", adminMap);

        Set<String> keySet = adminMap.keySet();

        for (String username : keySet) {

            ((Integer) adminMap.get(username)).intValue();
        }


        return "admin/uplocklist";
    }

    @RequestMapping(value = {"/commonAdmin/unlock.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String unlock(HttpServletResponse response, String username) {

        AdminMap.unLoadAdmin(username);

        ResponseUtils.renderJsonString(response, "解锁成功 !");

        return null;
    }
}

