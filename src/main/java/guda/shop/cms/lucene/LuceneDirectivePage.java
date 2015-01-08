package guda.shop.cms.lucene;


import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LuceneDirectivePage extends LuceneDirectiveAbstract {
    public static final String TPL_NAME = "ProductPage";
    protected WebsiteMng websiteMng;


    @Autowired
    private LuceneProductSvc luceneProductSvc;


    @Autowired
    private ServletContext servletContext;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Website web = this.websiteMng.findById(Long.valueOf(1L));

        String query = getQuery(params);

        Long ctgId = getCtgId(params);


        Long type = getPtypeId(params);

        Date start = getStartDate(params);

        Date end = getEndDate(params);

        int pageNo = ((TemplateNumberModel) env.getGlobalVariable("pageNo")).getAsNumber().intValue();
        Pagination pagination;

        try {

            String path = this.servletContext.getRealPath("/WEB-INF/lucene");

            pagination = this.luceneProductSvc.search(path, query, web.getId(), ctgId, start, end, pageNo, getCount(params));

        } catch (ParseException e) {


            throw new RuntimeException(e);

        }

        Map paramWrap = new HashMap(
                params);

        paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));

        Map origMap =
                DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
            includeTpl("shop", "ProductPage", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

    }


    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneDirectivePage
 * JD-Core Version:    0.6.2
 */