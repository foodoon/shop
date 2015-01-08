package guda.shop.cms.lucene;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.common.web.freemarker.DirectiveUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public abstract class LuceneDirectiveAbstract extends WebDirective
        implements TemplateDirectiveModel {
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_WEBSITE_ID = "websiteId";
    public static final String PARAM_PTYPE_ID = "ptypeId";
    public static final String PARAM_START_DATE = "startDate";
    public static final String PARAM_END_DATE = "endDate";
    public static final String CTG_ID = "ctgId";
    public static final String PARAM_TAG_ID = "tagId";
    public static final String PARAM_RECOMMEND = "recommend";
    public static final String PARAM_SPECIAL = "special";

    protected String getQuery(Map<String, TemplateModel> params)
            throws TemplateException {

        return DirectiveUtils.getString("q", params);
    }

    protected Long getWebSiteId(Map<String, TemplateModel> params) throws TemplateException {

        return DirectiveUtils.getLong("websiteId", params);
    }

    protected Long getPtypeId(Map<String, TemplateModel> params) throws TemplateException {

        return DirectiveUtils.getLong("ptypeId", params);
    }

    protected Date getStartDate(Map<String, TemplateModel> params) throws TemplateException {

        return DirectiveUtils.getDate("startDate", params);
    }

    protected Date getEndDate(Map<String, TemplateModel> params) throws TemplateException {

        return DirectiveUtils.getDate("endDate", params);
    }

    protected Long getCtgId(Map<String, TemplateModel> params) throws TemplateException {

        return getLong("ctgId", params);
    }

    protected Long getTagId(Map<String, TemplateModel> params)
            throws TemplateException {

        return getLong("tagId", params);
    }

    protected Boolean isRecommend(Map<String, TemplateModel> params) throws TemplateException {

        return getBool("recommend", params);
    }

    protected Boolean isSpecial(Map<String, TemplateModel> params) throws TemplateException {

        return getBool("special", params);
    }

    protected Boolean isHostSale(Map<String, TemplateModel> params) throws TemplateException {

        return getBool("hostSale", params);
    }

    protected Boolean isNewProduct(Map<String, TemplateModel> params) throws TemplateException {

        return getBool("newProduct", params);
    }

    protected void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        body.render(env.getOut());
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneDirectiveAbstract
 * JD-Core Version:    0.6.2
 */