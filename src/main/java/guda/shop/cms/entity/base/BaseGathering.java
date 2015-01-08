package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Gathering;
import guda.shop.cms.entity.Order;
import guda.shop.cms.entity.ShopAdmin;

import java.io.Serializable;

public abstract class BaseGathering
        implements Serializable {
    public static String REF = "Gathering";
    public static String PROP_DRAWEE = "drawee";
    public static String PROP_ACCOUNTS = "accounts";
    public static String PROP_MONEY = "money";
    public static String PROP_BANK = "bank";
    public static String PROP_COMMENT = "comment";
    public static String PROP_ID = "id";
    public static String PROP_SHOP_ADMIN = "shopAdmin";
    public static String PROP_INDENT = "indent";
    private int _$9 = -2147483648;
    private Long _$8;
    private String _$7;
    private String _$6;
    private double _$5;
    private String _$4;
    private String _$3;
    private Order _$2;
    private ShopAdmin _$1;

    public BaseGathering() {
        initialize();
    }

    public BaseGathering(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseGathering(Long paramLong, Order paramOrder, ShopAdmin paramShopAdmin, String paramString1, String paramString2, String paramString3, String paramString4) {
        setId(paramLong);
        setIndent(paramOrder);
        setShopAdmin(paramShopAdmin);
        setBank(paramString1);
        setAccounts(paramString2);
        setDrawee(paramString3);
        setComment(paramString4);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$8;
    }

    public void setId(Long paramLong) {
        this._$8 = paramLong;
        this._$9 = -2147483648;
    }

    public String getBank() {
        return this._$7;
    }

    public void setBank(String paramString) {
        this._$7 = paramString;
    }

    public String getAccounts() {
        return this._$6;
    }

    public void setAccounts(String paramString) {
        this._$6 = paramString;
    }

    public double getMoney() {
        return this._$5;
    }

    public void setMoney(double paramDouble) {
        this._$5 = paramDouble;
    }

    public String getDrawee() {
        return this._$4;
    }

    public void setDrawee(String paramString) {
        this._$4 = paramString;
    }

    public String getComment() {
        return this._$3;
    }

    public void setComment(String paramString) {
        this._$3 = paramString;
    }

    public Order getIndent() {
        return this._$2;
    }

    public void setIndent(Order paramOrder) {
        this._$2 = paramOrder;
    }

    public ShopAdmin getShopAdmin() {
        return this._$1;
    }

    public void setShopAdmin(ShopAdmin paramShopAdmin) {
        this._$1 = paramShopAdmin;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Gathering))
            return false;
        Gathering localGathering = (Gathering) paramObject;
        if ((null == getId()) || (null == localGathering.getId()))
            return false;
        return getId().equals(localGathering.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$9) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$9 = str.hashCode();
        }
        return this._$9;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseGathering
 * JD-Core Version:    0.6.2
 */