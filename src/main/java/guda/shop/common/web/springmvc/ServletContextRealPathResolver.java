package guda.shop.common.web.springmvc;

import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class ServletContextRealPathResolver
  implements RealPathResolver, ServletContextAware
{
  private ServletContext _$1;

  public String get(String paramString)
  {
    return this._$1.getRealPath(paramString);
  }

  public void setServletContext(ServletContext paramServletContext)
  {
    this._$1 = paramServletContext;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.ServletContextRealPathResolver
 * JD-Core Version:    0.6.2
 */