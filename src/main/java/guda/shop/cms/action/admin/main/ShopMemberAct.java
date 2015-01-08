package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.ShopDictionaryMng;
import guda.shop.cms.manager.ShopMemberGroupMng;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.UserMng;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopMemberAct {
    /*  37 */   private static final Logger log = LoggerFactory.getLogger(ShopMemberAct.class);

    @Autowired
    private ShopMemberGroupMng shopMemberGroupMng;

    @Autowired
    private UserMng userMng;

    @Autowired
    private ShopMemberMng manager;

    @Autowired
    private ShopDictionaryMng shopDictionaryMng;

    /*  42 */
    @RequestMapping({"/member/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SiteUtils.getWebId(request),
/*  43 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  44 */
        model.addAttribute("pagination", pagination);
/*  45 */
        return "member/list";
    }

    @RequestMapping({"/member/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {
/*  50 */
        Website web = SiteUtils.getWeb(request);
/*  51 */
        List groupList = this.shopMemberGroupMng.getList(
/*  52 */       SiteUtils.getWebId(request));

/*  54 */
        List userDegreeList = this.shopDictionaryMng.getListByType(Long.valueOf(1L));

/*  56 */
        List familyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));

/*  58 */
        List incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));

/*  60 */
        List workSeniorityList = this.shopDictionaryMng.getListByType(Long.valueOf(4L));

/*  62 */
        List degreeList = this.shopDictionaryMng.getListByType(Long.valueOf(5L));

/*  64 */
        model.addAttribute("groupList", groupList);
/*  65 */
        model.addAttribute("userDegreeList", userDegreeList);
/*  66 */
        model.addAttribute("familyMembersList", familyMembersList);
/*  67 */
        model.addAttribute("incomeDescList", incomeDescList);
/*  68 */
        model.addAttribute("workSeniorityList", workSeniorityList);
/*  69 */
        model.addAttribute("degreeList", degreeList);
/*  70 */
        return "member/add";
    }

    @RequestMapping({"/member/o_save.do"})
    public String save(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, String username, String password, String email, Boolean disabled, HttpServletRequest request, ModelMap model) {
/*  78 */
        WebErrors errors = validateSave(bean, username, email, request);
/*  79 */
        if (errors.hasErrors()) {
/*  80 */
            return errors.showErrorPage(model);
        }

/*  83 */
        bean = this.manager.register(username, password, email, Boolean.valueOf(true), null,
/*  84 */       request.getRemoteAddr(), disabled, SiteUtils.getWebId(request), 
/*  85 */       groupId, userDegreeId, degreeId, incomeDescId, workSeniorityId, 
/*  86 */       familyMembersId, bean);
/*  87 */
        log.info("save ShopMember, id={}", bean.getId());
/*  88 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/member/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  93 */
        Website web = SiteUtils.getWeb(request);
/*  94 */
        WebErrors errors = validateEdit(id, request);
/*  95 */
        if (errors.hasErrors()) {
/*  96 */
            return errors.showErrorPage(model);
        }
/*  98 */
        List groupList = this.shopMemberGroupMng.getList(
/*  99 */       SiteUtils.getWebId(request));

/* 102 */
        List userDegreeList = this.shopDictionaryMng.getListByType(Long.valueOf(1L));

/* 104 */
        List familyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));

/* 106 */
        List incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));

/* 108 */
        List workSeniorityList = this.shopDictionaryMng.getListByType(Long.valueOf(4L));

/* 110 */
        List degreeList = this.shopDictionaryMng.getListByType(Long.valueOf(5L));

/* 112 */
        ShopMember member = this.manager.findById(id);
/* 113 */
        model.addAttribute("groupList", groupList);
/* 114 */
        model.addAttribute("member", member);
/* 115 */
        model.addAttribute("groupList", groupList);
/* 116 */
        model.addAttribute("userDegreeList", userDegreeList);
/* 117 */
        model.addAttribute("familyMembersList", familyMembersList);
/* 118 */
        model.addAttribute("incomeDescList", incomeDescList);
/* 119 */
        model.addAttribute("workSeniorityList", workSeniorityList);
/* 120 */
        model.addAttribute("degreeList", degreeList);
