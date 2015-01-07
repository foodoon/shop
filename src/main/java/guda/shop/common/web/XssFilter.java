package guda.shop.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter
  implements Filter
{
  private String _$3;
  private String _$2;
  private String _$1;
  FilterConfig filterConfig = null;

  public void init(FilterConfig paramFilterConfig)
    throws ServletException
  {
    this._$3 = paramFilterConfig.getInitParameter("FilterChar");
    this._$2 = paramFilterConfig.getInitParameter("ReplaceChar");
    this._$1 = paramFilterConfig.getInitParameter("SplitChar");
    this.filterConfig = paramFilterConfig;
  }

  public void destroy()
  {
    this.filterConfig = null;
  }

  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws IOException, ServletException
  {
    paramFilterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)paramServletRequest, this._$3, this._$2, this._$1), paramServletResponse);
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.XssFilter
 * JD-Core Version:    0.6.2
 */