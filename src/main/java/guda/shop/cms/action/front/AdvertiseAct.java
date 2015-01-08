package guda.shop.cms.action.front;

import guda.shop.cms.entity.Adspace;
import guda.shop.cms.entity.Advertise;
import guda.shop.cms.manager.AdspaceMng;
import guda.shop.cms.manager.AdvertiseMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdvertiseAct {
    public static final String TPL_AD = "tpl.advertising";
    public static final String TPL_ADSPACE = "tpl.adspace";

    @Autowired
    private AdvertiseMng manager;

    @Autowired
    private AdspaceMng adspaceMng;

    @RequestMapping({"/ad.jspx"})
    public String ad(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 47 */
        Website web = SiteUtils.getWeb(request);
/* 48 */
        if (id != null) {
/* 49 */
            Advertise ad = this.manager.findById(id);
/* 50 */
            model.addAttribute("ad", ad);
        }
/* 52 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/* 53 */
        return web.getTplSys("member",
/* 54 */       MessageResolver.getMessage(request, 
/* 54 */       "tpl.mycollect", new Object[0]));
    }

    @RequestMapping({"/adspace.jspx"})
    public String adspace(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 60 */
        Website web = SiteUtils.getWeb(request);
/* 61 */
        if (id != null) {
/* 62 */
            Adspace adspace = this.adspaceMng.findById(id);
/* 63 */
            List adList = this.manager.getList(id, Boolean.valueOf(true));
/* 64 */
            model.addAttribute("adspace", adspace);
/* 65 */
            model.addAttribute("adList", adList);
        }
/* 67 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/* 68 */
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.adspace", new Object[0]));
    }

    @RequestMapping({"/ad_display.jspx"})
    public void display(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 74 */
        if (id != null) {
/* 75 */
            this.manager.display(id);
        }
/* 77 */
        response.setHeader("Pragma", "No-cache");
/* 78 */
        response.setHeader("Cache-Control", "no-cache");
/* 79 */
        response.setDateHeader("Expires", 0L);
    }

    @RequestMapping({"/ad_click.jspx"})
    public void click(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 85 */
        if (id != null) {
/* 86 */
            this.manager.click(id);
        }
/* 88 */
        response.setHeader("Pragma", "No-cache");
/* 89 */
        response.setHeader("Cache-Control", "no-cache");
/* 90 */
        response.setDateHeader("Expires", 0L);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.AdvertiseAct
 * JD-Core Version:    0.6.2
 */