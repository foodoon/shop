/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Advertise;
/*     */ import guda.shop.cms.manager.AdspaceMng;
/*     */ import guda.shop.cms.manager.AdvertiseMng;
/*     */ import guda.shop.cms.web.RequestUtils1;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.CookieUtils;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class AdvertiseAct
/*     */ {
/*  31 */   private static final Logger log = LoggerFactory.getLogger(AdvertiseAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private AdspaceMng adspaceMng;
/*     */ 
/*     */   @Autowired
/*     */   private AdvertiseMng manager;
/*     */ 
/*  36 */   @RequestMapping({"/advertise/v_list.do"})
/*     */   public String list(Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(queryAdspaceId, 
/*  37 */       queryEnabled, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  38 */     model.addAttribute("pagination", pagination);
/*  39 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  40 */     if (queryAdspaceId != null) {
/*  41 */       model.addAttribute("queryAdspaceId", queryAdspaceId);
/*     */     }
/*  43 */     if (queryEnabled != null) {
/*  44 */       model.addAttribute("queryEnabled", queryEnabled);
/*     */     }
/*  46 */     return "advertise/list"; }
/*     */ 
/*     */   @RequestMapping({"/advertise/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  51 */     List adspaceList = this.adspaceMng.getList();
/*  52 */     model.addAttribute("adspaceList", adspaceList);
/*  53 */     return "advertise/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertise/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  59 */     Advertise advertise = this.manager.findById(id);
/*  60 */     model.addAttribute("advertise", advertise);
/*  61 */     model.addAttribute("attr", advertise.getAttr());
/*  62 */     model.addAttribute("adspaceList", this.adspaceMng.getList());
/*  63 */     model.addAttribute("pageNo", pageNo);
/*  64 */     return "advertise/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertise/o_save.do"})
/*     */   public String save(Advertise bean, Integer adspaceId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  70 */     Map attr = RequestUtils1.getRequestMap(request, "attr_");
/*     */ 
/*  72 */     Set toRemove = new HashSet();
/*  73 */     for (Entry entry : attr.entrySet()) {
/*  74 */       if (StringUtils.isBlank((String)entry.getValue())) {
/*  75 */         toRemove.add((String)entry.getKey());
/*     */       }
/*     */     }
/*  78 */     for (String key : toRemove) {
/*  79 */       attr.remove(key);
/*     */     }
/*  81 */     bean = this.manager.save(bean, adspaceId, attr);
/*  82 */     log.info("save advertise id={}", bean.getId());
/*  83 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertise/o_update.do"})
/*     */   public String update(Integer queryAdspaceId, Boolean queryEnabled, Advertise bean, Integer adspaceId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  90 */     Map attr = RequestUtils1.getRequestMap(request, "attr_");
/*     */ 
/*  92 */     Set toRemove = new HashSet();
/*  93 */     for (Entry entry : attr.entrySet()) {
/*  94 */       if (StringUtils.isBlank((String)entry.getValue())) {
/*  95 */         toRemove.add((String)entry.getKey());
/*     */       }
/*     */     }
/*  98 */     for (String key : toRemove) {
/*  99 */       attr.remove(key);
/*     */     }
/* 101 */     bean = this.manager.update(bean, adspaceId, attr);
/* 102 */     log.info("update advertise id={}.", bean.getId());
/* 103 */     return list(queryAdspaceId, queryEnabled, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertise/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 110 */     Advertise[] beans = this.manager.deleteByIds(ids);
/* 111 */     for (Advertise bean : beans) {
/* 112 */       log.info("delete advertise id={}", bean.getId());
/*     */     }
/* 114 */     return list(queryAdspaceId, queryEnabled, pageNo, request, model);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AdvertiseAct
 * JD-Core Version:    0.6.2
 */