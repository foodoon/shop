package guda.shop.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class HttpSessionProvider
        implements SessionProvider {
    public Serializable getAttribute(HttpServletRequest paramHttpServletRequest, String paramString) {
        HttpSession localHttpSession = paramHttpServletRequest.getSession(false);
        if (localHttpSession != null)
            return (Serializable) localHttpSession.getAttribute(paramString);
        return null;
    }

    public void setAttribute(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Serializable paramSerializable) {
        HttpSession localHttpSession = paramHttpServletRequest.getSession();
        localHttpSession.setAttribute(paramString, paramSerializable);
    }

    public String getSessionId(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        return paramHttpServletRequest.getSession().getId();
    }

    public void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        HttpSession localHttpSession = paramHttpServletRequest.getSession(false);
        if (localHttpSession != null)
            localHttpSession.invalidate();
    }
}

