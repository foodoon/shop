package guda.shop.cms.action.front;

import guda.shop.cms.entity.Gift;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopMemberAddress;
import guda.shop.cms.manager.AddressMng;
import guda.shop.cms.manager.GiftExchangeMng;
import guda.shop.cms.manager.GiftMng;
import guda.shop.cms.manager.ShopMemberAddressMng;
import guda.shop.cms.web.FrontUtils;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import guda.shop.core.web.front.FrontHelper;
import guda.shop.core.web.front.URLHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class GiftAct {

    @Autowired
    private GiftMng manager;

    @Autowired
    private ShopMemberAddressMng shopMemberAddressMng;

    @Autowired
    private AddressMng addressMng;

    @Autowired
    private GiftExchangeMng giftExchangeMng;

    @RequestMapping(value = {"/gift*.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        int pageNo = URLHelper.getPageNo(request);

        ShopFrontHelper.setCommonData(request, model, web, pageNo);

        ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request), "gift", ".jspx", pageNo);

        return web.getTplSys("gift", MessageResolver.getMessage(request, "tpl.gift", new Object[0]));
    }

    @RequestMapping(value = {"/present.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String present(Long id, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if ((id != null) && (this.manager.findById(id) != null))
            model.addAttribute("gift", this.manager.findById(id));
        else {

            return FrontHelper.pageNotFound(web, model, request);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("gift", MessageResolver.getMessage(request, "tpl.present", new Object[0]));
    }

    @RequestMapping({"/fetchGift.jspx"})
    public void fetchGift(Long giftId, Integer giftNumb, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        } else {

            Gift gift = this.manager.findById(giftId);

            if (giftNumb.intValue() > gift.getGiftStock().intValue()) {

                json.put("status", 2);

                json.put("error", "库存不足");

            }
            if (gift.getGiftScore().intValue() * Long.parseLong(giftNumb.toString()) > member.getScore().intValue()) {

                json.put("status", 2);

                json.put("error", "积分不足");
            } else {

                json.put("status", 1);
            }
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/exchange.jspx"})
    public String shippingInput(Long id, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:login.jspx";
        }

        WebErrors errors = validateGiftView(id, request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        Gift gift = this.manager.findById(id);

        if (count.intValue() > gift.getGiftStock().intValue()) {

            return FrontUtils.showMessage(request, model, "库存不足");
        }

        if (gift.getGiftScore().intValue() * Long.parseLong(count.toString()) > member.getScore().intValue()) {

            return FrontUtils.showMessage(request, model, "积分不足");
        }

        model.addAttribute("gift", gift);

        model.addAttribute("count", count);

        model.addAttribute("totalScore", Long.valueOf(gift.getGiftScore().intValue() * Long.parseLong(count.toString())));


        List smalist = this.shopMemberAddressMng.getList(member.getId());

        model.addAttribute("smalist", smalist);


        List plist = this.addressMng.getListById(null);

        model.addAttribute("plist", plist);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("gift",
                MessageResolver.getMessage(request,
                        "tpl.exchange", new Object[0])
        );
    }

    @RequestMapping(value = {"/create_exchange.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String createExchange(Long deliveryInfo, Long id, Integer count, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:login.jspx";
        }

        WebErrors errors = validateGiftView(id, request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        Gift gift = this.manager.findById(id);

        if (count.intValue() > gift.getGiftStock().intValue()) {

            return FrontUtils.showMessage(request, model, "库存不足");
        }

        if (gift.getGiftScore().intValue() * Long.parseLong(count.toString()) > member.getScore().intValue()) {

            return FrontUtils.showMessage(request, model, "积分不足");
        }

        ShopMemberAddress shopMemberAddress = this.shopMemberAddressMng.findById(deliveryInfo);

        this.giftExchangeMng.save(gift, shopMemberAddress, member, count);

        return FrontUtils.showMessage(request, model, "兑换成功");
    }

    private WebErrors validateGiftView(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (errors.ifNull(id, "id")) {

            return errors;
        }

        Gift gift = this.manager.findById(id);

        if (errors.ifNotExist(gift, Gift.class, id)) {

            return errors;
        }

        return errors;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.GiftAct
 * JD-Core Version:    0.6.2
 */