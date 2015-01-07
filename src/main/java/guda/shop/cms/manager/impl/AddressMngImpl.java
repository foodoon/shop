package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.AddressDao;
iimport guda.shopcms.entity.Address;
imimport guda.shopms.manager.AddressMng;
impimport guda.shopmmon.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressMngImpl
  implements AddressMng
{
  private AddressDao _$1;

  public List<Address> getListById(Long paramLong)
  {
    return this._$1.getListById(paramLong);
  }

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public Pagination getPageByParentId(Long paramLong, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPageByParentId(paramLong, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public Address findById(Long paramLong)
  {
    Address localAddress = this._$1.findById(paramLong);
    return localAddress;
  }

  public Address save(Address paramAddress)
  {
    this._$1.save(paramAddress);
    return paramAddress;
  }

  public Address update(Address paramAddress)
  {
    Updater localUpdater = new Updater(paramAddress);
    Address localAddress = this._$1.updateByUpdater(localUpdater);
    return localAddress;
  }

  public Address deleteById(Long paramLong)
  {
    Address localAddress = this._$1.deleteById(paramLong);
    return localAddress;
  }

  public Address[] deleteByIds(Long[] paramArrayOfLong)
  {
    Address[] arrayOfAddress = new Address[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfAddress[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfAddress;
  }

  public Address[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger)
  {
    Address[] arrayOfAddress = new Address[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfAddress[i] = findById(paramArrayOfLong[i]);
      arrayOfAddress[i].setPriority(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfAddress;
  }

  @Autowired
  public void setDao(AddressDao paramAddressDao)
  {
    this._$1 = paramAddressDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.AddressMngImpl
 * JD-Core Version:    0.6.2
 */