package guda.shop.core.cache;

public abstract interface CoreCacheSvc
{
  public abstract void putWebsiteCount(int paramInt);

  public abstract Integer getWebsiteCount();

  public abstract void putWebsiteId(Long paramLong);

  public abstract boolean removeWebsiteId();

  public abstract Long getWebsiteId();
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.cache.CoreCacheSvc
 * JD-Core Version:    0.6.2
 */