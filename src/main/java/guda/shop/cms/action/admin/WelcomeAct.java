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
/* 25 */
        return "main";
    }

    @RequestMapping({"/left.do"})
    public String left() {
/* 30 */
        return "left";
    }

    @RequestMapping({"/right.do"})
    public String right(HttpServletRequest request, ModelMap model) {
/* 35 */
        List o = this.manager.getTotlaOrder();
/* 36 */
        ShopAdmin admin = AdminThread.get();
/* 37 */
        Long[] c = (Long[]) o.get(0);
/* 38 */
        Runtime runtime = Runtime.getRuntime();
/* 39 */
        long freeMemoery = runtime.freeMemory();
/* 40 */
        long totalMemory = runtime.totalMemory();
/* 41 */
        long usedMemory = totalMemory - freeMemoery;
/* 42 */
        long maxMemory = runtime.maxMemory();
/* 43 */
        long useableMemory = maxMemory - totalMemory + freeMemoery;
/* 44 */
        model.addAttribute("c", c);
/* 45 */
        model.addAttribute("admin", admin);
/* 46 */
        model.addAttribute("restart", Integer.valueOf(Integer.parseInt(this.updateMng.getRestart())));
/* 47 */
        model.addAttribute("site", SiteUtils.getWeb(request));
/* 48 */
        model.addAttribute("freeMemoery", Long.valueOf(freeMemoery));
/* 49 */
        model.addAttribute("totalMemory", Long.valueOf(totalMemory));
/* 50 */
        model.addAttribute("usedMemory", Long.valueOf(usedMemory));
/* 51 */
        model.addAttribute("maxMemory", Long.valueOf(maxMemory));
/* 52 */
        model.addAttribute("useableMemory", Long.valueOf(useableMemory));
/* 53 */
        return "right";
    }

    @RequestMapping({"/top.do"})
    public String top(HttpServletRequest request, ModelMap model) {
/* 58 */
        ShopAdmin admin = AdminThread.get();
/* 59 */
        model.addAttribute("admin", admin);
/* 60 */
        return "top";
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.WelcomeAct
 * JD-Core Version:    0.6.2
 */