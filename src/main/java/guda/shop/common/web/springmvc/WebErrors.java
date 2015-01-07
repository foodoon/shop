package guda.shop.common.web.springmvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public abstract class WebErrors
{
  public static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+$");
  public static final Pattern USERNAME_PATTERN = Pattern.compile("^[0-9a-zA-Z\\u4e00-\\u9fa5\\.\\-@_]+$");
  private MessageSource _$3;
  private Locale _$2;
  private List<String> _$1;

  public WebErrors(HttpServletRequest paramHttpServletRequest)
  {
    WebApplicationContext localWebApplicationContext = RequestContextUtils.getWebApplicationContext(paramHttpServletRequest);
    if (localWebApplicationContext != null)
    {
      LocaleResolver localLocaleResolver = RequestContextUtils.getLocaleResolver(paramHttpServletRequest);
      if (localLocaleResolver != null)
      {
        Locale localLocale = localLocaleResolver.resolveLocale(paramHttpServletRequest);
        this._$3 = localWebApplicationContext;
        this._$2 = localLocale;
      }
    }
  }

  public WebErrors()
  {
  }

  public WebErrors(MessageSource paramMessageSource, Locale paramLocale)
  {
    this._$3 = paramMessageSource;
    this._$2 = paramLocale;
  }

  public String getMessage(String paramString, Object[] paramArrayOfObject)
  {
    if (this._$3 == null)
      throw new IllegalStateException("MessageSource cannot be null.");
    return this._$3.getMessage(paramString, paramArrayOfObject, this._$2);
  }

  public void addErrorCode(String paramString, Object[] paramArrayOfObject)
  {
    getErrors().add(getMessage(paramString, paramArrayOfObject));
  }

  public void addErrorCode(String paramString)
  {
    getErrors().add(getMessage(paramString, new Object[0]));
  }

  public void addErrorString(String paramString)
  {
    getErrors().add(paramString);
  }

  public void addError(String paramString)
  {
    if (this._$3 != null)
      paramString = this._$3.getMessage(paramString, null, paramString, this._$2);
    getErrors().add(paramString);
  }

  public boolean hasErrors()
  {
    return (this._$1 != null) && (this._$1.size() > 0);
  }

  public int getCount()
  {
    return this._$1 == null ? 0 : this._$1.size();
  }

  public List<String> getErrors()
  {
    if (this._$1 == null)
      this._$1 = new ArrayList();
    return this._$1;
  }

  public String showErrorPage(ModelMap paramModelMap)
  {
    toModel(paramModelMap);
    return getErrorPage();
  }

  public void toModel(Map<String, Object> paramMap)
  {
    Assert.notNull(paramMap);
    if (!hasErrors())
      throw new IllegalStateException("no errors found!");
    paramMap.put(getErrorAttrName(), getErrors());
  }

  public boolean ifNull(Object paramObject, String paramString)
  {
    if (paramObject == null)
    {
      addErrorCode("error.required", new Object[] { paramString });
      return true;
    }
    return false;
  }

  public boolean ifEmpty(Object[] paramArrayOfObject, String paramString)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length <= 0))
    {
      addErrorCode("error.required", new Object[] { paramString });
      return true;
    }
    return false;
  }

  public boolean ifBlank(String paramString1, String paramString2, int paramInt)
  {
    if (StringUtils.isBlank(paramString1))
    {
      addErrorCode("error.required", new Object[] { paramString2 });
      return true;
    }
    return ifMaxLength(paramString1, paramString2, paramInt);
  }

  public boolean ifMaxLength(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 != null) && (paramString1.length() > paramInt))
    {
      addErrorCode("error.maxLength", new Object[] { paramString2, Integer.valueOf(paramInt) });
      return true;
    }
    return false;
  }

  public boolean ifOutOfLength(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (paramString1 == null)
    {
      addErrorCode("error.required", new Object[] { paramString2 });
      return true;
    }
    int i = paramString1.length();
    if ((i < paramInt1) || (i > paramInt2))
    {
      addErrorCode("error.outOfLength", new Object[] { paramString2, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      return true;
    }
    return false;
  }

  public boolean ifNotEmail(String paramString1, String paramString2, int paramInt)
  {
    if (ifBlank(paramString1, paramString2, paramInt))
      return true;
    Matcher localMatcher = EMAIL_PATTERN.matcher(paramString1);
    if (!localMatcher.matches())
    {
      addErrorCode("error.email", new Object[] { paramString2 });
      return true;
    }
    return false;
  }

  public boolean ifNotUsername(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (ifOutOfLength(paramString1, paramString2, paramInt1, paramInt2))
      return true;
    Matcher localMatcher = USERNAME_PATTERN.matcher(paramString1);
    if (!localMatcher.matches())
    {
      addErrorCode("error.username", new Object[] { paramString2 });
      return true;
    }
    return false;
  }

  public boolean ifNotExist(Object paramObject, Class<?> paramClass, Serializable paramSerializable)
  {
    if (paramObject == null)
    {
      addErrorCode("error.notExist", new Object[] { paramClass.getSimpleName(), paramSerializable });
      return true;
    }
    return false;
  }

  public void noPermission(Class<?> paramClass, Serializable paramSerializable)
  {
    addErrorCode("error.noPermission", new Object[] { paramClass.getSimpleName(), paramSerializable });
  }

  public MessageSource getMessageSource()
  {
    return this._$3;
  }

  public void setMessageSource(MessageSource paramMessageSource)
  {
    this._$3 = paramMessageSource;
  }

  public Locale getLocale()
  {
    return this._$2;
  }

  public void setLocale(Locale paramLocale)
  {
    this._$2 = paramLocale;
  }

  protected abstract String getErrorPage();

  protected abstract String getErrorAttrName();
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.WebErrors
 * JD-Core Version:    0.6.2
 */