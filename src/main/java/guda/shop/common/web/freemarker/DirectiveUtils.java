package guda.shop.common.web.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.common.web.springmvc.DateTypeEditor;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.Map.Entry;

public abstract class DirectiveUtils {
    public static Map<String, TemplateModel> addParamsToVariable(Environment paramEnvironment, Map<String, TemplateModel> paramMap)
            throws TemplateException {
        HashMap localHashMap = new HashMap();
        if (paramMap.size() <= 0)
            return localHashMap;
        Set localSet = paramMap.entrySet();
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            Entry localEntry = (Entry) localIterator.next();
            String str = (String) localEntry.getKey();
            TemplateModel localTemplateModel = paramEnvironment.getVariable(str);
            if (localTemplateModel != null)
                localHashMap.put(str, localTemplateModel);
            paramEnvironment.setVariable(str, (TemplateModel) localEntry.getValue());
        }
        return localHashMap;
    }

    public static void removeParamsFromVariable(Environment paramEnvironment, Map<String, TemplateModel> paramMap1, Map<String, TemplateModel> paramMap2)
            throws TemplateException {
        if (paramMap1.size() <= 0)
            return;
        Iterator localIterator = paramMap1.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            paramEnvironment.setVariable(str, (TemplateModel) paramMap2.get(str));
        }
    }

    public static boolean getBoolean(TemplateScalarModel paramTemplateScalarModel)
            throws TemplateModelException {
        return "1".equals(paramTemplateScalarModel.getAsString());
    }

    public static String getString(String paramString, Map<String, TemplateModel> paramMap)
            throws TemplateException {
        TemplateModel localTemplateModel = (TemplateModel) paramMap.get(paramString);
        if (localTemplateModel == null)
            return null;
        if ((localTemplateModel instanceof TemplateScalarModel))
            return ((TemplateScalarModel) localTemplateModel).getAsString();
        if ((localTemplateModel instanceof TemplateNumberModel))
            return ((TemplateNumberModel) localTemplateModel).getAsNumber().toString();
        throw new MustStringException(paramString);
    }

    public static Long getLong(String paramString, Map<String, TemplateModel> paramMap)
            throws TemplateException {
        TemplateModel localTemplateModel = (TemplateModel) paramMap.get(paramString);
        if (localTemplateModel == null)
            return null;
        if ((localTemplateModel instanceof TemplateScalarModel)) {
            String str = ((TemplateScalarModel) localTemplateModel).getAsString();
            if (StringUtils.isBlank(str))
                return null;
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException localNumberFormatException) {
                throw new MustNumberException(paramString);
            }
        }
        if ((localTemplateModel instanceof TemplateNumberModel))
            return Long.valueOf(((TemplateNumberModel) localTemplateModel).getAsNumber().longValue());
        throw new MustNumberException(paramString);
    }

    public static Integer getInt(String paramString, Map<String, TemplateModel> paramMap)
            throws TemplateException {
        TemplateModel localTemplateModel = (TemplateModel) paramMap.get(paramString);
        if (localTemplateModel == null)
            return null;
        if ((localTemplateModel instanceof TemplateScalarModel)) {
            String str = ((TemplateScalarModel) localTemplateModel).getAsString();
            if (StringUtils.isBlank(str))
                return null;
            try {
                return Integer.valueOf(Integer.parseInt(str));
            } catch (NumberFormatException localNumberFormatException) {
                throw new MustNumberException(paramString);
            }
        }
        if ((localTemplateModel instanceof TemplateNumberModel))
            return Integer.valueOf(((TemplateNumberModel) localTemplateModel).getAsNumber().intValue());
        throw new MustNumberException(paramString);
    }

    public static Date getDate(String paramString, Map<String, TemplateModel> paramMap)
            throws TemplateException {
        TemplateModel localTemplateModel = (TemplateModel) paramMap.get(paramString);
        if (localTemplateModel == null)
            return null;
        if ((localTemplateModel instanceof TemplateDateModel))
            return ((TemplateDateModel) localTemplateModel).getAsDate();
        if ((localTemplateModel instanceof TemplateScalarModel)) {
            DateTypeEditor localDateTypeEditor = new DateTypeEditor();
            localDateTypeEditor.setAsText(((TemplateScalarModel) localTemplateModel).getAsString());
            return (Date) localDateTypeEditor.getValue();
        }
        throw new MustDateException(paramString);
    }
}

