package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseExendedItem;

public class ExendedItem extends BaseExendedItem {
    private static final long serialVersionUID = 1L;

    public ExendedItem() {
    }

    public ExendedItem(Long paramLong) {
        super(paramLong);
    }

    public ExendedItem(Long paramLong, Exended paramExended, String paramString) {
        super(paramLong, paramExended, paramString);
    }
}

