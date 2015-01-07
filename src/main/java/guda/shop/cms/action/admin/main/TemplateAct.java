 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.manager.ResourceMng;
/*     */ import guda.shop.iteUtils;
/*     */ import guda.shop.common.fiap;
/*     */ import guda.shop.common.util.Zipper*/ import guda.shop.common.web.RequestUtils;
/*mport guda.shop.common.web.ResponseUtils;
/*     */ da.shop.common.web.springmvc.MessageResolver;
/*      guda.shop.core.entity.Website;
/*     */ import guda.shop.core.mmplateMng;
/*     */ import guda.shop.core.tpl.T;
/*     */ import java.io.File;
/*     */ import javception;
/*     */ import java.util.List;
/*    t javax.servlet.ServletContext;/ import javax.servlet.http.HttpServle
/*     */ import javax.servlet.hervletResponse;
/*     */ import org.apache.com.StringUtils;
/*     */ import org.slf4j.Logger;
/*      org.slf4j.LoggerFactory;
/*     */ import org.springframns.factory.annotation.Autowired;
/*     */ import org.mework.stereotype.Controller;
/*   rt org.springframework.ui.ModelMap;
/*    t org.springframework.web.bind.annotation.RequestMapping;
/*     */ imporingframework.web.bind.annotation.RequestParam;
/*     */ imppringframework.web.context.ServletContextAware;
/*mport org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     oller
/*     */ public class TemplateAct
/*     */   implements Servletare
/*     */ {
/*  44 */   private static final Logger log = LoggerFaLogger(TemplateAct.class);
/*     */   private static final String = "relPath */
/*     */   @Auto    */   private TemplateMng manage */   private ServletContext servletContext*/   private ResourceMng resourceMng;
/*     */   private TplManager tplManager;
/*     */ 
/*     */ tMapping({"/template/v_left.do"})
/*     */   public String lervletReques, ModelMap model)
/*   /*  52 */     Website web = SiteUtils.getst);
/*  53 */     String tplReal = this.servletConealPath(web.getTemplateRel());
/*  54 */     lName = MessageResolver.getMessage(request,e.tplName",ct[0]);
/*  55 */     FileWrap wrap = this.manager.gerap(tplReal, tplName);
/*  56 */     model.addAttribute("treeRoot", wrap);
     return "template/left";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOExc     */   {
/*   this.tplave(root, file);
/*  67 */     model.addAttribute("root", root);
/*  68 */     log.info("file upload seccess: {}, size:{}.",
/*  69 file.getOriginalFilename(), Long.valueOf(file.getSize()));
/*  70 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_treethod={org.springframework.web.bind.annotation.RequestMe)
/*     */   public String tree(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  76 */     Website web = SiteUtils.getWeb(request);
/*  77 */     String root = RequestUtils.getQueryParam(request, "root");
/*  78 */     log.debug("tree path={}", root);
/*    80 */     ifUtils.isBla || ("source".equals(root))) {
/*  81 */       root = web.getTemplateRel();
/*  82 */       model.addAttribute("isRoot", Boolea(true));
/*     */     } else {
/*  84 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*   }
/*  86 */     List tplList = this.tplManager.getChild(root);
/*  87 */     model.addAttribute("tplList", tplList);
/*  88 */     response.setHeader("Cache-Control", "no-cache");
/*  89 sponse.setContentType("text/json;charset=UTF-8");
/*  90 */     return "template/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_list.do"}, method={org.springframework.wnnotation.RequestMethod.GET})
/*     */   public String list(HttpServletRequest request, Model)
/*     */   {
/*  96 */     Website web = SiteUtils.getWeb(request);
/*  97 */     String root = (String)model.get("root");
/*  98 */     if (root == null) {
/*  99 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/* 101 */     log.debug("list Template root: {}", roo2 */     if (Ss.isBlank(r* 103 */       root = new String(web.getTemplateRelBuff());
/*     */     }
/* 105 */     String rel = root.substring(new StrinTemplateRelBuff()).length());
/* 106 */     if (rel.length() == 0) {
/* 107 rel = "/";
/*     */     }
/* 109 */     model.addAttribute("root", root);
/* 110 */     model.addAttribute("rel", rel);
/* 111 */     model.addAttribute("list", this.tplManager.getChild(root));
/* 112 */     return "template*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_create_dir.do"})
/*     */   public String createDir(String relPath, String dirName, HttpServletRequest request, Model)
/*     */   {
/* 118 */     Website web = SiteUtils.getWeb(request);
/* 119 */     String real = this.servletContext.getRealPath(web.getTemplateRel(relPath));
/* 120 *e file = new File(real, dirName);
/*     */ 
/* 122 */     if (!file.exists()) {
/* 123 */       file.mkdirs();
/*     */     }
/* 125 */     model.addAttribute("relPath", relPath);
/* 126 */     return list(request, model */   }
/*       */   @Reing(value={"/template/v_rename.do"}, method={org.springfram.bind.annotation.RequestMethod.GET})
/*     */   public String renameInput(HttpServletRequest request, ModelMap /* 131 */     Website web = SiteUtils.getWeb(request);
/* 132 */     String root = RequestUtils.getQueryParam(request, "root");
/* 133 */     String name = RequestUtils.getQueryParam(request, "name");
/* 134 */ng origName = name.substring(web.getTemplateRel().length());
/* 135 */     modribute("origName", origName);
/* 136 */     model.addAttribute("root", root);
/* 137 */     return "template/rena   */   }
/*       */   @pping({"/template/o_rename.do"})
/*     */   public String renameUpdate(String relPath, String origName, String newName, HttpServt request, ModelMap model)
/*     */   {
/* 143 */     Website web = SiteUtils.getWeb(request);
/* 144 */     String real = this.servletContext.getRealPath(web.getTemplateRel(relPath));
/* 145 */     File origFile = new File(real, origName);
/*     */ 
/* 148 */     File newFile = new File(real, newName);
/* 149 */     origFile.renameTo(newFile);
/* 150 */     log.info("rename template dir {} to {}", origFile.getAbsolutePath(), 
/* 151 */       newFile.getAbsolutePath());
/* 152 */     model.addAttributh", relPath);
     returnuest, model);
/*     */   }
/*     */ 
/*     */   @Reqng(value={"/template/v_add.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String add(HtRequest request, ModelMap model) {
/* 158 */     String root = RequestUtils.getQueryParam(request, "root");
/* 159 */     model.addAttribute("root", root);
/* 160 */     return "template/add";
/*     */   }
/*       */   @RequestMapping({"/template/v_edit.do"})
/*     */   public String edit(HttpServletRequest request, ModelMap model) {
/* 165 */     String root = RequestUtils.getQueryParam(request, "root");
/* 166 */     String name = RequestUtils.getQueryParam(request, "name");
/* 167 */     model.addAttribute("template", this.tplManager.);
/* 168 */  addAttribut root);
/* 169 */     return "template/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_save.do"})
/* public String save(String root, String filename, String source, HttpServletRequest request, ModelMap model)
/*     */   {
/* 175 */     String name = root + "/" + filename + ".html";
/* 176 */     this.tplManager.save(name, source, f 177 */     motribute("ro);
/* 178 */     log.info("save Template name={}", fi/* 179 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_update.do"})
/*     */   public void update(String root, String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 187 */     this.tplManager.update(name, source);
/* 188 */     log.info("update Template name={};
/* 189 */   ddAttributeroot);
/* 190 */     ResponseUtils.renderJson(responsess:true}");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_delete.do"})
/*     */   public String ding root, String[] names, HttpServletRequest request, ModelMap model)
/*     */   {
/* 196 */     int count = this.tplManager.delete(names);
/* 197 */     log.info("delete Template count: {}", Integer.valueOf(count));
/* 198 */     for (String name : names) {
/* 199 */       log.ine Template namme);
/*
/* 201 */     model.addAttribute("root", root);
/* 202eturn list(request, model);
/*     */   }
/*     */
/*     */   @RequestMapping({"/template/o_delete_single.do"})
/*     */   public String deletttpServletRequest request, ModelMap model)
/*     */   {
/* 208 */     String root = RequestUtils.getQueryParam(request, "root");
/* 209 */     String name = RequestUtils.getQueryParam(request, "name");
/* 210 */     int count = this.tplMante(new String[});
/* 211 g.info("delete Template {}, count {}", name, Integer.vant));
/* 212 */     model.addAttribute("root", root);
/* 213 */     return list(request, model);
/*     */   */
/*     */   @RequestMapping({"/template/v_solution.do"})
/*     */   public String solutionInput(HttpServletRequest request, ModelMap model) {
/* 218 */     return "template/solution";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/t_export.do"})
/*     */   public void templateExport(HttpServletRequest request, HttpServletResponse respon  */   {
/* 22List fileEnis.resourceMng.export();
/* 225 */     response.setContentTypetion/x-download;charset=UTF-8");
/* 226 */     response.addHeader("Content-dispositlename=template.zip");
/*     */     try {
/* 228 */       Zipper.zip(response.getOutputStream(), fileEntrys, "GBK");
/*     */     } catch (IOException e) {
/* 230 */       log.error("export template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_solution.do"})
/*     */   public String solutionUpdate(HttpServletRequest request, ModelMap model)
/*     */   {/     return sput(request
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/o_import.do"})
/*     */   public String importSubmit(@RequestParam(value="tplZip", required=false) MultipartFile file, HttpServt request, Httesponse resdelMap model)
/*     */     throws IOException
/*     *245 */     Website site = SiteUtils.getWeb(request);
/* 246 */     File tempFile = File.createTemlZip", "temp");
/* 247 */     file.transferTo(tempFile);
/* 248 */     this.resourceMng.imoport(tempFile, site);
/* 249 */     tempFile.delete();
/* 250 */     return solutionInput(request, model);
/*     */   }
/*     */ 
/*     */  oid setTplManager(TplManager tplManager)
/*     */   {
/* 261 */     this.tplManager = tplManag  */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceMng(ResourceMng re) {
/* 266 */   sourceMng = re;
/*     */   */
/*     */   public void setServletContext(ServletCrvletContext) {
/* 270 */     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.TemplateAct
 * JD-Core Version:    0.6.2
 */