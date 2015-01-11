package guda.shop.common.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter
        implements Filter {
    FilterConfig filterConfig = null;
    private String _$3;
    private String _$2;
    private String _$1;

    public void init(FilterConfig paramFilterConfig)
            throws ServletException {
        this._$3 = paramFilterConfig.getInitParameter("FilterChar");
        this._$2 = paramFilterConfig.getInitParameter("ReplaceChar");
        this._$1 = paramFilterConfig.getInitParameter("SplitChar");
        this.filterConfig = paramFilterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
            throws IOException, ServletException {
        paramFilterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) paramServletRequest, this._$3, this._$2, this._$1), paramServletResponse);
    }
}

