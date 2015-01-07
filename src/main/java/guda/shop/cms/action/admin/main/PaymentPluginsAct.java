 package guda.shop.cms.action.admin.main;

 import guda.shop.cms.entity.Payment;
 import guda.shop.cms.entity.PaymentPlugins;
 import guda.shop.cms.manager.PaymentPluginsMng;
 import guda.shop.core.web.WebErrors;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.servlet.http.HttpServletRequest;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 public class PaymentPluginsAct
 {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(PaymentPluginsAct.class);

   @Autowired
   private PaymentPluginsMng manager;

/*  32 */   @RequestMapping({"/plugins/v_list.do"})
   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getList();
/*  33 */     Set codeSet = new HashSet();
/*  34 */     for (PaymentPlugins p : list) {
/*  35 */       codeSet.add(p.getCode());
     }
/*  37 */     model.addAttribute("list", list);
/*  38 */     model.addAttribute("codeSet", codeSet);
/*  39 */     return "plugins/list"; }

   @RequestMapping({"/plugins/v_add.do"})
   public String add(String code, HttpServletRequest request, ModelMap model)
   {
/*  44 */     model.addAttribute("code", code);
/*  45 */     return "plugins/add";
   }

   @RequestMapping({"/plugins/v_edit.do"})
   public String edit(Long id, HttpServletRequest request, ModelMap model)
   {
     WebErrors errors = validateEdit(id, request);
/*  52 */     if (errors.hasErrors()) {
/*  53 */       return errors.showErrorPage(model);
     }
/*  55 */     PaymentPlugins paymentPlugins = this.manager.findById(id);
/*  56 */     model.addAttribute("paymentPlugins", paymentPlugins);
/*  57 */     return "plugins/edit";
   }

   @RequestMapping({"/plugins/o_save.do"})
   public String save(PaymentPlugins bean, HttpServletRequest request, ModelMap model) {
/*  62 */     WebErrors errors = validateSave(bean, request);
/*  63 */     if (errors.hasErrors()) {
/*  64 */       return errors.showErrorPage(model);
     }
/*  66 */     bean = this.manager.save(bean);
/*  67 */     log.info("save Payment, id={}", bean.getId());
/*  68 */     return "redirect:v_list.do";
   }

   @RequestMapping({"/plugins/o_update.do"})
   public String update(PaymentPlugins bean, long[] shippingIds, HttpServletRequest request, ModelMap model)
   {
/*  74 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
     }

/*  79 */     bean = this.manager.update(bean);
/*  80 */     log.info("update Payment, id={}.", bean.getId());
/*  81 */     return list(request, model);
   }

   @RequestMapping({"/plugins/o_delete.do"})
   public String delete(Long[] ids, HttpServletRequest request, ModelMap model)
   {
/*  87 */     WebErrors errors = validateDelete(ids, request);
/*  88 */     if (errors.hasErrors()) {
/*  89 */       return errors.showErrorPage(model);
     }
/*  91 */     PaymentPlugins[] beans = this.manager.deleteByIds(ids);
/*  92 */     for (PaymentPlugins bean : beans) {
/*  93 */       log.info("delete Payment, id={}", bean.getId());
     }
/*  95 */     return list(request, model);
   }

   @RequestMapping({"/plugins/o_priority.do"})
   public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/* 101 */     this.manager.updatePriority(wids, priority);
/* 102 */     model.addAttribute("message", "global.success");
/* 103 */     return list(request, model);
   }

   private WebErrors validateSave(PaymentPlugins bean, HttpServletRequest request) {
/* 107 */     WebErrors errors = WebErrors.create(request);
/* 108 */     return errors;
   }

   private WebErrors validateEdit(Long id, HttpServletRequest request)
   {
/* 113 */     WebErrors errors = WebErrors.create(request);
/* 114 */     if (vldExist(id, errors)) {
/* 115 */       return errors;
     }
/* 117 */     return errors;
   }

   private WebErrors validateUpdate(Long id, HttpServletRequest request)
   {
/* 122 */     WebErrors errors = WebErrors.create(request);
/* 123 */     if (vldExist(id, errors)) {
/* 124 */       return errors;
     }
/* 126 */     return errors;
   }

   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 130 */     WebErrors errors = WebErrors.create(request);
/* 131 */     errors.ifEmpty(ids, "ids");
/* 132 */     for (Long id : ids) {
/* 133 */       vldExist(id, errors);
     }
/* 135 */     return errors;
   }

   private boolean vldExist(Long id, WebErrors errors) {
/* 139 */     if (errors.ifNull(id, "id")) {
/* 140 */       return true;
     }
/* 142 */     PaymentPlugins entity = this.manager.findById(id);
/* 143 */     if (errors.ifNotExist(entity, Payment.class, id)) {
/* 144 */       return true;
     }

/* 147 */     return false;
   }
 }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.PaymentPluginsAct
 * JD-Core Version:    0.6.2
 */