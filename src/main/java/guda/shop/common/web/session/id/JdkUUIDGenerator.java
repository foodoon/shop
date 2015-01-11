package guda.shop.common.web.session.id;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;
public class JdkUUIDGenerator        implements SessionIdGenerator {
    public String get() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}

