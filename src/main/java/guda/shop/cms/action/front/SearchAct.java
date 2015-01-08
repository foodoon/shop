package guda.shop.cms.action.front;

import guda.shop.cms.manager.KeyWordMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class SearchAct
        implements ServletContextAware {
    private static final String SEARCH_INPUT = "tpl.searchInput";
    private static final String SEARCH_RESULT = "tpl.searchResult";
    private ServletContext servletContext;

    @Autowired
    private KeyWordMng keyWordMng;

    public static String parseKeywords(String q) {
        try {
/*  83 */
            String regular = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";
/*  84 */
            Pattern p = Pattern.compile(regular);
/*  85 */
            Matcher m = p.matcher(q);
/*  86 */
            String src = null;
/*  87 */
            while (m.find()) {
/*  88 */
                src = m.group();
/*  89 */
                q = q.replaceAll("\\" + src, "\\\\" + src);
            }
/*  91 */
            q = q.replaceAll("AND", "and").replaceAll("OR", "or").replace("NOT", "not");
        } catch (Exception localException) {
        }
/*  94 */
        return q;
    }

    @RequestMapping(value = {"/search*.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String search(HttpServletRequest request, ModelMap model) {
/*  39 */
        Website web = SiteUtils.getWeb(request);
/*  40 */
        model.putAll(RequestUtils.getQueryParams(request));
/*  41 */
        ShopFrontHelper.setCommon(request, model, web);
/*  42 */
        ShopFrontHelper.frontPage(request, model);
/*  43 */
        String q = RequestUtils.getQueryParam(request, "q");
/*  44 */
        q = StringUtils.trim(q);
/*  45 */
        q = parseKeywords(q);
/*  46 */
        model.addAttribute("q", q);
/*  47 */
        String ctgId = RequestUtils.getQueryParam(request, "ctgId");
/*  48 */
        ctgId = StringUtils.trim(ctgId);
/*  49 */
        if ((StringUtils.isBlank(q)) && (StringUtils.isBlank(ctgId))) {
/*  50 */
            model.remove("q");
/*  51 */
            model.remove("ctgId");
/*  52 */
            return web.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchInput", new Object[0]));
        }
/*  54 */
        model.addAttribute("q", q);
/*  55 */
        if (StringUtils.isBlank(ctgId))
/*  56 */ model.addAttribute("ctgId", null);
        else {
/*  58 */
            model.addAttribute("ctgId", Integer.valueOf(ctgId));
        }
/*  60 */
        this.keyWordMng.save(q);
/*  61 */
        return web.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchResult", new Object[0]));
    }

    public String encodeFilename(HttpServletRequest request, String fileName) {
/*  66 */
        String agent = request.getHeader("USER-AGENT");
        try {
/*  69 */
            if ((agent != null) && (-1 != agent.indexOf("MSIE")))
/*  70 */ fileName = URLEncoder.encode(fileName, "UTF8");
            else
/*  72 */         fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
/*  75 */
            e.printStackTrace();
        }
/*  77 */
        return fileName;
    }

    public void setServletContext(ServletContext servletContext) {
/* 100 */
        this.servletContext = servletContext;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.SearchAct
 * JD-Core Version:    0.6.2
 */