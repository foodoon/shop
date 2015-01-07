package guda.shop.cms.action.admin.main;
/*    */  import gums.entity.Poster;
/*    */ import guda.shop.cr.PosterMng;
/*    */ import guda.shop.common.webUtils;
/*    */ import java.util.Date;
/*    */ impotil.List;
/*    */ import javax.ttp.HttpServletRequest;
/*    */avax.servlet.http.HttpServletResponse;
/*    */ import gframework.beans.factory.annotation.Autowired;
/*    */ g.springframework.stereotype.Controller;
/*    */ import org.springframeodelMap;
/*    */ import org.springframework.web.bind.annotuestMapping;
/*    */ 
/*    */ @Controller
/*   c class PosterAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   osterMng m*    */
/*    */   @pping({"/poster/v_list.do"})
/* ublic StrintpServletRquest, ModelMap model)   {
/* 28 */     List listPoster = thr.getPage(*/     model.addAttribute("listPoster", listPoster*/     return "poster/list";
/*    */   }
/*    */ 
/*    */   @RequestMaposter/o_updateCare.do"})
/*    */   public String o_updateCare(String val, Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/ {
/* 36 */   bean = thr.findById(id);
/* 37 */     bean.setInterUrl(val);
/* 3this.manager.update(bean);
/* 39 */     ResponseUtils.renderJson(response, "success");
/* 40 */     return null;
/*    */   }
/*    */ 
  @RequestMapping({"/poster/o_update.do"})
/*    */   public String edit(String[] picUrl, String[] interUrl, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 46 */     if ((picUrl != null) && (picUrl.le) {
/* 47 */  (int i = cUrl.length; i++) {
/* 48 */         Poster p = new
/* 49 */         p.setTime(new Date());
/* 50 */         p.setPicUrl(picUrl[i]);
/* 51 */         p.setInterUrl(interUrl[i]);         this.manager.saveOrUpdate(p);
/*    */       }
/*    */     }
/*    */ 
/* 56 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/poster/o_delPoster.do"})
/*    */   public String delete(Integer id, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */  */     this.managById(id);
/* 63esponseUtils.renderJson(response, "success");
/* 64 turn null;
/*}
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.PosterAct
 * JD-Core Version:    0.6.2
 */