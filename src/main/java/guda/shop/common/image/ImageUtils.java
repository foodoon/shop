package guda.shop.common.image;

import java.util.Locale;

public abstract class ImageUtils {
    public static final String[] IMAGE_EXT = {"jpg", "jpeg", "gif", "png", "bmp"};

    public static boolean isValidImageExt(String paramString) {
        paramString = paramString.toLowerCase(Locale.ENGLISH);
        for (String str : IMAGE_EXT)
            if (str.equalsIgnoreCase(paramString))
                return true;
        return false;
    }

    public static boolean isImage(String paramString) {
        paramString = paramString.toLowerCase();
        for (String str : IMAGE_EXT)
            if (str.equals(paramString))
                return true;
        return false;
    }
}

