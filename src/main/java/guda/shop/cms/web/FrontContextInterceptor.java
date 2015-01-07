package guda.shop.cms.web;

import guda.shop.cms.entity.ShopConfig;
iimport guda.shopcms.entity.ShopMember;
imimport guda.shopms.manager.ShopConfigMng;
impimport guda.shops.service.LoginSvc;
impoimport guda.shop.web.threadvariable.GroupThread;
imporimport guda.shopweb.threadvariable.MemberThread;
importimport guda.shopentity.Website;
import guda.shop.core.web.SiteUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontContextInterceptor extends HandlerInterceptorAdapter
{

  @Autowired
  private ShopConfigMng _$2;

  @Autowired
  private LoginSvc _$1;

  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
    throws ServletException
  {
    Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
    ShopConfig localShopConfig = this._$2.findById(localWebsite.getId());
    if (localShopConfig == null)
      throw new IllegalStateException("no ShopConfig found in Website id=" + localWebsite.getId());
    paramHttpServletRequest.setAttribute("_shop_config_key", localShopConfig);
    ShopMember localShopMember = this._$1.getMember(paramHttpServletRequest, paramHttpServletResponse, localWebsite);
    if (localShopMember != null)
    {
      MemberThread.set(localShopMember);
      GroupThread.set(localShopMember.getGroup());
    }
    return true;
  }

  public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
    throws Exception
  {
    MemberThread.remove();
    GroupThread.remove();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.FrontContextInterceptor
 * JD-Core Version:    0.6.2
 */