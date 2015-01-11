package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.DiscussDao;
import guda.shop.cms.entity.Discuss;
import guda.shop.cms.manager.DiscussMng;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DiscussMngImpl
        implements DiscussMng {

    @Autowired
    private ShopMemberMng _$3;
    private ProductMng _$2;
    private DiscussDao _$1;

    @Transactional(readOnly = true)
    public Discuss findById(Long paramLong) {
        Discuss localDiscuss = this._$1.findById(paramLong);
        return localDiscuss;
    }

    public Discuss saveOrUpdate(Long paramLong1, String paramString, Long paramLong2) {
        Discuss localDiscuss = new Discuss();
        localDiscuss.setContent(paramString);
        localDiscuss.setMember(this._$3.findById(paramLong2));
        localDiscuss.setProduct(this._$2.findById(paramLong1));
        localDiscuss.setTime(new Date());
        this._$1.saveOrUpdate(localDiscuss);
        return localDiscuss;
    }

    public Pagination getPage(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, boolean paramBoolean) {
        return this._$1.getPageByProduct(paramLong, paramString1, paramString2, paramDate1, paramDate2, paramInt1, paramInt2, paramBoolean);
    }

    public Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        return this._$1.getPageByMember(paramLong, paramInt1, paramInt2, paramBoolean);
    }

    public Discuss update(Discuss paramDiscuss) {
        return this._$1.update(paramDiscuss);
    }

    public Discuss[] deleteByIds(Long[] paramArrayOfLong) {
        Discuss[] arrayOfDiscuss = new Discuss[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfDiscuss[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfDiscuss;
    }

    public Discuss deleteById(Long paramLong) {
        Discuss localDiscuss = this._$1.deleteById(paramLong);
        return localDiscuss;
    }

    @Autowired
    public void setProductMng(ProductMng paramProductMng) {
        this._$2 = paramProductMng;
    }

    @Autowired
    public void setDao(DiscussDao paramDiscussDao) {
        this._$1 = paramDiscussDao;
    }
}

