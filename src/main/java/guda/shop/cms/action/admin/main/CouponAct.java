package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Coupon;
import guda.shop.cms.manager.CouponMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CouponAct {

    @Autowired
    private CouponMng manager;

    @RequestMapping({"/coupon/add.do"})
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        return "coupon/add";
    }

    @RequestMapping({"/coupon/o_save.do"})
    public String save(Coupon bean, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website site = SiteUtils.getWeb(request);

        this.manager.save(bean, site.getId());

        return list(request, response, model);
    }

    @RequestMapping({"/coupon/list.do"})
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List cList = this.manager.getList();

        model.addAttribute("list", cList);

        return "coupon/list";
    }

    @RequestMapping({"/coupon/edit.do"})
    public String edit(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        model.addAttribute("coupon", this.manager.findById(id));

        return "coupon/edit";
    }

    @RequestMapping({"/coupon/o_update.do"})
    public String update(Coupon bean, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        this.manager.update(bean);

        return list(request, response, model);
    }

    @RequestMapping({"/coupon/o_delete.do"})
    public String delete(Long[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website site = SiteUtils.getWeb(request);

        String url = site.getUploadUrl();

        this.manager.deleteByIds(ids, url);

        return list(request, response, model);
    }
}

