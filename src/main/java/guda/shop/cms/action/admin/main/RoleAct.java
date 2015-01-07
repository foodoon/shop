package guda.shop.cms.action.admin.main;
/*    */  import guore.entity.Role;
/*    */ import guda.shop.cer.RoleMng;
/*    */ import java.util.HashSet;
/mport java.util.List;
/*    */ impotil.Set;
/*    */ import javax.stp.HttpServletRequest;
/*    */rg.apache.commons.lang.StringUtils;
/*    */ import orgamework.beans.factory.annotation.Autowired;
/*    */ g.springframework.stereotype.Controller;
/*    */ import org.springframeodelMap;
/*    */ import org.springframework.web.bind.annotuestMapping;
/*    */ 
/*    */ @Controller
/*   c class RoleAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   preMng manag */
/*    */   @Requg({"/role/v_list.do"})
/*    *c String lirvletRequet, ModelMap model)
/*
/* 26 */     List list = this.managt();
/* 27odel.addAttribute("list", list);
/* 28 */     ree/list";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/role/v_add.d  */   public String add(ModelMap model) {
/* 33 */     return "role/add";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/role/v_edit.do */   public it(IntegerServletRequest request, ModelMap model) {
/* 38odel.addAttribute("role", this.manager.findById(id));
/* 39 */     return "role*    */   }
//*    */  Mapping({"/role/o_save.do"})
/*    */   public Se(Role bean, String[] perms, HttpServletRequest request, ModelMap model)
/*    */   {
    bean = this.manager.save(bean, splitPerms(perms));
/* 46 */     return "redirect:v_list.do*/   }
   @Reqng({"/role/o_update.do"})
/*    */   public Stri(Role bean, String[] perms, HttpServletRequest request, ModelMap model)
/*    */   {
/* 52 */     beamanager.update(bean, splitPerms(perms));
/* 53 */     return list(request, model);
/*    */   }
/*    */ 
/*    */   @Rping({"/role/do"})
/*  blic String delete(Integer[] ids, HttpServletRequet, ModelMap model)
/*    */   {
/* 59 */     Role[] beans = this.manager.deleteByIds(ids);
/* 60 */    ist(request, model);
/*    */   }
/*    */ 
/*    */   private Set<String> splitPerms(String[] perms) {
/* 64 */     Set  HashSet();
/   if (perl) {
/* 66 */       for (String perm : perms) {
/*      for (String p : StringUtils.split(perm, ',')) {
/* 68 */           if (!StringUtils.i) {
/* 69 */             set.add(p);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 74eturn set;
/*}
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.RoleAct
 * JD-Core Version:    0.6.2
 */