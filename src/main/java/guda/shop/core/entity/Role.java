package guda.shop.core.entity;

import guda.shop.core.entity.base.BaseRole;
import java.util.Collection;
import java.util.Iterator;

public class Role extends BaseRole
{
  private static final long serialVersionUID = 1L;

  public static Integer[] fetchIds(Collection<Role> paramCollection)
  {
    if (paramCollection == null)
      return null;
    Integer[] arrayOfInteger = new Integer[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Role localRole = (Role)localIterator.next();
      arrayOfInteger[(i++)] = localRole.getId();
    }
    return arrayOfInteger;
  }

  public Role()
  {
  }

  public Role(Integer paramInteger)
  {
    super(paramInteger);
  }

  public Role(Integer paramInteger1, String paramString, Integer paramInteger2, Boolean paramBoolean)
  {
    super(paramInteger1, paramString, paramInteger2, paramBoolean);
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.Role
 * JD-Core Version:    0.6.2
 */