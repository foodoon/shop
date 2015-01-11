package guda.shop.common.fck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger _$4 = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String _$3 = "default.properties";
    private static final String _$2 = "/fckeditor.properties";
    private static Properties _$1 = new Properties();

    public static String getProperty(String paramString) {
        return _$1.getProperty(paramString);
    }

    public static void setProperty(String paramString1, String paramString2) {
        _$1.setProperty(paramString1, paramString2);
    }

    public static String getFileResourceTypePath() {
        return _$1.getProperty("connector.resourceType.file.path");
    }

    public static String getFlashResourceTypePath() {
        return _$1.getProperty("connector.resourceType.flash.path");
    }

    public static String getImageResourceTypePath() {
        return _$1.getProperty("connector.resourceType.image.path");
    }

    public static String getMediaResourceTypePath() {
        return _$1.getProperty("connector.resourceType.media.path");
    }

    public static String getFileResourceTypeAllowedExtensions() {
        return _$1.getProperty("connector.resourceType.file.extensions.allowed");
    }

    public static String getFileResourceTypeDeniedExtensions() {
        return _$1.getProperty("connector.resourceType.file.extensions.denied");
    }

    public static String getFlashResourceTypeAllowedExtensions() {
        return _$1.getProperty("connector.resourceType.flash.extensions.allowed");
    }

    public static String getFlashResourceTypeDeniedExtensions() {
        return _$1.getProperty("connector.resourceType.flash.extensions.denied");
    }

    public static String getImageResourceTypeAllowedExtensions() {
        return _$1.getProperty("connector.resourceType.image.extensions.allowed");
    }

    public static String getImageResourceTypeDeniedExtensions() {
        return _$1.getProperty("connector.resourceType.image.extensions.denied");
    }

    public static String getMediaResourceTypeAllowedExtensions() {
        return _$1.getProperty("connector.resourceType.media.extensions.allowed");
    }

    public static String getMediaResourceTypeDeniedExtensions() {
        return _$1.getProperty("connector.resourceType.media.extensions.denied");
    }

    public static String getUserFilesPath() {
        return _$1.getProperty("connector.userFilesPath");
    }

    public static String getUserFilesAbsolutePath() {
        return _$1.getProperty("connector.userFilesAbsolutePath");
    }

    static {
        Object localObject1 = PropertiesLoader.class.getResourceAsStream("default.properties");
        if (localObject1 == null) {
            _$4.error("{} not found", "default.properties");
            throw new RuntimeException("default.properties not found");
        }
        if (!(localObject1 instanceof BufferedInputStream))
            localObject1 = new BufferedInputStream((InputStream) localObject1);
        try {
            _$1.load((InputStream) localObject1);
            ((InputStream) localObject1).close();
            _$4.debug("{} loaded", "default.properties");
        } catch (Exception localException1) {
            _$4.error("Error while processing {}", "default.properties");
            throw new RuntimeException("Error while processing default.properties", localException1);
        }
        Object localObject2 = PropertiesLoader.class.getResourceAsStream("/fckeditor.properties");
        if (localObject2 == null) {
            _$4.info("{} not found", "/fckeditor.properties");
        } else {
            if (!(localObject2 instanceof BufferedInputStream))
                localObject2 = new BufferedInputStream((InputStream) localObject2);
            try {
                _$1.load((InputStream) localObject2);
                ((InputStream) localObject2).close();
                _$4.debug("{} loaded", "/fckeditor.properties");
            } catch (Exception localException2) {
                _$4.error("Error while processing {}", "/fckeditor.properties");
                throw new RuntimeException("Error while processing /fckeditor.properties", localException2);
            }
        }
    }
}

