package guda.shop.core.web;

import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebsiteFilter
        implements Filter {
    private WebsiteMng websiteMng;

    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
            throws IOException, ServletException {
        HttpServletRequest localHttpServletRequest = (HttpServletRequest) paramServletRequest;
        HttpServletResponse localHttpServletResponse = (HttpServletResponse) paramServletResponse;
        String str1 = localHttpServletRequest.getServerName();
        Website localWebsite = this.websiteMng.getWebsite(str1);
        if (localWebsite != null) {
            localHttpServletRequest.setAttribute("_web_key", localWebsite);
            String str2 = localWebsite.getBaseDomain();
            if (!StringUtils.isBlank(str2))
                localHttpServletRequest.setAttribute("_base_domain_key", str2);
            paramFilterChain.doFilter(localHttpServletRequest, localHttpServletResponse);
        } else {
            localHttpServletResponse.sendError(404, "domain '" + str1 + "' not found.");
        }
    }

    public void init(FilterConfig paramFilterConfig)
            throws ServletException {
        WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(paramFilterConfig.getServletContext());
        this.websiteMng = ((WebsiteMng) BeanFactoryUtils.beanOfTypeIncludingAncestors(localWebApplicationContext, WebsiteMng.class, true, false));
    }

    public void destroy() {
    }
}

