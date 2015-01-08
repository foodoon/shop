package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopChannelContentDao;
import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.cms.manager.ShopChannelContentMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopChannelContentMngImpl
        implements ShopChannelContentMng {
    private ShopChannelContentDao _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public ShopChannelContent findById(Long paramLong) {
        ShopChannelContent localShopChannelContent = this._$1.findById(paramLong);
        return localShopChannelContent;
    }

    public ShopChannelContent save(String paramString, ShopChannel paramShopChannel) {
        ShopChannelContent localShopChannelContent = new ShopChannelContent();
        localShopChannelContent.setContent(paramString);
        localShopChannelContent.setChannel(paramShopChannel);
        this._$1.save(localShopChannelContent);
        paramShopChannel.setChannelContent(localShopChannelContent);
        return localShopChannelContent;
    }

    public ShopChannelContent update(ShopChannelContent paramShopChannelContent) {
        Updater localUpdater = new Updater(paramShopChannelContent);
        ShopChannelContent localShopChannelContent = this._$1.updateByUpdater(localUpdater);
        return localShopChannelContent;
    }

    public ShopChannelContent deleteById(Long paramLong) {
        ShopChannelContent localShopChannelContent = this._$1.deleteById(paramLong);
        return localShopChannelContent;
    }

    public ShopChannelContent[] deleteByIds(Long[] paramArrayOfLong) {
        ShopChannelContent[] arrayOfShopChannelContent = new ShopChannelContent[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShopChannelContent[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShopChannelContent;
    }

    @Autowired
    public void setDao(ShopChannelContentDao paramShopChannelContentDao) {
        this._$1 = paramShopChannelContentDao;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopChannelContentMngImpl
 * JD-Core Version:    0.6.2
 */