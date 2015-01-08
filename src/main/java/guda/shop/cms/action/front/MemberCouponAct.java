package guda.shop.cms.action.front;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.MemberCouponMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberCouponAct {

    @Autowired
    private MemberCouponMng manage;

    @RequestMapping(value = {"/myCoupon.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String pay1(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        List list = this.manage.getList(member.getId());

        model.addAttribute("couList", list);

        model.addAttribute("historyProductIds", getHistoryProductIds(request));

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myCoupon", new Object[0]));
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
 * Qualified Name:     com.jspgou.cms.action.front.MemberCouponAct
 * JD-Core Version:    0.6.2
 */