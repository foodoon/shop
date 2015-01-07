 package guda.shop.cms.action.member;
/*     */*/ import gcms.web.SiteUtils;
/*     */ import guda.shop.le.FileNameUtils;
/*     */ import guda.shop.common.imUtils;
/*     */ import guda.shop.common.web.springmeResolver;
/*     */ import guda.shop.core.entity.Website;
/*    t guda.shop.core.web.WebErrors;
/*     */ importFile;
/*     */ import java.io.IOException;
/* port java.util.List;
/*     */ vax.servlet.ServletContext;
/*     */ vax.servlet.http.HttpServletReque  */ import org.apache.commons.io.FilenameUtils*/ import org.apache.commons.lang.StringUtils;
/*     */rg.slf4j.Logger;
/*     */ import org.slf4j.LoggerFact   */ import org.springframework.stereotype.Controller*/ import org.springframework.ui.Mo*     */ import org.springframework.web.bition.RequestMapping;
/*     */ import org.springframework.wenotation.RequestParam;
/*     */ import org.spring.web.context.ServletContextAware;
/*     */ import org.springframework.wert.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public claploadAct
/*     */   implements ServletContextAware
/*     */ {
/*  38vate static final Logger log = LoggerFactory.getLogger(ImageUpload);
/*     *te static final StringAGE = "iframe_upload";
/*     */   pubc final String ERROR = "error";
/*     */  ServletContext servletContext;
/*     */
/*     */   @RequestMapping({"/common/o_upload_image.jspx"})
/* public String execute(String fileName, Integer uploadNum, Integer zoomteger zoomHeight, @RequestParam(value="uploadFile", reque) MultipartFile file, HttpServletRequest request, model)
/*
/*  56 */     WebErrors errors = validate(fileName, file, re*  57 */     Website web = SiteUtils.getWeb(request);
/*  58 */     if (errors.hasErrors()) {
/*  59 */       model.addAttribute("error", errors.getErrors().get(0));
/*  60 */       return web.getTplSys("member", 
/*  61 */MessageResolver.getMessage(request,
/*  61 */         "iframe_upload", new Object[0]));
/*     */     }
/*  63 */     String real = this.servletContext.getRealPath(web.getUploadRel());
/*  64 */     String dateDir = FileNameUtils.genPathName();
/*     */ 
/*  66 */     File root = new File(real, dateDir);
/*  67 */     if (!root.exists()) {
/*  68 */       root.mkdirs();
/*     */     }
/*     */ 
/*   if (StringUtils.isBlank(fileName)) {
/*  72 */       String ext = FilenameUtils.getExtension(file.getOriginalFilename());
/*  73 */       fileName = FileNgenFileName(ext);
/*     */     } else {
/*  75 */       fileName = FilenameUtils.getName(fileName);
/*     */     }
/*     */ 
/    File tempFilile(root, fileName);
/*     */ 
/*  80 */     String ctx = request.getContextPath();
/*  81 */     String relPath = ctx + "/" + "u" + "/" + dateDir + "/" + fileName;
/*  82 */     model.addAttribute("zoomWimWidth);
/*  83 */     model.addAttribute("zoomHeight", zoomHeight);
/*     */      85 */       filrTo(tempFile);
/*  86 */       model.addAttribute("uploadPath", rel     */
/*  88 */       model.addAttribute("uploadNum", uploadNum);
/*     */     } catch (IllegalStateException e) {
/*  90 */       model.addAttribute("error", e.getMessage());
/*  91 */       log.error("upload file error!", e);
/*     */     } catch (IOExcept/*  93 */       model.addAttribute("error", e.getMessage());
/*  94 */       log.error("upload file error!", e);
/*     */  96 */     model.addAttribute("zoomWidth", zoomWidth);
/*  97 */     mttribute("zoomHeight", zoomHeight);
/*  98 */     return web.getTplSys("member",
/*  99 */       MessageResolver.getMessage(request, 
/*  99 */       "iframe_uploObject[0]));
/*     */   }
/*     */ 
/*     */   private WebErrors validate(String fileName, MultipartFile file, HttpServletRequest request)
/*     */   */     WebErrors errors = WebErrors.create(request);
/* 106 */     if (file == null) {
/* 107 */       errors.addErrorCode("imageupload.error.noFileToUpload");
/* 108 */       return errors;
/*     */     }
/* 110 */     if (StringUtils.isBlank(fileName)) {
/* 111 */       fileNameetOriginalFile*     */   3 */     String ext = FilenameUtils.getExtension(fileName);
/* 114 */     if (!ImageUtils.isImage(ext)) */       errors.addErrorCode("imageupload.error.notSupportExt", new Object[] { ext });
/* 116 */       return errors;
/*     */     }
/* 118 */     return errors;
/*     */   }
/*     */ 
/*     */   public void tContext(ServletContext servletContext)
/*     */   {
/* 124 */     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ImageUploadAct
 * JD-Core Version:    0.6.2
 */