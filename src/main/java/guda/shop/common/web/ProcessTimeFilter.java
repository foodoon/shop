package guda.shop.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ProcessTimeFilter
        implements Filter {
    public static final String START_TIME = "_start_time";
    protected final Logger log = LoggerFactory.getLogger(ProcessTimeFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
            throws IOException, ServletException {
        HttpServletRequest localHttpServletRequest = (HttpServletRequest) paramServletRequest;
        long l = System.currentTimeMillis();
        localHttpServletRequest.setAttribute("_start_time", Long.valueOf(l));
        paramFilterChain.doFilter(localHttpServletRequest, paramServletResponse);
        l = System.currentTimeMillis() - l;
        this.log.debug("process in {} ms: {}", Long.valueOf(l), localHttpServletRequest.getRequestURI());
    }

    public void init(FilterConfig paramFilterConfig)
            throws ServletException {
    }
}

