package guda.shop.core.web;

import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Locale;

public class WebErrors extends guda.shop.common.web.springmvc.WebErrors {
    public static final String ERROR_PAGE = "/common/error_message";
    public static final String ERROR_ATTR_NAME = "errors";

    public WebErrors() {
    }

    public WebErrors(HttpServletRequest paramHttpServletRequest) {
        super(paramHttpServletRequest);
    }

    public WebErrors(MessageSource paramMessageSource, Locale paramLocale) {
        super(paramMessageSource, paramLocale);
    }

    public static WebErrors create(HttpServletRequest paramHttpServletRequest) {
        return new WebErrors(paramHttpServletRequest);
    }

    public void notInWeb(Class paramClass, Serializable paramSerializable) {
        addErrorCode("error.notInWeb", new Object[]{paramClass.getSimpleName(), paramSerializable});
    }

    protected String getErrorAttrName() {
        return "errors";
    }

    protected String getErrorPage() {
        return "/common/error_message";
    }
}

