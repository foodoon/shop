package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class ParamsRequiredException extends TemplateModelException {
    public ParamsRequiredException(String paramString) {
        super("The required \"" + paramString + "\" paramter is missing.");
    }
}

