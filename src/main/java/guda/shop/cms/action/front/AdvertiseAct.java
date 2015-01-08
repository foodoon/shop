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

        Website web = SiteUtils.getWeb(request);

        if (id != null) {

            Advertise ad = this.manager.findById(id);

            model.addAttribute("ad", ad);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member",
                MessageResolver.getMessage(request,
                        "tpl.mycollect", new Object[0])
        );
    }

    @RequestMapping({"/adspace.jspx"})
    public String adspace(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if (id != null) {

            Adspace adspace = this.adspaceMng.findById(id);

            List adList = this.manager.getList(id, Boolean.valueOf(true));

            model.addAttribute("adspace", adspace);

            model.addAttribute("adList", adList);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.adspace", new Object[0]));
    }

    @RequestMapping({"/ad_display.jspx"})
    public void display(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        if (id != null) {

            this.manager.display(id);
        }

        response.setHeader("Pragma", "No-cache");

        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0L);
    }

    @RequestMapping({"/ad_click.jspx"})
    public void click(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        if (id != null) {

            this.manager.click(id);
        }

        response.setHeader("Pragma", "No-cache");

        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0L);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.AdvertiseAct
 * JD-Core Version:    0.6.2
 */