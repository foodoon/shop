package guda.shop.common.web.springmvc;

import java.util.Date;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class BindingInitializer
  implements WebBindingInitializer
{
  public void initBinder(WebDataBinder paramWebDataBinder, WebRequest paramWebRequest)
  {
    paramWebDataBinder.registerCustomEditor(Date.class, new DateTypeEditor());
    paramWebDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.BindingInitializer
 * JD-Core Version:    0.6.2
 */