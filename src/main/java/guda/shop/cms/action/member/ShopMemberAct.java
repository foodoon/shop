package guda.shop.cms.action.member;

import guda.shop.cms.dao.OrderDao;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.manager.ShopDictionaryMng;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.UserMng;
import guda.shop.core.web.WebErrors;
import guda.shop.core.web.front.FrontHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopMemberAct {
    public static final String MEMBER_CENTER = "tpl.memberCenter";
    public static final String MEMBER_PORTRAIT = "tpl.memberPortrait";
    public static final String MEMBER_PROFILE = "tpl.memberProfile";
    public static final String MEMBER_PASSWORD = "tpl.memberPassword";
    /*  46 */   private static final Logger log = LoggerFactory.getLogger(ShopMemberAct.class);
    @Autowired
    private OrderDao dao;

    @Autowired
    private UserMng userMng;

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ShopMemberMng manager;

    @Autowired
    private ShopDictionaryMng shopDictionaryMng;

    @RequestMapping(value = {"/shopMember/index.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index(HttpServletRequest request, ModelMap model) {
/*  74 */
        Website web = SiteUtils.getWeb(request);
/*  75 */
        ShopMember member = MemberThread.get();

/*  77 */
        if (member == null) {
/*  78 */
            return "redirect:../login.jspx";
        }
/*  80 */
        BigDecimal money = this.dao.getMemberMoneyByYear(member.getId());
/*  81 */
        Integer[] orders = this.dao.getOrderByMember(member.getId());
/*  82 */
        Integer[] products = this.productMng.getProductByTag(web.getId());
/*  83 */
        model.addAttribute("products", products);
/*  84 */
        model.addAttribute("orders", orders);
/*  85 */
        model.addAttribute("money", money);
/*  86 */
        model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  87 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/*  88 */
        return web.getTplSys("member",
/*  89 */       MessageResolver.getMessage(request, 
/*  89 */       "tpl.memberCenter", new Object[0]));
    }

    @RequestMapping(value = {"/shopMember/profile.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 102 */
        Website web = SiteUtils.getWeb(request);
/* 103 */
        ShopMember member = MemberThread.get();

/* 105 */
        if (member == null) {
/* 106 */
            return "redirect:../login.jspx";
        }

/* 109 */
        List userDegreeList = this.shopDictionaryMng.getListByType(Long.valueOf(1L));

/* 111 */
        List familyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));

/* 113 */
        List incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));

/* 115 */
        List workSeniorityList = this.shopDictionaryMng.getListByType(Long.valueOf(4L));

/* 117 */
        List degreeList = this.shopDictionaryMng.getListByType(Long.valueOf(5L));
/* 118 */
        model.addAttribute("member", member);

/* 120 */
        model.addAttribute("userDegreeList", userDegreeList);
/* 121 */
        model.addAttribute("familyMembersList", familyMembersList);
/* 122 */
        model.addAttribute("incomeDescList", incomeDescList);
/* 123 */
        model.addAttribute("workSeniorityList", workSeniorityList);
/* 124 */
        model.addAttribute("degreeList", degreeList);
