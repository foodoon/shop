package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.OrderDao;
iimport guda.shop.ms.entity.Cart;
imimport guda.shop.s.entity.CartItem;
impimport guda.shop..entity.Coupon;
impoimport guda.shop.entity.MemberCoupon;
imporimport guda.shop.ntity.Order;
importimport guda.shop.tity.OrderItem;
import import guda.shop.ity.OrderReturn;
import cimport guda.shop.ty.Payment;
import coimport guda.shop.y.PopularityGroup;
import comimport guda.shop..PopularityItem;
import com.import guda.shop.Product;
import com.jimport guda.shop.roductFashion;
import com.jsimport guda.shop.ipping;
import com.jspimport guda.shop.pMember;
import com.jspgimport guda.shop.MemberAddress;
import com.jspgoimport guda.shop.core;
import com.jspgouimport guda.shop.ore.ScoreTypes;
import com.jspgou.import guda.shop.emMng;
import com.jspgou.cimport guda.shop.;
import com.jspgou.cmimport guda.shop.gMng;
import com.jspgou.cmsimport guda.shop.ponMng;
import com.jspgou.cms.import guda.shop.import com.jspgou.cms.mimport guda.shop.Mng;
import com.jspgou.cms.maimport guda.shop.import com.jspgou.cms.manimport guda.shop.mMng;
import com.jspgou.cms.manaimport guda.shop.Mng;
import com.jspgou.cms.managimport guda.shop.ort com.jspgou.cms.manageimport guda.shop.port com.jspgou.cms.managerimport guda.shop.rt com.jspgou.cms.manager.import guda.shop.ng;
import com.jspgou.cms.manager.Simport guda.shop.rt com.jspgou.cms.manager.Shimport guda.shop. com.jspgou.common.hibernatimport guda.shop. com.jspgou.common.page.Pagiimport guda.shop..jspgou.core.entity.Websiimport guda.shop.gou.core.manager.WebsiteMng;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderMngImpl
  implements OrderMng
{

  @Autowired
  private ProductMng _$16;

  @Autowired
  private ProductFashionMng _$15;

  @Autowired
  private ShopScoreMng _$14;

  @Autowired
  private WebsiteMng _$13;

  @Autowired
  private ShopMemberMng _$12;

  @Autowired
  private CartMng _$11;

  @Autowired
  private OrderDao _$10;

  @Autowired
  private CartItemMng _$9;

  @Autowired
  private GatheringMng _$8;

  @Autowired
  private ShipmentsMng _$7;

  @Autowired
  private OrderReturnMng _$6;

  @Autowired
  private MemberCouponMng _$5;

  @Autowired
  private PaymentMng _$4;

  @Autowired
  private ShopMemberAddressMng _$3;

  @Autowired
  private ShippingMng _$2;

  @Autowired
  private PopularityItemMng _$1;

  public Pagination getPageForOrderReturn(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$10.getPageForOrderReturn(paramLong, paramInt1, paramInt2);
  }

  public Order createOrder(Cart paramCart, Long[] paramArrayOfLong, Long paramLong1, Long paramLong2, Long paramLong3, String paramString1, String paramString2, ShopMember paramShopMember, Long paramLong4, String paramString3)
  {
    Website localWebsite = this._$13.findById(paramLong4);
    Long localLong1 = null;
    if (!StringUtils.isBlank(paramString3))
      localLong1 = Long.valueOf(Long.parseLong(paramString3));
    Payment localPayment = this._$4.findById(paramLong3);
    Shipping localShipping = this._$2.findById(paramLong1);
    ShopMemberAddress localShopMemberAddress = this._$3.findById(paramLong2);
    Order localOrder = new Order();
    localOrder.setShipping(localShipping);
    localOrder.setWebsite(localWebsite);
    localOrder.setMember(paramShopMember);
    localOrder.setPayment(localPayment);
    localOrder.setIp(paramString2);
    localOrder.setComments(paramString1);
    localOrder.setStatus(Integer.valueOf(1));
    localOrder.setShippingStatus(Integer.valueOf(1));
    localOrder.setPaymentStatus(Integer.valueOf(1));
    localOrder.setReceiveName(localShopMemberAddress.getUsername());
    localOrder.setReceiveAddress(localShopMemberAddress.getDetailaddress());
    localOrder.setReceiveMobile(localShopMemberAddress.getTel());
    localOrder.setReceivePhone(localShopMemberAddress.getMobile());
    localOrder.setReceiveZip(localShopMemberAddress.getPostCode());
    int i = 0;
    int j = 0;
    double d1 = 0.0D;
    Double localDouble1 = Double.valueOf(0.0D);
    Double localDouble2 = Double.valueOf(0.0D);
    if (paramCart != null)
    {
      localObject1 = this._$1.getlist(paramCart.getId(), null);
      localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PopularityItem localPopularityItem = (PopularityItem)((Iterator)localObject2).next();
        localDouble2 = Double.valueOf(localDouble2.doubleValue() + localPopularityItem.getPopularityGroup().getPrice().doubleValue() * localPopularityItem.getCount().intValue());
      }
    }
    if (localLong1 != null)
    {
      localObject1 = this._$5.findById(localLong1);
      if ((localObject1 != null) && (((MemberCoupon)localObject1).getCoupon().getIsusing().booleanValue()) && (!((MemberCoupon)localObject1).getIsuse().booleanValue()))
      {
        localDouble1 = Double.valueOf(((MemberCoupon)localObject1).getCoupon().getCouponPrice().doubleValue());
        ((MemberCoupon)localObject1).setIsuse(Boolean.valueOf(true));
        this._$5.update((MemberCoupon)localObject1);
      }
    }
    Object localObject1 = new ArrayList();
    for (localObject3 : paramArrayOfLong)
      ((List)localObject1).add(this._$9.findById((Long)localObject3));
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      CartItem localCartItem = (CartItem)((Iterator)localObject2).next();
      i += localCartItem.getProduct().getScore().intValue() * localCartItem.getCount().intValue();
      j += localCartItem.getProduct().getWeight().intValue() * localCartItem.getCount().intValue();
      if (localCartItem.getProductFash() != null)
        d1 += localCartItem.getProductFash().getSalePrice().doubleValue() * localCartItem.getCount().intValue();
      else
        d1 += localCartItem.getProduct().getSalePrice().doubleValue() * localCartItem.getCount().intValue();
    }
    if (paramShopMember.getFreezeScore() != null)
      paramShopMember.setFreezeScore(Integer.valueOf(i + paramShopMember.getFreezeScore().intValue()));
    else
      paramShopMember.setFreezeScore(Integer.valueOf(i + 0));
    localOrder.setScore(Integer.valueOf(i));
    localOrder.setWeight(Double.valueOf(j));
    localOrder.setProductPrice(Double.valueOf(d1));
    double d2 = localShipping.calPrice(Double.valueOf(j)).doubleValue();
    localOrder.setFreight(Double.valueOf(d2));
    localOrder.setTotal(Double.valueOf(d2 + d1 - localDouble1.doubleValue() - localDouble2.doubleValue()));
    Long localLong2 = Long.valueOf(new Date().getTime() + paramShopMember.getId().longValue());
    localOrder.setCode(localLong2);
    Object localObject3 = null;
    OrderItem localOrderItem1 = null;
    String str = null;
    for (int n = 0; n < ((List)localObject1).size(); n++)
    {
      localOrderItem1 = new OrderItem();
      localObject3 = (CartItem)((List)localObject1).get(n);
      localOrderItem1.setOrdeR(localOrder);
      localOrderItem1.setProduct(((CartItem)localObject3).getProduct());
      localOrderItem1.setWebsite(localWebsite);
      localOrderItem1.setCount(((CartItem)localObject3).getCount());
      if (((CartItem)localObject3).getProductFash() != null)
      {
        localOrderItem1.setProductFash(((CartItem)localObject3).getProductFash());
        localOrderItem1.setSalePrice(((CartItem)localObject3).getProductFash().getSalePrice());
      }
      else
      {
        localOrderItem1.setSalePrice(((CartItem)localObject3).getProduct().getSalePrice());
      }
      str = str + localOrderItem1.getProduct().getName();
      localOrder.addToItems(localOrderItem1);
    }
    localOrder.setProductName(str);
    List localList = this._$1.getlist(paramCart.getId(), null);
    Object localObject4 = localList.iterator();
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (PopularityItem)((Iterator)localObject4).next();
      this._$1.deleteById(((PopularityItem)localObject5).getId());
    }
    paramCart.getItems().removeAll((Collection)localObject1);
    this._$11.update(paramCart);
    localOrder = save(localOrder);
    localObject4 = null;
    Object localObject5 = null;
    ProductFashion localProductFashion = null;
    Iterator localIterator = localOrder.getItems().iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem2 = (OrderItem)localIterator.next();
      localObject5 = localOrderItem2.getProduct();
      if (localOrderItem2.getProductFash() != null)
      {
        localProductFashion = localOrderItem2.getProductFash();
        localProductFashion.setStockCount(Integer.valueOf(localProductFashion.getStockCount().intValue() - localOrderItem2.getCount().intValue()));
        ((Product)localObject5).setStockCount(Integer.valueOf(((Product)localObject5).getStockCount().intValue() - localOrderItem2.getCount().intValue()));
        this._$15.update(localProductFashion);
      }
      else
      {
        ((Product)localObject5).setStockCount(Integer.valueOf(((Product)localObject5).getStockCount().intValue() - localOrderItem2.getCount().intValue()));
      }
      this._$16.updateByUpdater((Product)localObject5);
      localObject4 = new ShopScore();
      ((ShopScore)localObject4).setMember(paramShopMember);
      ((ShopScore)localObject4).setName(((CartItem)localObject3).getProduct().getName());
      ((ShopScore)localObject4).setScoreTime(new Date());
      ((ShopScore)localObject4).setStatus(false);
      ((ShopScore)localObject4).setUseStatus(false);
      ((ShopScore)localObject4).setScoreType(Integer.valueOf(ShopScore.ScoreTypes.ORDER_SCORE.getValue()));
      ((ShopScore)localObject4).setScore(localOrderItem2.getProduct().getScore());
      ((ShopScore)localObject4).setCode(Long.toString(localOrder.getCode()));
      this._$14.save((ShopScore)localObject4);
    }
    return localOrder;
  }

  public List<Order> getlistByforaddressId(Long paramLong)
  {
    return this._$10.getlistByforaddressId(paramLong);
  }

  public Order updateByUpdater(Order paramOrder)
  {
    Updater localUpdater = new Updater(paramOrder);
    return this._$10.updateByUpdater(localUpdater);
  }

  public Pagination getOrderByReturn(Long paramLong, Integer paramInteger1, Integer paramInteger2)
  {
    return this._$10.getOrderByReturn(paramLong, paramInteger1, paramInteger2);
  }

  @Transactional(readOnly=true)
  public Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Long paramLong5, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$10.getPage(paramLong1, paramLong2, paramString1, paramString2, paramLong3, paramLong4, paramDate1, paramDate2, paramDouble1, paramDouble2, paramInteger1, paramInteger2, paramInteger3, paramLong5, paramInt1, paramInt2);
    return localPagination;
  }

  public List<Order> getlist()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localDate1 = new Date();
    Date localDate2 = null;
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate1);
    localCalendar.set(5, localCalendar.get(5) - 1);
    try
    {
      localDate2 = localSimpleDateFormat.parse(localSimpleDateFormat.format(localCalendar.getTime()));
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    List localList = this._$10.getlist(localDate2);
    return localList;
  }

  public void abolishOrder()
  {
    List localList = getlist();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Order localOrder = (Order)localIterator.next();
      localOrder.setStatus(Integer.valueOf(3));
      Object localObject1 = localOrder.getItems().iterator();
      Object localObject4;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (OrderItem)((Iterator)localObject1).next();
        localObject3 = ((OrderItem)localObject2).getProduct();
        if (((OrderItem)localObject2).getProductFash() != null)
        {
          localObject4 = ((OrderItem)localObject2).getProductFash();
          ((ProductFashion)localObject4).setStockCount(Integer.valueOf(((ProductFashion)localObject4).getStockCount().intValue() + ((OrderItem)localObject2).getCount().intValue()));
          ((Product)localObject3).setStockCount(Integer.valueOf(((Product)localObject3).getStockCount().intValue() + ((OrderItem)localObject2).getCount().intValue()));
          this._$15.update((ProductFashion)localObject4);
        }
        else
        {
          ((Product)localObject3).setStockCount(Integer.valueOf(((Product)localObject3).getStockCount().intValue() + ((OrderItem)localObject2).getCount().intValue()));
        }
        this._$16.updateByUpdater((Product)localObject3);
      }
      localObject1 = localOrder.getMember();
      ((ShopMember)localObject1).setFreezeScore(Integer.valueOf(((ShopMember)localObject1).getFreezeScore().intValue() - localOrder.getScore().intValue()));
      this._$12.update((ShopMember)localObject1);
      Object localObject2 = this._$14.getlist(Long.toString(localOrder.getCode()));
      Object localObject3 = ((List)localObject2).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (ShopScore)((Iterator)localObject3).next();
        this._$14.deleteById(((ShopScore)localObject4).getId());
      }
      updateByUpdater(localOrder);
    }
  }

  @Transactional(readOnly=true)
  public Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Long paramLong3, Long paramLong4, Date paramDate1, Date paramDate2, Double paramDouble1, Double paramDouble2, Integer paramInteger, Long paramLong5, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$10.getPage(paramLong1, paramLong2, paramString1, paramString2, paramLong3, paramLong4, paramDate1, paramDate2, paramDouble1, paramDouble2, paramInteger, paramLong5, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public Pagination getPageForMember(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$10.getPageForMember(paramLong, paramInt1, paramInt2);
  }

  public Pagination getPageForMember1(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$10.getPageForMember1(paramLong, paramInt1, paramInt2);
  }

  public Pagination getPageForMember2(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$10.getPageForMember2(paramLong, paramInt1, paramInt2);
  }

  public Pagination getPageForMember3(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$10.getPageForMember3(paramLong, paramInt1, paramInt2);
  }

  @Transactional(readOnly=true)
  public Order findById(Long paramLong)
  {
    Order localOrder = this._$10.findById(paramLong);
    return localOrder;
  }

  public void updateSaleCount(Long paramLong)
  {
    Order localOrder = this._$10.findById(paramLong);
    Iterator localIterator = localOrder.getItems().iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      Product localProduct = localOrderItem.getProduct();
      localProduct.setSaleCount(Integer.valueOf(localProduct.getSaleCount().intValue() + localOrderItem.getCount().intValue()));
      this._$16.update(localProduct);
    }
  }

  public void updateliRun(Long paramLong)
  {
    Order localOrder = this._$10.findById(paramLong);
    Iterator localIterator = localOrder.getItems().iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      Product localProduct = localOrderItem.getProduct();
      ProductFashion localProductFashion = localOrderItem.getProductFash();
      if (localProductFashion != null)
        localProduct.setLiRun(Double.valueOf(localProduct.getLiRun().doubleValue() + localOrderItem.getCount().intValue() * (localProductFashion.getSalePrice().doubleValue() - localProductFashion.getCostPrice().doubleValue())));
      else
        localProduct.setLiRun(Double.valueOf(localProduct.getLiRun().doubleValue() + localOrderItem.getCount().intValue() * (localProduct.getSalePrice().doubleValue() - localProduct.getCostPrice().doubleValue())));
      this._$16.update(localProduct);
    }
  }

  public Order save(Order paramOrder)
  {
    paramOrder.init();
    this._$10.save(paramOrder);
    return paramOrder;
  }

  public List<Object> getTotlaOrder()
  {
    return this._$10.getTotlaOrder();
  }

  public BigDecimal getMemberMoneyByYear(Long paramLong)
  {
    return this._$10.getMemberMoneyByYear(paramLong);
  }

  public Order deleteById(Long paramLong)
  {
    Order localOrder = this._$10.findById(paramLong);
    this._$8.deleteByorderId(paramLong);
    this._$7.deleteByorderId(paramLong);
    if (localOrder.getReturnOrder() != null)
      this._$6.deleteById(localOrder.getReturnOrder().getId());
    localOrder = this._$10.deleteById(paramLong);
    return localOrder;
  }

  public Order[] deleteByIds(Long[] paramArrayOfLong)
  {
    Order[] arrayOfOrder = new Order[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfOrder[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfOrder;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.OrderMngImpl
 * JD-Core Version:    0.6.2
 */