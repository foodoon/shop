package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustNumberException extends TemplateModelException {
    public MustNumberException(String paramString) {
        super("The \"" + paramString + "\" parameter must be a number.");
    }
}

