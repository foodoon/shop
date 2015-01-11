package guda.shop.core.entity;

import guda.shop.core.entity.base.BaseLog;

import java.util.Date;

public class Log extends BaseLog {
    private static final long serialVersionUID = 1L;

    public Log() {
    }

    public Log(Long paramLong) {
        super(paramLong);
    }

    public Log(Long paramLong, Integer paramInteger, Date paramDate) {
        super(paramLong, paramInteger, paramDate);
    }
}

