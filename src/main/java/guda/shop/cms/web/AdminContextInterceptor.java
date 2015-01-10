package guda.shop.cms.web;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.manager.ShopAdminMng;
import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.web.threadvariable.AdminThread;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class AdminContextInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginSvc loginSvc;

    @Autowired
    private ShopAdminMng shopAdminMng;
    private Long developAdminId;

    private static String _$1(HttpServletRequest paramHttpServletRequest)
            throws IllegalStateException {
        UrlPathHelper localUrlPathHelper = new UrlPathHelper();
        String str1 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
        String str2 = localUrlPathHelper.getOriginatingContextPath(paramHttpServletRequest);
        int i = 0;
        int j = 0;
        int k = 2;
        if (!StringUtils.isBlank(str2))
            k++;
        while ((j < k) && (i != -1)) {
            i = str1.indexOf('/', i + 1);
            j++;
        }
        if (i <= 0)
            throw new IllegalStateException("admin access path not like '/admin/shop/...' pattern: " + str1);
        return str1.substring(i);
    }

    public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
            throws Exception {
        Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
        ShopAdmin localShopAdmin;
        if (this.developAdminId != null) {
            localShopAdmin = this.shopAdminMng.findById(this.developAdminId);
            if (localShopAdmin == null)
                throw new IllegalStateException("developAdminId not found: " + this.developAdminId);
            Long id = localShopAdmin.getWebsite().getId();
            if (!id.equals(localWebsite.getId()))
                throw new IllegalStateException("developAdminId's website id=" + id + " not in current website id=" + localWebsite.getId());
        } else {
            localShopAdmin = this.loginSvc.getAdmin(paramHttpServletRequest, paramHttpServletResponse, localWebsite);
        }
        Object localObject = _$1(paramHttpServletRequest);
        if (localShopAdmin != null) {
            boolean bool = localShopAdmin.getViewonlyAdmin().booleanValue();
            AdminThread.set(localShopAdmin);
            Set localSet = localShopAdmin.getPerms();
            paramHttpServletRequest.setAttribute("_permission_key", localSet);
            if ((bool) && (_$1((String) localObject))) {
                paramHttpServletRequest.setAttribute("message", MessageResolver.getMessage(paramHttpServletRequest, "login.notPermission", new Object[0]));
                paramHttpServletResponse.sendError(403);
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, ModelAndView paramModelAndView)
            throws Exception {
        ShopAdmin localShopAdmin = AdminThread.get();
        if ((localShopAdmin != null) && (paramModelAndView != null) && (paramModelAndView.getModelMap() != null) && (paramModelAndView.getViewName() != null) && (!paramModelAndView.getViewName().startsWith("redirect:")))
            paramModelAndView.getModelMap().addAttribute("_permission_key", localShopAdmin.getPerms());
    }

    public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
            throws Exception {
        AdminThread.remove();
    }

    private boolean _$1(String paramString) {
        String str = null;
        int i = paramString.lastIndexOf("/");
        if (i == -1)
            throw new RuntimeException("uri must start width '/':" + paramString);
        str = paramString.substring(i + 1);
        return str.startsWith("o_");
    }

    public void setDevelopAdminId(Long paramLong) {
        this.developAdminId = paramLong;
    }
}

