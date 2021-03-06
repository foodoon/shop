package guda.shop.cms.action.front;

import guda.shop.cms.entity.Coupon;
import guda.shop.cms.entity.MemberCoupon;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.CouponMng;
import guda.shop.cms.manager.MemberCouponMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.front.URLHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class CouponAct {

    @Autowired
    private CouponMng manager;

    @Autowired
    private MemberCouponMng memberCouponMng;

    @RequestMapping(value = {"/coupon*.htm"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        int pageNo = URLHelper.getPageNo(request);

        ShopFrontHelper.setCommonData(request, model, web, pageNo);

        ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request), "coupon", ".htm", pageNo);

        return web.getTplSys("coupon", MessageResolver.getMessage(request, "tpl.coupon", new Object[0]));
    }

    @RequestMapping({"/getcoupon.htm"})
    public void fetchGift(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        Date newdate = new Date();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);

        } else if ((id == null) || (this.manager.findById(id) == null)) {

            json.put("status", 2);

            json.put("error", "参数有误");

        } else if (this.memberCouponMng.findByCoupon(member.getId(), id) != null) {

            json.put("status", 2);

            json.put("error", "您已领取过该优惠券了");

        } else if (!this.manager.findById(id).getIsusing().booleanValue()) {

            json.put("status", 2);

            json.put("error", "该优惠券已停用");

        } else if (!newdate.before(this.manager.findById(id).getCouponEndTime())) {

            json.put("status", 2);

            json.put("error", "该优惠券已过期");

        } else if (this.manager.findById(id).getCouponCount().intValue() < 1) {

            json.put("status", 2);

            json.put("error", "该优惠券已领完");
        } else {

            Coupon coupon = this.manager.findById(id);

            MemberCoupon memberCoupon = new MemberCoupon();

            memberCoupon.setCoupon(coupon);

            memberCoupon.setMember(member);

            memberCoupon.setIsuse(Boolean.valueOf(false));

            coupon.setCouponCount(Integer.valueOf(coupon.getCouponCount().intValue() - 1));

            this.memberCouponMng.save(memberCoupon);

            this.manager.update(coupon);

            json.put("status", 1);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ResponseUtils.renderJson(response, json.toString());
    }
}

