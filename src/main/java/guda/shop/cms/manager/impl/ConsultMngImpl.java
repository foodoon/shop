package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ConsultDao;
import guda.shop.cms.entity.Consult;
import guda.shop.cms.manager.ConsultMng;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ConsultMngImpl
        implements ConsultMng {

    @Autowired
    private ShopMemberMng _$3;
    private ProductMng _$2;
    private ConsultDao _$1;

    @Transactional(readOnly = true)
    public Consult findById(Long paramLong) {
        Consult localConsult = this._$1.findById(paramLong);
        return localConsult;
    }

    public Consult saveOrUpdate(Long paramLong1, String paramString, Long paramLong2) {
        Consult localConsult1 = new Consult();
        localConsult1.setConsult(paramString);
        localConsult1.setMember(this._$3.findById(paramLong2));
        localConsult1.setTime(new Date());
        localConsult1.setProduct(this._$2.findById(paramLong1));
        Consult localConsult2 = this._$1.getSameConsult(paramLong2);
        Long localLong = Long.valueOf(System.currentTimeMillis());
        if (localConsult2 == null)
            return this._$1.saveOrUpdate(localConsult1);
        if ((localLong.longValue() - localConsult2.getTime().getTime()) / 1000L < 30L)
            return null;
        return this._$1.saveOrUpdate(localConsult1);
    }

    public List<Consult> findByProductId(Long paramLong) {
        return this._$1.findByProductId(paramLong);
    }

    public Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        return this._$1.getPageByMember(paramLong, paramInt1, paramInt2, paramBoolean);
    }

    public Pagination getPage(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, Boolean paramBoolean) {
        return this._$1.getPage(paramLong, paramString1, paramString2, paramDate1, paramDate2, paramInt1, paramInt2, paramBoolean.booleanValue());
    }

    public Consult update(Consult paramConsult) {
        return this._$1.update(paramConsult);
    }

    public Consult deleteById(Long paramLong) {
        Consult localConsult = this._$1.deleteById(paramLong);
        return localConsult;
    }

    public Consult[] deleteByIds(Long[] paramArrayOfLong) {
        Consult[] arrayOfConsult = new Consult[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfConsult[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfConsult;
    }

    @Autowired
    public void setProductMng(ProductMng paramProductMng) {
        this._$2 = paramProductMng;
    }

    @Autowired
    public void setDao(ConsultDao paramConsultDao) {
        this._$1 = paramConsultDao;
    }
}

