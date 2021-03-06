package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.CouponDao;
import guda.shop.cms.entity.Coupon;
import guda.shop.cms.manager.CouponMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.springmvc.RealPathResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CouponMngImpl
        implements CouponMng {

    @Autowired
    private RealPathResolver _$3;

    @Autowired
    private CouponDao _$2;

    @Autowired
    private WebsiteMng _$1;

    public Pagination getPage(int paramInt1, int paramInt2) {
        return this._$2.getPage(paramInt1, paramInt2);
    }

    @Transactional(readOnly = true)
    public Pagination getPageByUsing(int paramInt1, int paramInt2) {
        return this._$2.getPageByUsing(new Date(), paramInt1, paramInt2);
    }

    @Transactional(readOnly = true)
    public List<Coupon> getList() {
        List localList = this._$2.getList();
        return localList;
    }

    @Transactional(readOnly = true)
    public Coupon findById(Long paramLong) {
        Coupon localCoupon = this._$2.findById(paramLong);
        return localCoupon;
    }

    public Coupon save(Coupon paramCoupon, Long paramLong) {
        Website localWebsite = this._$1.findById(paramLong);
        paramCoupon.setWebsite(localWebsite);
        Coupon localCoupon = this._$2.save(paramCoupon);
        return localCoupon;
    }

    public Coupon update(Coupon paramCoupon) {
        Updater localUpdater = new Updater(paramCoupon);
        Coupon localCoupon = this._$2.updateByUpdater(localUpdater);
        return localCoupon;
    }

    public Coupon deleteById(Long paramLong, String paramString) {
        Coupon localCoupon = findById(paramLong);
        String str1 = localCoupon.getCouponPicture();
        String str2 = this._$3.get(str1).replace("\\", File.separator).replace("/", File.separator).replace(paramString.replace("\\", File.separator).replace("/", File.separator) + paramString.replace("\\", File.separator).replace("/", File.separator), paramString.replace("\\", File.separator).replace("/", File.separator));
        File localFile = new File(str2);
        if (localFile != null)
            localFile.delete();
        localCoupon = this._$2.deleteById(paramLong);
        return localCoupon;
    }

    public Coupon[] deleteByIds(Long[] paramArrayOfLong, String paramString) {
        Coupon[] arrayOfCoupon = new Coupon[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCoupon[i] = deleteById(paramArrayOfLong[i], paramString);
            i++;
        }
        return arrayOfCoupon;
    }
}

