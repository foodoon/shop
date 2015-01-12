package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseShopChannel
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "ShopChannel";
    public static String PROP_TYPE = "type";
    public static String PROP_CHANNEL_CONTENT = "channelContent";
    public static String PROP_PARAM1 = "param1";
    public static String PROP_RGT = "rgt";
    public static String PROP_WEBSITE = "website";
    public static String PROP_TPL_CHANNEL = "tplChannel";
    public static String PROP_PRIORITY = "priority";
    public static String PROP_TPL_CONTENT = "tplContent";
    public static String PROP_OUTER_URL = "outerUrl";
    public static String PROP_BLANK = "blank";
    public static String PROP_PARAM3 = "param3";
    public static String PROP_LFT = "lft";
    public static String PROP_PARENT = "parent";
    public static String PROP_PATH = "path";
    public static String PROP_DISPLAY = "display";
    public static String PROP_NAME = "name";
    public static String PROP_PARAM2 = "param2";
    public static String PROP_ID = "id";
    private int hash = -2147483648;
    private Long id;
    private Integer lft;
    private Integer rgt;
    private Integer type;
    private String name;
    private String path;
    private String outerUrl;
    private String tplChannel;
    private String tplContent;
    private Integer priority;
    private Boolean blank;
    private Boolean display;
    private String param1;
    private String param2;
    private String param3;
    private ShopChannelContent channelContent;
    private ShopChannel parent;
    private Website website;
    private Set<ShopChannel> child;

    public BaseShopChannel() {
        initialize();
    }

    public BaseShopChannel(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseShopChannel(Long paramLong, Website paramWebsite, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Integer paramInteger4) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setLft(paramInteger1);
        setRgt(paramInteger2);
        setType(paramInteger3);
        setName(paramString);
        setPriority(paramInteger4);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long paramLong) {
        this.id = paramLong;
        this.hash = -2147483648;
    }

    public Integer getLft() {
        return this.lft;
    }

    public void setLft(Integer paramInteger) {
        this.lft = paramInteger;
    }

    public Integer getRgt() {
        return this.rgt;
    }

    public void setRgt(Integer paramInteger) {
        this.rgt = paramInteger;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer paramInteger) {
        this.type = paramInteger;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String paramString) {
        this.path = paramString;
    }

    public String getOuterUrl() {
        return this.outerUrl;
    }

    public void setOuterUrl(String paramString) {
        this.outerUrl = paramString;
    }

    public String getTplChannel() {
        return this.tplChannel;
    }

    public void setTplChannel(String paramString) {
        this.tplChannel = paramString;
    }

    public String getTplContent() {
        return this.tplContent;
    }

    public void setTplContent(String paramString) {
        this.tplContent = paramString;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer paramInteger) {
        this.priority = paramInteger;
    }

    public Boolean getBlank() {
        return this.blank;
    }

    public void setBlank(Boolean paramBoolean) {
        this.blank = paramBoolean;
    }

    public Boolean getDisplay() {
        return this.display;
    }

    public void setDisplay(Boolean paramBoolean) {
        this.display = paramBoolean;
    }

    public String getParam1() {
        return this.param1;
    }

    public void setParam1(String paramString) {
        this.param1 = paramString;
    }

    public String getParam2() {
        return this.param2;
    }

    public void setParam2(String paramString) {
        this.param2 = paramString;
    }

    public String getParam3() {
        return this.param3;
    }

    public void setParam3(String paramString) {
        this.param3 = paramString;
    }

    public ShopChannelContent getChannelContent() {
        return this.channelContent;
    }

    public void setChannelContent(ShopChannelContent paramShopChannelContent) {
        this.channelContent = paramShopChannelContent;
    }

    public ShopChannel getParent() {
        return this.parent;
    }

    public void setParent(ShopChannel paramShopChannel) {
        this.parent = paramShopChannel;
    }

    public Website getWebsite() {
        return this.website;
    }

    public void setWebsite(Website paramWebsite) {
        this.website = paramWebsite;
    }

    public Set<ShopChannel> getChild() {
        return this.child;
    }

    public void setChild(Set<ShopChannel> paramSet) {
        this.child = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ShopChannel))
            return false;
        ShopChannel localShopChannel = (ShopChannel) paramObject;
        if ((null == getId()) || (null == localShopChannel.getId()))
            return false;
        return getId().equals(localShopChannel.getId());
    }

    public int hashCode() {
        if (-2147483648 == this.hash) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this.hash = str.hashCode();
        }
        return this.hash;
    }

    public String toString() {
        return super.toString();
    }
}

