package guda.shop.common.web.session;

import guda.shop.common.web.session.cache.SessionCache;
import guda.shop.common.web.session.id.SessionIdGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CacheSessionProvider
        implements SessionProvider, InitializingBean {
    public static final String CURRENT_SESSION = "_current_session";
    public static final String CURRENT_SESSION_ID = "_current_session_id";
    private SessionCache _$3;
    private SessionIdGenerator _$2;
    private int _$1 = 30;

    public Serializable getAttribute(HttpServletRequest paramHttpServletRequest, String paramString) {
        Map localMap = (Map) paramHttpServletRequest.getAttribute("_current_session");
        if (localMap != null)
            return (Serializable) localMap.get(paramString);
        String str = (String) paramHttpServletRequest.getAttribute("_current_session_id");
        if (str == null)
            str = paramHttpServletRequest.getRequestedSessionId();
        if (StringUtils.isBlank(str)) {
            paramHttpServletRequest.setAttribute("_current_session", new HashMap());
            return null;
        }
        localMap = this._$3.getSession(str);
        if (localMap != null) {
            paramHttpServletRequest.setAttribute("_current_session_id", str);
            paramHttpServletRequest.setAttribute("_current_session", localMap);
            return (Serializable) localMap.get(paramString);
        }
        return null;
    }

    public void setAttribute(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Serializable paramSerializable) {
        Object localObject = (Map) paramHttpServletRequest.getAttribute("_current_session");
        String str;
        if (localObject == null) {
            str = paramHttpServletRequest.getRequestedSessionId();
            if ((str != null) && (str.length() == 32))
                localObject = this._$3.getSession(str);
            if (localObject == null) {
                localObject = new HashMap();
                do
                    str = this._$2.get();
                while (this._$3.exist(str));
                paramHttpServletResponse.addCookie(_$1(paramHttpServletRequest, str));
            }
            paramHttpServletRequest.setAttribute("_current_session", localObject);
            paramHttpServletRequest.setAttribute("_current_session_id", str);
        } else {
            str = (String) paramHttpServletRequest.getAttribute("_current_session_id");
            if (str == null) {
                do
                    str = this._$2.get();
                while (this._$3.exist(str));
                paramHttpServletResponse.addCookie(_$1(paramHttpServletRequest, str));
                paramHttpServletRequest.setAttribute("_current_session_id", str);
            }
        }
        ((Map) localObject).put(paramString, paramSerializable);
        this._$3.setSession(str, (Map) localObject, this._$1);
    }

    public String getSessionId(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        String str = (String) paramHttpServletRequest.getAttribute("_current_session_id");
        if (str != null)
            return str;
        str = paramHttpServletRequest.getRequestedSessionId();
        if ((str == null) || (str.length() != 32) || (!this._$3.exist(str))) {
            do
                str = this._$2.get();
            while (this._$3.exist(str));
            this._$3.setSession(str, new HashMap(), this._$1);
            paramHttpServletResponse.addCookie(_$1(paramHttpServletRequest, str));
        }
        paramHttpServletRequest.setAttribute("_current_session_id", str);
        return str;
    }

    public void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        paramHttpServletRequest.removeAttribute("_current_session");
        paramHttpServletRequest.removeAttribute("_current_session_id");
        String str = paramHttpServletRequest.getRequestedSessionId();
        if (!StringUtils.isBlank(str)) {
            this._$3.clear(str);
            Cookie localCookie = _$1(paramHttpServletRequest, null);
            localCookie.setMaxAge(0);
            paramHttpServletResponse.addCookie(localCookie);
        }
    }

    private Cookie _$1(HttpServletRequest paramHttpServletRequest, String paramString) {
        Cookie localCookie = new Cookie("JSESSIONID", paramString);
        String str = paramHttpServletRequest.getContextPath();
        localCookie.setPath(StringUtils.isBlank(str) ? "/" : str);
        return localCookie;
    }

    public void afterPropertiesSet()
            throws Exception {
        Assert.notNull(this._$3);
        Assert.notNull(this._$2);
    }

    public void setSessionCache(SessionCache paramSessionCache) {
        this._$3 = paramSessionCache;
    }

    public void setSessionTimeout(int paramInt) {
        Assert.isTrue(paramInt > 0);
        this._$1 = paramInt;
    }

    public void setSessionIdGenerator(SessionIdGenerator paramSessionIdGenerator) {
        this._$2 = paramSessionIdGenerator;
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.session.CacheSessionProvider
 * JD-Core Version:    0.6.2
 */