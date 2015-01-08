package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.CartItemDao;
import guda.shop.cms.entity.CartItem;
import guda.shop.cms.manager.CartItemMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartItemMngImpl
        implements CartItemMng {

    @Autowired
    private CartItemDao _$1;

    @Transactional(readOnly = true)
    public CartItem findById(Long paramLong) {
        CartItem localCartItem = this._$1.findById(paramLong);
        return localCartItem;
    }

    public List<CartItem> getlist(Long paramLong1, Long paramLong2) {
        return this._$1.getlist(paramLong1, paramLong2);
    }

    public CartItem updateByUpdater(CartItem paramCartItem) {
        Updater localUpdater = new Updater(paramCartItem);
        return this._$1.updateByUpdater(localUpdater);
    }

    public CartItem deleteById(Long paramLong) {
        CartItem localCartItem = this._$1.deleteById(paramLong);
        return localCartItem;
    }

    public CartItem[] deleteByIds(Long[] paramArrayOfLong) {
        CartItem[] arrayOfCartItem = new CartItem[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfCartItem[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfCartItem;
    }

    public int deleteByProductId(Long paramLong) {
        return this._$1.deleteByProductId(paramLong);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.CartItemMngImpl
 * JD-Core Version:    0.6.2
 */