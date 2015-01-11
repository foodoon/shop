package guda.shop.common.page;

import java.io.Serializable;
import java.util.List;

public class Pagination extends SimplePage
        implements Serializable, Paginable {
    private List<?> _$1;

    public Pagination() {
    }

    public Pagination(int paramInt1, int paramInt2, int paramInt3) {
        super(paramInt1, paramInt2, paramInt3);
    }

    public Pagination(int paramInt1, int paramInt2, int paramInt3, List<?> paramList) {
        super(paramInt1, paramInt2, paramInt3);
        this._$1 = paramList;
    }

    public int getFirstResult() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public List<?> getList() {
        return this._$1;
    }

    public void setList(List paramList) {
        this._$1 = paramList;
    }
}

