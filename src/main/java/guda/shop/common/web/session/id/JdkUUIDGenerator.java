package guda.shop.common.web.session.id;

import java.util.UUID;
import org.apache.commons.lang.StringUtils;

public class JdkUUIDGenerator
  implements SessionIdGenerator
{
  public String get()
  {
    return StringUtils.remove(UUID.randomUUID().toString(), '-');
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.session.id.JdkUUIDGenerator
 * JD-Core Version:    0.6.2
 */