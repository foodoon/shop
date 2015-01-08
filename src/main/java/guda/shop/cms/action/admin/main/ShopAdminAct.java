package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.manager.ShopAdminMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.AdminMng;
import guda.shop.core.manager.RoleMng;
import guda.shop.core.manager.UserMng;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ShopAdminAct {
    /*  40 */   private static final Logger log = LoggerFactory.getLogger(ShopAdminAct.class);
    @Autowired
    protected RoleMng roleMng;
    @Autowired
    protected AdminMng adminMng;
    @Autowired
    private ShopAdminMng manager;
    @Autowired
    private UserMng userMng;

    /*  45 */
    @RequestMapping({"/admin/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SiteUtils.getWebId(request), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  46 */
        model.addAttribute(pagination);
/*  47 */
        return "admin/list";
    }

    @RequestMapping({"/admin/v_add.do"})
    public String add(ModelMap model) {
/*  52 */
        List roleList = this.roleMng.getList();
/*  53 */
        model.addAttribute("roleList", roleList);
/*  54 */
        return "admin/add";
    }

    @RequestMapping({"/admin/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  59 */
        WebErrors errors = validateEdit(id, request);
/*  60 */
        if (errors.hasErrors()) {
/*  61 */
            return errors.showErrorPage(model);
        }
/*  63 */
        List roleList = this.roleMng.getList();
/*  64 */
        model.addAttribute("roleList", roleList);
/*  65 */
        model.addAttribute("admin", this.manager.findById(id));
/*  66 */
        model.addAttribute("roleIds", this.manager.findById(id).getAdmin().getRoleIds());
/*  67 */
        return "admin/edit";
    }

    @RequestMapping({"/admin/o_save.do"})
    public String save(ShopAdmin bean, String username, String password, Boolean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, HttpServletRequest request, ModelMap model) {
/*  74 */
        WebErrors errors = validateSave(bean, request);
/*  75 */
        if (errors.hasErrors()) {
/*  76 */
            return errors.showErrorPage(model);
        }
/*  78 */
        Website web = SiteUtils.getWeb(request);
/*  79 */
        bean = this.manager.register(username, password, viewonlyAdmin, email, request.getRemoteAddr(), disabled.booleanValue(), web.getId(), bean);
/*  80 */
        this.adminMng.addRole(bean.getAdmin(), roleIds);
/*  81 */
        log.info("save ShopAdmin id={}", bean.getId());
/*  82 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/admin/o_update.do"})
    public String update(ShopAdmin bean, String password, Boolean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  89 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  90 */
        if (errors.hasErrors()) {
/*  91 */
            return errors.showErrorPage(model);
        }
/*  93 */
        bean = this.manager.update(bean, password, disabled, email, viewonlyAdmin);
/*  94 */
        this.adminMng.updateRole(bean.getAdmin(), roleIds);
/*  95 */
        log.info("update ShopAdmin id={}.", bean.getId());
/*  96 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/admin/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 102 */
        WebErrors errors = validateDelete(ids, request);
/* 103 */
        if (errors.hasErrors()) {
/* 104 */
            return errors.showErrorPage(model);
        }
/* 106 */
        ShopAdmin[] beans = this.manager.deleteByIds(ids);
/* 107 */
        for (ShopAdmin bean : beans) {
/* 108 */
            log.info("delete ShopAdmin id={}", bean.getId());
        }
/* 110 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/admin/v_check_username.do"})
    public String checkUsername(String username, HttpServletRequest request, HttpServletResponse response) {
/* 116 */
        if ((StringUtils.isBlank(username)) || (this.userMng.usernameExist(username)))
/* 117 */ ResponseUtils.renderJson(response, "false");
        else {
/* 119 */
            ResponseUtils.renderJson(response, "true");
        }
/* 121 */
        return null;
    }

    @RequestMapping({"/admin/v_check_email.do"})
    public String checkEmail(String email, HttpServletRequest request, HttpServletResponse response) {
/* 127 */
        if ((StringUtils.isBlank(email)) || (this.userMng.emailExist(email)))
/* 128 */ ResponseUtils.renderJson(response, "false");
        else {
/* 130 */
            ResponseUtils.renderJson(response, "true");
        }
/* 132 */
        return null;
    }

    private WebErrors validateSave(ShopAdmin bean, HttpServletRequest request) {
/* 137 */
        WebErrors errors = WebErrors.create(request);
/* 138 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 142 */
        WebErrors errors = WebErrors.create(request);
/* 143 */
        errors.ifNull(id, "id");
/* 144 */
        vldExist(id, errors);
/* 145 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 150 */
        WebErrors errors = WebErrors.create(request);
/* 151 */
        if (vldExist(id, errors)) {
/* 152 */
            return errors;
        }
/* 154 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 158 */
        WebErrors errors = WebErrors.create(request);
/* 159 */
        errors.ifEmpty(ids, "ids");
/* 160 */
        for (Long id : ids) {
/* 161 */
            vldExist(id, errors);
        }
/* 163 */
        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {
/* 167 */
        ShopAdmin entity = this.manager.findById(id);
/* 168 */
        return errors.ifNotExist(entity, ShopAdmin.class, id);
    }

    private Set<String> handleperms(String[] perms) {
/* 172 */
        Set permSet = new HashSet();

/* 174 */
        for (String perm : perms) {
/* 175 */
            String[] arr = perm.split("@");
/* 176 */
            int i = 0;
            for (int len = arr.length; i < len; i++) {
/* 177 */
                permSet.add(arr[i]);
            }
        }
/* 180 */
        return permSet;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopAdminAct
 * JD-Core Version:    0.6.2
 */