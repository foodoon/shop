package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.CartDao;
import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductFashion;
import guda.shop.cms.manager.*;
import guda.shop.core.manager.MemberMng;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartMngImpl
        implements CartMng {

    @Autowired
    private PopularityGroupMng _$7;

    @Autowired
    private CartDao _$6;

    @Autowired
    private GiftMng _$5;

    @Autowired
    private MemberMng _$4;

    @Autowired
    private ProductMng _$3;

    @Autowired
    private WebsiteMng _$2;

    @Autowired
    private ProductFashionMng _$1;

    @Transactional(readOnly = true)
    public Cart findById(Long paramLong) {
        Cart localCart = this._$6.findById(paramLong);
        return localCart;
    }

    public Cart collectAddItem(Product paramProduct, Long paramLong1, Long paramLong2, int paramInt, boolean paramBoolean, Long paramLong3, Long paramLong4) {
        Cart localCart = findById(paramLong3);
        if (localCart == null) {
            localCart = new Cart();
            localCart.setMember(this._$4.findById(paramLong3));
            localCart.setWebsite(this._$2.findById(paramLong4));
            localCart.init();
        }
        if (paramLong1 != null) {
            ProductFashion localProductFashion = this._$1.findById(paramLong1);
            localCart.addItem(localProductFashion.getProductId(), localProductFashion, this._$7.findById(paramLong2), paramInt, paramBoolean);
        } else {
            localCart.addItem(paramProduct, null, this._$7.findById(paramLong2), paramInt, paramBoolean);
        }
        localCart = this._$6.saveOrUpdate(localCart);
        return localCart;
    }

    public Cart addGift(Long paramLong1, int paramInt, boolean paramBoolean, Long paramLong2, Long paramLong3) {
        Cart localCart = findById(paramLong2);
        if (localCart == null) {
            localCart = new Cart();
            localCart.setMember(this._$4.findById(paramLong2));
            localCart.setWebsite(this._$2.findById(paramLong3));
        }
        localCart.addGift(this._$5.findById(paramLong1), paramInt, paramBoolean);
        this._$6.saveOrUpdate(localCart);
        return localCart;
    }

    public Cart update(Cart paramCart) {
        return this._$6.update(paramCart);
    }

    public Cart deleteById(Long paramLong) {
        Cart localCart = this._$6.deleteById(paramLong);
        return localCart;
    }
}

