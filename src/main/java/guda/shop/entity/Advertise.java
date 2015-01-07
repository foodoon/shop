package guda.shop.entity;

import guda.shop.cms.entity.base.BaseAdvertise;

public class Advertise extends BaseAdvertise
{
  private static final long serialVersionUID = 1L;

  public void init()
  {
    if (getClickCount() == null)
      setClickCount(Integer.valueOf(0));
    if (getDisplayCount() == null)
      setDisplayCount(Integer.valueOf(0));
    if (getEnabled() == null)
      setEnabled(Boolean.valueOf(true));
    if (getWeight() == null)
      setWeight(Integer.valueOf(1));
  }

  public Advertise()
  {
  }

  public Advertise(Integer paramInteger)
  {
    super(paramInteger);
  }

  public Advertise(Integer paramInteger, Adspace paramAdspace)
  {
    super(paramInteger, paramAdspace);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Advertise
 * JD-Core Version:    0.6.2
 */