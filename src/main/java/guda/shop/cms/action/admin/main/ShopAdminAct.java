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
    private static final Logger log = LoggerFactory.getLogger(ShopAdminAct.class);
    @Autowired
    protected RoleMng roleMng;
    @Autowired
    protected AdminMng adminMng;
    @Autowired
    private ShopAdminMng manager;
    @Autowired
    private UserMng userMng;


    @RequestMapping({"/admin/list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SiteUtils.getWebId(request), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute(pagination);

        return "admin/list";
    }

    @RequestMapping({"/admin/add.do"})
    public String add(ModelMap model) {

        List roleList = this.roleMng.getList();

        model.addAttribute("roleList", roleList);

        return "admin/add";
    }

    @RequestMapping({"/admin/edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        List roleList = this.roleMng.getList();

        model.addAttribute("roleList", roleList);

        model.addAttribute("admin", this.manager.findById(id));

        model.addAttribute("roleIds", this.manager.findById(id).getAdmin().getRoleIds());

        return "admin/edit";
    }

    @RequestMapping({"/admin/o_save.do"})
    public String save(ShopAdmin bean, String username, String password, Boolean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Website web = SiteUtils.getWeb(request);

        bean = this.manager.register(username, password, viewonlyAdmin, email, request.getRemoteAddr(), disabled.booleanValue(), web.getId(), bean);

        this.adminMng.addRole(bean.getAdmin(), roleIds);

        log.info("save ShopAdmin id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/admin/o_update.do"})
    public String update(ShopAdmin bean, String password, Boolean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean, password, disabled, email, viewonlyAdmin);

        this.adminMng.updateRole(bean.getAdmin(), roleIds);

        log.info("update ShopAdmin id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/admin/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ShopAdmin[] beans = this.manager.deleteByIds(ids);

        for (ShopAdmin bean : beans) {

            log.info("delete ShopAdmin id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    @RequestMapping({"/admin/check_username.do"})
    public String checkUsername(String username, HttpServletRequest request, HttpServletResponse response) {

        if ((StringUtils.isBlank(username)) || (this.userMng.usernameExist(username)))
            ResponseUtils.renderJsonString(response, "false");
        else {

            ResponseUtils.renderJsonString(response, "true");
        }

        return null;
    }

    @RequestMapping({"/admin/check_email.do"})
    public String checkEmail(String email, HttpServletRequest request, HttpServletResponse response) {

        if ((StringUtils.isBlank(email)) || (this.userMng.emailExist(email)))
            ResponseUtils.renderJsonString(response, "false");
        else {

            ResponseUtils.renderJsonString(response, "true");
        }

        return null;
    }

    private WebErrors validateSave(ShopAdmin bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (vldExist(id, errors)) {

            return errors;
        }

        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifEmpty(ids, "ids");

        for (Long id : ids) {

            vldExist(id, errors);
        }

        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {

        ShopAdmin entity = this.manager.findById(id);

        return errors.ifNotExist(entity, ShopAdmin.class, id);
    }

    private Set<String> handleperms(String[] perms) {

        Set permSet = new HashSet();


        for (String perm : perms) {

            String[] arr = perm.split("@");

            int i = 0;
            for (int len = arr.length; i < len; i++) {

                permSet.add(arr[i]);
            }
        }

        return permSet;
    }
}

