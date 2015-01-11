package guda.shop.cms.manager;

import guda.shop.cms.entity.Logistics;
import guda.shop.cms.entity.LogisticsText;

public abstract interface LogisticsTextMng {
    public abstract LogisticsText save(Logistics paramLogistics, String paramString);

    public abstract LogisticsText update(LogisticsText paramLogisticsText);
}

