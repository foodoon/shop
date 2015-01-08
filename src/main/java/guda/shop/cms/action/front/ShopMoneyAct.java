package guda.shop.cms.action.front;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.front.URLHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopMoneyAct {
    public static final String MEMBER_MONEY = "tpl.mymoney";

    @RequestMapping({"/shopMoney/mymoney*.jspx"})
    public String getMyScore(Integer status, Integer useStatus, String startTime, String endTime, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();


        if (member == null) {

            return "redirect:../login.jspx";
        }

        Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));

        model.addAttribute("status", status);

        model.addAttribute("startTime", startTime);

        model.addAttribute("endTime", endTime);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "mymoney", ".jspx", pageNo.intValue());

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.mymoney", new Object[0]));
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ShopMoneyAct
 * JD-Core Version:    0.6.2
 */