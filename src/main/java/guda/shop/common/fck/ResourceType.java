package guda.shop.common.fck;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceType {
    public static final ResourceType FILE = new ResourceType("File", PropertiesLoader.getFileResourceTypePath(), Utils.getSet(PropertiesLoader.getFileResourceTypeAllowedExtensions()), Utils.getSet(PropertiesLoader.getFileResourceTypeDeniedExtensions()));
    public static final ResourceType FLASH = new ResourceType("Flash", PropertiesLoader.getFlashResourceTypePath(), Utils.getSet(PropertiesLoader.getFlashResourceTypeAllowedExtensions()), Utils.getSet(PropertiesLoader.getFlashResourceTypeDeniedExtensions()));
    public static final ResourceType IMAGE = new ResourceType("Image", PropertiesLoader.getImageResourceTypePath(), Utils.getSet(PropertiesLoader.getImageResourceTypeAllowedExtensions()), Utils.getSet(PropertiesLoader.getImageResourceTypeDeniedExtensions()));
    public static final ResourceType MEDIA = new ResourceType("Media", PropertiesLoader.getMediaResourceTypePath(), Utils.getSet(PropertiesLoader.getMediaResourceTypeAllowedExtensions()), Utils.getSet(PropertiesLoader.getMediaResourceTypeDeniedExtensions()));
    private static Map<String, ResourceType> _$1 = new HashMap(4);
    private String _$5;
    private String _$4;
    private Set<String> _$3;
    private Set<String> _$2;

    private ResourceType(String paramString1, String paramString2, Set<String> paramSet1, Set<String> paramSet2) {
        this._$5 = paramString1;
        this._$4 = paramString2;
        if ((paramSet1.isEmpty()) && (paramSet2.isEmpty()))
            throw new IllegalArgumentException("Both sets are empty, one has always to be filled");
        if ((!paramSet1.isEmpty()) && (!paramSet2.isEmpty()))
            throw new IllegalArgumentException("Both sets contain extensions, only one can be filled at the same time");
        this._$3 = paramSet1;
        this._$2 = paramSet2;
    }

    public static ResourceType valueOf(String paramString) {
        if (Utils.isEmpty(paramString))
            throw new NullPointerException("Name is null or empty");
        ResourceType localResourceType = (ResourceType) _$1.get(paramString);
        if (localResourceType == null)
            throw new IllegalArgumentException("No resource type const " + paramString);
        return localResourceType;
    }

    public static boolean isValidType(String paramString) {
        return _$1.containsKey(paramString);
    }

    public static ResourceType getResourceType(String paramString) {
        try {
            return valueOf(paramString);
        } catch (Exception localException) {
        }
        return null;
    }

    public static ResourceType getDefaultResourceType(String paramString) {
        ResourceType localResourceType = getResourceType(paramString);
        return localResourceType == null ? FILE : localResourceType;
    }

    public String getName() {
        return this._$5;
    }

    public String getPath() {
        return this._$4;
    }

    public Set<String> getAllowedEextensions() {
        return Collections.unmodifiableSet(this._$3);
    }

    public Set<String> getDeniedExtensions() {
        return Collections.unmodifiableSet(this._$2);
    }

    public boolean isAllowedExtension(String paramString) {
        if (Utils.isEmpty(paramString))
            return false;
        String str = paramString.toLowerCase();
        if (this._$3.isEmpty())
            return !this._$2.contains(str);
        if (this._$2.isEmpty())
            return this._$3.contains(str);
        return false;
    }

    @Deprecated
    public boolean isNotAllowedExtension(String paramString) {
        return !isAllowedExtension(paramString);
    }

    public boolean isDeniedExtension(String paramString) {
        return !isAllowedExtension(paramString);
    }

    public boolean equals(Object paramObject) {
        if (this == paramObject)
            return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass()))
            return false;
        ResourceType localResourceType = (ResourceType) paramObject;
        return this._$5.equals(localResourceType.getName());
    }

    public int hashCode() {
        return this._$5.hashCode();
    }

    static {
        _$1.put(FILE.getName(), FILE);
        _$1.put(FLASH.getName(), FLASH);
        _$1.put(IMAGE.getName(), IMAGE);
        _$1.put(MEDIA.getName(), MEDIA);
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.fck.ResourceType
 * JD-Core Version:    0.6.2
 */