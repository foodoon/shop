/*     */ package guda.shop.action.member;
/*     */ 
/*     */ import guda.shop.cms.entity.Address;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.entity.ShopMemberAddress;
/*     */ import guda.shop.cms.manager.AddressMng;
/*     */ import guda.shop.cms.manager.OrderMng;
/*     */ import guda.shop.cms.manager.ShopMemberAddressMng;
/*     */ import guda.shop.cms.web.FrontUtils;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ShopMenberAddressAct
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(ShopMenberAddressAct.class);
/*     */   private static final String MEMBER_ADDRESS = "tpl.memberAddress";
/*     */   private static final String MEMBER_ADDRESS_EDIT = "tpl.memberAddressEdit";
/*     */ 
/*     */   @Autowired
/*     */   private OrderMng orderMng;
/*     */ 
/*     */   @Autowired
/*     */   private AddressMng addressMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberAddressMng shopMemberAddressMng;
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_list.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String list(HttpServletRequest request, ModelMap model)
/*     */   {
/*  52 */     Website web = SiteUtils.getWeb(request);
/*  53 */     ShopMember member = MemberThread.get();
/*  54 */     List list = this.shopMemberAddressMng.getList(member.getId());
/*  55 */     model.addAttribute("list", list);
/*  56 */     List plist = this.addressMng.getListById(null);
/*  57 */     model.addAttribute("plist", plist);
/*  58 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  59 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberAddress", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_save.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String save(ShopMemberAddress bean, Long provinceId, Long cityId, Long countryId, String returnUrl, HttpServletRequest request, ModelMap model)
/*     */   {
/*  65 */     Website web = SiteUtils.getWeb(request);
/*  66 */     ShopMember member = MemberThread.get();
/*  67 */     List list = this.shopMemberAddressMng.getList(member.getId());
/*  68 */     model.addAttribute("list", list);
/*  69 */     ShopMemberAddress entity = null;
/*  70 */     if (bean.getIsDefault()) {
/*  71 */       int i = 0; for (int j = list.size(); i < j; i++) {
/*  72 */         entity = (ShopMemberAddress)list.get(i);
/*  73 */         entity.setIsDefault(false);
/*  74 */         this.shopMemberAddressMng.updateByUpdater(entity);
/*     */       }
/*     */     }
/*  77 */     bean.setProvince(this.addressMng.findById(provinceId));
/*  78 */     bean.setCity(this.addressMng.findById(cityId));
/*  79 */     bean.setCountry(this.addressMng.findById(countryId));
/*  80 */     bean.setMember(member);
/*  81 */     this.shopMemberAddressMng.save(bean);
/*  82 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  83 */     log.info("ShopMemberAddress save success, id= {}", bean.getId());
/*  84 */     if (returnUrl != null) {
/*  85 */       return "redirect:" + returnUrl;
/*     */     }
/*  87 */     return "redirect:address_list.jspx";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_edit.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/*  93 */     Website web = SiteUtils.getWeb(request);
/*  94 */     ShopMember member = MemberThread.get();
/*  95 */     WebErrors errors = validateEdit(id, member.getId(), request);
/*  96 */     if (errors.hasErrors()) {
/*  97 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  99 */     List list = this.shopMemberAddressMng.getList(member.getId());
/* 100 */     model.addAttribute("list", list);
/* 101 */     ShopMemberAddress bean = this.shopMemberAddressMng.findById(id);
/* 102 */     model.addAttribute("bean", bean);
/* 103 */     List plist = this.addressMng.getListById(null);
/* 104 */     model.addAttribute("plist", plist);
/* 105 */     List clist = this.addressMng.getListById(bean.getProvince().getId());
/* 106 */     model.addAttribute("clist", clist);
/* 107 */     List alist = this.addressMng.getListById(bean.getCity().getId());
/* 108 */     model.addAttribute("alist", alist);
/* 109 */     model.addAttribute("id", id);
/* 110 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 111 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberAddressEdit", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_update.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String update(ShopMemberAddress bean, Long provinceId, Long cityId, Long countryId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 117 */     ShopMember member = MemberThread.get();
/* 118 */     List list = this.shopMemberAddressMng.getList(member.getId());
/* 119 */     ShopMemberAddress entity = null;
/* 120 */     if (bean.getIsDefault()) {
/* 121 */       int i = 0; for (int j = list.size(); i < j; i++) {
/* 122 */         entity = (ShopMemberAddress)list.get(i);
/* 123 */         entity.setIsDefault(false);
/* 124 */         this.shopMemberAddressMng.updateByUpdater(entity);
/*     */       }
/*     */     }
/* 127 */     this.shopMemberAddressMng.updateByUpdater(bean);
/* 128 */     log.info("ShopMemberAddress update success, id= {}", bean.getId());
/* 129 */     return "redirect:address_list.jspx";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_default.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String isDefault(Long id, String returnUrl, HttpServletRequest request, ModelMap model) {
/* 134 */     ShopMember member = MemberThread.get();
/* 135 */     List list = this.shopMemberAddressMng.getList(member.getId());
/* 136 */     ShopMemberAddress bean = this.shopMemberAddressMng.findById(id);
/* 137 */     ShopMemberAddress entity = null;
/* 138 */     int i = 0; for (int j = list.size(); i < j; i++) {
/* 139 */       entity = (ShopMemberAddress)list.get(i);
/* 140 */       entity.setIsDefault(false);
/* 141 */       this.shopMemberAddressMng.updateByUpdater(entity);
/*     */     }
/* 143 */     bean.setIsDefault(true);
/* 144 */     this.shopMemberAddressMng.updateByUpdater(bean);
/* 145 */     log.info("ShopMemberAddress default success, id= {}", bean.getId());
/* 146 */     if (returnUrl != null) {
/* 147 */       return "redirect:" + returnUrl;
/*     */     }
/* 149 */     return "redirect:address_list.jspx";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMemberAddress/address_delete.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String delete(Long id, String returnUrl, HttpServletRequest request, ModelMap model)
/*     */   {
/* 155 */     Website web = SiteUtils.getWeb(request);
/* 156 */     ShopMember member = MemberThread.get();
/* 157 */     WebErrors errors = validateDelete(id, member.getId(), request);
/* 158 */     if (errors.hasErrors()) {
/* 159 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/* 161 */     if (this.orderMng.getlistByforaddressId(id).size() > 0) {
/* 162 */       return FrontUtils.showMessage(request, model, "地址关联了订单");
/*     */     }
/* 164 */     this.shopMemberAddressMng.deleteById(id, member.getId());
/* 165 */     log.info("ShopMemberAddress delete success, id= {}", id);
/* 166 */     if (returnUrl != null) {
/* 167 */       return "redirect:" + returnUrl;
/*     */     }
/* 169 */     return "redirect:address_list.jspx";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMemberAddress/findAllCity.jspx"})
/*     */   public void findAllCity(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 178 */     List clist = this.addressMng.getListById(id);
/* 179 */     Long[] ids = new Long[clist.size()];
/* 180 */     String[] citys = new String[clist.size()];
/* 181 */     int i = 0; for (int j = clist.size(); i < j; i++) {
/* 182 */       Address city = (Address)clist.get(i);
/* 183 */       ids[i] = city.getId();
/* 184 */       citys[i] = city.getName();
/*     */     }
/* 186 */     JSONObject json = new JSONObject();
/*     */     try {
/* 188 */       json.put("ids", ids);
/* 189 */       json.put("citys", citys);
/* 190 */       json.put("success", true);
/*     */     } catch (JSONException e) {
/*     */       try {
/* 193 */         json.put("success", false);
/*     */       } catch (JSONException e1) {
/* 195 */         e1.printStackTrace();
/*     */       }
/* 197 */       e.printStackTrace();
/*     */     }
/* 199 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMemberAddress/findAllCountry.jspx"})
/*     */   public void findAllArea(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 207 */     List alist = this.addressMng.getListById(id);
/* 208 */     Long[] ids = new Long[alist.size()];
/* 209 */     String[] areas = new String[alist.size()];
/* 210 */     int i = 0; for (int j = alist.size(); i < j; i++) {
/* 211 */       Address area = (Address)alist.get(i);
/* 212 */       ids[i] = area.getId();
/* 213 */       areas[i] = area.getName();
/*     */     }
/* 215 */     JSONObject json = new JSONObject();
/*     */     try {
/* 217 */       json.put("ids", ids);
/* 218 */       json.put("areas", areas);
/* 219 */       json.put("success", true);
/*     */     } catch (JSONException e) {
/*     */       try {
/* 222 */         json.put("success", false);
/*     */       } catch (JSONException e1) {
/* 224 */         e1.printStackTrace();
/*     */       }
/* 226 */       e.printStackTrace();
/*     */     }
/* 228 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long addressId, Long memberId, HttpServletRequest request)
/*     */   {
/* 233 */     WebErrors errors = WebErrors.create(request);
/* 234 */     if (vldAddress(addressId, memberId, errors)) {
/* 235 */       return errors;
/*     */     }
/* 237 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long addressId, Long memberId, HttpServletRequest request)
/*     */   {
/* 242 */     WebErrors errors = WebErrors.create(request);
/* 243 */     if (vldAddress(addressId, memberId, errors)) {
/* 244 */       return errors;
/*     */     }
/* 246 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldAddress(Long addressId, Long memberId, WebErrors errors)
/*     */   {
/* 252 */     if (errors.ifNull(addressId, "id")) {
/* 253 */       return true;
/*     */     }
/* 255 */     ShopMemberAddress address = this.shopMemberAddressMng.findById(addressId);
/* 256 */     if (errors.ifNotExist(address, ShopMemberAddress.class, addressId)) {
/* 257 */       return true;
/*     */     }
/* 259 */     if (!memberId.equals(address.getMember().getId())) {
/* 260 */       errors.noPermission(ShopMemberAddress.class, addressId);
/*     */     }
/* 262 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ShopMenberAddressAct
 * JD-Core Version:    0.6.2
 */