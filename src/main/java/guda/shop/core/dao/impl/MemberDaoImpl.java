package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import guda.shop.core.dao.MemberDao;
import guda.shop.core.entity.Member;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl extends HibernateBaseDao<Member, Long>
  implements MemberDao
{
  public Member getByUserId(Long paramLong1, Long paramLong2)
  {
    String str = "from Member bean where bean.website.id=? and bean.user.id=?";
    return (Member)findUnique(str, new Object[] { paramLong1, paramLong2 });
  }

  public Member getByUserIdAndActive(String paramString, Long paramLong)
  {
    String str = "from Member bean where bean.activationCode=? and bean.user.id=?";
    return (Member)findUnique(str, new Object[] { paramString, paramLong });
  }

  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public Member findById(Long paramLong)
  {
    Member localMember = (Member)get(paramLong);
    return localMember;
  }

  public Member save(Member paramMember)
  {
    getSession().save(paramMember);
    return paramMember;
  }

  public Member deleteById(Long paramLong)
  {
    Member localMember = (Member)super.get(paramLong);
    if (localMember != null)
      getSession().delete(localMember);
    return localMember;
  }

  protected Class<Member> getEntityClass()
  {
    return Member.class;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.impl.MemberDaoImpl
 * JD-Core Version:    0.6.2
 */