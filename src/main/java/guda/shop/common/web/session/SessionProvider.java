package guda.shop.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract interface SessionProvider {
    public static final String JSESSION_COOKIE = "JSESSIONID";
    public static final String JSESSION_URL = "jsessionid";

    public abstract Serializable getAttribute(HttpServletRequest paramHttpServletRequest, String paramString);

    public abstract void setAttribute(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Serializable paramSerializable);

    public abstract String getSessionId(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}

