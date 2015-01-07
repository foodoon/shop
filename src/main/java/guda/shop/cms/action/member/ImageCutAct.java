/*    */ package guda.shop.cms.action.member;
/*    */ 
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.common.image.ImageScale;
/*    */ import guda.shop.core.entity.Website;
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
/* 28 */   private static final Logger log = LoggerFactory.getLogger(ImageCutAct.class);
/*    */   public static final String IMAGE_SELECT_RESULT = "/common/image_area_select";
/*    */   public static final String IMAGE_CUTED = "/common/image_cuted";
/*    */   public static final String ERROR = "error";
/*    */ 
/*    */   @Autowired
/*    */   private ImageScale imageScale;
/*    */   private ServletContext servletContext;
/*    */ 
/*    */   @RequestMapping({"/common/v_image_area_select.jspx"})
/*    */   public String imageAreaSelect(String imgSrcRoot, String imgSrcPath, Integer zoomWidth, Integer zoomHeight, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*    */   {
/* 46 */     model.addAttribute("imgSrcRoot", imgSrcRoot);
/* 47 */     model.addAttribute("imgSrcPath", imgSrcPath);
/* 48 */     model.addAttribute("zoomWidth", zoomWidth);
/* 49 */     model.addAttribute("zoomHeight", zoomHeight);
/* 50 */     model.addAttribute("uploadNum", uploadNum);
/* 51 */     return "/common/image_area_select";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/common/o_image_cut.jspx"})
/*    */   public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft, Integer imgWidth, Integer imgHeight, Integer reMinWidth, Integer reMinHeight, Float imgScale, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*    */   {
/* 59 */     Website web = SiteUtils.getWeb(request);
/* 60 */     String real = this.servletContext.getRealPath(web.getUploadRel(imgSrcPath));
/* 61 */     File srcFile = new File(real);
/*    */     try {
/* 63 */       if (imgWidth.intValue() > 0)
/* 64 */         this.imageScale.resizeFix(srcFile, srcFile, reMinWidth.intValue(), reMinHeight.intValue(), 
/* 65 */           getLen(imgTop.intValue(), imgScale.floatValue()), 
/* 66 */           getLen(imgLeft.intValue(), 
/* 66 */           imgScale.floatValue()), getLen(imgWidth.intValue(), imgScale.floatValue()), 
/* 67 */           getLen(imgHeight.intValue(), imgScale.floatValue()));
/*    */       else {
/* 69 */         this.imageScale.resizeFix(srcFile, srcFile, reMinWidth.intValue(), reMinHeight.intValue());
/*    */       }
/* 71 */       model.addAttribute("uploadNum", uploadNum);
/*    */     } catch (Exception e) {
/* 73 */       log.error("cut image error", e);
/* 74 */       model.addAttribute("error", e.getMessage());
/*    */     }
/* 76 */     return "/common/image_cuted";
/*    */   }
/*    */ 
/*    */   private int getLen(int len, float imgScale) {
/* 80 */     return Math.round(len / imgScale);
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext)
/*    */   {
/* 88 */     this.servletContext = servletContext;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ImageCutAct
 * JD-Core Version:    0.6.2
 */