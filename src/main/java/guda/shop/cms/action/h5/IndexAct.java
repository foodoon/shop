package guda.shop.cms.action.h5;

import guda.shop.cms.action.front.DynamicPageAct;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.core.entity.Website;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by foodoon on 2015/1/18.
 */
@Controller
public class IndexAct {

    public static final String H5_TEMPLATE_PATH = "/WEB-INF/front_h5/";

    @RequestMapping({"/index.h5"})
    public String index( HttpServletRequest request, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        ShopFrontHelper.setCommonData(request, model, web, 1);
        model.addAttribute(DynamicPageAct.GLOBAL_CURRENT_PATH,"/");
        return H5_TEMPLATE_PATH + "index/index.html";
    }

}
