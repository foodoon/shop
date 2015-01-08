package guda.shop.core.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.web.threadvariable.AdminThread;
import guda.shop.common.web.freemarker.MustStringException;
import guda.shop.common.web.freemarker.ParamsRequiredException;
import guda.shop.core.web.admin.AdminSecureInterceptor;
import org.springframework.web.servlet.support.RequestContext;

import java.io.IOException;
import java.util.Map;

public class AuthorizeDirective extends WebDirective {
    public static final String PARAM_URL = "url";
    private boolean _$1 = false;

    private static String _$1(String paramString1, String paramString2) {
        int i = 0;
        int j=0;
        for (j = paramString1.lastIndexOf("/"); paramString2.startsWith("../", i); j = paramString1.lastIndexOf("/", j - 1))
            i += 3;
        return paramString1.substring(0, j + 1) + paramString2.substring(i);
    }

    public void setDevelop(boolean paramBoolean) {
        this._$1 = paramBoolean;
    }

    public void execute(Environment paramEnvironment, Map paramMap, TemplateModel[] paramArrayOfTemplateModel, TemplateDirectiveBody paramTemplateDirectiveBody)
            throws TemplateException, IOException {
        ShopAdmin localShopAdmin = AdminThread.get();
        if (this._$1)
            paramTemplateDirectiveBody.render(paramEnvironment.getOut());
        String str1 = _$1(paramMap);
        RequestContext localRequestContext = getContext(paramEnvironment);
        String str2 = AdminSecureInterceptor.getURI(localRequestContext.getRequestUri(), localRequestContext.getContextPath());
        str2 = _$1(str2, str1);
        if (str2.contains("//")) {
            String[] localObject = str2.split("//");
            str2 = "/" + localObject[1];
        }
        Object localObject = _$1(paramEnvironment);
        boolean bool;
        if (localShopAdmin.getAdmin().isSuper())
            bool = true;
        else
            bool = _$1((TemplateSequenceModel) localObject, str2);
        if (bool)
            paramTemplateDirectiveBody.render(paramEnvironment.getOut());
    }

    private boolean _$1(TemplateSequenceModel paramTemplateSequenceModel, String paramString)
            throws TemplateModelException {
        int i = 0;
        int j = paramTemplateSequenceModel.size();
        while (i < j) {
            String str = ((TemplateScalarModel) paramTemplateSequenceModel.get(i)).getAsString();
            if ((paramString.equals(str)) || (paramString.startsWith(str)))
                return true;
            i++;
        }
        return false;
    }

    private TemplateSequenceModel _$1(Environment paramEnvironment)
            throws TemplateModelException {
        TemplateModel localTemplateModel = paramEnvironment.getGlobalVariable("_permission_key");
        if (localTemplateModel == null)
            throw new TemplateModelException("'_permission_key' not found in DataModel.");
        if ((localTemplateModel instanceof TemplateSequenceModel))
            return (TemplateSequenceModel) localTemplateModel;
        throw new TemplateModelException("'_permission_key' not instanse of TemplateSequenceModel: " + localTemplateModel.getClass().getName());
    }

    private String _$1(Map paramMap)
            throws TemplateException {
        TemplateModel localTemplateModel = (TemplateModel) paramMap.get("url");
        if (localTemplateModel == null)
            throw new ParamsRequiredException("url");
        if ((localTemplateModel instanceof TemplateScalarModel))
            return ((TemplateScalarModel) localTemplateModel).getAsString();
        throw new MustStringException("url");
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.directive.AuthorizeDirective
 * JD-Core Version:    0.6.2
 */