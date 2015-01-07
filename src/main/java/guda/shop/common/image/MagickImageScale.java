package guda.shop.common.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

public class MagickImageScale
{
  public static void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2)
    throws IOException, MagickException
  {
    ImageInfo localImageInfo = new ImageInfo(paramFile1.getAbsolutePath());
    MagickImage localMagickImage1 = new MagickImage(localImageInfo);
    Dimension localDimension = localMagickImage1.getDimension();
    int i = (int)localDimension.getWidth();
    int j = (int)localDimension.getHeight();
    int k;
    int m;
    if (i / j > paramInt1 / paramInt2)
    {
      k = paramInt1;
      m = Math.round(paramInt1 * j / i);
    }
    else
    {
      k = Math.round(paramInt2 * i / j);
      m = paramInt2;
    }
    MagickImage localMagickImage2 = localMagickImage1.scaleImage(k, m);
    localMagickImage2.setFileName(paramFile2.getAbsolutePath());
    localMagickImage2.writeImage(localImageInfo);
    localMagickImage2.destroyImages();
  }

  public static void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws IOException, MagickException
  {
    ImageInfo localImageInfo = new ImageInfo(paramFile1.getAbsolutePath());
    MagickImage localMagickImage1 = new MagickImage(localImageInfo);
    Rectangle localRectangle = new Rectangle(paramInt3, paramInt4, paramInt5, paramInt6);
    MagickImage localMagickImage2 = localMagickImage1.cropImage(localRectangle);
    Dimension localDimension = localMagickImage2.getDimension();
    int i = (int)localDimension.getWidth();
    int j = (int)localDimension.getHeight();
    int k;
    int m;
    if (i / j > paramInt1 / paramInt2)
    {
      k = paramInt1;
      m = Math.round(paramInt1 * j / i);
    }
    else
    {
      k = Math.round(paramInt2 * i / j);
      m = paramInt2;
    }
    MagickImage localMagickImage3 = localMagickImage2.scaleImage(k, m);
    localMagickImage3.setFileName(paramFile2.getAbsolutePath());
    localMagickImage3.writeImage(localImageInfo);
    localMagickImage3.destroyImages();
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    long l = System.currentTimeMillis();
    resizeFix(new File("test/com/jeecms/common/util/1.bmp"), new File("test/com/jeecms/common/util/1-n-3.bmp"), 310, 310, 50, 50, 320, 320);
    l = System.currentTimeMillis() - l;
    System.out.println("resize new img in " + l + "ms");
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.image.MagickImageScale
 * JD-Core Version:    0.6.2
 */