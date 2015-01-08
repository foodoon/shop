package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductType;
import guda.shop.core.entity.Website;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

public class ProductType extends BaseProductType {
    private static final long serialVersionUID = 1L;

    public ProductType() {
    }

    public ProductType(Long paramLong) {
        super(paramLong);
    }

    public ProductType(Long paramLong, Website paramWebsite, String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Integer paramInteger6) {
        super(paramLong, paramWebsite, paramString1, paramString3, paramString4, paramBoolean, paramInteger1, paramInteger2, paramInteger3, paramInteger4, paramInteger5, paramInteger6);
    }

    public static String[] getTpls(String paramString1, String paramString2, String paramString3) {
        File localFile = new File(paramString1);
        File[] arrayOfFile = localFile.listFiles(new FilenameFilter() {
            public boolean accept(File paramAnonymousFile, String paramAnonymousString) {
                paramAnonymousString = paramAnonymousString.substring(0, paramAnonymousString.indexOf("."));
                return paramAnonymousString.endsWith(REF);
            }
        });
        int i = arrayOfFile.length;
        String[] arrayOfString = new String[i];
        for (int j = 0; j < i; j++)
            arrayOfString[j] = (paramString2 + "/" + arrayOfFile[j].getName());
        return arrayOfString;
    }

    public String getTplDirRel() {
        return "/" + "category";
    }

    public String getCtgTplDirRel() {
        return "/" + "category";
    }

    public String getTxtTplDirRel() {
        return "/" + "product";
    }

    public String[] getChannelTpls(String paramString1, String paramString2) {
        return getTpls(paramString1, paramString2, getChannelPrefix());
    }

    public String[] getContentTpls(String paramString1, String paramString2) {
        return getTpls(paramString1, paramString2, getContentPrefix());
    }

    public void addToexendeds(Exended paramExended) {
        Object localObject = getExendeds();
        if (localObject == null) {
            localObject = new HashSet();
            setExendeds((Set) localObject);
        }
        ((Set) localObject).add(paramExended);
        paramExended.addToProductTypes(this);
    }

    public void removeFromExendeds(Exended paramExended) {
        Set localSet = getExendeds();
        if (localSet != null)
            localSet.remove(paramExended);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductType
 * JD-Core Version:    0.6.2
 */