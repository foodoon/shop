package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseAdvertise;

public class Advertise extends BaseAdvertise {
    private static final long serialVersionUID = 1L;

    public Advertise() {
    }

    public Advertise(Integer paramInteger) {
        super(paramInteger);
    }

    public Advertise(Integer paramInteger, Adspace paramAdspace) {
        super(paramInteger, paramAdspace);
    }

    public void init() {
        if (getClickCount() == null)
            setClickCount(Integer.valueOf(0));
        if (getDisplayCount() == null)
            setDisplayCount(Integer.valueOf(0));
        if (getEnabled() == null)
            setEnabled(Boolean.valueOf(true));
        if (getWeight() == null)
            setWeight(Integer.valueOf(1));
    }
}

