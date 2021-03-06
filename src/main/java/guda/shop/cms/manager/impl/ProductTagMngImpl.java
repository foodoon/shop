package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductTagDao;
import guda.shop.cms.entity.ProductTag;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.manager.ProductTagMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class ProductTagMngImpl
        implements ProductTagMng {
    private ProductMng _$2;
    private ProductTagDao _$1;

    @Transactional(readOnly = true)
    public List<ProductTag> getList(Long paramLong) {
        List localList = this._$1.getList(paramLong);
        return localList;
    }

    @Transactional(readOnly = true)
    public ProductTag findById(Long paramLong) {
        ProductTag localProductTag = this._$1.findById(paramLong);
        return localProductTag;
    }

    public ProductTag save(ProductTag paramProductTag) {
        paramProductTag.init();
        this._$1.save(paramProductTag);
        return paramProductTag;
    }

    public ProductTag[] updateTagName(Long[] paramArrayOfLong, String[] paramArrayOfString) {
        Assert.notEmpty(paramArrayOfLong);
        Assert.notEmpty(paramArrayOfString);
        if (paramArrayOfLong.length != paramArrayOfString.length)
            throw new IllegalArgumentException("ids length not equals tagNames length");
        ProductTag[] arrayOfProductTag = new ProductTag[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            ProductTag localProductTag = findById(paramArrayOfLong[i]);
            localProductTag.setName(paramArrayOfString[i]);
            arrayOfProductTag[i] = localProductTag;
            i++;
        }
        return arrayOfProductTag;
    }

    public ProductTag[] deleteByIds(Long[] paramArrayOfLong) {
        ProductTag[] arrayOfProductTag = new ProductTag[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfProductTag[i] = findById(paramArrayOfLong[i]);
            i++;
        }
        this._$2.deleteTagAssociation(arrayOfProductTag);
        i = 0;
        j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfProductTag[i] = this._$1.deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfProductTag;
    }

    @Autowired
    public void setProductMng(ProductMng paramProductMng) {
        this._$2 = paramProductMng;
    }

    @Autowired
    public void setDao(ProductTagDao paramProductTagDao) {
        this._$1 = paramProductTagDao;
    }
}

