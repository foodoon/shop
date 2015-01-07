package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductDao;
iimport guda.shopcms.dao.ProductFashionDao;
imimport guda.shopms.entity.Category;
impimport guda.shops.entity.Collect;
impoimport guda.shop.entity.Consult;
imporimport guda.shopentity.Product;
importimport guda.shopntity.ProductExt;
import import guda.shoptity.ProductFashion;
import cimport guda.shopity.ProductStandard;
import coimport guda.shopty.ProductTag;
import comimport guda.shopy.ProductText;
import com.import guda.shop.ProductType;
import com.jimport guda.shop.BrandMng;
import com.jsimport guda.shopCartItemMng;
import com.jspimport guda.shopategoryMng;
import com.jspgimport guda.shopllectMng;
import com.jspgoimport guda.shopsultMng;
import com.jspgouimport guda.shopuctExtMng;
import com.jspgou.import guda.shopctFashionMng;
import com.jspgou.cimport guda.shoptMng;
import com.jspgou.cmimport guda.shopStandardMng;
import com.jspgou.cmsimport guda.shopagMng;
import com.jspgou.cms.import guda.shopxtMng;
import com.jspgou.cms.mimport guda.shopMng;
import com.jspgou.commonimport guda.shopls;
import com.jspgou.common.import guda.shopr;
import com.jspgou.common.iimport guda.shopimport com.jspgou.common.imimport guda.shopmport com.jspgou.common.pagimport guda.shoport com.jspgou.common.web.import guda.shophResolver;
import com.jspgou.core.entity.import guda.shopom.jspgou.core.manager.WebsiteMng;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductMngImpl
  implements ProductMng
{

  @Autowired
  private ProductFashionDao _$16;

  @Autowired
  private ProductFashionMng _$15;

  @Autowired
  private CollectMng _$14;

  @Autowired
  private ConsultMng _$13;

  @Autowired
  private CartItemMng _$12;

  @Autowired
  private RealPathResolver _$11;

  @Autowired
  private WebsiteMng _$10;

  @Autowired
  private CategoryMng _$9;

  @Autowired
  private ProductTagMng _$8;

  @Autowired
  private ProductTextMng _$7;

  @Autowired
  private ShopConfigMng _$6;

  @Autowired
  private BrandMng _$5;

  @Autowired
  private ImageScale _$4;

  @Autowired
  private ProductExtMng _$3;

  @Autowired
  private ProductStandardMng _$2;

  @Autowired
  private ProductDao _$1;

  public List<Product> getList(Long paramLong1, Long paramLong2, String paramString)
  {
    return this._$1.getList(paramLong1, paramLong2, paramString, true);
  }

  public void resetSaleTop()
  {
    List localList = getList(null, null, null);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Product localProduct = (Product)localIterator.next();
      localProduct.setSaleCount(Integer.valueOf(0));
      update(localProduct);
    }
  }

  public void resetProfitTop()
  {
    List localList = getList(null, null, null);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Product localProduct = (Product)localIterator.next();
      localProduct.setLiRun(Double.valueOf(0.0D));
      update(localProduct);
    }
  }

  @Transactional(readOnly=true)
  public Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong3, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2)
  {
    Pagination localPagination;
    if (paramLong2 != null)
      localPagination = this._$1.getPageByCategory(paramLong2, paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramLong3, paramDouble1, paramDouble2, paramInteger1, paramInteger2, paramInt1, paramInt2, false);
    else
      localPagination = this._$1.getPageByWebsite(paramLong1, paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramLong3, paramDouble1, paramDouble2, paramInteger1, paramInteger2, paramInt1, paramInt2, false);
    return localPagination;
  }

  public Pagination getPage(int paramInt1, int paramInt2, int paramInt3)
  {
    return this._$1.getPage(paramInt1, paramInt2, paramInt3, true);
  }

  @Transactional(readOnly=true)
  public Pagination getPageForTag(Long paramLong1, Long paramLong2, Long paramLong3, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    Pagination localPagination;
    if (paramLong3 != null)
      localPagination = this._$1.getPageByTag(paramLong3, paramLong2, paramBoolean1, paramBoolean2, paramInt1, paramInt2, true);
    else if (paramLong2 != null)
      localPagination = this._$1.getPageByCategory(paramLong2, null, null, Boolean.valueOf(true), paramBoolean1, paramBoolean2, null, null, null, null, null, null, null, paramInt1, paramInt2, true);
    else
      localPagination = this._$1.getPageByWebsite(paramLong1, null, null, Boolean.valueOf(true), paramBoolean1, paramBoolean2, null, null, null, null, null, null, null, paramInt1, paramInt2, true);
    return localPagination;
  }

  public Pagination getPageByStockWarning(Long paramLong, Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2)
  {
    return this._$1.getPageByStockWarning(paramLong, paramInteger, paramBoolean, paramInt1, paramInt2);
  }

  @Transactional(readOnly=true)
  public Pagination getPageForTagChannel(Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Boolean paramBoolean1, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3)
  {
    Pagination localPagination;
    if (paramLong4 != null)
      localPagination = this._$1.getPageByTag(paramLong4, paramLong3, paramBoolean1, paramBoolean2, paramInt2, paramInt3, true);
    else if (paramLong3 != null)
      localPagination = this._$1.getPageByCategoryChannel(paramLong1, paramLong3, paramBoolean1, paramArrayOfString1, paramArrayOfString2, paramBoolean2, paramInt1, paramInt2, paramInt3, true);
    else
      localPagination = this._$1.getPageByWebsite(paramLong2, null, null, Boolean.valueOf(true), paramBoolean1, paramBoolean2, null, null, null, null, null, null, null, paramInt2, paramInt3, true);
    return localPagination;
  }

  public List<Product> getListForTag(Long paramLong1, Long paramLong2, Long paramLong3, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, int paramInt1, int paramInt2)
  {
    List localList;
    if (paramLong3 != null)
      localList = this._$1.getListByTag(paramLong3, paramLong2, paramBoolean1, paramBoolean2, paramInt1, paramInt2, true);
    else if (paramLong2 != null)
      localList = this._$1.getListByCategory(paramLong2, paramBoolean1, paramBoolean2, paramInt1, paramInt2, true);
    else
      localList = this._$1.getListByWebsite1(paramLong1, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramInt1, paramInt2, true);
    return localList;
  }

  public List<Product> getIsRecommend(Boolean paramBoolean, int paramInt)
  {
    return this._$1.getIsRecommend(paramBoolean, paramInt);
  }

  public Integer[] getProductByTag(Long paramLong)
  {
    return this._$1.getProductByTag(paramLong);
  }

  public int deleteTagAssociation(ProductTag[] paramArrayOfProductTag)
  {
    if (ArrayUtils.isEmpty(paramArrayOfProductTag))
      return 0;
    return this._$1.deleteTagAssociation(paramArrayOfProductTag);
  }

  @Transactional(readOnly=true)
  public Product findById(Long paramLong)
  {
    Product localProduct = this._$1.findById(paramLong);
    return localProduct;
  }

  public Product save(Product paramProduct, ProductExt paramProductExt, Long paramLong1, Long paramLong2, Long paramLong3, Long[] paramArrayOfLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, MultipartFile paramMultipartFile)
  {
    ProductText localProductText = paramProduct.getProductText();
    if (localProductText != null)
      localProductText.setProduct(paramProduct);
    Category localCategory = this._$9.findById(paramLong2);
    if (paramLong3 != null)
      paramProduct.setBrand(this._$5.findById(paramLong3));
    Website localWebsite = this._$10.findById(paramLong1);
    paramProduct.setWebsite(localWebsite);
    paramProduct.setConfig(this._$6.findById(paramLong1));
    paramProduct.setCategory(localCategory);
    paramProduct.setType(localCategory.getType());
    if (!ArrayUtils.isEmpty(paramArrayOfLong))
      for (Long localLong : paramArrayOfLong)
        paramProduct.addToTags(this._$8.findById(localLong));
    if (!ArrayUtils.isEmpty(paramArrayOfString1))
      paramProduct.setKeywords(Arrays.asList(paramArrayOfString1));
    paramProduct.init();
    this._$1.save(paramProduct);
    int i;
    if ((paramArrayOfString2 != null) && (paramArrayOfString2.length > 0))
    {
      i = 0;
      ??? = paramArrayOfString2.length;
      while (i < ???)
      {
        if (!StringUtils.isBlank(paramArrayOfString2[i]))
          paramProduct.addToExendeds(paramArrayOfString2[i], paramArrayOfString3[i]);
        i++;
      }
    }
    if ((paramArrayOfString4 != null) && (paramArrayOfString4.length > 0))
    {
      i = 0;
      ??? = paramArrayOfString4.length;
      while (i < ???)
      {
        if (!StringUtils.isBlank(paramArrayOfString4[i]))
          paramProduct.addToPictures(paramArrayOfString4[i], paramArrayOfString5[i], paramArrayOfString6[i]);
        i++;
      }
    }
    String str = this._$11.get(localWebsite.getUploadRel());
    _$1(paramProduct, paramProductExt, localCategory.getType(), paramMultipartFile, str);
    this._$3.save(paramProductExt, paramProduct);
    return paramProduct;
  }

  public Product updateByUpdater(Product paramProduct)
  {
    Updater localUpdater = new Updater(paramProduct);
    Product localProduct = this._$1.updateByUpdater(localUpdater);
    return localProduct;
  }

  public Product update(Product paramProduct, ProductExt paramProductExt, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, Map<String, String> paramMap, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, MultipartFile paramMultipartFile)
  {
    Product localProduct = findById(paramProduct.getId());
    ProductText localProductText = paramProduct.getProductText();
    if (localProductText != null)
    {
      localObject1 = localProduct.getProductText();
      if (localObject1 != null)
      {
        ((ProductText)localObject1).setText(localProductText.getText());
        ((ProductText)localObject1).setText1(localProductText.getText1());
        ((ProductText)localObject1).setText2(localProductText.getText2());
      }
      else
      {
        localProductText.setId(paramProduct.getId());
        localProductText.setProduct(localProduct);
        localProduct.setProductText(localProductText);
        this._$7.save(localProductText);
      }
    }
    localProduct.removeAllTags();
    Object localObject1 = this._$9.findById(paramLong1);
    localProduct.setCategory((Category)localObject1);
    localProduct.setType(((Category)localObject1).getType());
    if (localProduct.getOnSale() == null)
      localProduct.setOnSale(Boolean.valueOf(false));
    if (paramLong2 != null)
      localProduct.setBrand(this._$5.findById(paramLong2));
    else
      localProduct.setBrand(null);
    if (!ArrayUtils.isEmpty(paramArrayOfLong))
      for (Long localLong : paramArrayOfLong)
        localProduct.addToTags(this._$8.findById(localLong));
    if (paramArrayOfString1 != null)
    {
      ??? = localProduct.getKeywords();
      ((List)???).clear();
      ((List)???).addAll(Arrays.asList(paramArrayOfString1));
    }
    else
    {
      localProduct.getKeywords().clear();
    }
    ??? = new Updater(paramProduct);
    ((Updater)???).exclude(Product.PROP_WEBSITE);
    ((Updater)???).exclude(Product.PROP_PRODUCT_TEXT);
    localProduct = this._$1.updateByUpdater((Updater)???);
    if (paramMap != null)
    {
      Map localMap = localProduct.getAttr();
      localMap.clear();
      localMap.putAll(paramMap);
    }
    localProduct.getExendeds().clear();
    int j;
    if ((paramArrayOfString2 != null) && (paramArrayOfString2.length > 0))
    {
      j = 0;
      ??? = paramArrayOfString2.length;
      while (j < ???)
      {
        if (!StringUtils.isBlank(paramArrayOfString2[j]))
          localProduct.addToExendeds(paramArrayOfString2[j], paramArrayOfString3[j]);
        j++;
      }
    }
    localProduct.getPictures().clear();
    if ((paramArrayOfString4 != null) && (paramArrayOfString4.length > 0))
    {
      j = 0;
      ??? = paramArrayOfString4.length;
      while (j < ???)
      {
        if (!StringUtils.isBlank(paramArrayOfString4[j]))
          localProduct.addToPictures(paramArrayOfString4[j], paramArrayOfString5[j], paramArrayOfString6[j]);
        j++;
      }
    }
    String str = this._$11.get(localProduct.getWebsite().getUploadRel());
    _$1(localProduct, paramProductExt, ((Category)localObject1).getType(), paramMultipartFile, str);
    this._$3.saveOrUpdate(paramProductExt, localProduct);
    return localProduct;
  }

  public Product update(Product paramProduct)
  {
    Updater localUpdater = new Updater(paramProduct);
    Product localProduct = this._$1.updateByUpdater(localUpdater);
    return localProduct;
  }

  public Product[] deleteByIds(Long[] paramArrayOfLong)
  {
    Product[] arrayOfProduct1 = new Product[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    Object localObject1;
    while (i < j)
    {
      this._$12.deleteByProductId(paramArrayOfLong[i]);
      List localList = this._$14.findByProductId(paramArrayOfLong[i]);
      localObject1 = localList.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Collect)((Iterator)localObject1).next();
        this._$14.deleteById(((Collect)localObject2).getId());
      }
      localObject1 = this._$13.findByProductId(paramArrayOfLong[i]);
      Object localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Consult)((Iterator)localObject2).next();
        this._$13.deleteById(((Consult)localObject3).getId());
      }
      localObject2 = this._$2.findByProductId(paramArrayOfLong[i]);
      Object localObject3 = ((List)localObject2).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        ProductStandard localProductStandard = (ProductStandard)((Iterator)localObject3).next();
        this._$2.deleteById(localProductStandard.getId());
      }
      localObject3 = findById(paramArrayOfLong[i]);
      ((Product)localObject3).getTags().clear();
      ((Product)localObject3).getFashions().clear();
      ((Product)localObject3).getKeywords().clear();
      ((Product)localObject3).getPopularityGroups().clear();
      arrayOfProduct1[i] = this._$1.deleteById(paramArrayOfLong[i]);
      i++;
    }
    for (localObject1 : arrayOfProduct1)
      ((Product)localObject1).removeAllTags();
    return arrayOfProduct1;
  }

  private boolean _$1(Product paramProduct, ProductExt paramProductExt, ProductType paramProductType, MultipartFile paramMultipartFile, String paramString)
  {
    if ((paramMultipartFile == null) || (paramMultipartFile.isEmpty()))
      return false;
    deleteImage(paramProduct, paramString);
    String str1 = FilenameUtils.getExtension(paramMultipartFile.getOriginalFilename());
    if (!ImageUtils.isImage(str1))
      return false;
    String str2 = FileNameUtils.genPathName();
    File localFile1 = new File(paramString, str2);
    String str3 = "/" + str2 + "/";
    if (!localFile1.exists())
      localFile1.mkdirs();
    String str4 = FileNameUtils.genFileName();
    File localFile2 = new File(localFile1, str4);
    try
    {
      paramMultipartFile.transferTo(localFile2);
    }
    catch (Exception localException1)
    {
      throw new RuntimeException(localException1);
    }
    try
    {
      String str5 = str4 + Product.DETAIL_SUFFIX + "." + str1;
      File localFile3 = new File(localFile1, str5);
      this._$4.resizeFix(localFile2, localFile3, paramProductType.getDetailImgWidth().intValue(), paramProductType.getDetailImgHeight().intValue());
      paramProductExt.setDetailImg(str3 + str5);
      String str6 = str4 + Product.LIST_SUFFIX + "." + str1;
      File localFile4 = new File(localFile1, str6);
      this._$4.resizeFix(localFile2, localFile4, paramProductType.getListImgWidth().intValue(), paramProductType.getListImgHeight().intValue());
      paramProductExt.setListImg(str3 + str6);
      String str7 = str4 + Product.MIN_SUFFIX + "." + str1;
      File localFile5 = new File(localFile1, str7);
      this._$4.resizeFix(localFile2, localFile5, paramProductType.getMinImgWidth().intValue(), paramProductType.getMinImgHeight().intValue());
      paramProductExt.setMinImg(str3 + str7);
    }
    catch (Exception localException2)
    {
      throw new RuntimeException(localException2);
    }
    localFile2.delete();
    return true;
  }

  public void deleteImage(Product paramProduct, String paramString)
  {
    String str1 = paramProduct.getDetailImg();
    if (!StringUtils.isBlank(str1))
      new File(paramString + str1).delete();
    String str2 = paramProduct.getListImg();
    if (!StringUtils.isBlank(str2))
      new File(paramString + str2).delete();
    String str3 = paramProduct.getMinImg();
    if (!StringUtils.isBlank(str3))
      new File(paramString + str3).delete();
  }

  public Integer getStoreByProductPattern(Long paramLong1, Long paramLong2)
  {
    ProductFashion localProductFashion = this._$16.getPfashion(paramLong1, paramLong2);
    return localProductFashion.getStockCount();
  }

  public List<Product> getHistoryProduct(HashSet<Long> paramHashSet, Integer paramInteger)
  {
    return this._$1.getHistoryProduct(paramHashSet, paramInteger);
  }

  public Integer getTotalStore(Long paramLong)
  {
    Product localProduct = this._$1.findById(paramLong);
    Set localSet = localProduct.getFashions();
    Integer localInteger = Integer.valueOf(0);
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        ProductFashion localProductFashion = (ProductFashion)localIterator.next();
        localInteger = Integer.valueOf(localInteger.intValue() + localProductFashion.getStockCount().intValue());
      }
    }
    return localInteger;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductMngImpl
 * JD-Core Version:    0.6.2
 */