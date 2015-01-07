package guda.shop.cms.web;

import guda.shop.cms.entity.ShopMember;
iimport guda.shopcms.web.threadvariable.GroupThread;
imimport guda.shopms.web.threadvariable.MemberThread;
impimport guda.shopre.entity.Website;
impoimport guda.shope.web.front.FrontHelper;
imporimport guda.shop.web.front.URLHelper;
import com.jspgou.core.web.front.URLHelper.PageInfo;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.util.UrlPathHelper;

public abstract class ShopFrontHelper
{
  public static void setDynamicPageData(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    FrontHelper.setDynamicPageData(paramHttpServletRequest, paramModelMap, paramWebsite, paramString1, paramString2, paramString3, paramInt);
    setShopDate(paramHttpServletRequest, paramModelMap);
  }

  public static void frontPageData(HttpServletRequest paramHttpServletRequest, Map<String, Object> paramMap)
  {
    int i = URLHelper.getPageNo(paramHttpServletRequest);
    URLHelper.PageInfo localPageInfo = URLHelper.getPageInfo(paramHttpServletRequest);
    String str1 = localPageInfo.getHref();
    String str2 = localPageInfo.getHrefFormer();
    String str3 = localPageInfo.getHrefLatter();
    frontPageData(i, str1, str2, str3, paramMap);
  }

  public static void frontPage(HttpServletRequest paramHttpServletRequest, Map<String, Object> paramMap)
  {
    int i = URLHelper.getPageNo(paramHttpServletRequest);
    URLHelper.PageInfo localPageInfo = URLHelper.getPageInfo(paramHttpServletRequest);
    String str1 = localPageInfo.getHref();
    String str2 = localPageInfo.getHrefFormer();
    String str3 = localPageInfo.getHrefLatter();
    frontPageData(i, str1, str2, str3, paramMap);
  }

  public static void frontPageData(int paramInt, String paramString1, String paramString2, String paramString3, Map<String, Object> paramMap)
  {
    paramMap.put("pageNo", Integer.valueOf(paramInt));
    paramMap.put("href", paramString1);
    paramMap.put("hrefFormer", paramString2);
    paramMap.put("hrefLatter", paramString3);
  }

  public static void setCommonData(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, int paramInt)
  {
    FrontHelper.setCommonData(paramHttpServletRequest, paramModelMap, paramWebsite, paramInt);
    setShopDate(paramHttpServletRequest, paramModelMap);
  }

  public static void setCommon(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite)
  {
    ShopMember localShopMember = (ShopMember)paramHttpServletRequest.getAttribute("_member_key");
    if (localShopMember != null)
    {
      paramModelMap.addAttribute("member", localShopMember);
      paramModelMap.addAttribute("group", GroupThread.get());
    }
    paramModelMap.addAttribute("config", paramHttpServletRequest.getAttribute("_shop_config_key"));
    FrontHelper.setCommon(paramHttpServletRequest, paramModelMap, paramWebsite);
  }

  public static void setShopDate(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap)
  {
    paramModelMap.addAttribute("config", paramHttpServletRequest.getAttribute("_shop_config_key"));
    ShopMember localShopMember = MemberThread.get();
    if (localShopMember != null)
    {
      paramModelMap.addAttribute("member", localShopMember);
      paramModelMap.addAttribute("group", GroupThread.get());
    }
  }

  public static String getLocation(HttpServletRequest paramHttpServletRequest)
  {
    UrlPathHelper localUrlPathHelper = new UrlPathHelper();
    StringBuffer localStringBuffer = paramHttpServletRequest.getRequestURL();
    String str1 = paramHttpServletRequest.getRequestURI();
    String str2 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
    localStringBuffer.replace(localStringBuffer.length() - str1.length(), localStringBuffer.length(), str2);
    String str3 = localUrlPathHelper.getOriginatingQueryString(paramHttpServletRequest);
    if (str3 != null)
      localStringBuffer.append("?").append(str3);
    return localStringBuffer.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.ShopFrontHelper
 * JD-Core Version:    0.6.2
 */