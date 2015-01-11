package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import guda.shop.core.entity.Member;

public abstract interface MemberDao {
    public abstract Member getByUserId(Long paramLong1, Long paramLong2);

    public abstract Member getByUserIdAndActive(String paramString, Long paramLong);

    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Member findById(Long paramLong);

    public abstract Member save(Member paramMember);

    public abstract Member updateByUpdater(Updater<Member> paramUpdater);

    public abstract Member deleteById(Long paramLong);
}

