package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCategory;
import guda.shop.common.hibernate3.HibernateTree;
import guda.shop.common.hibernate3.PriorityComparator;
import guda.shop.common.hibernate3.PriorityInterface;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class Category extends BaseCategory
        implements HibernateTree, PriorityInterface {
    private static final long serialVersionUID = 1L;

    public Category() {
    }

    public Category(Long paramLong) {
        super(paramLong);
    }

    public Category(Long paramLong, ProductType paramProductType, Website paramWebsite, String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3) {
        super(paramLong, paramProductType, paramWebsite, paramString1, paramString2, paramInteger1, paramInteger2, paramInteger3);
    }

    public String getUrl() {
        return "/" + getPath() + "/" + "index" + getWebsite().getSuffix();
    }

    public String getTplChannelRel() {
        String str = getTplChannel();
        if (StringUtils.isBlank(str)) {
            str = getType().getChannelPrefix();
            return getWebsite().getTemplate("category", str);
        }
        return getWebsite().getTemplateRel(str);
    }

    public String getTplContentRel() {
        String str = getTplContent();
        if (StringUtils.isBlank(str)) {
            str = getType().getContentPrefix();
            return getWebsite().getTemplate("product", str);
        }
        return getWebsite().getTemplateRel(str);
    }

    public Category getTopCategory() {
        Category localObject = this;
        for (Category localCategory = ((Category) localObject).getParent(); localCategory != null; localCategory = localCategory.getParent())
            localObject = localCategory;
        return localObject;
    }

    public List<Category> getCategoryList() {
        LinkedList localLinkedList = new LinkedList();
        for (Category localCategory = this; localCategory != null; localCategory = localCategory.getParent())
            localLinkedList.add(0, localCategory);
        return localLinkedList;
    }

    public int getDeep() {
        int i = 0;
        for (Category localCategory = getParent(); localCategory != null; localCategory = localCategory.getParent())
            i++;
        return i;
    }

    public boolean isLeaf() {
        Set localSet = getChild();
        return (localSet != null) && (localSet.size() > 0);
    }

    public void addToChild(Category paramCategory) {
        Object localObject = getChild();
        if (localObject == null) {
            localObject = new TreeSet(PriorityComparator.INSTANCE);
            setChild((Set) localObject);
        }
        ((Set) localObject).add(paramCategory);
    }

    public void removeFromChild(Category paramCategory) {
        Set localSet = getChild();
        if (localSet != null)
            localSet.remove(paramCategory);
    }

    public String getTreeCondition() {
        return "bean.website.id=" + getWebsite().getId();
    }

    public Long getParentId() {
        Category localCategory = getParent();
        if (localCategory != null)
            return localCategory.getId();
        return null;
    }

    public Long[] getBrandIds() {
        Set localSet = getBrands();
        if (localSet == null)
            return null;
        Long[] arrayOfLong = new Long[localSet.size()];
        int i = 0;
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            Brand localBrand = (Brand) localIterator.next();
            arrayOfLong[i] = localBrand.getId();
            i++;
        }
        return arrayOfLong;
    }

    public void addToBrands(Brand paramBrand) {
        Object localObject = getBrands();
        if (localObject == null) {
            localObject = new HashSet();
            setBrands((Set) localObject);
        }
        ((Set) localObject).add(paramBrand);
        paramBrand.addToCategory(this);
    }

    public void addToStandardTypes(StandardType paramStandardType) {
        Object localObject = getStandardType();
        if (localObject == null) {
            localObject = new HashSet();
            setStandardType((Set) localObject);
        }
        ((Set) localObject).add(paramStandardType);
        paramStandardType.addToCategory(this);
    }

    public Long[] getStandardTypeIds() {
        Set localSet = getStandardType();
        if (localSet == null)
            return null;
        Long[] arrayOfLong = new Long[localSet.size()];
        int i = 0;
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            StandardType localStandardType = (StandardType) localIterator.next();
            arrayOfLong[i] = localStandardType.getId();
            i++;
        }
        return arrayOfLong;
    }

    public String getLftName() {
        return "lft";
    }

    public String getParentName() {
        return "parent";
    }


    public String getRgtName() {
        return "rgt";
    }
}
