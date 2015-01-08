package guda.shop.cms.action.member;

import guda.shop.cms.entity.Address;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopMemberAddress;
import guda.shop.cms.manager.AddressMng;
import guda.shop.cms.manager.OrderMng;
import guda.shop.cms.manager.ShopMemberAddressMng;
import guda.shop.cms.web.FrontUtils;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import guda.shop.core.web.front.FrontHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ShopMenberAddressAct {
    private static final Logger log = LoggerFactory.getLogger(ShopMenberAddressAct.class);
    private static final String MEMBER_ADDRESS = "tpl.memberAddress";
    private static final String MEMBER_ADDRESS_EDIT = "tpl.memberAddressEdit";

    @Autowired
    private OrderMng orderMng;

    @Autowired
    private AddressMng addressMng;

    @Autowired
    private ShopMemberAddressMng shopMemberAddressMng;

    @RequestMapping(value = {"/shopMemberAddress/address_list.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        List list = this.shopMemberAddressMng.getList(member.getId());

        model.addAttribute("list", list);

        List plist = this.addressMng.getListById(null);

        model.addAttribute("plist", plist);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberAddress", new Object[0]));
    }

    @RequestMapping(value = {"/shopMemberAddress/address_save.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String save(ShopMemberAddress bean, Long provinceId, Long cityId, Long countryId, String returnUrl, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        List list = this.shopMemberAddressMng.getList(member.getId());

        model.addAttribute("list", list);

        ShopMemberAddress entity = null;

        if (bean.getIsDefault()) {

            int i = 0;
            for (int j = list.size(); i < j; i++) {

                entity = (ShopMemberAddress) list.get(i);

                entity.setIsDefault(false);

                this.shopMemberAddressMng.updateByUpdater(entity);
            }
        }

        bean.setProvince(this.addressMng.findById(provinceId));

        bean.setCity(this.addressMng.findById(cityId));

        bean.setCountry(this.addressMng.findById(countryId));

        bean.setMember(member);

        this.shopMemberAddressMng.save(bean);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        log.info("ShopMemberAddress save success, id= {}", bean.getId());

        if (returnUrl != null) {

            return "redirect:" + returnUrl;
        }

        return "redirect:address_list.jspx";
    }

    @RequestMapping(value = {"/shopMemberAddress/address_edit.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        WebErrors errors = validateEdit(id, member.getId(), request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        List list = this.shopMemberAddressMng.getList(member.getId());

        model.addAttribute("list", list);

        ShopMemberAddress bean = this.shopMemberAddressMng.findById(id);

        model.addAttribute("bean", bean);

        List plist = this.addressMng.getListById(null);

        model.addAttribute("plist", plist);

        List clist = this.addressMng.getListById(bean.getProvince().getId());

        model.addAttribute("clist", clist);

        List alist = this.addressMng.getListById(bean.getCity().getId());

        model.addAttribute("alist", alist);

        model.addAttribute("id", id);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberAddressEdit", new Object[0]));
    }

    @RequestMapping(value = {"/shopMemberAddress/address_update.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String update(ShopMemberAddress bean, Long provinceId, Long cityId, Long countryId, HttpServletRequest request, ModelMap model) {

        ShopMember member = MemberThread.get();

        List list = this.shopMemberAddressMng.getList(member.getId());

        ShopMemberAddress entity = null;

        if (bean.getIsDefault()) {

            int i = 0;
            for (int j = list.size(); i < j; i++) {

                entity = (ShopMemberAddress) list.get(i);

                entity.setIsDefault(false);

                this.shopMemberAddressMng.updateByUpdater(entity);
            }
        }

        this.shopMemberAddressMng.updateByUpdater(bean);

        log.info("ShopMemberAddress update success, id= {}", bean.getId());

        return "redirect:address_list.jspx";
    }

    @RequestMapping(value = {"/shopMemberAddress/address_default.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String isDefault(Long id, String returnUrl, HttpServletRequest request, ModelMap model) {

        ShopMember member = MemberThread.get();

        List list = this.shopMemberAddressMng.getList(member.getId());

        ShopMemberAddress bean = this.shopMemberAddressMng.findById(id);

        ShopMemberAddress entity = null;

        int i = 0;
        for (int j = list.size(); i < j; i++) {

            entity = (ShopMemberAddress) list.get(i);

            entity.setIsDefault(false);

            this.shopMemberAddressMng.updateByUpdater(entity);
        }

        bean.setIsDefault(true);

        this.shopMemberAddressMng.updateByUpdater(bean);

        log.info("ShopMemberAddress default success, id= {}", bean.getId());

        if (returnUrl != null) {

            return "redirect:" + returnUrl;
        }

        return "redirect:address_list.jspx";
    }

    @RequestMapping(value = {"/shopMemberAddress/address_delete.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String delete(Long id, String returnUrl, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        WebErrors errors = validateDelete(id, member.getId(), request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        if (this.orderMng.getlistByforaddressId(id).size() > 0) {

            return FrontUtils.showMessage(request, model, "地址关联了订单");
        }

        this.shopMemberAddressMng.deleteById(id, member.getId());

        log.info("ShopMemberAddress delete success, id= {}", id);

        if (returnUrl != null) {

            return "redirect:" + returnUrl;
        }

        return "redirect:address_list.jspx";
    }

    @RequestMapping({"/shopMemberAddress/findAllCity.jspx"})
    public void findAllCity(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List clist = this.addressMng.getListById(id);

        Long[] ids = new Long[clist.size()];

        String[] citys = new String[clist.size()];

        int i = 0;
        for (int j = clist.size(); i < j; i++) {

            Address city = (Address) clist.get(i);

            ids[i] = city.getId();

            citys[i] = city.getName();
        }

        JSONObject json = new JSONObject();
        try {

            json.put("ids", ids);

            json.put("citys", citys);

            json.put("success", true);
        } catch (JSONException e) {
            try {

                json.put("success", false);
            } catch (JSONException e1) {

                e1.printStackTrace();
            }

            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/shopMemberAddress/findAllCountry.jspx"})
    public void findAllArea(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List alist = this.addressMng.getListById(id);

        Long[] ids = new Long[alist.size()];

        String[] areas = new String[alist.size()];

        int i = 0;
        for (int j = alist.size(); i < j; i++) {

            Address area = (Address) alist.get(i);

            ids[i] = area.getId();

            areas[i] = area.getName();
        }

        JSONObject json = new JSONObject();
        try {

            json.put("ids", ids);

            json.put("areas", areas);

            json.put("success", true);
        } catch (JSONException e) {
            try {

                json.put("success", false);
            } catch (JSONException e1) {

                e1.printStackTrace();
            }

            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, json.toString());
    }

    private WebErrors validateEdit(Long addressId, Long memberId, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (vldAddress(addressId, memberId, errors)) {

            return errors;
        }

        return errors;
    }

    private WebErrors validateDelete(Long addressId, Long memberId, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (vldAddress(addressId, memberId, errors)) {

            return errors;
        }

        return errors;
    }

    private boolean vldAddress(Long addressId, Long memberId, WebErrors errors) {

        if (errors.ifNull(addressId, "id")) {

            return true;
        }

        ShopMemberAddress address = this.shopMemberAddressMng.findById(addressId);

        if (errors.ifNotExist(address, ShopMemberAddress.class, addressId)) {

            return true;
        }

        if (!memberId.equals(address.getMember().getId())) {

            errors.noPermission(ShopMemberAddress.class, addressId);
        }

        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ShopMenberAddressAct
 * JD-Core Version:    0.6.2
 */