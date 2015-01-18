package guda.shop.cms.action.front;

import guda.shop.cms.entity.Collect;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.CollectMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.front.URLHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CollectAct {
    private static final String MY_COLLECT = "tpl.mycollect";

    @Autowired
    private CollectMng manager;

    @RequestMapping({"/collect/add_to_collect.jspx"})
    public String addToCollect(Long productId, Long productFashId, Boolean isAdd, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {
        ShopMember member = MemberThread.get();
        if (member == null) {
            ResponseUtils.renderJson(response, new JSONObject().put("status", 0).toString());
            return null;
        }
        Collect collect = null;
        if (productFashId == null) {
            List clist = this.manager.findByProductId(productId);
            if (clist.size() > 0) {
                ResponseUtils.renderJson(response, new JSONObject().put("status", 2).toString());
                return null;
            }
            collect = this.manager.save(member, productId, null);
        } else {
            collect = this.manager.findByProductFashionId(productFashId);
            if (collect != null) {
                ResponseUtils.renderJson(response, new JSONObject().put("status", 2).toString());
                return null;
            }
            collect = this.manager.save(member, productId, productFashId);
        }

        ResponseUtils.renderJson(response, new JSONObject().put("status", 1).toString());

        return null;
    }

    @RequestMapping({"/collect/mycollect*.jspx"})
    public String myCollect(HttpServletRequest request, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        ShopMember member = MemberThread.get();
        if (member == null) {
            return "";
        }
        Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
        model.addAttribute("historyProductIds", "");
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("shop_record")) {
                String str = cookie[i].getValue();
                model.addAttribute("historyProductIds", str);
                break;
            }
        }
        ShopFrontHelper.setCommonData(request, model, web, 1);
        ShopFrontHelper.setDynamicPageData(request, model, web, "", "mycollect", ".jspx", pageNo.intValue());
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.mycollect", new Object[0]));
    }

    @RequestMapping({"/collect/delCollect.jspx"})
    public String delCollect(Integer[] collectIds, HttpServletResponse response, ModelMap model) {
        ShopMember member = MemberThread.get();
        if (member == null) {
            return "";
        }
        this.manager.deleteByIds(collectIds);
        ResponseUtils.renderJson(response, "删除成功!");
        return null;
    }
}

