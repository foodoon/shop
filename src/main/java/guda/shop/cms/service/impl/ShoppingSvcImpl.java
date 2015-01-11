package guda.shop.cms.service.impl;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.CartMng;
import guda.shop.cms.service.ShoppingSvc;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class ShoppingSvcImpl
        implements ShoppingSvc {

    @Autowired
    private CartMng _$1;

    public Cart collectAddToCart(Product paramProduct, Long paramLong1, Long paramLong2, int paramInt, boolean paramBoolean, ShopMember paramShopMember, Website paramWebsite, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        Cart localCart = this._$1.collectAddItem(paramProduct, paramLong1, paramLong2, paramInt, paramBoolean, paramShopMember.getId(), paramWebsite.getId());
        Cookie localCookie = _$1(localCart.getTotalItems().toString(), paramHttpServletRequest);
        paramHttpServletResponse.addCookie(localCookie);
        return localCart;
    }

    public void clearCart(ShopMember paramShopMember) {
        Cart localCart = this._$1.findById(paramShopMember.getId());
        localCart.getItems().clear();
        this._$1.update(localCart);
    }

    public Cart getCart(ShopMember paramShopMember, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        Cart localCart = this._$1.findById(paramShopMember.getId());
        if ((localCart != null) && (localCart.getItems().size() > 0)) {
            Cookie cookie = _$1(localCart.getTotalItems().toString(), paramHttpServletRequest);
            paramHttpServletResponse.addCookie(cookie);
            return localCart;
        }
        Cookie localCookie = _$1("0", paramHttpServletRequest);
        paramHttpServletResponse.addCookie(localCookie);
        return null;
    }

    public Cart getCart(Long paramLong) {
        Cart localCart = this._$1.findById(paramLong);
        if ((localCart != null) && (localCart.getItems().size() > 0))
            return localCart;
        return null;
    }

    public void addCookie(ShopMember paramShopMember, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        getCart(paramShopMember, paramHttpServletRequest, paramHttpServletResponse);
    }

    public void clearCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        Cookie localCookie = _$1(null, paramHttpServletRequest);
        localCookie.setMaxAge(0);
        paramHttpServletResponse.addCookie(localCookie);
    }

    private Cookie _$1(String paramString, HttpServletRequest paramHttpServletRequest) {
        Cookie localCookie = new Cookie("cart_total_items", paramString);
        String str = paramHttpServletRequest.getContextPath();
        localCookie.setPath(StringUtils.isBlank(str) ? "/" : str);
        return localCookie;
    }
}

