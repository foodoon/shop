package guda.shop.manager.impl;

import guda.shop.cms.dao.DataBackupDao;
iimport guda.shopcms.entity.DataBackup;
imimport guda.shopms.manager.DataBackupMng;
import com.jspgou.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataBackupMngImpl
  implements DataBackupMng
{

  @Autowired
  private DataBackupDao _$1;

  public DataBackup getDataBackup()
  {
    return this._$1.getDataBackup();
  }

  public DataBackup update(DataBackup paramDataBackup)
  {
    Updater localUpdater = new Updater(paramDataBackup);
    DataBackup localDataBackup = this._$1.updateByUpdater(localUpdater);
    return localDataBackup;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.DataBackupMngImpl
 * JD-Core Version:    0.6.2
 */