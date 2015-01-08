package guda.shop.cms.web;

import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.security.annotation.Secured;
import guda.shop.core.web.front.FrontHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontSecureInterceptor extends HandlerInterceptorAdapter
        implements InitializingBean {
    private String _$2;
    private LoginSvc _$1;

    public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
            throws Exception {
        paramHttpServletRequest.setAttribute("_login_url", this._$2);
        Secured localSecured = (Secured) paramObject.getClass().getAnnotation(Secured.class);
        if ((localSecured != null) && (MemberThread.get() == null)) {
            this._$1.clearCookie(paramHttpServletRequest, paramHttpServletResponse);
            paramHttpServletResponse.sendRedirect(FrontHelper.getLoginUrl(this._$2, paramHttpServletRequest.getContextPath(), paramHttpServletRequest.getRequestURL().toString()));
            return false;
        }
        return true;
    }

    public void afterPropertiesSet()
            throws Exception {
        Assert.notNull(this._$2);
    }

    public void setLoginUrl(String paramString) {
        this._$2 = paramString;
    }

    @Autowired
    public void setLoginSvc(LoginSvc paramLoginSvc) {
        this._$1 = paramLoginSvc;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.FrontSecureInterceptor
 * JD-Core Version:    0.6.2
 */