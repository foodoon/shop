package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustStringException extends TemplateModelException {
    public MustStringException(String paramString) {
        super("The \"" + paramString + "\" parameter must be a string.");
    }
}

