package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.StandardTypeDao;
import guda.shop.cms.entity.StandardType;
import guda.shop.cms.manager.StandardTypeMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StandardTypeMngImpl
  implements StandardTypeMng
{
  private StandardTypeDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public StandardType getByField(String paramString)
  {
    return this._$1.getByField(paramString);
  }

  @Transactional(readOnly=true)
  public StandardType findById(Long paramLong)
  {
    StandardType localStandardType = this._$1.findById(paramLong);
    return localStandardType;
  }

  public List<StandardType> getList()
  {
    return this._$1.getList();
  }

  public List<StandardType> getList(Long paramLong)
  {
    return this._$1.getList(paramLong);
  }

  public StandardType save(StandardType paramStandardType)
  {
    paramStandardType = this._$1.save(paramStandardType);
    return paramStandardType;
  }

  public StandardType update(StandardType paramStandardType)
  {
    Updater localUpdater = new Updater(paramStandardType);
    StandardType localStandardType = this._$1.updateByUpdater(localUpdater);
    return localStandardType;
  }

  public StandardType deleteById(Long paramLong)
  {
    StandardType localStandardType = this._$1.deleteById(paramLong);
    return localStandardType;
  }

  public StandardType[] deleteByIds(Long[] paramArrayOfLong)
  {
    StandardType[] arrayOfStandardType = new StandardType[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfStandardType[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfStandardType;
  }

  public StandardType[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger)
  {
    int i = paramArrayOfLong.length;
    StandardType[] arrayOfStandardType = new StandardType[i];
    for (int j = 0; j < i; j++)
    {
      arrayOfStandardType[j] = findById(paramArrayOfLong[j]);
      arrayOfStandardType[j].setPriority(paramArrayOfInteger[j]);
    }
    return arrayOfStandardType;
  }

  @Autowired
  public void setDao(StandardTypeDao paramStandardTypeDao)
  {
    this._$1 = paramStandardTypeDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.StandardTypeMngImpl
 * JD-Core Version:    0.6.2
 */