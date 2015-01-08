package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ExendedDao;
import guda.shop.cms.entity.Exended;
import guda.shop.cms.entity.ExendedItem;
import guda.shop.cms.entity.ProductType;
import guda.shop.cms.manager.ExendedItemMng;
import guda.shop.cms.manager.ExendedMng;
import guda.shop.cms.manager.ProductTypeMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ExendedMngImpl
        implements ExendedMng {
    private ExendedDao _$3;

    @Autowired
    private ProductTypeMng _$2;

    @Autowired
    private ExendedItemMng _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$3.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public Exended findById(Long paramLong) {
        Exended localExended = this._$3.findById(paramLong);
        return localExended;
    }

    public List<Exended> getList() {
        return this._$3.getList();
    }

    public List<Exended> getList(Long paramLong) {
        return this._$3.getList(paramLong);
    }

    public Exended save(Exended paramExended, Long[] paramArrayOfLong, List<ExendedItem> paramList) {
        paramExended = this._$3.save(paramExended);
        if ((paramArrayOfLong != null) && (paramArrayOfLong.length > 0))
            for (Long localLong : paramArrayOfLong)
                this._$2.findById(localLong).addToexendeds(paramExended);
        this._$1.save(paramList, paramExended);
        return paramExended;
    }

    public Exended update(Exended paramExended, Long[] paramArrayOfLong, List<ExendedItem> paramList) {
        Updater localUpdater = new Updater(paramExended);
        Exended localExended = this._$3.updateByUpdater(localUpdater);
        Set localSet = localExended.getProductTypes();
        Object localObject = localSet.iterator();
        while (((Iterator) localObject).hasNext()) {
            ProductType localProductType = (ProductType) ((Iterator) localObject).next();
            localProductType.removeFromExendeds(localExended);
        }
        localSet.clear();
        if ((paramArrayOfLong != null) && (paramArrayOfLong.length > 0))
            for (Long localLong : paramArrayOfLong)
                this._$2.findById(localLong).addToexendeds(localExended);
        localObject = localExended.getItems();
        Iterator localIterator = ((Set) localObject).iterator();
        while (localIterator.hasNext()) {
            ExendedItem localExendedItem = (ExendedItem) localIterator.next();
            this._$1.deleteById(localExendedItem.getId());
        }
        this._$1.save(paramList, paramExended);
        return localExended;
    }

    public Exended deleteById(Long paramLong) {
        Exended localExended = this._$3.deleteById(paramLong);
        return localExended;
    }

    public Exended[] deleteByIds(Long[] paramArrayOfLong) {
        Exended[] arrayOfExended = new Exended[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfExended[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfExended;
    }

    public Exended[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        int i = paramArrayOfLong.length;
        Exended[] arrayOfExended = new Exended[i];
        for (int j = 0; j < i; j++) {
            arrayOfExended[j] = findById(paramArrayOfLong[j]);
            arrayOfExended[j].setPriority(paramArrayOfInteger[j]);
        }
        return arrayOfExended;
    }

    @Autowired
    public void setDao(ExendedDao paramExendedDao) {
        this._$3 = paramExendedDao;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ExendedMngImpl
 * JD-Core Version:    0.6.2
 */