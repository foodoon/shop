/*     */ package guda.shop.action.admin;
/*     */ 
/*     */ import guda.shop.common.fck.Command;
/*     */ import guda.shop.common.fck.ResourceType;
/*     */ import guda.shop.common.fck.UploadResponse;
/*     */ import guda.shop.common.fck.Utils;
/*     */ import guda.shop.common.upload.FileRepository;
/*     */ import guda.shop.common.upload.UploadUtils;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ 
/*     */ @Controller
/*     */ public class FckAct
/*     */ {
/*     */   public static final String UPLOAD_PATH = "/u/jspgou/";
/*  41 */   private static final Logger log = LoggerFactory.getLogger(FckAct.class);
/*     */   private FileRepository fileRepository;
/*     */ 
/*     */   @RequestMapping(value={"/fck/upload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void upload(@RequestParam(value="Command", required=false) String commandStr, @RequestParam(value="Type", required=false) String typeStr, @RequestParam(value="CurrentFolder", required=false) String currentFolderStr, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  51 */     log.debug("Entering Dispatcher#doPost");
/*  52 */     responseInit(response);
/*  53 */     if ((Utils.isEmpty(commandStr)) && (Utils.isEmpty(currentFolderStr))) {
/*  54 */       commandStr = "QuickUpload";
/*  55 */       currentFolderStr = "/";
/*  56 */       if (Utils.isEmpty(typeStr)) {
/*  57 */         typeStr = "File";
/*     */       }
/*     */     }
/*  60 */     if ((currentFolderStr != null) && (!currentFolderStr.startsWith("/"))) {
/*  61 */       currentFolderStr = "/".concat(currentFolderStr);
/*     */     }
/*  63 */     log.debug("Parameter Command: {}", commandStr);
/*  64 */     log.debug("Parameter Type: {}", typeStr);
/*  65 */     log.debug("Parameter CurrentFolder: {}", currentFolderStr);
/*  66 */     UploadResponse ur = validateUpload(request, commandStr, typeStr, 
/*  67 */       currentFolderStr);
/*  68 */     if (ur == null) {
/*  69 */       ur = doUpload(request, typeStr, currentFolderStr);
/*     */     }
/*  71 */     PrintWriter out = response.getWriter();
/*  72 */     out.print(ur);
/*  73 */     out.flush();
/*  74 */     out.close();
/*     */   }
/*     */ 
/*     */   private UploadResponse doUpload(HttpServletRequest request, String typeStr, String currentFolderStr)
/*     */     throws Exception
/*     */   {
/*  80 */     ResourceType type = ResourceType.getDefaultResourceType(typeStr);
/*     */     try {
/*  82 */       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
/*  83 */       MultipartFile uplFile = (MultipartFile)((Entry)multipartRequest.getFileMap().entrySet().iterator().next()).getValue();
/*  84 */       String filename = FilenameUtils.getName(uplFile
/*  85 */         .getOriginalFilename());
/*  86 */       log.debug("Parameter NewFile: {}", filename);
/*  87 */       String ext = FilenameUtils.getExtension(filename);
/*  88 */       if (type.isDeniedExtension(ext)) {
/*  89 */         return UploadResponse.getInvalidFileTypeError(request);
/*     */       }
/*     */ 
/*  92 */       String fileUrl = this.fileRepository.storeByExt("/u/jspgou/", ext, uplFile);
/*     */ 
/*  94 */       fileUrl = request.getContextPath() + fileUrl;
/*  95 */       return UploadResponse.getOK(request, fileUrl); } catch (IOException e) {
/*     */     }
/*  97 */     return UploadResponse.getFileUploadWriteError(request);
/*     */   }
/*     */ 
/*     */   private void responseInit(HttpServletResponse response)
/*     */   {
/* 102 */     response.setCharacterEncoding("UTF-8");
/* 103 */     response.setContentType("text/html");
/* 104 */     response.setHeader("Cache-Control", "no-cache");
/*     */   }
/*     */ 
/*     */   private UploadResponse validateUpload(HttpServletRequest request, String commandStr, String typeStr, String currentFolderStr)
/*     */   {
/* 111 */     if (!Command.isValidForPost(commandStr)) {
/* 112 */       return UploadResponse.getInvalidCommandError(request);
/*     */     }
/* 114 */     if (!ResourceType.isValidType(typeStr)) {
/* 115 */       return UploadResponse.getInvalidResourceTypeError(request);
/*     */     }
/* 117 */     if (!UploadUtils.isValidPath(currentFolderStr)) {
/* 118 */       return UploadResponse.getInvalidCurrentFolderError(request);
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository)
/*     */   {
/* 128 */     this.fileRepository = fileRepository;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.FckAct
 * JD-Core Version:    0.6.2
 */