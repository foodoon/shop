package guda.shop.cms.action.admin;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.manager.OrderMng;
import guda.shop.cms.manager.UpdateMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.AdminThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WelcomeAct {

    @Autowired
    private OrderMng manager;

    @Autowired
    private UpdateMng updateMng;

    @RequestMapping({"/main.do"})
    public String main() {

        return "main";
    }

    @RequestMapping({"/left.do"})
    public String left() {

        return "left";
    }

    @RequestMapping({"/right.do"})
    public String right(HttpServletRequest request, ModelMap model) {

        List o = this.manager.getTotlaOrder();

        ShopAdmin admin = AdminThread.get();

        Long[] c = (Long[]) o.get(0);

        Runtime runtime = Runtime.getRuntime();

        long freeMemoery = runtime.freeMemory();

        long totalMemory = runtime.totalMemory();

        long usedMemory = totalMemory - freeMemoery;

        long maxMemory = runtime.maxMemory();

        long useableMemory = maxMemory - totalMemory + freeMemoery;

        model.addAttribute("c", c);

        model.addAttribute("admin", admin);

        model.addAttribute("restart", Integer.valueOf(Integer.parseInt(this.updateMng.getRestart())));

        model.addAttribute("site", SiteUtils.getWeb(request));

        model.addAttribute("freeMemoery", Long.valueOf(freeMemoery));

        model.addAttribute("totalMemory", Long.valueOf(totalMemory));

        model.addAttribute("usedMemory", Long.valueOf(usedMemory));

        model.addAttribute("maxMemory", Long.valueOf(maxMemory));

        model.addAttribute("useableMemory", Long.valueOf(useableMemory));

        return "right";
    }

    @RequestMapping({"/top.do"})
    public String top(HttpServletRequest request, ModelMap model) {

        ShopAdmin admin = AdminThread.get();

        model.addAttribute("admin", admin);

        return "top";
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.WelcomeAct
 * JD-Core Version:    0.6.2
 */