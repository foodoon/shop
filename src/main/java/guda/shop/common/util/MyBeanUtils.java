package guda.shop.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;
import org.springframework.util.Assert;

public class MyBeanUtils
{
  public static Object getFieldValue(Object paramObject, String paramString)
  {
    Field localField = getDeclaredField(paramObject, paramString);
    if (localField == null)
      throw new IllegalArgumentException("Could not find field [" + paramString + "] on target [" + paramObject + "]");
    makeAccessible(localField);
    Object localObject = null;
    try
    {
      localObject = localField.get(paramObject);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException("never happend exception!", localIllegalAccessException);
    }
    return localObject;
  }

  public static void setFieldValue(Object paramObject1, String paramString, Object paramObject2)
  {
    Field localField = getDeclaredField(paramObject1, paramString);
    if (localField == null)
      throw new IllegalArgumentException("Could not find field [" + paramString + "] on target [" + paramObject1 + "]");
    makeAccessible(localField);
    try
    {
      localField.set(paramObject1, paramObject2);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException("never happend exception!", localIllegalAccessException);
    }
  }

  protected static Field getDeclaredField(Object paramObject, String paramString)
  {
    Assert.notNull(paramObject);
    return getDeclaredField(paramObject.getClass(), paramString);
  }

  protected static Field getDeclaredField(Class paramClass, String paramString)
  {
    Assert.notNull(paramClass);
    Assert.hasText(paramString);
    Class localClass = paramClass;
    while (localClass != Object.class)
      try
      {
        return localClass.getDeclaredField(paramString);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        localClass = localClass.getSuperclass();
      }
    return null;
  }

  protected static void makeAccessible(Field paramField)
  {
    if ((!Modifier.isPublic(paramField.getModifiers())) || (!Modifier.isPublic(paramField.getDeclaringClass().getModifiers())))
      paramField.setAccessible(true);
  }

  public static Object getSimpleProperty(Object paramObject, String paramString)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    return paramObject.getClass().getMethod(_$1(paramString), new Class[0]).invoke(paramObject, new Object[0]);
  }

  private static String _$1(String paramString)
  {
    return "get" + paramString.substring(0, 1).toUpperCase(Locale.ENGLISH) + paramString.substring(1);
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.util.MyBeanUtils
 * JD-Core Version:    0.6.2
 */