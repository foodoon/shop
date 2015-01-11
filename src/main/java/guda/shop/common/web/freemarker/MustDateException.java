package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustDateException extends TemplateModelException {
    public MustDateException(String paramString) {
        super("The \"" + paramString + "\" parameter must be a date.");
    }
}

