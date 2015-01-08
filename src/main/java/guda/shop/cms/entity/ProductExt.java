package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductExt;

import java.util.ArrayList;
import java.util.List;

public class ProductExt extends BaseProductExt {
    private static final long serialVersionUID = 1L;

    public ProductExt() {
    }

    public ProductExt(Long paramLong) {
        super(paramLong);
    }

    public ProductExt(Long paramLong, Integer paramInteger, String paramString) {
        super(paramLong, paramInteger, paramString);
    }

    public void init() {
        if (getWeight() == null)
            setWeight(Integer.valueOf(0));
        if (getUnit() == null)
            setUnit("");
    }

    public List<String> getImgs() {
        String str = getProductImgs();
        ArrayList localArrayList = new ArrayList();
        if ((str != null) && (!str.equals(""))) {
            String[] arrayOfString = str.split("@@");
            for (int i = 0; i < arrayOfString.length; i++)
                if (arrayOfString[i].indexOf("/") != -1)
                    localArrayList.add(arrayOfString[i]);
        }
        return localArrayList;
    }

    public List<String> getImgsDesc() {
        String str = getProductImgDesc();
        ArrayList localArrayList = new ArrayList();
        String[] arrayOfString = str.split("@@");
        for (int i = 0; i < arrayOfString.length; i++)
            localArrayList.add(arrayOfString[i]);
        return localArrayList;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductExt
 * JD-Core Version:    0.6.2
 */