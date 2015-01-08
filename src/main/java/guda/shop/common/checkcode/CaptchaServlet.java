package guda.shop.common.checkcode;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;
import guda.shop.common.web.session.SessionProvider;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptchaServlet extends HttpServlet {
    public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
    private MultiTypeCaptchaService _$2;
    private SessionProvider _$1;

    public void init(ServletConfig paramServletConfig)
            throws ServletException {
        super.init(paramServletConfig);
        WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(paramServletConfig.getServletContext());
        this._$2 = ((MultiTypeCaptchaService) BeanFactoryUtils.beanOfTypeIncludingAncestors(localWebApplicationContext, MultiTypeCaptchaService.class));
        this._$1 = ((SessionProvider) BeanFactoryUtils.beanOfTypeIncludingAncestors(localWebApplicationContext, SessionProvider.class));
    }

    protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
            throws ServletException, IOException {
        byte[] arrayOfByte = null;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        try {
            String str = this._$1.getSessionId(paramHttpServletRequest, paramHttpServletResponse);
            BufferedImage localBufferedImage = this._$2.getImageChallengeForID(str, paramHttpServletRequest.getLocale());
            ImageIO.write(localBufferedImage, "jpeg", localByteArrayOutputStream);
        } catch (IllegalArgumentException localIllegalArgumentException) {
            paramHttpServletResponse.sendError(404);
            return;
        } catch (CaptchaServiceException localCaptchaServiceException) {
            paramHttpServletResponse.sendError(500);
            return;
        }
        arrayOfByte = localByteArrayOutputStream.toByteArray();
        paramHttpServletResponse.setHeader("Cache-Control", "no-store");
        paramHttpServletResponse.setHeader("Pragma", "no-cache");
        paramHttpServletResponse.setDateHeader("Expires", 0L);
        paramHttpServletResponse.setContentType("image/jpeg");
        ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();
        localServletOutputStream.write(arrayOfByte);
        localServletOutputStream.flush();
        localServletOutputStream.close();
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.checkcode.CaptchaServlet
 * JD-Core Version:    0.6.2
 */