/* 125 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/* 126 */
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberProfile", new Object[0]));
    }

    @RequestMapping(value = {"/shopMember/profile.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String profileSubmit(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 142 */
        ShopMember member = MemberThread.get();

/* 144 */
        if (member == null) {
/* 145 */
            return "redirect:../login.jspx";
        }
/* 147 */
        bean = this.manager.update(bean, groupId, userDegreeId, degreeId, incomeDescId, workSeniorityId, familyMembersId);
/* 148 */
        log.info("ShopMember update infomation: {}", bean.getUsername());
/* 149 */
        return index(request, response, model);
    }

    @RequestMapping(value = {"/shopMember/pwd.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String passwordInput(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 163 */
        Website web = SiteUtils.getWeb(request);
/* 164 */
        ShopMember member = MemberThread.get();

/* 166 */
        if (member == null) {
/* 167 */
            return "redirect:../login.jspx";
        }
/* 169 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/* 170 */
        model.addAttribute("email", MemberThread.get().getEmail());
/* 171 */
        model.addAttribute("historyProductIds", getHistoryProductIds(request));
/* 172 */
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberPassword", new Object[0]));
    }

    @RequestMapping(value = {"/shopMember/portrait.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String portrait(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 186 */
        Website web = SiteUtils.getWeb(request);
/* 187 */
        ShopMember member = MemberThread.get();

/* 189 */
        if (member == null) {
/* 190 */
            return "redirect:../login.jspx";
        }
/* 192 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/* 193 */
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberPortrait", new Object[0]));
    }

    @RequestMapping(value = {"/shopMember/updateAvatar.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateAvatar(String picPaths, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 206 */
        ShopMember member = MemberThread.get();

/* 208 */
        if (member == null) {
/* 209 */
            return "redirect:../login.jspx";
        }
/* 211 */
        member.setAvatar(picPaths);
/* 212 */
        this.manager.update(member);
/* 213 */
        return "redirect: index.jspx";
    }

    @RequestMapping(value = {"/shopMember/pwd.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String passwordSubmit(String origPwd, String newPwd, String email, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws IOException {
/* 238 */
        Website web = SiteUtils.getWeb(request);
/* 239 */
        ShopMember member = MemberThread.get();

/* 241 */
        if (member == null) {
/* 242 */
            return "redirect:../login.jspx";
        }
/* 244 */
        Long userId = member.getMember().getUser().getId();
/* 245 */
        WebErrors errors = validatePassword(userId, email, newPwd, member.getEmail(), origPwd, request);
/* 246 */
        if (errors.hasErrors()) {
/* 247 */
            return FrontHelper.showError(errors, web, model, request);
        }
/* 249 */
        this.userMng.updateUser(userId, newPwd, email);
/* 250 */
        log.info("ShopMember update password: {}", member.getUsername());
/* 251 */
        return FrontHelper.showSuccess("global.success", nextUrl, web, model, request);
    }

    @RequestMapping({"/shopMember/checkPwd.jspx"})
    public void checkPwd(String origPwd, HttpServletRequest request, HttpServletResponse response) {
/* 265 */
        ShopMember member = MemberThread.get();
/* 266 */
        Long userId = member.getMember().getUser().getId();
/* 267 */
        boolean pass = this.userMng.isPasswordValid(userId, origPwd);
/* 268 */
        ResponseUtils.renderJson(response, pass ? "true" : "false");
    }

    public String getHistoryProductIds(HttpServletRequest request) {
/* 272 */
        String str = null;
/* 273 */
        Cookie[] cookie = request.getCookies();
/* 274 */
        int num = cookie.length;
/* 275 */
        for (int i = 0; i < num; i++) {
/* 276 */
            if (cookie[i].getName().equals("shop_record")) {
/* 277 */
                str = cookie[i].getValue();
/* 278 */
                break;
            }
        }
/* 281 */
        return str;
    }

    private WebErrors validatePassword(Long userId, String email, String newPwd, String origEmail, String origPwd, HttpServletRequest request) {
/* 287 */
        WebErrors errors = WebErrors.create(request);
/* 288 */
        if ((!StringUtils.isBlank(newPwd)) &&
/* 289 */       (errors.ifOutOfLength(newPwd, "password", 3, 32))) {
/* 290 */
            return errors;
        }
/* 292 */
        if (!this.userMng.isPasswordValid(userId, origPwd)) {
/* 293 */
            errors.addErrorCode("error.passwordInvalid");
        }
/* 295 */
        if (errors.ifNotEmail(email, "email", 100)) {
/* 296 */
            return errors;
        }
/* 298 */
        if ((!email.equals(origEmail)) && (this.userMng.emailExist(email))) {
/* 299 */
            errors.addErrorCode("error.emailExist");
/* 300 */
            return errors;
        }
/* 302 */
        return errors;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ShopMemberAct
 * JD-Core Version:    0.6.2
 */