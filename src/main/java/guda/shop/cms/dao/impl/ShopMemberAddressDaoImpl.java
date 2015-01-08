package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopMemberAddressDao;
import guda.shop.cms.entity.ShopMemberAddress;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopMemberAddressDaoImpl extends HibernateBaseDao<ShopMemberAddress, Long>
        implements ShopMemberAddressDao {
    public List<ShopMemberAddress> getList(Long paramLong) {
        String str = "from ShopMemberAddress bean where bean.member.id=? order by bean.isDefault desc";
        return find(str, new Object[]{paramLong});
    }

    public List<ShopMemberAddress> findByMemberDefault(Long paramLong, Boolean paramBoolean) {
        Finder localFinder = Finder.create("from ShopMemberAddress bean where 1=1");
        if (paramLong != null) {
            localFinder.append(" and bean.member.id=:memberId");
            localFinder.setParam("memberId", paramLong);
        }
        if (paramBoolean != null) {
            localFinder.append(" and bean.isDefault=:isDefault ");
            localFinder.setParam("isDefault", paramBoolean);
        }
        localFinder.append(" order by bean.isDefault desc");
        return find(localFinder);
    }

    public ShopMemberAddress findById(Long paramLong) {
        ShopMemberAddress localShopMemberAddress = (ShopMemberAddress) get(paramLong);
        return localShopMemberAddress;
    }

    public ShopMemberAddress save(ShopMemberAddress paramShopMemberAddress) {
        getSession().save(paramShopMemberAddress);
        return paramShopMemberAddress;
    }

    public ShopMemberAddress deleteById(Long paramLong) {
        ShopMemberAddress localShopMemberAddress = (ShopMemberAddress) super.get(paramLong);
        if (localShopMemberAddress != null)
            getSession().delete(localShopMemberAddress);
        return localShopMemberAddress;
    }

    protected Class<ShopMemberAddress> getEntityClass() {
        return ShopMemberAddress.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopMemberAddressDaoImpl
 * JD-Core Version:    0.6.2
 */