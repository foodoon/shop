package guda.shop.common.web.springmvc;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class BindingInitializer
        implements WebBindingInitializer {
    public void initBinder(WebDataBinder paramWebDataBinder, WebRequest paramWebRequest) {
        paramWebDataBinder.registerCustomEditor(Date.class, new DateTypeEditor());
        paramWebDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}

