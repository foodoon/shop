package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.Category;
import guda.shop.cms.entity.ProductType;
import guda.shop.cms.entity.StandardType;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public abstract class BaseCategory
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Category";
    public static String PROP_RGT = "rgt";
    public static String PROP_PARENT = "parent";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_WEBSITE = "website";
    public static String PROP_TPL_CHANNEL = "tplChannel";
    public static String PROP_TYPE = "type";
    public static String PROP_TITLE = "title";
    public static String PROP_PATH = "path";
    public static String PROP_PRIORITY = "priority";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";
    public static String PROP_LFT = "lft";
    public static String PROP_IMAGE_PATH = "imagePath";
    public static String PROP_KEYWORDS = "keywords";
    public static String PROP_TPL_CONTENT = "tplContent";
    private int hash = -2147483648;
    private Long id;
    private String name;
    private String path;
    private String tplChannel;
    private String tplContent;
    private Integer lft;
    private Integer rgt;
    private Integer priority;
    private String title;
    private String imagePath;
    private String keywords;
    private String description;
    private Boolean colorsize;
    private Category parent;
    private ProductType type;
    private Website website;
    private Set<Category> child;
    private Set<Brand> brands;
    private Set<StandardType> standardType;
    private Map<String, String> attr;

    public BaseCategory() {
        initialize();
    }

    public BaseCategory(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCategory(Long paramLong, ProductType paramProductType, Website paramWebsite, String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3) {
        setId(paramLong);
        setType(paramProductType);
        setWebsite(paramWebsite);
        setName(paramString1);
        setPath(paramString2);
        setLft(paramInteger1);
        setRgt(paramInteger2);
        setPriority(paramInteger3);
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

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer paramInteger) {
        this.priority = paramInteger;
    }

    public Boolean getColorsize() {
        return this.colorsize;
    }

    public void setColorsize(Boolean paramBoolean) {
        this.colorsize = paramBoolean;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String paramString) {
        this.imagePath = paramString;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String paramString) {
        this.keywords = paramString;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String paramString) {
        this.description = paramString;
    }

    public Category getParent() {
        return this.parent;
    }

    public void setParent(Category paramCategory) {
        this.parent = paramCategory;
    }

    public ProductType getType() {
        return this.type;
    }

    public void setType(ProductType paramProductType) {
        this.type = paramProductType;
    }

    public Website getWebsite() {
        return this.website;
    }

    public void setWebsite(Website paramWebsite) {
        this.website = paramWebsite;
    }

    public Set<Category> getChild() {
        return this.child;
    }

    public void setChild(Set<Category> paramSet) {
        this.child = paramSet;
    }

    public Set<Brand> getBrands() {
        return this.brands;
    }

    public void setBrands(Set<Brand> paramSet) {
        this.brands = paramSet;
    }

    public Set<StandardType> getStandardType() {
        return this.standardType;
    }

    public void setStandardType(Set<StandardType> paramSet) {
        this.standardType = paramSet;
    }

    public Map<String, String> getAttr() {
        return this.attr;
    }

    public void setAttr(Map<String, String> paramMap) {
        this.attr = paramMap;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Category))
            return false;
        Category localCategory = (Category) paramObject;
        if ((null == getId()) || (null == localCategory.getId()))
            return false;
        return getId().equals(localCategory.getId());
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

