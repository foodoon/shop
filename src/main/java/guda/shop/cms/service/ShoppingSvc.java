package guda.shop.cms.service;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ShopMember;
import guda.shop.core.entity.Website;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface ShoppingSvc {
    public abstract Cart getCart(ShopMember paramShopMember, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract void addCookie(ShopMember paramShopMember, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract void clearCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract Cart getCart(Long paramLong);

    public abstract void clearCart(ShopMember paramShopMember);

    public abstract Cart collectAddToCart(Product paramProduct, Long paramLong1, Long paramLong2, int paramInt, boolean paramBoolean, ShopMember paramShopMember, Website paramWebsite, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}

