package guda.shop.manager;

import guda.shop.cms.entity.Adspace;
import java.util.List;

public abstract interface AdspaceMng
{
  public abstract Adspace findById(Integer paramInteger);

  public abstract Adspace save(Adspace paramAdspace);

  public abstract Adspace updateByUpdater(Adspace paramAdspace);

  public abstract Adspace deleteById(Integer paramInteger);

  public abstract Adspace updateByAdspacenumb(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);

  public abstract Adspace[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract List<Adspace> getList();
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.AdspaceMng
 * JD-Core Version:    0.6.2
 */