package guda.shop.common.image;

import java.io.File;
import magick.Magick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageScaleImpl
  implements ImageScale
{
  private static final Logger _$2 = LoggerFactory.getLogger(ImageScaleImpl.class);
  private boolean _$1 = false;

  public void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2)
    throws Exception
  {
    if (this._$1)
      MagickImageScale.resizeFix(paramFile1, paramFile2, paramInt1, paramInt2);
    else
      AverageImageScale.resizeFix(paramFile1, paramFile2, paramInt1, paramInt2);
  }

  public void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws Exception
  {
    if (this._$1)
      MagickImageScale.resizeFix(paramFile1, paramFile2, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    else
      AverageImageScale.resizeFix(paramFile1, paramFile2, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }

  public void init()
  {
    try
    {
      System.setProperty("jmagick.systemclassloader", "no");
      new Magick();
      _$2.info("use jmagick");
      this._$1 = true;
    }
    catch (Throwable localThrowable)
    {
      _$2.warn("load magick fail, use java image scale. message:{}", localThrowable.getMessage());
      this._$1 = false;
    }
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.image.ImageScaleImpl
 * JD-Core Version:    0.6.2
 */