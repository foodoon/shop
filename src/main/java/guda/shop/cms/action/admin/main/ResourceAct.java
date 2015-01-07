 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.manager.ResourceMng;
/*     */ import guda.shop.iteUtils;
/*     */ import guda.shop.common.fiap;
/*     */ import guda.shop.common.web.Request     */ import guda.shop.common.web.ResponseUtils;
/import guda.shop.common.web.springmvc.MessageResolver*/ import guda.shop.common.web.springmvc.RealPathResolver;
/*    t guda.shop.core.entity.Website;
/*     */ import guda.shop.core.mmplateMng;
/*     */ import java.io.File;
/*    t java.io.IOException;
/*     */ import java.util.Col/*     */ import java.util.List*/ import java.util.Map;
/*     */ imp.servlet.ServletContext;
/*     */ imposervlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSponse;
/*     */ import org.apache.commons.io.F
/*     */ import org.apache.commons.lang.StringUtils;
/import org.slf4j.Logger;
/*     */ import org.slf4j.Logge
/*     */ import org.springframework.beans.factorion.Autowired;
/*     */ import org.springframework.stController;
/*     */ import org.spwork.ui.ModelMap;
/*     */ import org.sprork.web.bind.annotation.RequestMapping;
/*     */ import org.springframewind.annotation.RequestParam;
/*     */ import org.springfram.context.ServletContextAware;
/*     */ import orgamework.web.multipart.MultipartFile;
/*     */ import org.springframeworkipart.MultipartHttpServletRequest;
/*     */ 
/*     */ @Controller
/* blic class ResourceAct
/*     */   implements ServletContextAware
/*  *  45 */   private static final Logger log = LoggerFactory.getLoggceAct.class);
/*     */   private static final String REL_PATH = "relPath";
/*  rivate Stri/*     */
/*     */  ed
/*     */   private TemplateMng /*     */
/*     */   @Autowired
/*     */e ResourceMng resourceMng;
/*     */   private ServletContext servletContext;
/*     */   private Realver realPathResolver;
/*     */
/*     */   @RequestMapping({e/v_left.do"})
/*     */   publiceft(HttpSerst request, ModelMap mo   */   {
/*  53 */     Website web = SittWeb(reques4 */     String resRealervletContext.getRealPath(web.getResBaseRel() */     String resName =
/*  56 */       MessageRetMessage(request, "resource.resName", new Object[0]);
/    FileWrathis.manager.getResFileWrap(resReal, resName);
/*  58odel.addAttribute("treeRoot", wrap);
/*  59 */     return "resource/left";
   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_tree.do"})
/*     */   public String tree(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  65 */     Website web = SiteUtils.getWeb(request);
/*  66 */     String root = RequestUtils.getQueryParam(request, "root");
/*  67 */     log.debug("tree path={}", root);
/*     */ 
/*  69 */     if ((StringUtils.isBlank(root)) || ("source".equals(r/*  70 */     web.getResB
/*  71 */       model.addAttribute("isRoot", Booleantrue));
/*     */     } else {
/*  73 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*   }
/*  75 */     List resList = this.resourceMng.listFile(root, true);
/*  76 */     model.addAttribute("resList", resList);
/*  77 */     response.setHeader("Cache-Control", "no-cache");
/    response.setContentType("text/json;charset=UTF-8");
/*  79 */     return "resource/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_list.do"})
/*     */   public String lirvletRequest request, ModelMap model)
/*     */   {
/*  85 */     Website web = SiteUtils.getst);
/*  86 */     String root = (String)model.get("root");
/*  87 */     if (root == null) {
/*  88 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/*  90 */     log.debug("list Resource root: {}", root);
/*  91 */     if (StringUtils.isBlank(root)) {
/*  92 */       root = sBaseRel();
/*   }
/*  94tring rel = root.substring(web.getResBaseRel().length95 */     if (rel.length() == 0) {
/*  96 */       rel = "/";
/*     */     */     model.addAttribute("root", root);
/*  99 */     model.addAttribute("rel", rel);
/* 100 */     model.addAttribute("resBase", web.getResBaseUrl());
/* 101 */     model.addAttribute("list", this.resourceMng.listFile(root,
/* 102 */     return "resource/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_create_dir.do"})
/*     */   public String createDir(Strintring dirName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 109 */     this.resourceMng.createDir(root, dirName);
/* 110 */     model.addAtroot", root);
/* 111 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_rename.do"})
/*     */   public String renameInput(HttpServletRequest request, ModelMap model) {
/* 116 */     Website web = SiteUtils.getWeb(request);
/* 117 */     StringequestUtils.geam(request,
/* 118 */     String name = RequestUtils.getQueryParam(reqme");
/* 119 */     String origName = name.substring(web.getResBaseRel().length());
/* 120 */     model.addAtorigName", origName);
/* 121 */     model.addAttribute("root", root);
/* 122 */     return "resource/rename";
/*     */   }
/*     */ 
/*     */   @RequestMappingrce/o_rename.d   */   pubg renameUpdate(String relPath, String origName, String HttpServletRequest request, ModelMap model)
/*     */   {
/* 128 */     Website web = SiteUtils.getWeb(request);
/* 129 */     String real = this.servletContext.getRealPath(web.getResBaseRel(relPath));
/* 130 */     File origFile = new File(real, origName);
/*     */ 
/* 133 */     File newFile = new File(real, newName);
/* 134 */     origFile.renameTo(newFile);
/* 135 */     log.info("rename resource dir {} to {}", origFile.getAbsolutePath(), 
/* 136 */       newFile.getAbsolutePath());
/* 137 */     Attribute("rellPath);
/*   return list(request, model);
/*     */   }
/*     */ /   @RequestMapping({"/resource/v_add.do"})
/*     */   public String add(String relPath, HttpServletRequest request, ModelMap model3 */     model.addAttribute("relPath", relPath);
/* 144 */     return "resource/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_save.do"})
/*     */   public String save(String relPath, Strame, String extension, String content, HttpServletRequest request, ModelMap model)
/*     */   {
/* 150 */     Website web = SiteUtils.getWeb(request);
/* 151 */     String real = this.servletContext.getRealPath(web.getResBaseRel(relPath));
/*     */     try {
/* 153 */       File file = new File(real, filename + extension);
/* 15  FileUtils.wrToFile(file, "UTF-8");
/* 155 */       log.info("save resource ess: {}", file.getAbsolutePath());
/*     */     } catch (IOException e) {
/* 157 */       log.error("write resource file error", e);
/*     */     }
/* 159 */     model.addAttribute(, relPath);
/*   return lst, model);
/*     */   }
/*     */ 
/*     */   @Reqng({"/resource/v_edit.do"})
/*     */   public String edit(HttpServletRequest request, ModelMap model) {
/* 165 */     String root = RequestUteryParam(request, "root");
/* 166 */     String name = RequestUtils.getQueryParam(request, "name");
/* 167 */     Website web = SiteUtils.getWeb(request);
/*   String real = this.servletContext.getRealPath(name);
/* 169 */     File file = new File(real);
/* 170 */     String filename = file.getName();
/*     */     try {
/* 172 */       String content = FileUtils.readFileToString(file, "UTF-8")*/       model.addAttribute("content", content);
/*     */     } catch (IOException e) {
/* 175 */ .error("read resource file error", e);
/*     */     }
/* 177 */     model.addAttribute("filename", filename);
/*   model.addAtroot", root */     model.addAttribute("name", name);
/* 180 */   "resource/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_update.do"})
/*     */   public String update(String name, String content, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 187 */     String real = this.realPathResolver.get(name);
/* 188 */     File file = new File(real);
/*     */     try {
/* 190 */       FileUtils.writeStringToFile(file, content, "UTF-   */     } catch (IOException e) {
/* 192 */       log.error("write resource file error", e);
/*     */     }
/* 194 */     ResponseUtils.renderJson(retrue");
/*     */
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMappingrce/o_delete.do"})
/*     */   public String delete(String relPath, String[] names, HttpServletRequest request, ModelMap model)
/*     */   {
/* 202 */     String root = RequestUtils.getQueryParam(request, /* 203 */     ng name : n* 204 */       int count = this.resourceMng.delete(new { name });
/* 205 */       log.info("delete Resource {}, count {}", name, Integer.valueOf(count));
/*     */     }
/* 207 */     model.ate("root", root);
/* 208 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, model)
/*     */   {
/* 214 */     String root = RequestUtils.getQueryParam(request, "root");
/* 21String name = RequestUtils.getQueryParam(request, "name");
/* 216 */     i= this.resourceMng.delete(new String[]);
/* 217 */  fo("delete {}, count {}", name, Integer.valueOf(count));
/* 218 */l.addAttribute("root", root);
/* 219 */     return list(request, model);
/*     */   }
/*     */ 
/*     */  Mapping({"/resource/v_upload.do"})
/*     */   public String uploadInput(String relPath, HttpServletRequest request, ModelMap model)
/*     */   {
/* 225 */     model.addAttribute("relPath", relPath);
/* 226 */     return "resource/upload";
/*     */   }
/*     */ 
/*     */   @RequestMappingrce/o_upload.do"})
/*     */   public String uploadSubmit(String relPath, HttpServletRequest request, Model)
/*     */   */     MultServletRequest multipartRequest = (MultipartHttpServletRequest
/* 234 */     Map map = multipartRequest.getFileMap();
/* 235 */     MultipartFile= new MultipartFile[map.size()];
/* 236 */     map.values().toArray(files);
/* 237 */     if (files.length > 0) {
/* 238 */       Website web = SiteUtils.getWeb(request);
/* 239 */       String realPath = this.servletContext.getRealPath(
/* 240 */         web.getResBaseRel(relPath));
/* 241 */       this.manager.uploadResourceFile(realPath, files);
/*     */     }
/* 243 */     model.addAttribute("relPath",
/* 244 */    ist(request
/*     */   }
/*     */ 
/*     */   @RequestMapping(vesource/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/* public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpSuest request, etResponse  ModelMap model)
/*     */     throws IllegalStateExcepxception
/*     */   {
/* 253 */     this.resourceMng.saveFile(root, file);
/* 254 */     model.add("root", root);
/* 255 */     log.info("file upload seccess: {}, size:{}.",
/* 256 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/* 257 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 271 */     this.realPathResolver = realPathResolver;
/* 272 */     this.root = realPathResolver.get("");
/*     */   }
/*     */ 
/*     */   public void setServletContext(ServletContext servletContext) {
     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ResourceAct
 * JD-Core Version:    0.6.2
 */