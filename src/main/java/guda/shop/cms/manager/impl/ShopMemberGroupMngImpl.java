package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopMemberGroupDao;
import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.cms.manager.ShopMemberGroupMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopMemberGroupMngImpl
        implements ShopMemberGroupMng {
    private ShopMemberGroupDao _$1;

    public ShopMemberGroup findGroupByScore(Long paramLong, int paramInt) {
        List localList = this._$1.getList(paramLong, true);
        int i = localList.size();
        if (i < 1)
            throw new IllegalStateException("ShopMmeberGroup not found in website id=" + paramLong);
        if (i == 1)
            return (ShopMemberGroup) localList.get(0);
        ShopMemberGroup localObject = (ShopMemberGroup) localList.get(0);
        for (int j = i - 1; j > 0; j--) {
            ShopMemberGroup localShopMemberGroup = (ShopMemberGroup) localList.get(j);
            if (paramInt > localShopMemberGroup.getScore().intValue()) {
                localObject = localShopMemberGroup;
                break;
            }
        }
        return localObject;
    }

    @Transactional(readOnly = true)
    public List<ShopMemberGroup> getList(Long paramLong) {
        return this._$1.getList(paramLong, false);
    }

    @Transactional(readOnly = true)
    public ShopMemberGroup findById(Long paramLong) {
        ShopMemberGroup localShopMemberGroup = this._$1.findById(paramLong);
        return localShopMemberGroup;
    }

    public ShopMemberGroup save(ShopMemberGroup paramShopMemberGroup) {
        this._$1.save(paramShopMemberGroup);
        return paramShopMemberGroup;
    }

    public ShopMemberGroup update(ShopMemberGroup paramShopMemberGroup) {
        Updater localUpdater = new Updater(paramShopMemberGroup);
        ShopMemberGroup localShopMemberGroup = this._$1.updateByUpdater(localUpdater);
        return localShopMemberGroup;
    }

    public ShopMemberGroup deleteById(Long paramLong) {
        ShopMemberGroup localShopMemberGroup = this._$1.deleteById(paramLong);
        return localShopMemberGroup;
    }

    public ShopMemberGroup[] deleteByIds(Long[] paramArrayOfLong) {
        ShopMemberGroup[] arrayOfShopMemberGroup = new ShopMemberGroup[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShopMemberGroup[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShopMemberGroup;
    }

    @Autowired
    public void setDao(ShopMemberGroupDao paramShopMemberGroupDao) {
        this._$1 = paramShopMemberGroupDao;
    }
}

