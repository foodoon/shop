package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import guda.shop.core.entity.User;

public abstract interface UserDao {
    public abstract User getByUsername(String paramString);

    public abstract User getByEmail(String paramString);

    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract User findById(Long paramLong);

    public abstract User save(User paramUser);

    public abstract User updateByUpdater(Updater<User> paramUpdater);

    public abstract User deleteById(Long paramLong);
}

