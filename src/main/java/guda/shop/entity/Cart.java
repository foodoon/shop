package guda.shop.entity;

import guda.shop.cms.entity.base.BaseCart;
import com.jspgou.core.entity.Website;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Cart extends BaseCart
{
  private static final long serialVersionUID = 1L;

  public Double getTotalPrice()
  {
    return getSubtotal();
  }

  public Double getProductTotalPrice()
  {
    return getSubtotal();
  }

  public int getWeightForFreight()
  {
    int i = 0;
    Iterator localIterator = getItems().iterator();
    while (localIterator.hasNext())
    {
      CartItem localCartItem = (CartItem)localIterator.next();
      i += localCartItem.getWeightForFreight();
    }
    return i;
  }

  public int getCountForFreight()
  {
    int i = 0;
    Iterator localIterator = getItems().iterator();
    while (localIterator.hasNext())
    {
      CartItem localCartItem = (CartItem)localIterator.next();
      i += localCartItem.getCountForFreigt();
    }
    return i;
  }

  public Double getSubtotal()
  {
    Set localSet = getItems();
    Double localDouble = Double.valueOf(0.0D);
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartItem localCartItem = (CartItem)localIterator.next();
        localDouble = Double.valueOf(localDouble.doubleValue() + localCartItem.getSubtotal().doubleValue());
      }
    }
    return localDouble;
  }

  public Double getTotalSubtotal()
  {
    Set localSet = getItems();
    Double localDouble = Double.valueOf(0.0D);
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartItem localCartItem = (CartItem)localIterator.next();
        if ((localCartItem.getProduct().getProductExt().getIslimitTime() != null) && (localCartItem.getProduct().getProductExt().getIslimitTime().booleanValue()))
          localDouble = Double.valueOf(localDouble.doubleValue() + localCartItem.getLimitSubtotal().doubleValue());
        else
          localDouble = Double.valueOf(localDouble.doubleValue() + localCartItem.getSubtotal().doubleValue());
      }
    }
    return localDouble;
  }

  public int calTotalItem()
  {
    Set localSet = getItems();
    int i = 0;
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartItem localCartItem = (CartItem)localIterator.next();
        i += localCartItem.getCount().intValue();
      }
    }
    setTotalItems(Integer.valueOf(i));
    return i;
  }

  public int getTotalScore()
  {
    Set localSet = getItems();
    int i = 0;
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartItem localCartItem = (CartItem)localIterator.next();
        i += localCartItem.getScore().intValue();
      }
    }
    return i;
  }

  public int calTotalGift()
  {
    Set localSet = getGifts();
    int i = 0;
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartGift localCartGift = (CartGift)localIterator.next();
        i += localCartGift.getCount().intValue();
      }
    }
    setTotalGifts(Integer.valueOf(i));
    return i;
  }

  public void addItem(Product paramProduct, ProductFashion paramProductFashion, PopularityGroup paramPopularityGroup, int paramInt, boolean paramBoolean)
  {
    CartItem localCartItem = extensionItem(paramProduct, paramProductFashion, paramPopularityGroup);
    paramInt = extensionCount(paramProduct, paramProductFashion, paramPopularityGroup, paramInt, paramBoolean);
    if (paramInt > 0)
    {
      if (localCartItem == null)
        localCartItem = new CartItem();
      localCartItem.setCount(Integer.valueOf(paramInt));
      localCartItem.setScore(Integer.valueOf(paramInt * paramProduct.getScore().intValue()));
      if (extensionItem(paramProduct, paramProductFashion, paramPopularityGroup) == null)
      {
        if (paramProductFashion != null)
          localCartItem.setProductFash(paramProductFashion);
        if (paramPopularityGroup != null)
          localCartItem.setPopularityGroup(paramPopularityGroup);
        localCartItem.setProduct(paramProduct);
        addToItems(localCartItem);
      }
    }
    else if (localCartItem != null)
    {
      getItems().remove(localCartItem);
    }
    calTotalItem();
  }

  public CartItem extensionItem(Product paramProduct, ProductFashion paramProductFashion, PopularityGroup paramPopularityGroup)
  {
    CartItem localCartItem = null;
    if (paramProductFashion != null)
      localCartItem = findItemfashion(paramProductFashion.getId(), paramPopularityGroup);
    else
      localCartItem = findItem(paramProduct.getId(), paramPopularityGroup);
    return localCartItem;
  }

  public int extensionCount(Product paramProduct, ProductFashion paramProductFashion, PopularityGroup paramPopularityGroup, int paramInt, boolean paramBoolean)
  {
    CartItem localCartItem = extensionItem(paramProduct, paramProductFashion, paramPopularityGroup);
    if ((localCartItem != null) && (paramBoolean))
      paramInt += localCartItem.getCount().intValue();
    if (paramProductFashion != null)
    {
      if (paramInt > paramProductFashion.getStockCount().intValue())
        paramInt = paramProductFashion.getStockCount().intValue();
    }
    else if (paramInt > paramProduct.getStockCount().intValue())
      paramInt = paramProduct.getStockCount().intValue();
    return paramInt;
  }

  public void addGift(Gift paramGift, int paramInt, boolean paramBoolean)
  {
    CartGift localCartGift = findGift(paramGift.getId());
    if (localCartGift != null)
    {
      if (paramBoolean)
        paramInt += localCartGift.getCount().intValue();
      if (paramInt <= 0)
      {
        getGifts().remove(localCartGift);
      }
      else
      {
        if (paramInt > paramGift.getGiftStock().intValue())
          paramInt = paramGift.getGiftStock().intValue();
        localCartGift.setCount(Integer.valueOf(paramInt));
      }
    }
    else if (paramInt > 0)
    {
      if (paramInt > paramGift.getGiftStock().intValue())
        paramInt = paramGift.getGiftStock().intValue();
      localCartGift = new CartGift();
      localCartGift.setGift(paramGift);
      localCartGift.setCount(Integer.valueOf(paramInt));
      addToGift(localCartGift);
    }
    calTotalGift();
  }

  public CartItem findItem(Long paramLong, PopularityGroup paramPopularityGroup)
  {
    Set localSet = getItems();
    if ((localSet != null) && (localSet.size() > 0))
    {
      Iterator localIterator;
      CartItem localCartItem;
      if (paramPopularityGroup != null)
      {
        localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          localCartItem = (CartItem)localIterator.next();
          if ((localCartItem.getPopularityGroup() != null) && (localCartItem.getProduct().getId().equals(paramLong)) && (localCartItem.getPopularityGroup().getId().equals(paramPopularityGroup.getId())))
            return localCartItem;
        }
      }
      else
      {
        localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          localCartItem = (CartItem)localIterator.next();
          if ((localCartItem.getProduct().getId().equals(paramLong)) && (localCartItem.getPopularityGroup() == null))
            return localCartItem;
        }
      }
    }
    return null;
  }

  public CartItem findItemfashion(Long paramLong, PopularityGroup paramPopularityGroup)
  {
    Set localSet = getItems();
    if ((localSet != null) && (localSet.size() > 0))
    {
      Iterator localIterator;
      CartItem localCartItem;
      if (paramPopularityGroup != null)
      {
        localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          localCartItem = (CartItem)localIterator.next();
          if ((localCartItem.getProductFash() != null) && (localCartItem.getPopularityGroup() != null) && (localCartItem.getProductFash().getId().equals(paramLong)) && (localCartItem.getPopularityGroup().getId().equals(paramPopularityGroup.getId())))
            return localCartItem;
        }
      }
      else
      {
        localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          localCartItem = (CartItem)localIterator.next();
          if ((localCartItem.getProductFash() != null) && (localCartItem.getProductFash().getId().equals(paramLong)) && (localCartItem.getPopularityGroup() == null))
            return localCartItem;
        }
      }
    }
    return null;
  }

  public CartGift findGift(Long paramLong)
  {
    Set localSet = getGifts();
    if ((localSet != null) && (localSet.size() > 0))
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartGift localCartGift = (CartGift)localIterator.next();
        if (localCartGift.getGift().getId().equals(paramLong))
          return localCartGift;
      }
    }
    return null;
  }

  public void addToGift(CartGift paramCartGift)
  {
    Object localObject = getGifts();
    if (localObject == null)
    {
      localObject = new LinkedHashSet();
      setGifts((Set)localObject);
    }
    paramCartGift.setWebsite(getWebsite());
    paramCartGift.setCart(this);
    ((Set)localObject).add(paramCartGift);
  }

  public void addToItems(CartItem paramCartItem)
  {
    Object localObject = getItems();
    if (localObject == null)
    {
      localObject = new LinkedHashSet();
      setItems((Set)localObject);
    }
    paramCartItem.setWebsite(getWebsite());
    paramCartItem.setCart(this);
    ((Set)localObject).add(paramCartItem);
  }

  public List<PopularityGroup> getPopularits()
  {
    ArrayList localArrayList = new ArrayList();
    Set localSet = getItems();
    if (localSet != null)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        CartItem localCartItem = (CartItem)localIterator.next();
        if ((localCartItem.getPopularityGroup() != null) && (!localArrayList.contains(localCartItem.getPopularityGroup())))
          localArrayList.add(localCartItem.getPopularityGroup());
      }
    }
    return localArrayList;
  }

  public void init()
  {
    if (getTotalItems() == null)
      setTotalItems(Integer.valueOf(0));
  }

  public Cart()
  {
  }

  public Cart(Long paramLong)
  {
    super(paramLong);
  }

  public Cart(Long paramLong, Website paramWebsite, Integer paramInteger)
  {
    super(paramLong, paramWebsite, paramInteger);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Cart
 * JD-Core Version:    0.6.2
 */