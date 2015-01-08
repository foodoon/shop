package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopMemberDao;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopMemberAddress;
import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.cms.manager.*;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.image.ImageScale;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.springmvc.RealPathResolver;
import guda.shop.core.entity.Member;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.MemberMng;
import guda.shop.core.manager.UserMng;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ShopMemberMngImpl
        implements ShopMemberMng {

    @Autowired
    private CartMng _$10;

    @Autowired
    private UserMng _$9;

    @Autowired
    private ShopMemberDao _$8;

    @Autowired
    private MemberMng _$7;

    @Autowired
    private WebsiteMng _$6;

    @Autowired
    private ImageScale _$5;

    @Autowired
    private RealPathResolver _$4;

    @Autowired
    private ShopDictionaryMng _$3;

    @Autowired
    private ShopMemberGroupMng _$2;

    @Autowired
    private ShopMemberAddressMng _$1;

    public ShopMember getByUserId(Long paramLong1, Long paramLong2) {
        Member localMember = this._$7.getByUserId(paramLong1, paramLong2);
        if (localMember != null)
            return findById(localMember.getId());
        return null;
    }

    public ShopMember getByUsername(Long paramLong, String paramString) {
        Member localMember = this._$7.getByUsername(paramLong, paramString);
        if (localMember == null)
            return null;
        return findById(localMember.getId());
    }

    public ShopMember register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6, Long paramLong7, ShopMember paramShopMember) {
        Website localWebsite = this._$6.findById(paramLong1);
        if (paramShopMember == null)
            paramShopMember = new ShopMember();
        Member localMember = this._$7.register(paramString1, paramString2, paramString3, paramBoolean1, paramString4, paramString5, paramBoolean2, localWebsite);
        paramShopMember.setMember(localMember);
        paramShopMember.setWebsite(localWebsite);
        paramShopMember.setFreezeScore(Integer.valueOf(0));
        if (paramLong2 != null)
            paramShopMember.setGroup(this._$2.findById(paramLong2));
        if (paramLong3 != null)
            paramShopMember.setUserDegree(this._$3.findById(paramLong3));
        if (paramLong4 != null)
            paramShopMember.setDegree(this._$3.findById(paramLong4));
        if (paramLong5 != null)
            paramShopMember.setIncomeDesc(this._$3.findById(paramLong5));
        if (paramLong6 != null)
            paramShopMember.setWorkSeniority(this._$3.findById(paramLong6));
        if (paramLong7 != null)
            paramShopMember.setFamilyMembers(this._$3.findById(paramLong7));
        return save(paramShopMember);
    }

    public ShopMember join(String paramString, Long paramLong1, Long paramLong2) {
        Website localWebsite = this._$6.findById(paramLong1);
        Member localMember = this._$7.join(paramString, localWebsite);
        ShopMember localShopMember = new ShopMember();
        localShopMember.setMember(localMember);
        localShopMember.setWebsite(localWebsite);
        localShopMember.setGroup(this._$2.findById(paramLong2));
        return save(localShopMember);
    }

    public ShopMember join(Long paramLong1, Long paramLong2, ShopMemberGroup paramShopMemberGroup) {
        User localUser = this._$9.findById(paramLong1);
        return join(localUser, paramLong2, paramShopMemberGroup);
    }

    public ShopMember join(User paramUser, Long paramLong, ShopMemberGroup paramShopMemberGroup) {
        Website localWebsite = this._$6.findById(paramLong);
        Member localMember = this._$7.join(paramUser, localWebsite);
        ShopMember localShopMember = new ShopMember();
        localShopMember.setMember(localMember);
        localShopMember.setWebsite(localWebsite);
        localShopMember.setGroup(paramShopMemberGroup);
        return save(localShopMember);
    }

    public ShopMember register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Long paramLong1, Long paramLong2) {
        return register(paramString1, paramString2, paramString3, paramBoolean1, paramString4, paramString5, paramBoolean2, paramLong1, paramLong2, null, null, null, null, null, new ShopMember());
    }

    @Transactional(readOnly = true)
    public Pagination getPage(Long paramLong, int paramInt1, int paramInt2) {
        Pagination localPagination = this._$8.getPage(paramLong, paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public ShopMember findById(Long paramLong) {
        ShopMember localShopMember = this._$8.findById(paramLong);
        return localShopMember;
    }

    public ShopMember save(ShopMember paramShopMember) {
        paramShopMember.init();
        if (paramShopMember.getGroup() == null)
            paramShopMember.setGroup(this._$2.findGroupByScore(paramShopMember.getWebsite().getId(), paramShopMember.getScore().intValue()));
        this._$8.save(paramShopMember);
        return paramShopMember;
    }

    public ShopMember update(ShopMember paramShopMember, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6, String paramString1, String paramString2, Boolean paramBoolean) {
        ShopMember localShopMember = findById(paramShopMember.getId());
        this._$9.updateUser(localShopMember.getMember().getUser().getId(), paramString1, paramString2);
        if (paramBoolean != null)
            localShopMember.getMember().setDisabled(paramBoolean);
        if (paramLong1 != null)
            localShopMember.setGroup(this._$2.findById(paramLong1));
        if (paramLong2 != null)
            localShopMember.setUserDegree(this._$3.findById(paramLong2));
        if (paramLong3 != null)
            localShopMember.setDegree(this._$3.findById(paramLong3));
        if (paramLong4 != null)
            localShopMember.setIncomeDesc(this._$3.findById(paramLong4));
        if (paramLong5 != null)
            localShopMember.setWorkSeniority(this._$3.findById(paramLong5));
        if (paramLong6 != null)
            localShopMember.setFamilyMembers(this._$3.findById(paramLong6));
        Updater localUpdater = new Updater(paramShopMember);
        this._$8.updateByUpdater(localUpdater);
        localShopMember.setGroup(this._$2.findGroupByScore(localShopMember.getWebsite().getId(), localShopMember.getScore().intValue()));
        return localShopMember;
    }

    public ShopMember update(ShopMember paramShopMember) {
        Updater localUpdater = new Updater(paramShopMember);
        this._$8.updateByUpdater(localUpdater);
        return paramShopMember;
    }

    public ShopMember updateScore(ShopMember paramShopMember, Integer paramInteger) {
        Integer localInteger1 = this._$8.findById(paramShopMember.getId()).getScore();
        Integer localInteger2 = Integer.valueOf(localInteger1.intValue() + paramInteger.intValue());
        paramShopMember.setScore(localInteger2);
        paramShopMember.setGroup(this._$2.findGroupByScore(paramShopMember.getWebsite().getId(), localInteger2.intValue()));
        Updater localUpdater = new Updater(paramShopMember);
        this._$8.updateByUpdater(localUpdater);
        return paramShopMember;
    }

    public ShopMember update(ShopMember paramShopMember, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6) {
        ShopMember localShopMember = findById(paramShopMember.getId());
        if (paramLong2 != null)
            localShopMember.setUserDegree(this._$3.findById(paramLong2));
        if (paramLong3 != null)
            localShopMember.setDegree(this._$3.findById(paramLong3));
        if (paramLong4 != null)
            localShopMember.setIncomeDesc(this._$3.findById(paramLong4));
        if (paramLong5 != null)
            localShopMember.setWorkSeniority(this._$3.findById(paramLong5));
        if (paramLong6 != null)
            localShopMember.setFamilyMembers(this._$3.findById(paramLong6));
        Updater localUpdater = new Updater(paramShopMember);
        localUpdater.exclude(ShopMember.PROP_SCORE);
        this._$8.updateByUpdater(localUpdater);
        return localShopMember;
    }

    public ShopMember deleteById(Long paramLong) {
        ShopMember localShopMember = this._$8.findById(paramLong);
        Long localLong = localShopMember.getMember().getUser().getId();
        localShopMember = this._$8.deleteById(paramLong);
        this._$9.deleteById(localLong);
        return localShopMember;
    }

    public ShopMember[] deleteByIds(Long[] paramArrayOfLong) {
        ShopMember[] arrayOfShopMember = new ShopMember[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            List localList = this._$1.findByMemberId(paramArrayOfLong[i]);
            Iterator localIterator = localList.iterator();
            while (localIterator.hasNext()) {
                ShopMemberAddress localShopMemberAddress = (ShopMemberAddress) localIterator.next();
                this._$1.deleteById(localShopMemberAddress.getId(), paramArrayOfLong[i]);
            }
            this._$10.deleteById(paramArrayOfLong[i]);
            arrayOfShopMember[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShopMember;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopMemberMngImpl
 * JD-Core Version:    0.6.2
 */