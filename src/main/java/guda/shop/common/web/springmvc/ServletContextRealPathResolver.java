package guda.shop.common.web.springmvc;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class ServletContextRealPathResolver
        implements RealPathResolver, ServletContextAware {
    private ServletContext _$1;

    public String get(String paramString) {
        return this._$1.getRealPath(paramString);
    }

    public void setServletContext(ServletContext paramServletContext) {
        this._$1 = paramServletContext;
    }
}

