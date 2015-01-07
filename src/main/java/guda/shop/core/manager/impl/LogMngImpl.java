package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
imimport guda.shop.re.dao.LogDao;
impimport guda.shop.e.entity.Log;
import guda.shop.core.manager.LogMng;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogMngImpl
  implements LogMng
{
  private LogDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public Log findById(Long paramLong)
  {
    Log localLog = this._$1.findById(paramLong);
    return localLog;
  }

  public Log save(Log paramLog)
  {
    this._$1.save(paramLog);
    return paramLog;
  }

  public void save(String paramString1, String paramString2)
  {
    Date localDate = new Date();
    Log localLog = new Log();
    localLog.setContent(paramString2);
    localLog.setTitle(paramString1);
    localLog.setCategory(Integer.valueOf(1));
    localLog.setTime(localDate);
    this._$1.save(localLog);
  }

  public Log update(Log paramLog)
  {
    Updater localUpdater = new Updater(paramLog);
    Log localLog = this._$1.updateByUpdater(localUpdater);
    return localLog;
  }

  public Log deleteById(Long paramLong)
  {
    Log localLog = this._$1.deleteById(paramLong);
    return localLog;
  }

  public Log[] deleteByIds(Long[] paramArrayOfLong)
  {
    Log[] arrayOfLog = new Log[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfLog[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfLog;
  }

  @Autowired
  public void setDao(LogDao paramLogDao)
  {
    this._$1 = paramLogDao;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.LogMngImpl
 * JD-Core Version:    0.6.2
 */