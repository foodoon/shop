package guda.shop.manager;

import guda.shop.cms.entity.Address;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface AddressMng
{
  public abstract List<Address> getListById(Long paramLong);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Pagination getPageByParentId(Long paramLong, int paramInt1, int paramInt2);

  public abstract Address findById(Long paramLong);

  public abstract Address save(Address paramAddress);

  public abstract Address update(Address paramAddress);

  public abstract Address deleteById(Long paramLong);

  public abstract Address[] deleteByIds(Long[] paramArrayOfLong);

  public abstract Address[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.AddressMng
 * JD-Core Version:    0.6.2
 */