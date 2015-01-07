/*    */ package guda.shop.action.admin;
/*    */ 
/*    */ import guda.shop.common.image.ImageScale;
/*    */ import guda.shop.common.web.springmvc.RealPathResolver;
/*    */ import java.io.File;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.context.ServletContextAware;
/*    */ 
/*    */ @Controller
/*    */ public class ImageCutAct
/*    */   implements ServletContextAware
/*    */ {
/* 25 */   private static final Logger log = LoggerFactory.getLogger(ImageCutAct.class);
/*    */   public static final String IMAGE_SELECT_RESULT = "/common/image_area_select";
/*    */   public static final String IMAGE_CUTED = "/common/image_cuted";
/*    */   public static final String ERROR = "error";
/*    */ 
/*    */   @Autowired
/*    */   private ImageScale imageScale;
/*    */   private ServletContext servletContext;
/*    */   private RealPathResolver realPathResolver;
/*    */ 
/*    */   @RequestMapping({"/common/v_image_area_select.do"})
/*    */   public String imageAreaSelect(String imgSrcPath, Integer zoomWidth, Integer zoomHeight, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*    */   {
/* 43 */     model.addAttribute("imgSrcPath", imgSrcPath);
/* 44 */     model.addAttribute("zoomWidth", zoomWidth);
/* 45 */     model.addAttribute("zoomHeight", zoomHeight);
/* 46 */     model.addAttribute("uploadNum", uploadNum);
/* 47 */     return "/common/image_area_select";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/common/o_image_cut.do"})
/*    */   public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft, Integer imgWidth, Integer imgHeight, Integer reMinWidth, Integer reMinHeight, Float imgScale, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*    */   {
/* 55 */     String ctx = request.getContextPath();
/* 56 */     imgSrcPath = imgSrcPath.substring(ctx.length());
/* 57 */     String real = this.realPathResolver.get(imgSrcPath);
/* 58 */     File srcFile = new File(real);
/* 59 */     model.addAttribute("uploadNum", uploadNum);
/*    */     try {
/* 61 */       if (imgWidth.intValue() > 0) this.imageScale
/* 62 */           .resizeFix(srcFile, srcFile, reMinWidth.intValue(), reMinHeight.intValue(), getLen(imgTop.intValue(), imgScale.floatValue()), 
/* 62 */           getLen(imgLeft.intValue(), 
/* 62 */           imgScale.floatValue()), getLen(imgWidth.intValue(), imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*    */       else
/* 64 */         this.imageScale.resizeFix(srcFile, srcFile, reMinWidth.intValue(), reMinHeight.intValue());
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 68 */       log.error("cut image error", e);
/* 69 */       model.addAttribute("error", e.getMessage());
/*    */     }
/* 71 */     return "/common/image_cuted";
/*    */   }
/*    */ 
/*    */   private int getLen(int len, float imgScale) {
/* 75 */     return Math.round(len / imgScale);
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext)
/*    */   {
/* 83 */     this.servletContext = servletContext;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*    */   {
/* 90 */     this.realPathResolver = realPathResolver;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.ImageCutAct
 * JD-Core Version:    0.6.2
 */