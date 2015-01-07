package guda.shop.cms.action.admin.main;
/*    */  import gums.entity.Adspace;
/*    */ import guda.shop.cr.AdspaceMng;
/*    */ import java.util.List;
/*  rt javax.servlet.http.HttpServle
/*    */ import javax.servlet.http.HttpServletResponse/ import org.springframework.beans.factory.annotation.Au/*    */ import org.springframework.stereotype.Controller;
/*    */ imporingframework.ui.ModelMap;
/*    */ import org.springframewind.annotation.RequestMapping;
/*    */
/*    */ler
/*    */ public class AdspaceAct
/*    */ {
/*    */
/*    */   @Au*    */   dspaceMng manager;
/**    */   @RequestMapping({"/adspt.do"})
/* ublic StrittpServletRequest requServletResponse response, ModelMap mode*/   {
/*  List list = this.manager.getList();
/* 26 */     mttribute("list", list);
/* 27 */     return "adspace/list";
/*    */   }
/*    */
/*    */   @RequestMaadspace/v_add.do"})
/*    */   public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 33 */ n "adspace/ad */   }
/**    */   @RequestMapping({"/adspace/v_edit.do"})
  public String edit(Integer pageNo, Integer id, HttpServletRequest request, HttpServletResponse responMap model)
/*    */   {
/* 39 */     Adspace beamanager.findB/* 40 */  addAttribute("adspace", bean);
/* 41 */     return edit";
/*    */   }
/*    */
/*    */   @RequestMapping({"/adspace/o_update.do"})
/*    */   public String update(Adspace bean, Httequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 47 */     this.manager.updateByUpdater(bean);
/* 48 */     return list(requense, model);
  }
   @RequestMapping({"/adspace/o_save.do"})
/*    *c String save(Integer pageNo, Adspace bean, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*{
/* 54 */     this.manager.save(bean);
/* 55 */     return list(request, response, model);
/*    */   }
/*    */    @RequestMaadspace/o_"})
/*    */   public String delete(Integer[] ids, etRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 61 */     this.manager.deleteByIds(ids);
/* 62 */    ist(request, response, model);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AdspaceAct
 * JD-Core Version:    0.6.2
 */