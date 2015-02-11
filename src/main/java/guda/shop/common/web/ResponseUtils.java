package guda.shop.common.web;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ResponseUtils {
    public static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

    public static void renderJson(HttpServletResponse paramHttpServletResponse, String paramString) {
        render(paramHttpServletResponse, "application/json;charset=UTF-8", paramString);
    }

    public static void renderJsonString(HttpServletResponse paramHttpServletResponse, String paramString) {
        render(paramHttpServletResponse, "application/json;charset=UTF-8", JSON.toJSONString(paramString));
    }

    public static void renderText(HttpServletResponse paramHttpServletResponse, String paramString) {
        render(paramHttpServletResponse, "text/plain;charset=UTF-8", paramString);
    }

    public static void render(HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2) {
        paramHttpServletResponse.setContentType(paramString1);
        paramHttpServletResponse.setHeader("Pragma", "No-cache");
        paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
        paramHttpServletResponse.setDateHeader("Expires", 0L);
        try {
            paramHttpServletResponse.getWriter().write((paramString2));
        } catch (IOException localIOException) {
            log.error(localIOException.getMessage(), localIOException);
        }
    }
}

