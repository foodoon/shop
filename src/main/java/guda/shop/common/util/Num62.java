package guda.shop.common.util;

public class Num62 {
    public static final char[] N62_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] N36_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final int LONG_N36_LEN = 13;
    public static final int LONG_N62_LEN = 11;

    private static StringBuilder _$1(long paramLong, char[] paramArrayOfChar) {
        int i = paramArrayOfChar.length;
        StringBuilder localStringBuilder = new StringBuilder();
        while (paramLong > 0L) {
            int j = (int) (paramLong % i);
            localStringBuilder.append(paramArrayOfChar[j]);
            paramLong /= i;
        }
        return localStringBuilder;
    }

    public static String longToN62(long paramLong) {
        return _$1(paramLong, N62_CHARS).reverse().toString();
    }

    public static String longToN36(long paramLong) {
        return _$1(paramLong, N36_CHARS).reverse().toString();
    }

    public static String longToN62(long paramLong, int paramInt) {
        StringBuilder localStringBuilder = _$1(paramLong, N62_CHARS);
        for (int i = localStringBuilder.length(); i < paramInt; i++)
            localStringBuilder.append('0');
        return localStringBuilder.reverse().toString();
    }

    public static String longToN36(long paramLong, int paramInt) {
        StringBuilder localStringBuilder = _$1(paramLong, N36_CHARS);
        for (int i = localStringBuilder.length(); i < paramInt; i++)
            localStringBuilder.append('0');
        return localStringBuilder.reverse().toString();
    }

    public static long n62ToLong(String paramString) {
        return _$1(paramString, N62_CHARS);
    }

    public static long n36ToLong(String paramString) {
        return _$1(paramString, N36_CHARS);
    }

    private static long _$1(String paramString, char[] paramArrayOfChar) {
        char[] arrayOfChar = paramString.toCharArray();
        long l1 = 0L;
        long l2 = 1L;
        int i = arrayOfChar.length - 1;
        while (i >= 0) {
            int j = _$1(arrayOfChar[i], paramArrayOfChar);
            l1 += j * l2;
            i--;
            l2 *= paramArrayOfChar.length;
        }
        return l1;
    }

    private static int _$1(char paramChar, char[] paramArrayOfChar) {
        for (int i = 0; i < paramArrayOfChar.length; i++)
            if (paramChar == paramArrayOfChar[i])
                return i;
        throw new RuntimeException("N62(N36)非法字符：" + paramChar);
    }

    public static void main(String[] paramArrayOfString) {
        System.out.println(longToN62(9223372036854775807L));
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.util.Num62
 * JD-Core Version:    0.6.2
 */