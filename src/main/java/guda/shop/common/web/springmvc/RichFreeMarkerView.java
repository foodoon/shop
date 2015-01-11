package guda.shop.common.web.springmvc;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RichFreeMarkerView extends FreeMarkerView {
    public static final String CONTEXT_PATH = "base";

    protected void exposeHelpers(Map paramMap, HttpServletRequest paramHttpServletRequest)
            throws Exception {
        super.exposeHelpers(paramMap, paramHttpServletRequest);
        paramMap.put("base", paramHttpServletRequest.getContextPath());
    }
}

