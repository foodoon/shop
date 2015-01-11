package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Product;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.core.entity.Global;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductStatisticsAct {

    @Autowired
    private ProductMng productMng;

    @RequestMapping({"/productStatistics/v_productLack.do"})
    public String productLack(Integer count, Boolean status, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        Global global = web.getGlobal();

        if (count == null) {

            count = global.getStockWarning();
        }

        Pagination pagination = this.productMng.getPageByStockWarning(web.getId(), count, status, SimplePage.cpn(pageNo),
                CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        model.addAttribute("count", count);

        model.addAttribute("status", status);

        return "sale/productLack";
    }

    @RequestMapping({"/productStatistics/o_updateRemind.do"})
    public String updateRemind(Boolean status, Integer count, Integer pageNo, Long productId, HttpServletRequest request, ModelMap model) {

        Product product = this.productMng.findById(productId);

        product.setLackRemind(status);

        this.productMng.updateByUpdater(product);

        if (status.booleanValue())
            status = Boolean.valueOf(false);
        else {

            status = Boolean.valueOf(true);
        }

        model.addAttribute("status", status);

        model.addAttribute("count", count);

        return "redirect:v_productLack.do";
    }

    @RequestMapping({"/productStatistics/o_resetSaleTop.do"})
    public String resetSaleTop(Integer pageNo, HttpServletRequest request, ModelMap model) {

        this.productMng.resetSaleTop();

        return productSaleTop(pageNo, request, model);
    }

    @RequestMapping({"/productStatistics/v_productSaleTop.do"})
    public String productSaleTop(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Pagination pagination = this.productMng.getPage(4, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        model.addAttribute("pageNo", pageNo);

        return "sale/productSaleTop";
    }

    @RequestMapping({"/productStatistics/o_resetProfitTop.do"})
    public String resetProfitTop(Integer pageNo, HttpServletRequest request, ModelMap model) {

        this.productMng.resetProfitTop();

        return productProfitTop(pageNo, request, model);
    }

    @RequestMapping({"/productStatistics/v_productProfitTop.do"})
    public String productProfitTop(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Pagination pagination = this.productMng.getPage(8, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        model.addAttribute("pageNo", pageNo);

        return "sale/productProfitTop";
    }
}

