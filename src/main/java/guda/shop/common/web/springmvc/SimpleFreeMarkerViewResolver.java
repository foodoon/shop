package guda.shop.common.web.springmvc;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class SimpleFreeMarkerViewResolver extends AbstractTemplateViewResolver
{
  public SimpleFreeMarkerViewResolver()
  {
    setViewClass(SimpleFreeMarkerView.class);
  }

  protected AbstractUrlBasedView buildView(String paramString)
    throws Exception
  {
    AbstractUrlBasedView localAbstractUrlBasedView = super.buildView(paramString);
    if (paramString.startsWith("/"))
      localAbstractUrlBasedView.setUrl(paramString + getSuffix());
    return localAbstractUrlBasedView;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.SimpleFreeMarkerViewResolver
 * JD-Core Version:    0.6.2
 */