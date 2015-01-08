package guda.shop.cms.web;

import guda.shop.cms.entity.ShopConfig;

import javax.servlet.http.HttpServletRequest;

public class SiteUtils extends guda.shop.core.web.SiteUtils {
    public static ShopConfig getConfig(HttpServletRequest paramHttpServletRequest) {
        ShopConfig localShopConfig = (ShopConfig) paramHttpServletRequest.getAttribute("_shop_config_key");
        if (localShopConfig == null)
            throw new IllegalStateException("Config not found in Request Attribute '_shop_config_key'");
        return localShopConfig;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.SiteUtils
 * JD-Core Version:    0.6.2
 */