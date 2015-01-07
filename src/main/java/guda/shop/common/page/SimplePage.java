package guda.shop.common.page;

public class SimplePage
  implements Paginable
{
  private static final long serialVersionUID = 1L;
  public static final int DEF_COUNT = 20;
  protected int totalCount = 0;
  protected int pageSize = 20;
  protected int pageNo = 1;

  public static int cpn(Integer paramInteger)
  {
    return (paramInteger == null) || (paramInteger.intValue() < 1) ? 1 : paramInteger.intValue();
  }

  public SimplePage()
  {
  }

  public SimplePage(int paramInt1, int paramInt2, int paramInt3)
  {
    setTotalCount(paramInt3);
    setPageSize(paramInt2);
    setPageNo(paramInt1);
    adjustPageNo();
  }

  public void adjustPageNo()
  {
    if (this.pageNo == 1)
      return;
    int i = getTotalPage();
    if (this.pageNo > i)
      this.pageNo = i;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public int getTotalPage()
  {
    int i = this.totalCount / this.pageSize;
    if ((i == 0) || (this.totalCount % this.pageSize != 0))
      i++;
    return i;
  }

  public boolean isFirstPage()
  {
    return this.pageNo <= 1;
  }

  public boolean isLastPage()
  {
    return this.pageNo >= getTotalPage();
  }

  public int getNextPage()
  {
    if (isLastPage())
      return this.pageNo;
    return this.pageNo + 1;
  }

  public int getPrePage()
  {
    if (isFirstPage())
      return this.pageNo;
    return this.pageNo - 1;
  }

  public void setTotalCount(int paramInt)
  {
    if (paramInt < 0)
      this.totalCount = 0;
    else
      this.totalCount = paramInt;
  }

  public void setPageSize(int paramInt)
  {
    if (paramInt < 1)
      this.pageSize = 20;
    else
      this.pageSize = paramInt;
  }

  public void setPageNo(int paramInt)
  {
    if (paramInt < 1)
      this.pageNo = 1;
    else
      this.pageNo = paramInt;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.page.SimplePage
 * JD-Core Version:    0.6.2
 */