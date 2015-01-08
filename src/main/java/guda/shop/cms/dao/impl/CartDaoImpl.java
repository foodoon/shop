package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CartDao;
import guda.shop.cms.entity.Cart;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl extends HibernateBaseDao<Cart, Long>
        implements CartDao {
    public Cart findById(Long paramLong) {
        Cart localCart = (Cart) get(paramLong);
        return localCart;
    }

    public Cart saveOrUpdate(Cart paramCart) {
        getSession().saveOrUpdate(paramCart);
        return paramCart;
    }

    public Cart update(Cart paramCart) {
        getSession().update(paramCart);
        return paramCart;
    }

    public Cart deleteById(Long paramLong) {
        Cart localCart = (Cart) super.get(paramLong);
        if (localCart != null)
            getSession().delete(localCart);
        return localCart;
    }

    protected Class<Cart> getEntityClass() {
        return Cart.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.CartDaoImpl
 * JD-Core Version:    0.6.2
 */