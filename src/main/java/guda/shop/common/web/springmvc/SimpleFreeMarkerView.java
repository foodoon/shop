package guda.shop.common.web.springmvc;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SimpleFreeMarkerView extends AbstractTemplateView {
    public static final String CONTEXT_PATH = "base";
    private Configuration _$1;

    protected Configuration getConfiguration() {
        return this._$1;
    }

    public void setConfiguration(Configuration paramConfiguration) {
        this._$1 = paramConfiguration;
    }

    protected FreeMarkerConfig autodetectConfiguration()
            throws BeansException {
        try {
            return (FreeMarkerConfig) BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(), FreeMarkerConfig.class, true, false);
        } catch (NoSuchBeanDefinitionException localNoSuchBeanDefinitionException) {
            throw new ApplicationContextException("Must define a single FreeMarkerConfig bean in this web application context (may be inherited): FreeMarkerConfigurer is the usual implementation. This bean may be given any name.", localNoSuchBeanDefinitionException);
        }
    }

    protected void initApplicationContext()
            throws BeansException {
        super.initApplicationContext();
        if (getConfiguration() == null) {
            FreeMarkerConfig localFreeMarkerConfig = autodetectConfiguration();
            setConfiguration(localFreeMarkerConfig.getConfiguration());
        }
        checkTemplate();
    }

    protected void checkTemplate()
            throws ApplicationContextException {
        try {
            getConfiguration().getTemplate(getUrl());
        } catch (ParseException localParseException) {
            throw new ApplicationContextException("Failed to parse FreeMarker template for URL [" + getUrl() + "]", localParseException);
        } catch (IOException localIOException) {
            throw new ApplicationContextException("Could not load FreeMarker template for URL [" + getUrl() + "]", localIOException);
        }
    }

    protected void renderMergedTemplateModel(Map paramMap, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
            throws Exception {
        paramMap.put("base", paramHttpServletRequest.getContextPath());
        getConfiguration().getTemplate(getUrl()).process(paramMap, paramHttpServletResponse.getWriter());
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.SimpleFreeMarkerView
 * JD-Core Version:    0.6.2
 */