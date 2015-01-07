package guda.shop.cms.dao;

import guda.shop.cms.entity.KeyWord;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface KeyWordDao
{
  public abstract List<KeyWord> getAllList();

  public abstract KeyWord findById(Integer paramInteger);

  public abstract KeyWord save(KeyWord paramKeyWord);

  public abstract KeyWord updateByUpdater(Updater<KeyWord> paramUpdater);

  public abstract KeyWord deleteById(Integer paramInteger);

  public abstract List<KeyWord> getKeyWordContent(String paramString);

  public abstract List<KeyWord> findKeyWord(Integer paramInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.KeyWordDao
 * JD-Core Version:    0.6.2
 */