package guda.shop.cms.action.admin.main;
/*    */  import gums.entity.Consult;
/*    */ import guda.shop.c.Discuss;
/*    */ import guda.shop.cms.manageMng;
/*    */ import guda.shop.cms.manager.Discuss  */ import guda.shop.common.page.Pagination;
/*  rt guda.shop.common.page.SimplePage;
/*    */ impohop.common.web.CookieUtils;
/*    */ import javax.ttp.HttpServletRequest;
/*    */ import org.slf4j.*    */ import org.slf4j.LoggerFactory;
/*    */ importngframework.beans.factory.annotatired;
/*    */ import org.springframework.e.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ g.springframework.web.bind.annotation.RequestMapping;
 @Controller
/*    */ public class MessageAct {
/* 27 */   private static final Logger log = LoggerFactory.getLogger(t.class);

/*    */   @Autowire/   private DiscussMng manager;
//*    */   @Autowired
/*    */   private ConsultMng consultMng;
/*    */
/* 32 */   @RequestMappinage/v_prodo"})
/*    */   publicist(Integer pageNo, HttpServletRequest ModelMap magination pagination =ager.getPage(null, null, null, null, null,ge.cpn(pageNo),
/* 33 */       CookieUtils.getPageSize(request), tr4 */     model.addAttribute("pagination", pagination);
/*    */
/* 36 */     return "message/list";
/*    */   }
/*    */
/*    */   @RequestMapping({"/message/v_edit.do"})
/*    */   public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 42 */     Discusshis.manager.findById(id);
    model.ate("discuss",* 44 */   "message/edit";
/*    */   }
/*    */
/*    */   @pping({"/message/v_delete.do"})
/*    */   public String delete(Long[] ids, Integer pageNo, HttpSerst request, ModelMap model)
/*    */   {
/* 50 */     this.manager.deleteByIds(ids);
/* 51 */     model.addAttribute("pageNo", pageNo);
/* 52 */     returct:v_productD/*    */  */
/*    */   @RequestMapping({"/message/v_productCo})
/*    */   public String listConsult(Integer pageNo, HttpServletRequest request, ModelMap model)
/*  /* 59 */     Pagination pagination = this.consultMng.getPage(null, null, null, null, null, SimplePage.cpn(pageNo),
/* 60 */       CookieUtils.getPageSize(reoolean.valueO
/* 61 */ .addAttribute("pagination", pagination);
/* 62 */     return list";
/*    */   }
/*    */
/*    */   @RequestMapping({"/message/v_editConsult.do"})
/*    */ String editConsult(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 68 */     Consult bean = this.consultMng.findById(id);
/* 69 */     model.addAttribute("consult", bean);
/* 70 */     return "consult/edit";
/*    */   }
/*    */
/*    */   @RequestMapping({"v_updateConsu/*    */  tring updateConsult(Long id, String adminReplay, Integer ptpServletRequest request, ModelMap model)
/*    */   {
/* 76 */     Consult bean = this.consultMng.findByI 77 */     bean.setAdminReplay(adminReplay);
/* 78 */     this.consultMng.update(bean);
/* 79 */     model.addAttribute("pageNo", pageNo);
/* 80 */     returct:v_productC";
/*    *   */
/*    */   @RequestMapping({"/message/v_deleteConsult    */   public String deleteConsult(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 86 is.consultMng.deleteByIds(ids);
/* 87 */     model.addAttribute("pageNo", pageNo);
/* 88 */     return "redirect:v_productConsult.do";
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.MessageAct
 * JD-Core Version:    0.6.2
 */