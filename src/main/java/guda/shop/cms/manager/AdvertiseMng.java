package guda.shop.cms.manager;

import guda.shop.cms.entity.Advertise;
import guda.shop.common.page.Pagination;

import java.util.List;
import java.util.Map;

public abstract interface AdvertiseMng {
    public abstract Pagination getPage(Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract List<Advertise> getList(Integer paramInteger, Boolean paramBoolean);

    public abstract Advertise findById(Integer paramInteger);

    public abstract Advertise save(Advertise paramAdvertise, Integer paramInteger, Map<String, String> paramMap);

    public abstract Advertise update(Advertise paramAdvertise, Integer paramInteger, Map<String, String> paramMap);

    public abstract Advertise deleteById(Integer paramInteger);

    public abstract Advertise[] deleteByIds(Integer[] paramArrayOfInteger);

    public abstract void display(Integer paramInteger);

    public abstract void click(Integer paramInteger);
}

