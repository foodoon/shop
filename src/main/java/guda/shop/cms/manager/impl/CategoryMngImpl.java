package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.BrandDao;
import guda.shop.cms.dao.CategoryDao;
import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.Category;
import guda.shop.cms.entity.StandardType;
import guda.shop.cms.manager.BrandMng;
import guda.shop.cms.manager.CategoryMng;
import guda.shop.cms.manager.ProductTypeMng;
import guda.shop.cms.manager.StandardTypeMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
@Transactional
public class CategoryMngImpl
        implements CategoryMng {

    @Autowired
    private BrandDao _$5;

    @Autowired
    private BrandMng _$4;

    @Autowired
    private StandardTypeMng _$3;
    private ProductTypeMng _$2;
    private CategoryDao _$1;

    public Category getByPath(Long paramLong, String paramString) {
        return this._$1.getByPath(paramLong, paramString, false);
    }

    public Category getByPathForTag(Long paramLong, String paramString) {
        return this._$1.getByPath(paramLong, paramString, true);
    }

    @Transactional(readOnly = true)
    public List<Category> getListForParent(Long paramLong1, Long paramLong2) {
        List localList1 = getList(paramLong1);
        if (paramLong2 != null) {
            List localList2 = this._$1.getListForChild(paramLong1, paramLong2);
            localList1.removeAll(localList2);
        }
        return localList1;
    }

    @Transactional(readOnly = true)
    public List<Category> getListForProduct(Long paramLong1, Long paramLong2) {
        ArrayList localArrayList = new ArrayList();
        Category localCategory = findById(paramLong2);
        _$1(localArrayList, Arrays.asList(new Category[]{localCategory}), localCategory.getType().getId());
        return localArrayList;
    }

    @Transactional(readOnly = true)
    public List<Category> getTopList(Long paramLong) {
        return this._$1.getTopList(paramLong, false);
    }

    public List<Category> getChildList(Long paramLong1, Long paramLong2) {
        return this._$1.getChildList(paramLong1, paramLong2);
    }

    public List<Category> getTopListForTag(Long paramLong) {
        return this._$1.getTopList(paramLong, true);
    }

    @Transactional(readOnly = true)
    public List<Category> getList(Long paramLong) {
        List localList = this._$1.getTopList(paramLong, false);
        ArrayList localArrayList = new ArrayList();
        _$1(localArrayList, localList, null);
        return localArrayList;
    }

    public boolean checkPath(Long paramLong, String paramString) {
        return this._$1.countPath(paramLong, paramString) <= 0;
    }

    @Transactional(readOnly = true)
    public Category findById(Long paramLong) {
        Category localCategory = this._$1.findById(paramLong);
        return localCategory;
    }

    public Category save(Category paramCategory, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong1, Long[] paramArrayOfLong2) {
        Category localCategory1 = null;
        if (paramLong1 != null) {
            localCategory1 = findById(paramLong1);
            paramCategory.setParent(localCategory1);
        }
        if (paramLong2 != null)
            paramCategory.setType(this._$2.findById(paramLong2));
        Category localCategory2 = this._$1.save(paramCategory);
        Long localLong = null;
        if ((paramArrayOfLong1 != null) && (paramArrayOfLong1.length > 0))
            for (Long t : paramArrayOfLong1)
                localCategory2.addToBrands(this._$4.findById(t));
        else
            localCategory2.setBrands(new HashSet());
        if (localCategory1 != null) {
            localCategory1.addToChild(paramCategory);
        }
        if ((paramArrayOfLong2 != null) && (paramArrayOfLong2.length > 0)) {
            for (Long t : paramArrayOfLong2) {
                localCategory2.addToStandardTypes(this._$3.findById(t));
            }
        }
        return paramCategory;
    }

    public List<Brand> getBrandByCate(Long paramLong) {
        return this._$5.getListByCate(paramLong);
    }

    public Category update(Category paramCategory, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong1, Map<String, String> paramMap, Long[] paramArrayOfLong2) {
        Assert.notNull(paramCategory);
        Category localCategory1 = findById(paramCategory.getId());
        Category localCategory2 = localCategory1.getParent();
        Category localCategory3 = null;
        if (paramLong1 != null) {
            localCategory3 = findById(paramLong1);
            paramCategory.setParent(localCategory3);
        } else {
            paramCategory.setParent(null);
        }
        Updater localUpdater = new Updater(paramCategory);
        localUpdater.include(Category.PROP_PARENT);
        localCategory1 = this._$1.updateByUpdater(localUpdater);
        if (localCategory2 != null)
            localCategory2.removeFromChild(localCategory1);
        if (localCategory3 != null)
            localCategory3.addToChild(localCategory1);
        Set localSet = localCategory1.getBrands();
        Object localObject1 = localSet.iterator();
        while (((Iterator) localObject1).hasNext()) {
            Brand localBrand = (Brand) ((Iterator) localObject1).next();
            localBrand.removeFromCategorys(localCategory1);
        }
        localSet.clear();
        if ((paramArrayOfLong1 != null) && (paramArrayOfLong1.length > 0))
            for (Long localLong1 : paramArrayOfLong1)
                localCategory1.addToBrands(this._$4.findById(localLong1));
        else
            localCategory1.setBrands(new HashSet());
        localObject1 = localCategory1.getStandardType();
        Object localObject2 = ((Set) localObject1).iterator();
        while (((Iterator) localObject2).hasNext()) {
            StandardType localStandardType = (StandardType) ((Iterator) localObject2).next();
            localStandardType.removeFromCategorys(localCategory1);
        }
        ((Set) localObject1).clear();
        if ((paramArrayOfLong2 != null) && (paramArrayOfLong2.length > 0))
            for (Long localLong2 : paramArrayOfLong2)
                localCategory1.addToStandardTypes(this._$3.findById(localLong2));
        if (paramMap != null) {
            localObject2 = localCategory1.getAttr();
            ((Map) localObject2).clear();
            ((Map) localObject2).putAll(paramMap);
        }
        return localCategory1;
    }

    public Category deleteById(Long paramLong) {
        Category localCategory1 = findById(paramLong).getParent();
        Category localCategory2 = this._$1.deleteById(paramLong);
        if (localCategory1 != null)
            localCategory1.removeFromChild(localCategory2);
        return localCategory2;
    }

    public Category[] deleteByIds(Long[] paramArrayOfLong) {
        Category[] arrayOfCategory1 = new Category[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCategory1[i] = this._$1.deleteById(paramArrayOfLong[i]);
            i++;
        }
        for (Category localCategory2 : arrayOfCategory1) {
            Category localCategory1 = localCategory2.getParent();
            if (localCategory1 != null)
                localCategory1.removeFromChild(localCategory2);
        }
        return arrayOfCategory1;
    }

    public Category[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        Category[] arrayOfCategory = new Category[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCategory[i] = findById(paramArrayOfLong[i]);
            arrayOfCategory[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfCategory;
    }

    private void _$1(List<Category> paramList, Collection<Category> paramCollection, Long paramLong) {
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext()) {
            Category localCategory = (Category) localIterator.next();
            if (paramLong != null) {
                if (paramLong.equals(localCategory.getType().getId()))
                    paramList.add(localCategory);
            } else
                paramList.add(localCategory);
            Set localSet = localCategory.getChild();
            if ((localSet != null) && (localSet.size() > 0))
                _$1(paramList, localSet, paramLong);
        }
    }

    public List<Category> getListBypType(Long paramLong1, Long paramLong2, Integer paramInteger) {
        return this._$1.getListByptype(paramLong1, paramLong2, paramInteger);
    }

    @Autowired
    public void setProductTypeMng(ProductTypeMng paramProductTypeMng) {
        this._$2 = paramProductTypeMng;
    }

    @Autowired
    public void setDao(CategoryDao paramCategoryDao) {
        this._$1 = paramCategoryDao;
    }
}

