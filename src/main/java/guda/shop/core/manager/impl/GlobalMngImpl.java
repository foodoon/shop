package guda.shop.core.manager.impl;

import guda.shop.core.dao.GlobalDao;
iimport guda.shop.ore.entity.Global;
import guda.shop.core.manager.GlobalMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GlobalMngImpl
  implements GlobalMng
{
  private GlobalDao _$1;

  public Global findById(Long paramLong)
  {
    return this._$1.findById(paramLong);
  }

  public Global update(Global paramGlobal)
  {
    return this._$1.update(paramGlobal);
  }

  @Autowired
  public void setDao(GlobalDao paramGlobalDao)
  {
    this._$1 = paramGlobalDao;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.GlobalMngImpl
 * JD-Core Version:    0.6.2
 */