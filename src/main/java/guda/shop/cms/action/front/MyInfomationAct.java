package guda.shop.cms.action.front;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.ConsultMng;
import guda.shop.cms.manager.DiscussMng;
import guda.shop.cms.manager.OrderItemMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.security.annotation.Secured;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Secured
@Controller
public class MyInfomationAct {

    @Autowired
    private OrderItemMng orderItemMng;

    @Autowired
    private ConsultMng consultMng;

    @Autowired
    private DiscussMng discussMng;

    @RequestMapping({"/my_discuss*.jspx"})
    public String getMyDiscuss(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Pagination pagination = this.discussMng.getPageByMember(member.getId(), SimplePage.cpn(pageNo), 10, true);

        model.addAttribute("pagination", pagination);

        model.addAttribute("historyProductIds", getHistoryProductIds(request));

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "my_discuss", ".jspx", SimplePage.cpn(pageNo));

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.mydiscuss", new Object[0]));
    }

    @RequestMapping({"/my_cousult*.jspx"})
    public String getMyCousult(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Pagination pagination = this.consultMng.getPageByMember(member.getId(), SimplePage.cpn(pageNo), 1, true);

        model.addAttribute("pagination", pagination);

        model.addAttribute("historyProductIds", getHistoryProductIds(request));

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "my_cousult", ".jspx", SimplePage.cpn(pageNo));

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myconsult", new Object[0]));
    }

    @RequestMapping({"/buyRecord*.jspx"})
    public String getBuyRecord(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Pagination pagination = this.orderItemMng.getPageByMember(Integer.valueOf(4), member.getId(), SimplePage.cpn(pageNo), 2);

        model.addAttribute("pagination", pagination);

        model.addAttribute("historyProductIds", getHistoryProductIds(request));

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "buyRecord", ".jspx", SimplePage.cpn(pageNo));

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.buyRecord", new Object[0]));
    }

    public String getHistoryProductIds(HttpServletRequest request) {

        String str = null;

        Cookie[] cookie = request.getCookies();

        int num = cookie.length;

        for (int i = 0; i < num; i++) {

            if (cookie[i].getName().equals("shop_record")) {

                str = cookie[i].getValue();

                break;
            }
        }

        return str;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.MyInfomationAct
 * JD-Core Version:    0.6.2
 */