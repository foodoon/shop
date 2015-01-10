package guda.shop.cms.web;

import guda.shop.cms.entity.ShopConfig;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.ShopConfigMng;
import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.web.threadvariable.GroupThread;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontContextInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ShopConfigMng shopConfigMng;

    @Autowired
    private LoginSvc loginSvc;

    public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
            throws ServletException {
        if(paramHttpServletRequest.getRequestURI().equals("/")){
            return true;
        }
        Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
        ShopConfig localShopConfig = this.shopConfigMng.findById(localWebsite.getId());
        if (localShopConfig == null)
            throw new IllegalStateException("no ShopConfig found in Website id=" + localWebsite.getId());
        paramHttpServletRequest.setAttribute("_shop_config_key", localShopConfig);
        ShopMember localShopMember = this.loginSvc.getMember(paramHttpServletRequest, paramHttpServletResponse, localWebsite);
        if (localShopMember != null) {
            MemberThread.set(localShopMember);
            GroupThread.set(localShopMember.getGroup());
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
            throws Exception {
        MemberThread.remove();
        GroupThread.remove();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.FrontContextInterceptor
 * JD-Core Version:    0.6.2
 */