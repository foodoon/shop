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

            String regular = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";

            Pattern p = Pattern.compile(regular);

            Matcher m = p.matcher(q);

            String src = null;

            while (m.find()) {

                src = m.group();

                q = q.replaceAll("\\" + src, "\\\\" + src);
            }

            q = q.replaceAll("AND", "and").replaceAll("OR", "or").replace("NOT", "not");
        } catch (Exception localException) {
        }

        return q;
    }

    @RequestMapping(value = {"/search*.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String search(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        model.putAll(RequestUtils.getQueryParams(request));

        ShopFrontHelper.setCommon(request, model, web);

        ShopFrontHelper.frontPage(request, model);

        String q = RequestUtils.getQueryParam(request, "q");

        q = StringUtils.trim(q);

        q = parseKeywords(q);

        model.addAttribute("q", q);

        String ctgId = RequestUtils.getQueryParam(request, "ctgId");

        ctgId = StringUtils.trim(ctgId);

        if ((StringUtils.isBlank(q)) && (StringUtils.isBlank(ctgId))) {

            model.remove("q");

            model.remove("ctgId");

            return web.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchInput", new Object[0]));
        }

        model.addAttribute("q", q);

        if (StringUtils.isBlank(ctgId))
            model.addAttribute("ctgId", null);
        else {

            model.addAttribute("ctgId", Integer.valueOf(ctgId));
        }

        this.keyWordMng.save(q);

        return web.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchResult", new Object[0]));
    }

    public String encodeFilename(HttpServletRequest request, String fileName) {

        String agent = request.getHeader("USER-AGENT");
        try {

            if ((agent != null) && (-1 != agent.indexOf("MSIE")))
                fileName = URLEncoder.encode(fileName, "UTF8");
            else
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return fileName;
    }

    public void setServletContext(ServletContext servletContext) {

        this.servletContext = servletContext;
    }
}

