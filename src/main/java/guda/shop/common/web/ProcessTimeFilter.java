package guda.shop.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessTimeFilter
  implements Filter
{
  protected final Logger log = LoggerFactory.getLogger(ProcessTimeFilter.class);
  public static final String START_TIME = "_start_time";

  public void destroy()
  {
  }

  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws IOException, ServletException
  {
    HttpServletRequest localHttpServletRequest = (HttpServletRequest)paramServletRequest;
    long l = System.currentTimeMillis();
    localHttpServletRequest.setAttribute("_start_time", Long.valueOf(l));
    paramFilterChain.doFilter(localHttpServletRequest, paramServletResponse);
    l = System.currentTimeMillis() - l;
    this.log.debug("process in {} ms: {}", Long.valueOf(l), localHttpServletRequest.getRequestURI());
  }

  public void init(FilterConfig paramFilterConfig)
    throws ServletException
  {
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.ProcessTimeFilter
 * JD-Core Version:    0.6.2
 */