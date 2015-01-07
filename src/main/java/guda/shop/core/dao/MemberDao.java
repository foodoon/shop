package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
import com.jspgou.core.entity.Member;

public abstract interface MemberDao
{
  public abstract Member getByUserId(Long paramLong1, Long paramLong2);

  public abstract Member getByUserIdAndActive(String paramString, Long paramLong);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Member findById(Long paramLong);

  public abstract Member save(Member paramMember);

  public abstract Member updateByUpdater(Updater<Member> paramUpdater);

  public abstract Member deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.MemberDao
 * JD-Core Version:    0.6.2
 */