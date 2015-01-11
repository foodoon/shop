package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseLogistics;

import java.util.Iterator;
import java.util.Set;

public class Logistics extends BaseLogistics {
    private static final long serialVersionUID = 1L;

    public Logistics() {
    }

    public Logistics(Long paramLong) {
        super(paramLong);
    }

    public Logistics(Long paramLong, String paramString, Integer paramInteger) {
        super(paramLong, paramString, paramInteger);
    }

    public LogisticsText getLogisticsText() {
        Set localSet = getLogisticsTextSet();
        if (localSet != null) {
            Iterator localIterator = localSet.iterator();
            if (localIterator.hasNext())
                return (LogisticsText) localIterator.next();
        }
        return null;
    }

    public String getText() {
        LogisticsText localLogisticsText = getLogisticsText();
        if (localLogisticsText != null)
            return localLogisticsText.getText();
        return null;
    }
}

