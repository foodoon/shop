package guda.shop.core.manager;

import guda.shop.common.page.Pagination;
import guda.shop.core.entity.Member;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;

public abstract interface MemberMng {
    public abstract Member getByUsername(Long paramLong, String paramString);

    public abstract Member getByUserIdAndActive(String paramString, Long paramLong);

    public abstract Member getByUserId(Long paramLong1, Long paramLong2);

    public abstract Member register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Website paramWebsite);

    public abstract Member join(String paramString, Website paramWebsite);

    public abstract Member join(User paramUser, Website paramWebsite);

    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Member findById(Long paramLong);

    public abstract Member save(Member paramMember);

    public abstract Member update(Member paramMember);

    public abstract Member deleteById(Long paramLong);

    public abstract Member[] deleteByIds(Long[] paramArrayOfLong);
}

