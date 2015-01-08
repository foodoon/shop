package guda.shop.common.web.springmvc;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTypeEditor extends PropertyEditorSupport {
    public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int SHORT_DATE = 10;

    public String getAsText() {
        java.util.Date localDate = (java.util.Date) getValue();
        return localDate != null ? DF_LONG.format(localDate) : "";
    }

    public void setAsText(String paramString)
            throws IllegalArgumentException {
        paramString = paramString.trim();
        if (!StringUtils.hasText(paramString)) {
            setValue(null);
            return;
        }
        try {
            if (paramString.length() <= 10)
                setValue(new java.sql.Date(DF_SHORT.parse(paramString).getTime()));
            else
                setValue(new Timestamp(DF_LONG.parse(paramString).getTime()));
        } catch (ParseException localParseException) {
            IllegalArgumentException localIllegalArgumentException = new IllegalArgumentException("Could not parse date: " + localParseException.getMessage());
            localIllegalArgumentException.initCause(localParseException);
            throw localIllegalArgumentException;
        }
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.DateTypeEditor
 * JD-Core Version:    0.6.2
 */