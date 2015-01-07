package guda.shop.common.page;

public abstract interface Paginable
{
  public abstract int getTotalCount();

  public abstract int getTotalPage();

  public abstract int getPageSize();

  public abstract int getPageNo();

  public abstract boolean isFirstPage();

  public abstract boolean isLastPage();

  public abstract int getNextPage();

  public abstract int getPrePage();
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.page.Paginable
 * JD-Core Version:    0.6.2
 */