package guda.shop.common.hibernate3;

import java.util.HashSet;
import java.util.Set;

public class Updater<T>
{
  private T _$4;
  private Set<String> _$3 = new HashSet();
  private Set<String> _$2 = new HashSet();
  private UpdateMode _$1 = UpdateMode.MIDDLE;

  public Updater(T paramT)
  {
    this._$4 = paramT;
  }

  public Updater(T paramT, UpdateMode paramUpdateMode)
  {
    this._$4 = paramT;
    this._$1 = paramUpdateMode;
  }

  public Updater<T> setUpdateMode(UpdateMode paramUpdateMode)
  {
    this._$1 = paramUpdateMode;
    return this;
  }

  public Updater<T> include(String paramString)
  {
    this._$3.add(paramString);
    return this;
  }

  public Updater<T> exclude(String paramString)
  {
    this._$2.add(paramString);
    return this;
  }

  public boolean isUpdate(String paramString, Object paramObject)
  {
    if (this._$1 == UpdateMode.MAX)
      return !this._$2.contains(paramString);
    if (this._$1 == UpdateMode.MIN)
      return this._$3.contains(paramString);
    if (this._$1 == UpdateMode.MIDDLE)
    {
      if (paramObject != null)
        return !this._$2.contains(paramString);
      return this._$3.contains(paramString);
    }
    return true;
  }

  public T getBean()
  {
    return this._$4;
  }

  public Set<String> getExcludeProperties()
  {
    return this._$2;
  }

  public Set<String> getIncludeProperties()
  {
    return this._$3;
  }

  public static enum UpdateMode
  {
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.hibernate3.Updater
 * JD-Core Version:    0.6.2
 */