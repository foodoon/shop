/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.manager.ResourceMng;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.file.FileWrap;
/*     */ import guda.shop.common.util.Zipper;
/*     */ import guda.shop.common.web.RequestUtils;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.manager.TemplateMng;
/*     */ import guda.shop.core.tpl.TplManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.context.ServletContextAware;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class TemplateAct
/*     */   implements ServletContextAware
/*     */ {
/*  44 */   private static final Logger log = LoggerFactory.getLogger(TemplateAct.class);
/*     */   private static final String REL_PATH = "relPath";
/*     */ 
/*     */   @Autowired
/*     */   private TemplateMng manager;
/*     */   private ServletContext servletContext;
/*     */   private ResourceMng resourceMng;
/*     */   private TplManager tplManager;
/*     */ 
/*     */   @RequestMapping({"/template/v_left.do"})
/*     */   public String left(HttpServletRequest request, ModelMap model)
/*     */   {
/*  52 */     Website web = SiteUtils.getWeb(request);
/*  53 */     String tplReal = this.servletContext.getRealPath(web.getTemplateRel());
/*  54 */     String tplName = MessageResolver.getMessage(request, "template.tplName", new Object[0]);
/*  55 */     FileWrap wrap = this.manager.getTplFileWrap(tplReal, tplName);
/*  56 */     model.addAttribute("treeRoot", wrap);
/*  57 */     return "template/left";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOException
/*     */   {
/*  66 */     this.tplManager.save(root, file);
/*  67 */     model.addAttribute("root", root);
/*  68 */     log.info("file upload seccess: {}, size:{}.", 
/*  69 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/*  70 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_tree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String tree(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  76 */     Website web = SiteUtils.getWeb(request);
/*  77 */     String root = RequestUtils.getQueryParam(request, "root");
/*  78 */     log.debug("tree path={}", root);
/*     */ 
/*  80 */     if ((StringUtils.isBlank(root)) || ("source".equals(root))) {
/*  81 */       root = web.getTemplateRel();
/*  82 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*     */     } else {
/*  84 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  86 */     List tplList = this.tplManager.getChild(root);
/*  87 */     model.addAttribute("tplList", tplList);
/*  88 */     response.setHeader("Cache-Control", "no-cache");
/*  89 */     response.setContentType("text/json;charset=UTF-8");
/*  90 */     return "template/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_list.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String list(HttpServletRequest request, ModelMap model)
/*     */   {
/*  96 */     Website web = SiteUtils.getWeb(request);
/*  97 */     String root = (String)model.get("root");
/*  98 */     if (root == null) {
/*  99 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/* 101 */     log.debug("list Template root: {}", root);
/* 102 */     if (StringUtils.isBlank(root)) {
/* 103 */       root = new String(web.getTemplateRelBuff());
/*     */     }
/* 105 */     String rel = root.substring(new String(web.getTemplateRelBuff()).length());
/* 106 */     if (rel.length() == 0) {
/* 107 */       rel = "/";
/*     */     }
/* 109 */     model.addAttribute("root", root);
/* 110 */     model.addAttribute("rel", rel);
/* 111 */     model.addAttribute("list", this.tplManager.getChild(root));
/* 112 */     return "template/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_create_dir.do"})
/*     */   public String createDir(String relPath, String dirName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 118 */     Website web = SiteUtils.getWeb(request);
/* 119 */     String real = this.servletContext.getRealPath(web.getTemplateRel(relPath));
/* 120 */     File file = new File(real, dirName);
/*     */ 
/* 122 */     if (!file.exists()) {
/* 123 */       file.mkdirs();
/*     */     }
/* 125 */     model.addAttribute("relPath", relPath);
/* 126 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String renameInput(HttpServletRequest request, ModelMap model) {
/* 131 */     Website web = SiteUtils.getWeb(request);
/* 132 */     String root = RequestUtils.getQueryParam(request, "root");
/* 133 */     String name = RequestUtils.getQueryParam(request, "name");
/* 134 */     String origName = name.substring(web.getTemplateRel().length());
/* 135 */     model.addAttribute("origName", origName);
/* 136 */     model.addAttribute("root", root);
/* 137 */     return "template/rename";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_rename.do"})
/*     */   public String renameUpdate(String relPath, String origName, String newName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 143 */     Website web = SiteUtils.getWeb(request);
/* 144 */     String real = this.servletContext.getRealPath(web.getTemplateRel(relPath));
/* 145 */     File origFile = new File(real, origName);
/*     */ 
/* 148 */     File newFile = new File(real, newName);
/* 149 */     origFile.renameTo(newFile);
/* 150 */     log.info("rename template dir {} to {}", origFile.getAbsolutePath(), 
/* 151 */       newFile.getAbsolutePath());
/* 152 */     model.addAttribute("relPath", relPath);
/* 153 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_add.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/* 158 */     String root = RequestUtils.getQueryParam(request, "root");
/* 159 */     model.addAttribute("root", root);
/* 160 */     return "template/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/v_edit.do"})
/*     */   public String edit(HttpServletRequest request, ModelMap model) {
/* 165 */     String root = RequestUtils.getQueryParam(request, "root");
/* 166 */     String name = RequestUtils.getQueryParam(request, "name");
/* 167 */     model.addAttribute("template", this.tplManager.get(name));
/* 168 */     model.addAttribute("root", root);
/* 169 */     return "template/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_save.do"})
/*     */   public String save(String root, String filename, String source, HttpServletRequest request, ModelMap model)
/*     */   {
/* 175 */     String name = root + "/" + filename + ".html";
/* 176 */     this.tplManager.save(name, source, false);
/* 177 */     model.addAttribute("root", root);
/* 178 */     log.info("save Template name={}", filename);
/* 179 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_update.do"})
/*     */   public void update(String root, String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 187 */     this.tplManager.update(name, source);
/* 188 */     log.info("update Template name={}.", name);
/* 189 */     model.addAttribute("root", root);
/* 190 */     ResponseUtils.renderJson(response, "{success:true}");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_delete.do"})
/*     */   public String delete(String root, String[] names, HttpServletRequest request, ModelMap model)
/*     */   {
/* 196 */     int count = this.tplManager.delete(names);
/* 197 */     log.info("delete Template count: {}", Integer.valueOf(count));
/* 198 */     for (String name : names) {
/* 199 */       log.info("delete Template name={}", name);
/*     */     }
/* 201 */     model.addAttribute("root", root);
/* 202 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, ModelMap model)
/*     */   {
/* 208 */     String root = RequestUtils.getQueryParam(request, "root");
/* 209 */     String name = RequestUtils.getQueryParam(request, "name");
/* 210 */     int count = this.tplManager.delete(new String[] { name });
/* 211 */     log.info("delete Template {}, count {}", name, Integer.valueOf(count));
/* 212 */     model.addAttribute("root", root);
/* 213 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/v_solution.do"})
/*     */   public String solutionInput(HttpServletRequest request, ModelMap model) {
/* 218 */     return "template/solution";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_export.do"})
/*     */   public void templateExport(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 224 */     List fileEntrys = this.resourceMng.export();
/* 225 */     response.setContentType("application/x-download;charset=UTF-8");
/* 226 */     response.addHeader("Content-disposition", "filename=template.zip");
/*     */     try {
/* 228 */       Zipper.zip(response.getOutputStream(), fileEntrys, "GBK");
/*     */     } catch (IOException e) {
/* 230 */       log.error("export template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_solution.do"})
/*     */   public String solutionUpdate(HttpServletRequest request, ModelMap model)
/*     */   {
/* 237 */     return solutionInput(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_import.do"})
/*     */   public String importSubmit(@RequestParam(value="tplZip", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 245 */     Website site = SiteUtils.getWeb(request);
/* 246 */     File tempFile = File.createTempFile("tplZip", "temp");
/* 247 */     file.transferTo(tempFile);
/* 248 */     this.resourceMng.imoport(tempFile, site);
/* 249 */     tempFile.delete();
/* 250 */     return solutionInput(request, model);
/*     */   }
/*     */ 
/*     */   public void setTplManager(TplManager tplManager)
/*     */   {
/* 261 */     this.tplManager = tplManager;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceMng(ResourceMng resourceMng) {
/* 266 */     this.resourceMng = resourceMng;
/*     */   }
/*     */ 
/*     */   public void setServletContext(ServletContext servletContext) {
/* 270 */     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.TemplateAct
 * JD-Core Version:    0.6.2
 */