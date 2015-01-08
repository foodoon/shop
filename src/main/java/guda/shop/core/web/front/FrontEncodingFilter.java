package guda.shop.core.web.front;

import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FrontEncodingFilter
        implements Filter {
    public static final String AJAX_HEAD = "isAjax";
    private static final Logger _$1 = LoggerFactory.getLogger(FrontEncodingFilter.class);

    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
            throws IOException, ServletException {
        HttpServletRequest localHttpServletRequest = (HttpServletRequest) paramServletRequest;
        Website localWebsite = SiteUtils.getWeb(localHttpServletRequest);
        String str = localHttpServletRequest.getHeader("isAjax");
        if ((str != null) && ("true".equals(str))) {
            localHttpServletRequest.setCharacterEncoding("UTF-8");
            _$1.debug("ajax request");
        } else {
            localHttpServletRequest.setCharacterEncoding(localWebsite.getFrontEncoding());
            paramServletResponse.setContentType(localWebsite.getFrontContentType());
        }
        paramFilterChain.doFilter(localHttpServletRequest, paramServletResponse);
    }

    public void init(FilterConfig paramFilterConfig)
            throws ServletException {
    }

    public void destroy() {
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.front.FrontEncodingFilter
 * JD-Core Version:    0.6.2
 */