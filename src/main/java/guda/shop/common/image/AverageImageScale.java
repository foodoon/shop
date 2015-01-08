package guda.shop.common.image;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AverageImageScale {
    public static void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2)
            throws IOException {
        BufferedImage localBufferedImage1 = ImageIO.read(paramFile1);
        int i = localBufferedImage1.getWidth();
        int j = localBufferedImage1.getHeight();
        if ((i <= paramInt1) && (j <= paramInt2)) {
            FileUtils.copyFile(paramFile1, paramFile2);
            return;
        }
        int k;
        int m;
        if (i / j > paramInt1 / paramInt2) {
            k = paramInt1;
            m = Math.round(paramInt1 * j / i);
        } else {
            k = Math.round(paramInt2 * i / j);
            m = paramInt2;
        }
        BufferedImage localBufferedImage2 = _$1(localBufferedImage1, i, j, k, m);
        writeFile(localBufferedImage2, paramFile2);
    }

    public static void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
            throws IOException {
        BufferedImage localBufferedImage1 = ImageIO.read(paramFile1);
        localBufferedImage1 = localBufferedImage1.getSubimage(paramInt3, paramInt4, paramInt5, paramInt6);
        int i = localBufferedImage1.getWidth();
        int j = localBufferedImage1.getHeight();
        if ((i <= paramInt1) && (j <= paramInt2)) {
            writeFile(localBufferedImage1, paramFile2);
            return;
        }
        int k;
        int m;
        if (i / j > paramInt1 / paramInt2) {
            k = paramInt1;
            m = Math.round(paramInt1 * j / i);
        } else {
            k = Math.round(paramInt2 * i / j);
            m = paramInt2;
        }
        BufferedImage localBufferedImage2 = _$1(localBufferedImage1, i, j, k, m);
        writeFile(localBufferedImage2, paramFile2);
    }

    public static void writeFile(BufferedImage paramBufferedImage, File paramFile)
            throws IOException {
        File localFile = paramFile.getParentFile();
        if (!localFile.exists())
            localFile.mkdirs();
        ImageIO.write(paramBufferedImage, "jpeg", paramFile);
    }

    private static BufferedImage _$1(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int[] arrayOfInt = paramBufferedImage.getRGB(0, 0, paramInt1, paramInt2, null, 0, paramInt1);
        BufferedImage localBufferedImage = new BufferedImage(paramInt3, paramInt4, 1);
        float f1 = paramInt1 / paramInt3;
        int i = (int) (f1 + 0.5D);
        float f2 = paramInt2 / paramInt4;
        int j = (int) (f2 + 0.5D);
        int k = i * j;
        for (int i5 = 0; i5 < paramInt4; i5++) {
            int i1 = (int) (i5 * f2);
            int i2 = i1 + j;
            for (int i4 = 0; i4 < paramInt3; i4++) {
                int m = (int) (i4 * f1);
                int n = m + i;
                long l3;
                long l2;
                long l1 = l2 = l3 = 0L;
                for (int i6 = m; i6 < n; i6++)
                    for (int i7 = i1; i7 < i2; i7++) {
                        int i3 = arrayOfInt[(paramInt1 * i7 + i6)];
                        l1 += _$3(i3);
                        l2 += _$2(i3);
                        l3 += _$1(i3);
                    }
                localBufferedImage.setRGB(i4, i5, _$1((int) (l1 / k), (int) (l2 / k), (int) (l3 / k)));
            }
        }
        return localBufferedImage;
    }

    private static int _$3(int paramInt) {
        return (paramInt & 0xFF0000) >> 16;
    }

    private static int _$2(int paramInt) {
        return (paramInt & 0xFF00) >> 8;
    }

    private static int _$1(int paramInt) {
        return paramInt & 0xFF;
    }

    private static int _$1(int paramInt1, int paramInt2, int paramInt3) {
        return (paramInt1 << 16) + (paramInt2 << 8) + paramInt3;
    }

    public static void main(String[] paramArrayOfString)
            throws Exception {
        long l = System.currentTimeMillis();
        resizeFix(new File("test/com/jeecms/common/util/1.bmp"), new File("test/com/jeecms/common/util/1-n-2.bmp"), 310, 310, 50, 50, 320, 320);
        l = System.currentTimeMillis() - l;
        System.out.println("resize2 img in " + l + "ms");
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.image.AverageImageScale
 * JD-Core Version:    0.6.2
 */