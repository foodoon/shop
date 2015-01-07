package guda.shop.common.hibernate3;

import java.io.Serializable;
import java.util.Comparator;

public class PriorityComparator
  implements Comparator<PriorityInterface>, Serializable
{
  public static final PriorityComparator INSTANCE = new PriorityComparator();

  public int compare(PriorityInterface paramPriorityInterface1, PriorityInterface paramPriorityInterface2)
  {
    Number localNumber1 = paramPriorityInterface1.getPriority();
    Number localNumber2 = paramPriorityInterface2.getPriority();
    Number localNumber3 = paramPriorityInterface1.getId();
    Number localNumber4 = paramPriorityInterface2.getId();
    if ((localNumber3 != null) && (localNumber4 != null) && (localNumber3.equals(localNumber4)))
      return 0;
    if (localNumber1 == null)
      return 1;
    if (localNumber2 == null)
      return -1;
    if (localNumber1.longValue() > localNumber2.longValue())
      return 1;
    if (localNumber1.longValue() < localNumber2.longValue())
      return -1;
    if (localNumber3 == null)
      return 1;
    if (localNumber4 == null)
      return -1;
    if (localNumber3.longValue() > localNumber4.longValue())
      return 1;
    if (localNumber3.longValue() < localNumber4.longValue())
      return -1;
    return 0;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.hibernate3.PriorityComparator
 * JD-Core Version:    0.6.2
 */