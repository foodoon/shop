package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.GiftExchangeDao;
import guda.shop.cms.entity.*;
import guda.shop.cms.manager.GiftExchangeMng;
import guda.shop.cms.manager.ShopScoreMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GiftExchangeMngImpl
        implements GiftExchangeMng {
    private GiftExchangeDao _$2;

    @Autowired
    private ShopScoreMng _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$2.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    public List<GiftExchange> getlist(Long paramLong) {
        return this._$2.getlist(paramLong);
    }

    @Transactional(readOnly = true)
    public GiftExchange findById(Long paramLong) {
        GiftExchange localGiftExchange = this._$2.findById(paramLong);
        return localGiftExchange;
    }

    public GiftExchange save(GiftExchange paramGiftExchange) {
        this._$2.save(paramGiftExchange);
        return paramGiftExchange;
    }

    public GiftExchange save(Gift paramGift, ShopMemberAddress paramShopMemberAddress, ShopMember paramShopMember, Integer paramInteger) {
        GiftExchange localGiftExchange = new GiftExchange();
        Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
        localGiftExchange.setAmount(paramInteger);
        localGiftExchange.setCreateTime(localTimestamp);
        localGiftExchange.setDetailaddress(paramShopMemberAddress.getUsername() + "," + paramShopMemberAddress.getTel() + "," + paramShopMemberAddress.getDetailaddress() + "," + paramShopMemberAddress.getPostCode());
        localGiftExchange.setGift(paramGift);
        localGiftExchange.setMember(paramShopMember);
        localGiftExchange.setScore(paramGift.getGiftScore());
        localGiftExchange.setTotalScore(Integer.valueOf(paramGift.getGiftScore().intValue() * paramInteger.intValue()));
        localGiftExchange.setStatus(Integer.valueOf(1));
        ShopScore localShopScore = new ShopScore();
        localShopScore.setMember(paramShopMember);
        localShopScore.setName(paramGift.getGiftName());
        localShopScore.setScoreTime(new Date());
        localShopScore.setStatus(true);
        localShopScore.setUseStatus(true);
        localShopScore.setScoreType(Integer.valueOf(ShopScore.ScoreTypes.ORDER_SCORE.getValue()));
        localShopScore.setScore(Integer.valueOf(paramGift.getGiftScore().intValue() * paramInteger.intValue()));
        this._$1.save(localShopScore);
        paramShopMember.setScore(Integer.valueOf(paramShopMember.getScore().intValue() - paramGift.getGiftScore().intValue() * paramInteger.intValue()));
        return save(localGiftExchange);
    }

    public GiftExchange update(GiftExchange paramGiftExchange) {
        Updater localUpdater = new Updater(paramGiftExchange);
        GiftExchange localGiftExchange = this._$2.updateByUpdater(localUpdater);
        return localGiftExchange;
    }

    public GiftExchange deleteById(Long paramLong) {
        GiftExchange localGiftExchange = this._$2.deleteById(paramLong);
        return localGiftExchange;
    }

    public GiftExchange[] deleteByIds(Long[] paramArrayOfLong) {
        GiftExchange[] arrayOfGiftExchange = new GiftExchange[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfGiftExchange[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfGiftExchange;
    }

    @Autowired
    public void setDao(GiftExchangeDao paramGiftExchangeDao) {
        this._$2 = paramGiftExchangeDao;
    }
}