/* 121 */
        return "member/edit";
    }

    @RequestMapping({"/member/o_update.do"})
    public String update(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, String password, String email, Boolean disabled, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 129 */
        WebErrors errors = validateUpdate(bean.getId(), email, request);
/* 130 */
        if (errors.hasErrors()) {
/* 131 */
            return errors.showErrorPage(model);
        }
/* 133 */
        bean = this.manager.update(bean, groupId, userDegreeId,
/* 134 */       degreeId, incomeDescId, workSeniorityId, familyMembersId, 
/* 135 */       password, email, disabled);
/* 136 */
        log.info("update ShopMember, id={}.", bean.getId());
/* 137 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/member/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 143 */
        WebErrors errors = validateDelete(ids, request);
/* 144 */
        if (errors.hasErrors()) {
/* 145 */
            return errors.showErrorPage(model);
        }
/* 147 */
        ShopMember[] beans = this.manager.deleteByIds(ids);
/* 148 */
        for (ShopMember bean : beans) {
/* 149 */
            log.info("delete ShopMember, id={}", bean.getId());
        }
/* 151 */
        return list(pageNo, request, model);
    }

    private WebErrors validateSave(ShopMember bean, String username, String email, HttpServletRequest request) {
/* 156 */
        WebErrors errors = WebErrors.create(request);
/* 157 */
        Website web = SiteUtils.getWeb(request);
/* 158 */
        bean.setWebsite(web);
/* 159 */
        if (vldUsername(username, errors)) {
/* 160 */
            return errors;
        }
/* 162 */
        if (vldEmail(email, errors)) {
/* 163 */
            return errors;
        }
/* 165 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 169 */
        WebErrors errors = WebErrors.create(request);
/* 170 */
        Website web = SiteUtils.getWeb(request);
/* 171 */
        if (vldExist(id, web.getId(), errors)) {
/* 172 */
            return errors;
        }
/* 174 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, String email, HttpServletRequest request) {
/* 179 */
        WebErrors errors = WebErrors.create(request);
/* 180 */
        Website web = SiteUtils.getWeb(request);
/* 181 */
        if (vldEmailUpdate(id, email, errors)) {
/* 182 */
            return errors;
        }
/* 184 */
        if (vldExist(id, web.getId(), errors)) {
/* 185 */
            return errors;
        }
/* 187 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 191 */
        WebErrors errors = WebErrors.create(request);
/* 192 */
        Website web = SiteUtils.getWeb(request);
/* 193 */
        errors.ifEmpty(ids, "ids");
/* 194 */
        for (Long id : ids) {
/* 195 */
            vldExist(id, web.getId(), errors);
        }
/* 197 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 201 */
        if (errors.hasErrors()) {
/* 202 */
            return true;
        }
/* 204 */
        if (errors.ifNull(id, "id")) {
/* 205 */
            return true;
        }
/* 207 */
        ShopMember entity = this.manager.findById(id);
/* 208 */
        if (errors.ifNotExist(entity, ShopMember.class, id)) {
/* 209 */
            return true;
        }
/* 211 */
        if (!entity.getWebsite().getId().equals(webId)) {
/* 212 */
            errors.notInWeb(ShopMember.class, id);
/* 213 */
            return true;
        }
/* 215 */
        return false;
    }

    private boolean vldEmailUpdate(Long id, String email, WebErrors errors) {
/* 219 */
        if (!StringUtils.isBlank(email)) {
/* 220 */
            ShopMember member = this.manager.findById(id);
/* 221 */
            if ((!member.getEmail().equals(email)) &&
/* 222 */         (vldEmail(email, errors))) {
/* 223 */
                return true;
            }
        }

/* 227 */
        return false;
    }

    private boolean vldEmail(String email, WebErrors errors) {
/* 231 */
        if (errors.ifNotEmail(email, "email", 100)) {
/* 232 */
            return true;
        }
/* 234 */
        if (this.userMng.emailExist(email)) {
/* 235 */
            errors.addErrorCode("error.emailExist");
/* 236 */
            return true;
        }
/* 238 */
        return false;
    }

    private boolean vldUsername(String username, WebErrors errors) {
/* 242 */
        if (errors.ifNotUsername(username, "username", 3, 100)) {
/* 243 */
            return true;
        }
/* 245 */
        if (this.userMng.usernameExist(username)) {
/* 246 */
            errors.addErrorCode("error.usernameExist");
/* 247 */
            return true;
        }
/* 249 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopMemberAct
 * JD-Core Version:    0.6.2
 */