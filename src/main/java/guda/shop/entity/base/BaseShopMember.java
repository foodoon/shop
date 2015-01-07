package guda.shop.entity.base;

import guda.shop.cms.entity.Product;
iimport guda.shopcms.entity.ShopDictionary;
imimport guda.shopms.entity.ShopMember;
impimport guda.shops.entity.ShopMemberGroup;
impoimport guda.shope.entity.Member;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public abstract class BaseShopMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopMember";
  public static String PROP_STREET = "street";
  public static String PROP_MEMBER = "member";
  public static String PROP_WEBSITE = "website";
  public static String PROP_FAX = "fax";
  public static String PROP_AVATAR = "avatar";
  public static String PROP_REAL_NAME = "realName";
  public static String PROP_CITY = "city";
  public static String PROP_GROUP = "group";
  public static String PROP_BIRTHDAY = "birthday";
  public static String PROP_STATE = "state";
  public static String PROP_DISTRICT = "district";
  public static String PROP_GENDER = "gender";
  public static String PROP_SUBURB = "suburb";
  public static String PROP_MOBILE = "mobile";
  public static String PROP_FIRSTNAME = "firstname";
  public static String PROP_LASTNAME = "lastname";
  public static String PROP_ID = "id";
  public static String PROP_ZIP = "zip";
  public static String PROP_COMPANY = "company";
  public static String PROP_SCORE = "score";
  public static String PROP_TEL = "tel";
  private int _$31 = -2147483648;
  private Long _$30;
  private String _$29;
  private Boolean _$28;
  private Integer _$27;
  private Integer _$26;
  private Double _$25;
  private Date _$24;
  private String _$23;
  private String _$22;
  private String _$21;
  private Boolean _$20;
  private String _$19;
  private Boolean _$18;
  private String _$17;
  private String _$16;
  private String _$15;
  private Date _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private Member _$9;
  private Website _$8;
  private ShopMemberGroup _$7;
  private ShopDictionary _$6;
  private ShopDictionary _$5;
  private ShopDictionary _$4;
  private ShopDictionary _$3;
  private ShopDictionary _$2;
  private Set<Product> _$1;

  public BaseShopMember()
  {
    initialize();
  }

  public BaseShopMember(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopMember(Long paramLong, Website paramWebsite, ShopMemberGroup paramShopMemberGroup, Integer paramInteger)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setGroup(paramShopMemberGroup);
    setScore(paramInteger);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$30;
  }

  public void setId(Long paramLong)
  {
    this._$30 = paramLong;
    this._$31 = -2147483648;
  }

  public String getRealName()
  {
    return this._$29;
  }

  public void setRealName(String paramString)
  {
    this._$29 = paramString;
  }

  public Boolean getGender()
  {
    return this._$28;
  }

  public void setGender(Boolean paramBoolean)
  {
    this._$28 = paramBoolean;
  }

  public Integer getScore()
  {
    return this._$27;
  }

  public void setScore(Integer paramInteger)
  {
    this._$27 = paramInteger;
  }

  public Integer getFreezeScore()
  {
    return this._$26;
  }

  public void setFreezeScore(Integer paramInteger)
  {
    this._$26 = paramInteger;
  }

  public Double getMoney()
  {
    return this._$25;
  }

  public void setMoney(Double paramDouble)
  {
    this._$25 = paramDouble;
  }

  public Date getBirthday()
  {
    return this._$24;
  }

  public void setBirthday(Date paramDate)
  {
    this._$24 = paramDate;
  }

  public String getTel()
  {
    return this._$23;
  }

  public void setTel(String paramString)
  {
    this._$23 = paramString;
  }

  public String getMobile()
  {
    return this._$22;
  }

  public void setMobile(String paramString)
  {
    this._$22 = paramString;
  }

  public Boolean getMarriage()
  {
    return this._$20;
  }

  public void setMarriage(Boolean paramBoolean)
  {
    this._$20 = paramBoolean;
  }

  public String getCompany()
  {
    return this._$19;
  }

  public void setCompany(String paramString)
  {
    this._$19 = paramString;
  }

  public String getAvatar()
  {
    return this._$21;
  }

  public void setAvatar(String paramString)
  {
    this._$21 = paramString;
  }

  public Boolean getIsCar()
  {
    return this._$18;
  }

  public void setIsCar(Boolean paramBoolean)
  {
    this._$18 = paramBoolean;
  }

  public String getPosition()
  {
    return this._$17;
  }

  public void setPosition(String paramString)
  {
    this._$17 = paramString;
  }

  public String getSchoolTag()
  {
    return this._$15;
  }

  public void setSchoolTag(String paramString)
  {
    this._$15 = paramString;
  }

  public Date getSchoolTagDate()
  {
    return this._$14;
  }

  public void setSchoolTagDate(Date paramDate)
  {
    this._$14 = paramDate;
  }

  public String getFavoriteBrand()
  {
    return this._$13;
  }

  public void setFavoriteBrand(String paramString)
  {
    this._$13 = paramString;
  }

  public String getFavoriteStar()
  {
    return this._$12;
  }

  public void setFavoriteStar(String paramString)
  {
    this._$12 = paramString;
  }

  public String getFavoriteMovie()
  {
    return this._$11;
  }

  public void setFavoriteMovie(String paramString)
  {
    this._$11 = paramString;
  }

  public String getFavoritePersonage()
  {
    return this._$10;
  }

  public void setFavoritePersonage(String paramString)
  {
    this._$10 = paramString;
  }

  public Member getMember()
  {
    return this._$9;
  }

  public void setMember(Member paramMember)
  {
    this._$9 = paramMember;
  }

  public Website getWebsite()
  {
    return this._$8;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$8 = paramWebsite;
  }

  public ShopMemberGroup getGroup()
  {
    return this._$7;
  }

  public void setGroup(ShopMemberGroup paramShopMemberGroup)
  {
    this._$7 = paramShopMemberGroup;
  }

  public String getAddress()
  {
    return this._$16;
  }

  public void setAddress(String paramString)
  {
    this._$16 = paramString;
  }

  public int getHashCode()
  {
    return this._$31;
  }

  public void setHashCode(int paramInt)
  {
    this._$31 = paramInt;
  }

  public ShopDictionary getUserDegree()
  {
    return this._$6;
  }

  public void setUserDegree(ShopDictionary paramShopDictionary)
  {
    this._$6 = paramShopDictionary;
  }

  public ShopDictionary getFamilyMembers()
  {
    return this._$5;
  }

  public void setFamilyMembers(ShopDictionary paramShopDictionary)
  {
    this._$5 = paramShopDictionary;
  }

  public ShopDictionary getIncomeDesc()
  {
    return this._$4;
  }

  public void setIncomeDesc(ShopDictionary paramShopDictionary)
  {
    this._$4 = paramShopDictionary;
  }

  public ShopDictionary getWorkSeniority()
  {
    return this._$3;
  }

  public void setWorkSeniority(ShopDictionary paramShopDictionary)
  {
    this._$3 = paramShopDictionary;
  }

  public ShopDictionary getDegree()
  {
    return this._$2;
  }

  public void setDegree(ShopDictionary paramShopDictionary)
  {
    this._$2 = paramShopDictionary;
  }

  public Set<Product> getFavorites()
  {
    return this._$1;
  }

  public void setFavorites(Set<Product> paramSet)
  {
    this._$1 = paramSet;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopMember))
      return false;
    ShopMember localShopMember = (ShopMember)paramObject;
    if ((null == getId()) || (null == localShopMember.getId()))
      return false;
    return getId().equals(localShopMember.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$31)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$31 = str.hashCode();
    }
    return this._$31;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopMember
 * JD-Core Version:    0.6.2
 */