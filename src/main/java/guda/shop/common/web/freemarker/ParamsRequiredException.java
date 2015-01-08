package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class ParamsRequiredException extends TemplateModelException {
    public ParamsRequiredException(String paramString) {
        super("The required \"" + paramString + "\" paramter is missing.");
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.freemarker.ParamsRequiredException
 * JD-Core Version:    0.6.2
 */