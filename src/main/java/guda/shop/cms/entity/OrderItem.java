package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseOrderItem;
import com.jspgou.core.entity.Website;

public class OrderItem extends BaseOrderItem
{
  private static final long serialVersionUID = 1L;

  public Double getSubtotal()
  {
    return Double.valueOf(getFinalPrice().doubleValue() * getCount().intValue());
  }

  public Double getLimitSubtotal()
  {
    return Double.valueOf(getSeckillprice().doubleValue() * getCount().intValue());
  }

  public int getWeightForFreight()
  {
    Product localProduct = getProduct();
    return localProduct.getWeight().intValue() * getCount().intValue();
  }

  public int getCountForFreigt()
  {
    return getCount().intValue();
  }

  public static OrderItem parse(CartItem paramCartItem, ShopMemberGroup paramShopMemberGroup)
  {
    return parse(paramCartItem.getProduct(), paramCartItem.getCount().intValue(), paramShopMemberGroup, paramCartItem.getWebsite());
  }

  public static OrderItem parse1(CartItem paramCartItem, ShopMemberGroup paramShopMemberGroup)
  {
    if (paramCartItem.getProductFash() == null)
      return parse(paramCartItem.getProduct(), paramCartItem.getCount().intValue(), paramShopMemberGroup, paramCartItem.getWebsite());
    return parse1(paramCartItem.getProduct(), paramCartItem.getProductFash(), paramCartItem.getCount().intValue(), paramShopMemberGroup, paramCartItem.getWebsite());
  }

  public static OrderItem parse(Product paramProduct, int paramInt, ShopMemberGroup paramShopMemberGroup, Website paramWebsite)
  {
    paramProduct.setStockCount(Integer.valueOf(paramProduct.getStockCount().intValue() - paramInt));
    paramProduct.setSaleCount(Integer.valueOf(paramInt + paramProduct.getSaleCount().intValue()));
    OrderItem localOrderItem = new OrderItem();
    localOrderItem.setCount(Integer.valueOf(paramInt));
    localOrderItem.setWebsite(paramWebsite);
    localOrderItem.setProduct(paramProduct);
    if ((paramProduct.getProductExt().getIslimitTime() != null) && (paramProduct.getProductExt().getIslimitTime().booleanValue()) && (paramProduct.getProductExt().getSeckillprice() != null))
      localOrderItem.setSeckillprice(paramProduct.getProductExt().getSeckillprice());
    localOrderItem.setSalePrice(paramProduct.getSalePrice());
    localOrderItem.setCostPrice(paramProduct.getCostPrice());
    localOrderItem.setMemberPrice(paramProduct.getMemberPrice(paramShopMemberGroup));
    localOrderItem.setFinalPrice(localOrderItem.getMemberPrice());
    return localOrderItem;
  }

  public static OrderItem parse1(Product paramProduct, ProductFashion paramProductFashion, int paramInt, ShopMemberGroup paramShopMemberGroup, Website paramWebsite)
  {
    paramProductFashion.setStockCount(Integer.valueOf(paramProductFashion.getStockCount().intValue() - paramInt));
    paramProductFashion.setSaleCount(Integer.valueOf(paramInt + paramProductFashion.getSaleCount().intValue()));
    OrderItem localOrderItem = new OrderItem();
    localOrderItem.setCount(Integer.valueOf(paramInt));
    localOrderItem.setWebsite(paramWebsite);
    localOrderItem.setProduct(paramProduct);
    if ((paramProduct.getProductExt().getIslimitTime() != null) && (paramProduct.getProductExt().getIslimitTime().booleanValue()) && (paramProduct.getProductExt().getSeckillprice() != null))
      localOrderItem.setSeckillprice(paramProduct.getProductExt().getSeckillprice());
    localOrderItem.setSalePrice(paramProduct.getSalePrice());
    localOrderItem.setCostPrice(paramProduct.getCostPrice());
    localOrderItem.setMemberPrice(paramProduct.getMemberPrice(paramShopMemberGroup));
    localOrderItem.setFinalPrice(localOrderItem.getMemberPrice());
    localOrderItem.setProductFash(paramProductFashion);
    return localOrderItem;
  }

  public OrderItem()
  {
  }

  public OrderItem(Long paramLong)
  {
    super(paramLong);
  }

  public OrderItem(Long paramLong, Website paramWebsite, Product paramProduct, Order paramOrder, Integer paramInteger)
  {
    super(paramLong, paramWebsite, paramProduct, paramOrder, paramInteger);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.OrderItem
 * JD-Core Version:    0.6.2
 */