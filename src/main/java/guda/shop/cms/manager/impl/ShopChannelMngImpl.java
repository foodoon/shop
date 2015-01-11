package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopChannelDao;
import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.cms.manager.ShopChannelContentMng;
import guda.shop.cms.manager.ShopChannelMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ShopChannelMngImpl
        implements ShopChannelMng {
    private ShopChannelContentMng _$2;
    private ShopChannelDao _$1;

    public List<ShopChannel> getTopList(Long paramLong) {
        return this._$1.getTopList(paramLong, false, null);
    }

    public List<ShopChannel> getChildList(Long paramLong1, Long paramLong2) {
        return this._$1.getChildList(paramLong1, paramLong2);
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getList(Long paramLong) {
        List localList = this._$1.getTopList(paramLong, false, null);
        ArrayList localArrayList = new ArrayList();
        _$1(localArrayList, localList);
        return localArrayList;
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getArticleList(Long paramLong) {
        return this._$1.getList(paramLong, Integer.valueOf(2));
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getAloneChannelList(Long paramLong) {
        return this._$1.getList(paramLong, Integer.valueOf(1));
    }

    public List<ShopChannel> getList(Long paramLong1, Long paramLong2, Long paramLong3) {
        return this._$1.getList(paramLong1, Integer.valueOf(2), paramLong2, paramLong3);
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getListForParent(Long paramLong1, Long paramLong2) {
        List localList1 = getList(paramLong1);
        List localList2 = this._$1.getListForChild(paramLong1, paramLong2);
        localList1.removeAll(localList2);
        return localList1;
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getListForParentNoSort(Long paramLong1, Long paramLong2) {
        return this._$1.getListForParent(paramLong1, paramLong2);
    }

    @Transactional(readOnly = true)
    public List<ShopChannel> getTopListForTag(Long paramLong, Integer paramInteger) {
        return this._$1.getTopList(paramLong, true, paramInteger);
    }

    private void _$1(List<ShopChannel> paramList, Collection<ShopChannel> paramCollection) {
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext()) {
            ShopChannel localShopChannel = (ShopChannel) localIterator.next();
            paramList.add(localShopChannel);
            Set localSet = localShopChannel.getChild();
            if ((localSet != null) && (localSet.size() > 0))
                _$1(paramList, localSet);
        }
    }

    public ShopChannel getByPath(Long paramLong, String paramString) {
        return this._$1.getByPath(paramLong, paramString);
    }

    @Transactional(readOnly = true)
    public ShopChannel findById(Long paramLong) {
        ShopChannel localShopChannel = this._$1.findById(paramLong);
        return localShopChannel;
    }

    public ShopChannel save(ShopChannel paramShopChannel, Long paramLong, String paramString) {
        ShopChannel localShopChannel = null;
        if (paramLong != null) {
            localShopChannel = findById(paramLong);
            paramShopChannel.setParent(localShopChannel);
        }
        this._$1.save(paramShopChannel);
        if (paramString != null)
            this._$2.save(paramString, paramShopChannel);
        return paramShopChannel;
    }

    public ShopChannel update(ShopChannel paramShopChannel, Long paramLong, String paramString) {
        ShopChannel localShopChannel = findById(paramShopChannel.getId());
        ShopChannelContent localShopChannelContent = localShopChannel.getChannelContent();
        if (localShopChannelContent != null)
            localShopChannelContent.setContent(paramString);
        else
            this._$2.save(paramString, localShopChannel);
        if (paramLong != null)
            localShopChannel.setParent(findById(paramLong));
        else
            localShopChannel.setParent(null);
        Updater localUpdater = new Updater(paramShopChannel);
        localShopChannel = this._$1.updateByUpdater(localUpdater);
        return localShopChannel;
    }

    public ShopChannel deleteById(Long paramLong) {
        ShopChannel localShopChannel = this._$1.deleteById(paramLong);
        return localShopChannel;
    }

    public ShopChannel[] deleteByIds(Long[] paramArrayOfLong) {
        ShopChannel[] arrayOfShopChannel = new ShopChannel[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShopChannel[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShopChannel;
    }

    public ShopChannel[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        ShopChannel[] arrayOfShopChannel = new ShopChannel[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShopChannel[i] = findById(paramArrayOfLong[i]);
            arrayOfShopChannel[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfShopChannel;
    }

    @Autowired
    public void setDao(ShopChannelDao paramShopChannelDao) {
        this._$1 = paramShopChannelDao;
    }

    @Autowired
    public void setShopChannelContentMng(ShopChannelContentMng paramShopChannelContentMng) {
        this._$2 = paramShopChannelContentMng;
    }
}

