package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.LogisticsTextDao;
import guda.shop.cms.entity.Logistics;
import guda.shop.cms.entity.LogisticsText;
import guda.shop.cms.manager.LogisticsTextMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogisticsTextMngImpl
        implements LogisticsTextMng {
    private LogisticsTextDao _$1;

    public LogisticsText save(Logistics paramLogistics, String paramString) {
        LogisticsText localLogisticsText = new LogisticsText();
        localLogisticsText.setLogistics(paramLogistics);
        localLogisticsText.setText(paramString);
        this._$1.save(localLogisticsText);
        return localLogisticsText;
    }

    public LogisticsText update(LogisticsText paramLogisticsText) {
        LogisticsText localLogisticsText = this._$1.updateByUpdater(new Updater(paramLogisticsText));
        return localLogisticsText;
    }

    @Autowired
    public void setDao(LogisticsTextDao paramLogisticsTextDao) {
        this._$1 = paramLogisticsTextDao;
    }
}

