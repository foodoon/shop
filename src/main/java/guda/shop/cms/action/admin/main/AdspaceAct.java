/*    */ package guda.shop.cms.action.admin.main;
/*    */ 
/*    */ import guda.shop.cms.entity.Adspace;
/*    */ import guda.shop.cms.manager.AdspaceMng;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class AdspaceAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private AdspaceMng manager;
/*    */ 
/*    */   @RequestMapping({"/adspace/v_list.do"})
/*    */   public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 25 */     List list = this.manager.getList();
/* 26 */     model.addAttribute("list", list);
/* 27 */     return "adspace/list";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace/v_add.do"})
/*    */   public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 33 */     return "adspace/add";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace/v_edit.do"})
/*    */   public String edit(Integer pageNo, Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 39 */     Adspace bean = this.manager.findById(id);
/* 40 */     model.addAttribute("adspace", bean);
/* 41 */     return "adspace/edit";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace/o_update.do"})
/*    */   public String update(Adspace bean, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 47 */     this.manager.updateByUpdater(bean);
/* 48 */     return list(request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace/o_save.do"})
/*    */   public String save(Integer pageNo, Adspace bean, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 54 */     this.manager.save(bean);
/* 55 */     return list(request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace/o_delete.do"})
/*    */   public String delete(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 61 */     this.manager.deleteByIds(ids);
/* 62 */     return list(request, response, model);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AdspaceAct
 * JD-Core Version:    0.6.2
 */