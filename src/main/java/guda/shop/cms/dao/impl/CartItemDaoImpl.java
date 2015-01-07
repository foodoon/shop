package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CartItemDao;
import guda.shop.cms.entity.CartItem;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl extends HibernateBaseDao<CartItem, Long>
  implements CartItemDao
{
  public CartItem findById(Long paramLong)
  {
    CartItem localCartItem = (CartItem)get(paramLong);
    return localCartItem;
  }

  public CartItem deleteById(Long paramLong)
  {
    CartItem localCartItem = (CartItem)super.get(paramLong);
    if (localCartItem != null)
      getSession().delete(localCartItem);
    return localCartItem;
  }

  public List<CartItem> getlist(Long paramLong1, Long paramLong2)
  {
    Finder localFinder = Finder.create("select bean from CartItem bean");
    localFinder.append(" where bean.cart.id=:cartId and bean.popularityGroup.id=:popularityGroupId");
    localFinder.setParam("cartId", paramLong1).setParam("popularityGroupId", paramLong2);
    localFinder.append(" order by bean.id desc");
    return find(localFinder);
  }

  public int deleteByProductId(Long paramLong)
  {
    String str = " delete CartItem bean where bean.product.id=:productId";
    return getSession().createQuery(str).setParameter("productId", paramLong).executeUpdate();
  }

  protected Class<CartItem> getEntityClass()
  {
    return CartItem.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.CartItemDaoImpl
 * JD-Core Version:    0.6.2
 */