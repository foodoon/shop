package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Gift;
import guda.shop.cms.entity.GiftExchange;
import guda.shop.cms.entity.ShopMember;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseGiftExchange
        implements Serializable {
    public static String REF = "GiftExchange";
    public static String PROP_STATUS = "status";
    public static String PROP_MEMBER = "member";
    public static String PROP_AMOUNT = "amount";
    public static String PROP_DETAILADDRESS = "detailaddress";
    public static String PROP_GIFT = "gift";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_WAYBILL = "waybill";
    public static String PROP_TOTAL_SCORE = "totalScore";
    public static String PROP_ID = "id";
    public static String PROP_SCORE = "score";
    private int _$11 = -2147483648;
    private Long _$10;
    private Integer _$9;
    private Integer _$8;
    private Date _$7;
    private Integer _$6;
    private String _$5;
    private Integer _$4;
    private String _$3;
    private ShopMember _$2;
    private Gift _$1;

    public BaseGiftExchange() {
        initialize();
    }

    public BaseGiftExchange(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseGiftExchange(Long paramLong, ShopMember paramShopMember, Gift paramGift, Date paramDate, Integer paramInteger) {
        setId(paramLong);
        setMember(paramShopMember);
        setGift(paramGift);
        setCreateTime(paramDate);
        setStatus(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$10;
    }

    public void setId(Long paramLong) {
        this._$10 = paramLong;
        this._$11 = -2147483648;
    }

    public Integer getScore() {
        return this._$9;
    }

    public void setScore(Integer paramInteger) {
        this._$9 = paramInteger;
    }

    public Integer getAmount() {
        return this._$8;
    }

    public void setAmount(Integer paramInteger) {
        this._$8 = paramInteger;
    }

    public Date getCreateTime() {
        return this._$7;
    }

    public void setCreateTime(Date paramDate) {
        this._$7 = paramDate;
    }

    public Integer getTotalScore() {
        return this._$6;
    }

    public void setTotalScore(Integer paramInteger) {
        this._$6 = paramInteger;
    }

    public String getDetailaddress() {
        return this._$5;
    }

    public void setDetailaddress(String paramString) {
        this._$5 = paramString;
    }

    public Integer getStatus() {
        return this._$4;
    }

    public void setStatus(Integer paramInteger) {
        this._$4 = paramInteger;
    }

    public String getWaybill() {
        return this._$3;
    }

    public void setWaybill(String paramString) {
        this._$3 = paramString;
    }

    public ShopMember getMember() {
        return this._$2;
    }

    public void setMember(ShopMember paramShopMember) {
        this._$2 = paramShopMember;
    }

    public Gift getGift() {
        return this._$1;
    }

    public void setGift(Gift paramGift) {
        this._$1 = paramGift;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof GiftExchange))
            return false;
        GiftExchange localGiftExchange = (GiftExchange) paramObject;
        if ((null == getId()) || (null == localGiftExchange.getId()))
            return false;
        return getId().equals(localGiftExchange.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$11) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$11 = str.hashCode();
        }
        return this._$11;
    }

    public String toString() {
        return super.toString();
    }
}

