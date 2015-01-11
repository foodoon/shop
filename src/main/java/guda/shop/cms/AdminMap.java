package guda.shop.cms;

import java.util.HashMap;
import java.util.Map;

public class AdminMap {
    public static Map<String, Integer> adminmap = new HashMap();

    public static Integer getAdminMapVal(String paramString) {
        return (Integer) adminmap.get(paramString);
    }

    public static void addAdminMapVal(String paramString) {
        if (adminmap.get(paramString) == null)
            adminmap.put(paramString, Integer.valueOf(1));
        else
            adminmap.put(paramString, Integer.valueOf(((Integer) adminmap.get(paramString)).intValue() + 1));
    }

    public static void unLoadAdmin(String paramString) {
        adminmap.put(paramString, Integer.valueOf(0));
        adminmap.remove(paramString);
    }
}
/* Location */
