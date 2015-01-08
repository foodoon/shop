package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Consult;
import guda.shop.cms.entity.Discuss;
import guda.shop.cms.manager.ConsultMng;
import guda.shop.cms.manager.DiscussMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageAct {
       private static final Logger log = LoggerFactory.getLogger(MessageAct.class);

    @Autowired
    private DiscussMng manager;

    @Autowired
    private ConsultMng consultMng;


    @RequestMapping({"/message/v_productDiss.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(null, null, null, null, null, SimplePage.cpn(pageNo),
       CookieUtils.getPageSize(request), true);

        model.addAttribute("pagination", pagination);


        return "message/list";
    }

    @RequestMapping({"/message/v_edit.do"})
    public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Discuss bean = this.manager.findById(id);

        model.addAttribute("discuss", bean);

        return "message/edit";
    }

    @RequestMapping({"/message/v_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        this.manager.deleteByIds(ids);

        model.addAttribute("pageNo", pageNo);

        return "redirect:v_productDiss.do";
    }

    @RequestMapping({"/message/v_productConsult.do"})
    public String listConsult(Integer pageNo, HttpServletRequest request, ModelMap model) {

        Pagination pagination = this.consultMng.getPage(null, null, null, null, null, SimplePage.cpn(pageNo),
       CookieUtils.getPageSize(request), Boolean.valueOf(true));

        model.addAttribute("pagination", pagination);

        return "consult/list";
    }

    @RequestMapping({"/message/v_editConsult.do"})
    public String editConsult(Long id, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Consult bean = this.consultMng.findById(id);

        model.addAttribute("consult", bean);

        return "consult/edit";
    }

    @RequestMapping({"/message/v_updateConsult.do"})
    public String updateConsult(Long id, String adminReplay, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Consult bean = this.consultMng.findById(id);

        bean.setAdminReplay(adminReplay);

        this.consultMng.update(bean);

        model.addAttribute("pageNo", pageNo);

        return "redirect:v_productConsult.do";
    }

    @RequestMapping({"/message/v_deleteConsult.do"})
    public String deleteConsult(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        this.consultMng.deleteByIds(ids);

        model.addAttribute("pageNo", pageNo);

        return "redirect:v_productConsult.do";
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.MessageAct
 * JD-Core Version:    0.6.2
 */