package guda.shop.common.web.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.common.util.StrUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class TextCutDirective
        implements TemplateDirectiveModel {
    public static final String PARAM_S = "s";
    public static final String PARAM_LEN = "len";
    public static final String PARAM_APPEND = "append";

    public void execute(Environment paramEnvironment, Map paramMap, TemplateModel[] paramArrayOfTemplateModel, TemplateDirectiveBody paramTemplateDirectiveBody)
            throws TemplateException, IOException {
        String str1 = DirectiveUtils.getString("s", paramMap);
        Integer localInteger = DirectiveUtils.getInt("len", paramMap);
        String str2 = DirectiveUtils.getString("append", paramMap);
        if (str1 != null) {
            Writer localWriter = paramEnvironment.getOut();
            if (localInteger != null)
                localWriter.append(StrUtils.textCut(str1, localInteger.intValue(), str2));
            else
                localWriter.append(str1);
        }
    }
}

