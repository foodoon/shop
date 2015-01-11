package guda.shop.cms.action.front;

import guda.shop.cms.entity.*;
import guda.shop.cms.manager.ConsultMng;
import guda.shop.cms.manager.DiscussMng;
import guda.shop.cms.manager.OrderItemMng;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.front.FrontHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProductFormAct {

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ConsultMng consultMng;

    @Autowired
    private OrderItemMng orderItemMng;

    @Autowired
    private DiscussMng discussMng;

    @RequestMapping({"/searchDiscussPage*.jspx"})
    public String searchDiscussPage(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        Pagination page = this.discussMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 10, true);

        Product bean = this.productMng.findById(productId);

        model.addAttribute("product", bean);

        model.addAttribute("pagination", page);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "searchDiscussPage", ".jspx", SimplePage.cpn(pageNo));

        return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.discussContentPage", new Object[0]));
    }

    @RequestMapping({"/haveDiscuss.jspx"})
    public String haveDiscuss(Long productId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            ResponseUtils.renderJson(response, "denru");

            return null;
        }

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        OrderItem bean = this.orderItemMng.findByMember(member.getId(), productId);

        if (bean != null) {

            ResponseUtils.renderJson(response, "success");

            return null;
        }

        ResponseUtils.renderJson(response, "false");

        return null;
    }

    @RequestMapping({"/consultProduct*.jspx"})
    public String consultProduct(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        ShopFrontHelper.setCommonData(request, model, web, SimplePage.cpn(pageNo));

        Product bean = this.productMng.findById(productId);

        Pagination page = this.consultMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 5, Boolean.valueOf(true));

        model.addAttribute("product", bean);

        model.addAttribute("pagination", page);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "consultProduct", ".jspx", SimplePage.cpn(pageNo));

        return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.consultProduct", new Object[0]));
    }

    @RequestMapping({"/bargain*.jspx"})
    public String bargain(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        ShopFrontHelper.setCommonData(request, model, web, SimplePage.cpn(pageNo));

        Product bean = this.productMng.findById(productId);

        Pagination page = this.orderItemMng.getOrderItem(Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(4), productId);

        model.addAttribute("pagination", page);

        model.addAttribute("product", bean);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "bargain", ".jspx", SimplePage.cpn(pageNo));

        return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.bargain", new Object[0]));
    }

    @RequestMapping({"/insertConsult.jspx"})
    public String insertConsult(Long productId, String content, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            ResponseUtils.renderJson(response, "false");

            return null;
        }

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        Consult bean = this.consultMng.saveOrUpdate(productId, content, member.getId());

        if (bean == null) {

            ResponseUtils.renderJson(response, "sameUsually");

            return null;
        }

        ResponseUtils.renderJson(response, "success");

        return null;
    }

    @RequestMapping({"/insertDiscuss.jspx"})
    public String insertDiscuss(Long productId, String disCon, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            ResponseUtils.renderJson(response, "false");

            return null;
        }

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        Discuss bean = this.discussMng.saveOrUpdate(productId, disCon, member.getId());

        if (bean == null) {

            ResponseUtils.renderJson(response, "sameUsually");

            return null;
        }

        ResponseUtils.renderJson(response, "success");

        return null;
    }

    @RequestMapping({"/historyRecord.jspx"})
    public String historyRecord(Long productId, HttpServletResponse response, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            ResponseUtils.renderJson(response, "false");

            return null;
        }

        if ((productId == null) || (this.productMng.findById(productId) == null)) {

            return FrontHelper.pageNotFound(web, model, request);
        }

        String str = "";

        Cookie[] cookeis = request.getCookies();

        int num = cookeis.length;

        for (int i = 0; i < num; i++) {

            if (cookeis[i].getName().equals("shop_record")) {

                str = ',' + cookeis[i].getValue();

                break;
            }
        }

        str = productId + str;

        int n = 0;
        int m = 0;

        int j = str.length();

        for (int i = 0; i < j; i++) {

            if (str.charAt(i) == ',') {

                n++;
            }

            if (n == 6) {
                break;
            }

            m = i + 1;
        }

        Cookie cook = new Cookie("shop_record", str.substring(0, m));

        String path = request.getContextPath();

        cook.setPath(StringUtils.isBlank(path) ? "/" : path);

        response.addCookie(cook);

        return null;
    }
}

