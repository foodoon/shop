package guda.shop.common.upload;

import guda.shop.common.util.Num62;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class UploadUtils {
    public static final DateFormat MONTH_FORMAT = new SimpleDateFormat("/yyyyMM/ddHHmmss");
    public static final DateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyyMM");
    protected static final Pattern ILLEGAL_CURRENT_FOLDER_PATTERN = Pattern.compile("^[^/]|[^/]$|/\\.{1,2}|\\\\|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}");

    public static String generateFilename(String paramString1, String paramString2) {
        return paramString1 + MONTH_FORMAT.format(new Date()) + RandomStringUtils.random(4, Num62.N36_CHARS) + "." + paramString2;
    }

    public static String generateMonthname() {
        return YEAR_MONTH_FORMAT.format(new Date());
    }

    public static String generateByFilename(String paramString1, String paramString2, String paramString3) {
        return paramString1 + paramString2 + "." + paramString3;
    }

    public static String sanitizeFileName(String paramString) {
        if (StringUtils.isBlank(paramString))
            return paramString;
        String str = forceSingleExtension(paramString);
        return str.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
    }

    public static String sanitizeFolderName(String paramString) {
        if (StringUtils.isBlank(paramString))
            return paramString;
        return paramString.replaceAll("\\.|\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
    }

    public static boolean isValidPath(String paramString) {
        if (StringUtils.isBlank(paramString))
            return false;
        return !ILLEGAL_CURRENT_FOLDER_PATTERN.matcher(paramString).find();
    }

    public static String forceSingleExtension(String paramString) {
        return paramString.replaceAll("\\.(?![^.]+$)", "_");
    }

    public static boolean isSingleExtension(String paramString) {
        return paramString.matches("[^\\.]+\\.[^\\.]+");
    }

    public static void checkDirAndCreate(File paramFile) {
        if (!paramFile.exists())
            paramFile.mkdirs();
    }

    public static File getUniqueFile(File paramFile) {
        if (!paramFile.exists())
            return paramFile;
        File localFile1 = new File(paramFile.getAbsolutePath());
        File localFile2 = localFile1.getParentFile();
        int i = 1;
        String str1 = FilenameUtils.getExtension(localFile1.getName());
        String str2 = FilenameUtils.getBaseName(localFile1.getName());
        do
            localFile1 = new File(localFile2, str2 + "(" + i++ + ")." + str1);
        while (localFile1.exists());
        return localFile1;
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.upload.UploadUtils
 * JD-Core Version:    0.6.2
 */