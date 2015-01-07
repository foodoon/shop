/*    */ package guda.shop.action.admin.main;
/*    */ 
/*    */ import guda.shop.cms.entity.Consult;
/*    */ import guda.shop.cms.entity.Discuss;
/*    */ import guda.shop.cms.manager.ConsultMng;
/*    */ import guda.shop.cms.manager.DiscussMng;
/*    */ import guda.shop.common.page.Pagination;
/*    */ import guda.shop.common.page.SimplePage;
/*    */ import guda.shop.common.web.CookieUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class MessageAct
/*    */ {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(MessageAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private DiscussMng manager;
/*    */ 
/*    */   @Autowired
/*    */   private ConsultMng consultMng;
/*    */ 
/* 32 */   @RequestMapping({"/message/v_productDiss.do"})
/*    */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(null, null, null, null, null, SimplePage.cpn(pageNo), 
/* 33 */       CookieUtils.getPageSize(request), true);
/* 34 */     model.addAttribute("pagination", pagination);
/*    */ 
/* 36 */     return "message/list";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_edit.do"})
/*    */   public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 42 */     Discuss bean = this.manager.findById(id);
/* 43 */     model.addAttribute("discuss", bean);
/* 44 */     return "message/edit";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_delete.do"})
/*    */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 50 */     this.manager.deleteByIds(ids);
/* 51 */     model.addAttribute("pageNo", pageNo);
/* 52 */     return "redirect:v_productDiss.do";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_productConsult.do"})
/*    */   public String listConsult(Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 59 */     Pagination pagination = this.consultMng.getPage(null, null, null, null, null, SimplePage.cpn(pageNo), 
/* 60 */       CookieUtils.getPageSize(request), Boolean.valueOf(true));
/* 61 */     model.addAttribute("pagination", pagination);
/* 62 */     return "consult/list";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_editConsult.do"})
/*    */   public String editConsult(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 68 */     Consult bean = this.consultMng.findById(id);
/* 69 */     model.addAttribute("consult", bean);
/* 70 */     return "consult/edit";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_updateConsult.do"})
/*    */   public String updateConsult(Long id, String adminReplay, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 76 */     Consult bean = this.consultMng.findById(id);
/* 77 */     bean.setAdminReplay(adminReplay);
/* 78 */     this.consultMng.update(bean);
/* 79 */     model.addAttribute("pageNo", pageNo);
/* 80 */     return "redirect:v_productConsult.do";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/message/v_deleteConsult.do"})
/*    */   public String deleteConsult(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 86 */     this.consultMng.deleteByIds(ids);
/* 87 */     model.addAttribute("pageNo", pageNo);
/* 88 */     return "redirect:v_productConsult.do";
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.MessageAct
 * JD-Core Version:    0.6.2
 */