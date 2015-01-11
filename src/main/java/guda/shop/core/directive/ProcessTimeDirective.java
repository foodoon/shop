package guda.shop.core.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.common.web.freemarker.MustNumberException;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;

public class ProcessTimeDirective extends WebDirective {
    private static final DecimalFormat _$1 = new DecimalFormat("0.000");

    public void execute(Environment paramEnvironment, Map paramMap, TemplateModel[] paramArrayOfTemplateModel, TemplateDirectiveBody paramTemplateDirectiveBody)
            throws TemplateException, IOException {
        long l = _$1(paramEnvironment);
        if (l == -1L)
            return;
        l = System.currentTimeMillis() - l;
        Writer localWriter = paramEnvironment.getOut();
        localWriter.append("Processed in " + _$1.format((float) l / 1000.0F) + " second(s)");
    }

    private long _$1(Environment paramEnvironment)
            throws TemplateModelException {
        TemplateModel localTemplateModel = paramEnvironment.getGlobalVariable("_start_time");
        if (localTemplateModel == null)
            return -1L;
        if ((localTemplateModel instanceof TemplateNumberModel))
            return ((TemplateNumberModel) localTemplateModel).getAsNumber().longValue();
        throw new MustNumberException("_start_time");
    }
}

