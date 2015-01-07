package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
imimport guda.shop.mmon.security.UsernameNotFoundException;
impimport guda.shop.mon.security.userdetails.UserDetails;
impoimport guda.shop.on.security.userdetails.UserDetailsService;
imporimport guda.shop.dao.MemberDao;
importimport guda.shop.ntity.Member;
import import guda.shop.tity.User;
import cimport guda.shop.ity.Website;
import coimport guda.shop.ger.MemberMng;
import com.jspgou.core.manager.UserMng;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberMngImpl
  implements MemberMng, UserDetailsService
{
  private UserMng _$2;
  private MemberDao _$1;

  public UserDetails loadUser(Long paramLong, String paramString)
    throws UsernameNotFoundException
  {
    Member localMember = findById(paramLong);
    if (localMember == null)
      throw new UsernameNotFoundException("member id not found '" + paramLong + "'");
    return localMember;
  }

  public Member getByUsername(Long paramLong, String paramString)
  {
    User localUser = this._$2.getByUsername(paramString);
    if (localUser == null)
      return null;
    return getByUserId(paramLong, localUser.getId());
  }

  public Member getByUserIdAndActive(String paramString, Long paramLong)
  {
    return this._$1.getByUserIdAndActive(paramString, paramLong);
  }

  public Member getByUserId(Long paramLong1, Long paramLong2)
  {
    return this._$1.getByUserId(paramLong1, paramLong2);
  }

  public Member register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Website paramWebsite)
  {
    Member localMember = new Member();
    Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
    User localUser = this._$2.register(paramString1, paramString2, paramString3, paramString5, localTimestamp);
    localMember.setCreateTime(localTimestamp);
    localMember.setUser(localUser);
    localMember.setWebsite(paramWebsite);
    localMember.setDisabled(paramBoolean2);
    localMember.setActive(paramBoolean1);
    localMember.setActivationCode(paramString4);
    return save(localMember);
  }

  public Member join(String paramString, Website paramWebsite)
  {
    User localUser = this._$2.getByUsername(paramString);
    if (localUser == null)
      throw new IllegalStateException("User not found: " + paramString);
    return join(localUser, paramWebsite);
  }

  public Member join(User paramUser, Website paramWebsite)
  {
    Member localMember1 = getByUserId(paramWebsite.getId(), paramUser.getId());
    if (localMember1 != null)
      return localMember1;
    Member localMember2 = new Member();
    localMember2.setUser(paramUser);
    localMember2.setWebsite(paramWebsite);
    return save(localMember2);
  }

  public Pagination getPage(int paramInt1, int paramInt2)
  {
    return this._$1.getPage(paramInt1, paramInt2);
  }

  public Member findById(Long paramLong)
  {
    return this._$1.findById(paramLong);
  }

  public Member save(Member paramMember)
  {
    paramMember.init();
    return this._$1.save(paramMember);
  }

  public Member update(Member paramMember)
  {
    return this._$1.updateByUpdater(new Updater(paramMember));
  }

  public Member deleteById(Long paramLong)
  {
    return this._$1.deleteById(paramLong);
  }

  public Member[] deleteByIds(Long[] paramArrayOfLong)
  {
    Member[] arrayOfMember = new Member[paramArrayOfLong.length];
    for (int i = 0; i < paramArrayOfLong.length; i++)
      arrayOfMember[i] = deleteById(paramArrayOfLong[i]);
    return arrayOfMember;
  }

  @Autowired
  public void setDao(MemberDao paramMemberDao)
  {
    this._$1 = paramMemberDao;
  }

  @Autowired
  public void setUserMng(UserMng paramUserMng)
  {
    this._$2 = paramUserMng;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.MemberMngImpl
 * JD-Core Version:    0.6.2
 */