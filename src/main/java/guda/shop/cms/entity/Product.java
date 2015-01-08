package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProduct;
import guda.shop.cms.web.threadvariable.GroupThread;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class Product extends BaseProduct
        implements ProductInfo {
    private static final long serialVersionUID = 1L;
    public static String DETAIL_SUFFIX = "_d";
    public static String LIST_SUFFIX = "_l";
    public static String MIN_SUFFIX = "_m";

    public Product() {
    }

    public Product(Long paramLong) {
        super(paramLong);
    }

    public Product(Long paramLong1, ShopConfig paramShopConfig, Category paramCategory, ProductType paramProductType, Website paramWebsite, String paramString, Double paramDouble1, Double paramDouble2, Double paramDouble3, Long paramLong2, Integer paramInteger1, Integer paramInteger2, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5) {
        super(paramLong1, paramShopConfig, paramCategory, paramProductType, paramWebsite, paramString, paramDouble1, paramDouble2, paramDouble3, paramLong2, paramInteger1, paramInteger2, paramDate, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5);
    }

    public Double getMemberPrice() {
        ShopMemberGroup localShopMemberGroup = GroupThread.get();
        if (localShopMemberGroup == null)
            return getSalePrice();
        return getMemberPrice(localShopMemberGroup);
    }

    public Double getMemberPrice(ShopMemberGroup paramShopMemberGroup) {
        return Double.valueOf(getSalePrice().doubleValue() * paramShopMemberGroup.getDiscount().intValue() / 100.0D);
    }

    public String getUrl() {
        return "/" + getCategory().getPath() + "/" + getId() + getWebsite().getSuffix();
    }

    public String getDetailImgUrl() {
        return _$1(getDetailImg());
    }

    public String getListImgUrl() {
        return _$1(getListImg());
    }

    public String getCoverImgUrl() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getCoverImg();
        return null;
    }

    public Double getPrice() {
        ProductFashion localProductFashion = getProductFashion();
        if (localProductFashion != null)
            return localProductFashion.getSalePrice();
        return getSalePrice();
    }

    public String getMinImgUrl() {
        return _$1(getMinImg());
    }

    public ProductFashion getProductFashion() {
        Set localSet = getFashions();
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            ProductFashion localProductFashion = (ProductFashion) localIterator.next();
            if (localProductFashion.getIsDefault().booleanValue())
                return localProductFashion;
        }
        return null;
    }

    private String _$1(String paramString) {
        if (StringUtils.isBlank(paramString))
            return null;
        return getWebsite().getUploadUrl(paramString);
    }

    public List<Category> getCategoryList() {
        return getCategory().getCategoryList();
    }

    public void addToTags(ProductTag paramProductTag) {
        Object localObject = getTags();
        if (localObject == null) {
            localObject = new HashSet();
            setTags((Set) localObject);
        }
        paramProductTag.increaseCount();
        ((Set) localObject).add(paramProductTag);
    }

    public void removeFromTags(ProductTag paramProductTag) {
        Set localSet = getTags();
        if (localSet != null) {
            paramProductTag.reduceCount();
            localSet.remove(paramProductTag);
        }
    }

    public void removeAllTags() {
        Set localSet = getTags();
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            ProductTag localProductTag = (ProductTag) localIterator.next();
            localProductTag.reduceCount();
        }
        localSet.clear();
    }

    public Long[] getTagIds() {
        Set localSet = getTags();
        if (localSet == null)
            return null;
        Long[] arrayOfLong = new Long[localSet.size()];
        int i = 0;
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            ProductTag localProductTag = (ProductTag) localIterator.next();
            arrayOfLong[(i++)] = localProductTag.getId();
        }
        return arrayOfLong;
    }

    public void addToExendeds(String paramString1, String paramString2) {
        Object localObject = getExendeds();
        if (localObject == null) {
            localObject = new ArrayList();
            setExendeds((List) localObject);
        }
        ProductExended localProductExended = new ProductExended();
        localProductExended.setName(paramString1);
        localProductExended.setValue(paramString2);
        ((List) localObject).add(localProductExended);
    }

    public void addToPictures(String paramString1, String paramString2, String paramString3) {
        Object localObject = getPictures();
        if (localObject == null) {
            localObject = new ArrayList();
            setPictures((List) localObject);
        }
        ProductPicture localProductPicture = new ProductPicture();
        localProductPicture.setPicturePath(paramString1);
        localProductPicture.setBigPicturePath(paramString2);
        localProductPicture.setAppPicturePath(paramString3);
        ((List) localObject).add(localProductPicture);
    }

    public String getText() {
        ProductText localProductText = getProductText();
        if (localProductText != null)
            return localProductText.getText();
        return null;
    }

    public void setText(String paramString) {
        ProductText localProductText = getProductText();
        if (localProductText != null) {
            localProductText.setText(paramString);
        } else {
            localProductText = new ProductText();
            localProductText.setText(paramString);
            setProductText(localProductText);
        }
    }

    public String getText1() {
        ProductText localProductText = getProductText();
        if (localProductText != null)
            return localProductText.getText1();
        return null;
    }

    public void setText1(String paramString) {
        ProductText localProductText = getProductText();
        if (localProductText != null) {
            localProductText.setText1(paramString);
        } else {
            localProductText = new ProductText();
            localProductText.setText1(paramString);
            setProductText(localProductText);
        }
    }

    public String getText2() {
        ProductText localProductText = getProductText();
        if (localProductText != null)
            return localProductText.getText2();
        return null;
    }

    public void setText2(String paramString) {
        ProductText localProductText = getProductText();
        if (localProductText != null) {
            localProductText.setText2(paramString);
        } else {
            localProductText = new ProductText();
            localProductText.setText2(paramString);
            setProductText(localProductText);
        }
    }

    public Collection<String> getKeywordArray() {
        return getKeywords();
    }

    public Collection<String> getTagArray() {
        Set localSet = getTags();
        ArrayList localArrayList = new ArrayList(localSet.size());
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            ProductTag localProductTag = (ProductTag) localIterator.next();
            localArrayList.add(localProductTag.getName());
        }
        return localArrayList;
    }

    public String getBrandName() {
        Brand localBrand = getBrand();
        if (localBrand != null)
            return localBrand.getName();
        return null;
    }

    public Collection<String> getCategoryNameArray() {
        List localList = getCategoryList();
        ArrayList localArrayList = new ArrayList(localList.size());
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Category localCategory = (Category) localIterator.next();
            localArrayList.add(localCategory.getName());
        }
        return localArrayList;
    }

    public Collection<Long> getCategoryIdArray() {
        List localList = getCategoryList();
        ArrayList localArrayList = new ArrayList(localList.size());
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Category localCategory = (Category) localIterator.next();
            localArrayList.add(localCategory.getId());
        }
        return localArrayList;
    }

    public void init() {
        if (getLackRemind() == null)
            setLackRemind(Boolean.valueOf(true));
        if (getOnSale() == null)
            setOnSale(Boolean.valueOf(true));
        if (getViewCount() == null)
            setViewCount(Long.valueOf(0L));
        if (getSaleCount() == null)
            setSaleCount(Integer.valueOf(0));
        if (getStockCount() == null)
            setStockCount(Integer.valueOf(10));
        if (getMarketPrice() == null)
            setMarketPrice(Double.valueOf(0.0D));
        if (getSalePrice() == null)
            setSalePrice(Double.valueOf(0.0D));
        if (getCostPrice() == null)
            setCostPrice(Double.valueOf(0.0D));
        if (getRecommend() == null)
            setRecommend(Boolean.valueOf(false));
        if (getSpecial() == null)
            setSpecial(Boolean.valueOf(false));
        if (getScore() == null)
            setScore(Integer.valueOf(0));
        if (getLiRun() == null)
            setLiRun(Double.valueOf(0.0D));
        setCreateTime(new Timestamp(System.currentTimeMillis()));
    }

    public String getMtitle() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getMtitle();
        return null;
    }

    public String getMkeywords() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getMkeywords();
        return null;
    }

    public String getMdescription() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getMdescription();
        return null;
    }

    public String getDetailImg() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getDetailImg();
        return null;
    }

    public String getListImg() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getListImg();
        return null;
    }

    public String getCoverImg() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getCoverImg();
        return null;
    }

    public String getMinImg() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getMinImg();
        return null;
    }

    public Integer getWeight() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getWeight();
        return null;
    }

    public String getUnit() {
        ProductExt localProductExt = getProductExt();
        if (localProductExt != null)
            return localProductExt.getUnit();
        return null;
    }

    public Integer getProductTotalStockCount() {
        Set localSet = getFashions();
        int i = 0;
        if (localSet != null) {
            Iterator localIterator = localSet.iterator();
            while (localIterator.hasNext()) {
                ProductFashion localProductFashion = (ProductFashion) localIterator.next();
                i += localProductFashion.getStockCount().intValue();
            }
        }
        return Integer.valueOf(i);
    }

    public Integer getProductTotalSaleCount() {
        Set localSet = getFashions();
        int i = 0;
        if (localSet != null) {
            Iterator localIterator = localSet.iterator();
            while (localIterator.hasNext()) {
                ProductFashion localProductFashion = (ProductFashion) localIterator.next();
                i += localProductFashion.getSaleCount().intValue();
            }
        }
        return Integer.valueOf(i);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Product
 * JD-Core Version:    0.6.2
 */