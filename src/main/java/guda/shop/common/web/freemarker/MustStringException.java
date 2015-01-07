package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustStringException extends TemplateModelException
{
  public MustStringException(String paramString)
  {
    super("The \"" + paramString + "\" parameter must be a string.");
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.freemarker.MustStringException
 * JD-Core Version:    0.6.2
 */