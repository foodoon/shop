package guda.shop.manager.impl;

import guda.shop.cms.dao.ProductTagDao;
iimport guda.shopcms.entity.ProductTag;
imimport guda.shopms.manager.ProductMng;
import com.jspgou.cms.manager.ProductTagMng;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class ProductTagMngImpl
  implements ProductTagMng
{
  private ProductMng _$2;
  private ProductTagDao _$1;

  @Transactional(readOnly=true)
  public List<ProductTag> getList(Long paramLong)
  {
    List localList = this._$1.getList(paramLong);
    return localList;
  }

  @Transactional(readOnly=true)
  public ProductTag findById(Long paramLong)
  {
    ProductTag localProductTag = this._$1.findById(paramLong);
    return localProductTag;
  }

  public ProductTag save(ProductTag paramProductTag)
  {
    paramProductTag.init();
    this._$1.save(paramProductTag);
    return paramProductTag;
  }

  public ProductTag[] updateTagName(Long[] paramArrayOfLong, String[] paramArrayOfString)
  {
    Assert.notEmpty(paramArrayOfLong);
    Assert.notEmpty(paramArrayOfString);
    if (paramArrayOfLong.length != paramArrayOfString.length)
      throw new IllegalArgumentException("ids length not equals tagNames length");
    ProductTag[] arrayOfProductTag = new ProductTag[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      ProductTag localProductTag = findById(paramArrayOfLong[i]);
      localProductTag.setName(paramArrayOfString[i]);
      arrayOfProductTag[i] = localProductTag;
      i++;
    }
    return arrayOfProductTag;
  }

  public ProductTag[] deleteByIds(Long[] paramArrayOfLong)
  {
    ProductTag[] arrayOfProductTag = new ProductTag[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfProductTag[i] = findById(paramArrayOfLong[i]);
      i++;
    }
    this._$2.deleteTagAssociation(arrayOfProductTag);
    i = 0;
    j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfProductTag[i] = this._$1.deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfProductTag;
  }

  @Autowired
  public void setProductMng(ProductMng paramProductMng)
  {
    this._$2 = paramProductMng;
  }

  @Autowired
  public void setDao(ProductTagDao paramProductTagDao)
  {
    this._$1 = paramProductTagDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductTagMngImpl
 * JD-Core Version:    0.6.2
 */