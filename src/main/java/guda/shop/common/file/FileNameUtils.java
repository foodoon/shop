package guda.shop.common.file;

import guda.shop.common.util.Num62;
import org.apache.commons.lang.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameUtils {
    public static final DateFormat pathDf = new SimpleDateFormat("yyyyMM");
    public static final DateFormat nameDf = new SimpleDateFormat("ddHHmmss");

    public static String genPathName() {
        return pathDf.format(new Date());
    }

    public static String genFileName() {
        return nameDf.format(new Date()) + RandomStringUtils.random(4, Num62.N36_CHARS);
    }

    public static String genFileName(String paramString) {
        return genFileName() + "." + paramString;
    }

    public static void main(String[] paramArrayOfString) {
        System.out.println(genPathName());
        System.out.println(genFileName());
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.file.FileNameUtils
 * JD-Core Version:    0.6.2
 */