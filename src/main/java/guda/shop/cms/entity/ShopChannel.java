package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopChannel;
iimport guda.shop.ommon.hibernate3.HibernateTree;
imimport guda.shop.mmon.hibernate3.PriorityComparator;
impimport guda.shop.mon.hibernate3.PriorityInterface;
import com.jspgou.core.entity.Website;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;

public class ShopChannel extends BaseShopChannel
  implements HibernateTree, PriorityInterface
{
  private static final long serialVersionUID = 1L;
  public static final int ALONE = 1;
  public static final int ARTICLE = 2;
  public static final int OUTER_URL = 3;
  public static final String CHANNEL_SUFFIX = "栏目";
  public static final String CONTENT_SUFFIX = "内容";
  public static final String ALONE_SUFFIX = "单页";

  public static String getChannelTplDirRel(Website paramWebsite)
  {
    return "/" + "channel";
  }

  public static String getContentTplDirRel(Website paramWebsite)
  {
    return "/" + "article";
  }

  public static String[] getChannelTpls(Integer paramInteger, String paramString1, String paramString2)
  {
    String str = getPrefix(paramInteger, true);
    if (str != null)
      return ProductType.getTpls(paramString1, paramString2, str);
    return null;
  }

  public static String[] getContentTpls(Integer paramInteger, String paramString1, String paramString2)
  {
    String str = getPrefix(paramInteger, false);
    if (str != null)
      return ProductType.getTpls(paramString1, paramString2, str);
    return null;
  }

  public static String getPrefix(Integer paramInteger, boolean paramBoolean)
  {
    if (paramInteger == null)
      throw new IllegalStateException("ShopChannle type connot be null");
    if (paramInteger.intValue() == 1)
      return "单页";
    if (paramInteger.intValue() == 2)
      return paramBoolean ? "栏目" : "内容";
    return null;
  }

  public String getTplChannelRel()
  {
    String str1 = getTplChannel();
    if (StringUtils.isBlank(str1))
    {
      String str2 = getPrefix(getType(), true);
      if (str2 != null)
        return getWebsite().getTemplate("channel", "栏目");
      return null;
    }
    return getWebsite().getTemplateRel(str1);
  }

  public String getTplContentRel()
  {
    String str1 = getTplContent();
    if (StringUtils.isBlank(str1))
    {
      String str2 = getPrefix(getType(), false);
      if (str2 != null)
        return getWebsite().getTemplate("article", "内容");
      return null;
    }
    return getWebsite().getTemplateRel(str1);
  }

  public String getUrl()
  {
    int i = getType().intValue();
    if (i == 3)
    {
      String str = getOuterUrl();
      if (StringUtils.isBlank(str))
        throw new IllegalStateException("ShopChannel outerUrl cannot be blank while type is OUTER_URL, ID=" + getId());
      if (str.startsWith("/"))
        return str;
      if (str.startsWith("http"))
        return str;
      return "http://" + str;
    }
    if (i == 1)
      return "/" + getPath() + getWebsite().getSuffix();
    if (i == 2)
      return "/" + getPath() + "/" + "index" + getWebsite().getSuffix();
    throw new IllegalStateException("ShopChannel type not supported: id=" + getId() + " type=" + i);
  }

  public String getContent()
  {
    ShopChannelContent localShopChannelContent = getChannelContent();
    if (localShopChannelContent != null)
      return localShopChannelContent.getContent();
    return null;
  }

  public int getDeep()
  {
    int i = 0;
    for (ShopChannel localShopChannel = getParent(); localShopChannel != null; localShopChannel = localShopChannel.getParent())
      i++;
    return i;
  }

  public void addToChild(ShopChannel paramShopChannel)
  {
    Object localObject = getChild();
    if (localObject == null)
    {
      localObject = new TreeSet(PriorityComparator.INSTANCE);
      setChild((Set)localObject);
    }
    ((Set)localObject).add(paramShopChannel);
  }

  public String getTreeCondition()
  {
    return "bean.website.id=" + getWebsite().getId();
  }

  public Long getParentId()
  {
    ShopChannel localShopChannel = getParent();
    if (localShopChannel != null)
      return localShopChannel.getId();
    return null;
  }

  public String getLftName()
  {
    return "lft";
  }

  public String getParentName()
  {
    return "parent";
  }

  public String getRgtName()
  {
    return "rgt";
  }

  public ShopChannel()
  {
  }

  public ShopChannel(Long paramLong)
  {
    super(paramLong);
  }

  public ShopChannel(Long paramLong, Website paramWebsite, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Integer paramInteger4)
  {
    super(paramLong, paramWebsite, paramInteger1, paramInteger2, paramInteger3, paramString, paramInteger4);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopChannel
 * JD-Core Version:    0.6.2
 */