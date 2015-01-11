package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Address;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopMemberAddress;

import java.io.Serializable;

public abstract class BaseShopMemberAddress
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "ShopMemberAddress";
    public static String PROP_PHONE = "phone";
    public static String PROP_MEMBER = "member";
    public static String PROP_IS_DEFAULT = "isDefault";
    public static String PROP_PROVINCE = "province";
    public static String PROP_COUNTRY = "country";
    public static String PROP_CITY = "city";
    public static String PROP_AREA_CODE = "areaCode";
    public static String PROP_DETAILADDRESS = "detailaddress";
    public static String PROP_POST_CODE = "postCode";
    public static String PROP_USERNAME = "username";
    public static String PROP_GENDER = "gender";
    public static String PROP_EXT_NUMBER = "extNumber";
    public static String PROP_ID = "id";
    public static String PROP_TEL = "tel";
    private int _$15 = -2147483648;
    private Long _$14;
    private String _$13;
    private boolean _$12;
    private String _$11;
    private String _$10;
    private String _$9;
    private String _$8;
    private String _$7;
    private String _$6;
    private boolean _$5;
    private ShopMember _$4;
    private Address _$3;
    private Address _$2;
    private Address _$1;

    public BaseShopMemberAddress() {
        initialize();
    }

    public BaseShopMemberAddress(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseShopMemberAddress(Long paramLong, ShopMember paramShopMember, Address paramAddress1, Address paramAddress2, Address paramAddress3, String paramString1, String paramString2, boolean paramBoolean) {
        setId(paramLong);
        setMember(paramShopMember);
        setProvince(paramAddress1);
        setCity(paramAddress2);
        setCountry(paramAddress3);
        setUsername(paramString1);
        setDetailaddress(paramString2);
        setIsDefault(paramBoolean);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$14;
    }

    public void setId(Long paramLong) {
        this._$14 = paramLong;
        this._$15 = -2147483648;
    }

    public String getUsername() {
        return this._$13;
    }

    public void setUsername(String paramString) {
        this._$13 = paramString;
    }

    public boolean getGender() {
        return this._$12;
    }

    public void setGender(boolean paramBoolean) {
        this._$12 = paramBoolean;
    }

    public String getDetailaddress() {
        return this._$11;
    }

    public void setDetailaddress(String paramString) {
        this._$11 = paramString;
    }

    public String getPostCode() {
        return this._$10;
    }

    public void setPostCode(String paramString) {
        this._$10 = paramString;
    }

    public String getTel() {
        return this._$9;
    }

    public void setTel(String paramString) {
        this._$9 = paramString;
    }

    public String getAreaCode() {
        return this._$8;
    }

    public void setAreaCode(String paramString) {
        this._$8 = paramString;
    }

    public String getPhone() {
        return this._$7;
    }

    public void setPhone(String paramString) {
        this._$7 = paramString;
    }

    public String getExtNumber() {
        return this._$6;
    }

    public void setExtNumber(String paramString) {
        this._$6 = paramString;
    }

    public boolean getIsDefault() {
        return this._$5;
    }

    public void setIsDefault(boolean paramBoolean) {
        this._$5 = paramBoolean;
    }

    public ShopMember getMember() {
        return this._$4;
    }

    public void setMember(ShopMember paramShopMember) {
        this._$4 = paramShopMember;
    }

    public Address getProvince() {
        return this._$3;
    }

    public void setProvince(Address paramAddress) {
        this._$3 = paramAddress;
    }

    public Address getCity() {
        return this._$2;
    }

    public void setCity(Address paramAddress) {
        this._$2 = paramAddress;
    }

    public Address getCountry() {
        return this._$1;
    }

    public void setCountry(Address paramAddress) {
        this._$1 = paramAddress;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ShopMemberAddress))
            return false;
        ShopMemberAddress localShopMemberAddress = (ShopMemberAddress) paramObject;
        if ((null == getId()) || (null == localShopMemberAddress.getId()))
            return false;
        return getId().equals(localShopMemberAddress.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$15) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$15 = str.hashCode();
        }
        return this._$15;
    }

    public String toString() {
        return super.toString();
    }
}

