package guda.shop.cms.action.front;

import guda.shop.cms.entity.*;
import guda.shop.cms.manager.*;
import guda.shop.cms.service.ShoppingSvc;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.security.annotation.Secured;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import guda.shop.core.web.front.FrontHelper;
import guda.shop.core.web.front.URLHelper;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Secured
@Controller
public class OrderAct {
    private static final String MY_ORDER = "tpl.myOrder";
    private static final String MY_RETURN_ORDER = "tpl.myReturnOrder";
    private static final String MY_ORDER_VIEW = "tpl.myOrderView";
    private static final String SUCCESSFULLY_ORDER = "tpl.successfullyOrder";

    @Autowired
    private OrderMng manager;

    @Autowired
    private ShippingMng shippingMng;

    @Autowired
    private PaymentMng paymentMng;

    @Autowired
    private ShoppingSvc shoppingSvc;

    @Autowired
    private PaymentPluginsMng paymentPluginsMng;

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ProductFashionMng productFashionMng;

    @Autowired
    private ShopMemberMng shopMemberMng;

    @Autowired
    private ShopScoreMng shopScoreMng;

    @Autowired
    private OrderReturnMng orderReturnMng;

    @RequestMapping({"/order/myorder*.htm"})
    public String myOrder(Integer status, String code, String userName, Long paymentId, Long shippingId, String startTime, String endTime, Double startOrderTotal, Double endOrderTotal, HttpServletRequest request, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        ShopMember member = MemberThread.get();
        if (member == null) {
            return "redirect:../login.htm";
        }
        if (StringUtils.isBlank(userName)) {
            userName = null;
        }
        if (StringUtils.isBlank(startTime)) {
            startTime = null;
        }
        if (StringUtils.isBlank(endTime)) {
            endTime = null;
        }
        List shippingList = this.shippingMng.getList(web.getId(), true);
        List paymentList = this.paymentMng.getList(web.getId(), true);
        model.addAttribute("historyProductIds", getHistoryProductIds(request));
        model.addAttribute("paymentList", paymentList);
        model.addAttribute("shippingList", shippingList);
        model.addAttribute("status", status);
        model.addAttribute("code", code);
        model.addAttribute("userName", userName);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("shippingId", shippingId);
        model.addAttribute("startOrderTotal", startOrderTotal);
        model.addAttribute("endOrderTotal", endOrderTotal);
        Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
        ShopFrontHelper.setCommonData(request, model, web, 1);
        ShopFrontHelper.setDynamicPageData(request, model, web, "", "myorder", ".htm", pageNo.intValue());
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myOrder", new Object[0]));
    }

    @RequestMapping({"/order/myOrderView.htm"})
    public String myOrderView(Long orderId, HttpServletRequest request, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        ShopMember member = MemberThread.get();
        if (member == null) {
            return "redirect:../login.htm";
        }
        WebErrors errors = validateOrderView(orderId, member, request);
        if (errors.hasErrors()) {
            return FrontHelper.showError(errors, web, model, request);
        }
        Order order = this.manager.findById(orderId);
        model.addAttribute("order", order);
        ShopFrontHelper.setCommonData(request, model, web, 1);
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myOrderView", new Object[0]));
    }

    @RequestMapping(value = {"/order/order_shipping.htm"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String orderShipping(Long deliveryInfo, Long shippingMethodId, Long paymentMethodId, Long[] cartItemId, String comments, String memberCouponId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        ShopMember member = MemberThread.get();
        if (member == null) {
            return "redirect:../login.htm";
        }
        Order order = null;
        Cart cart = this.shoppingSvc.getCart(member.getId());
        if (cart != null) {
            order = this.manager.createOrder(cart, cartItemId, shippingMethodId, deliveryInfo, paymentMethodId, comments, request.getRemoteAddr(), member, web.getId(), memberCouponId);
        }
        this.shoppingSvc.clearCookie(request, response);
        List list = this.paymentPluginsMng.getList();
        model.addAttribute("list", list);
        model.addAttribute("order", order);
        ShopFrontHelper.setCommonData(request, model, web, 1);
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.successfullyOrder", new Object[0]));
    }

    @RequestMapping({"/order/deleteOrder.htm"})
    public void deleteOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
            throws JSONException {
        JSONObject json = new JSONObject();
        if (orderId != null) {
            Order order = this.manager.findById(orderId);
            order.getItems().clear();
            this.manager.deleteById(orderId);
        }
        json.put("success", true);
        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/order/abolishOrder.htm"})
    public void abolishOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
            throws JSONException {
        JSONObject json = new JSONObject();
        ShopMember member = MemberThread.get();
        if (orderId != null) {
            Order order = this.manager.findById(orderId);
            order.setStatus(Integer.valueOf(3));
            Set<OrderItem> set = order.getItems();
            Product product;
            for (OrderItem item : set) {
                product = item.getProduct();
                if (item.getProductFash() != null) {
                    ProductFashion fashion = item.getProductFash();
                    fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));
                    product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
                    this.productFashionMng.update(fashion);
                } else {
                    product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
                }
                this.productMng.updateByUpdater(product);
            }
            member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));
            this.shopMemberMng.update(member);

            List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(order.getCode()));

            for (ShopScore s : list) {

                this.shopScoreMng.deleteById(s.getId());
            }

            this.manager.updateByUpdater(order);
        }

        json.put("success", true);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/order/accomplishOrder.htm"})
    public void accomplishOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
            throws JSONException {

        JSONObject json = new JSONObject();

        ShopMember member = MemberThread.get();

        if (orderId != null) {

            Order order = this.manager.findById(orderId);

            order.setStatus(Integer.valueOf(4));


            member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));

            member.setScore(Integer.valueOf(member.getScore().intValue() + order.getScore().intValue()));

            this.shopMemberMng.update(member);

            List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(order.getCode()));

            for (ShopScore s : list) {

                s.setStatus(true);

                this.shopScoreMng.update(s);
            }

            this.manager.updateliRun(orderId);

            this.manager.updateByUpdater(order);
        }

        json.put("success", true);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/order/order_payAgain.htm"})
    public String payOrderAgain(Long orderId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.htm";
        }

        WebErrors errors = validateOrderView(orderId, member, request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        this.shoppingSvc.clearCookie(request, response);

        Order order = this.manager.findById(orderId);

        List list = this.paymentPluginsMng.getList();

        model.addAttribute("list", list);

        model.addAttribute("order", order);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.successfullyOrder", new Object[0]));
    }

    @RequestMapping({"/order/myReturnOrder*.htm"})
    public String myReturnOrder(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.htm";
        }


        Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));

        Pagination pagination = this.manager.getPageForOrderReturn(member.getId(), pageNo.intValue(), 10);

        model.addAttribute("pagination", pagination);

        model.addAttribute("historyProductIds", getHistoryProductIds(request));

        ShopFrontHelper.setCommonData(request, model, web, 1);

        ShopFrontHelper.setDynamicPageData(request, model, web, "", "myReturnOrder", ".htm", pageNo.intValue());

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myReturnOrder", new Object[0]));
    }

    @RequestMapping({"/order/shipments.htm"})
    public String shipments(Long id, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.htm";
        }

        WebErrors errors = validateOrderReturnView(id, member, request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        OrderReturn entity = this.orderReturnMng.findById(id);

        entity.setStatus(Integer.valueOf(4));

        this.orderReturnMng.update(entity);

        return myReturnOrder(request, model);
    }

    @RequestMapping({"/order/accomplish.htm"})
    public String accomplish(Long id, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.htm";
        }

        WebErrors errors = validateOrderReturnView(id, member, request);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        OrderReturn entity = this.orderReturnMng.findById(id);

        entity.setStatus(Integer.valueOf(7));

        Set<OrderItem> set = entity.getOrder().getItems();
        Product product;

        for (OrderItem item : set) {

            product = item.getProduct();

            if (item.getProductFash() != null) {

                ProductFashion fashion = item.getProductFash();

                fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));

                product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));

                this.productFashionMng.update(fashion);
            } else {

                product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
            }

            this.productMng.updateByUpdater(product);
        }


        member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - entity.getOrder().getScore().intValue()));

        this.shopMemberMng.update(member);

        List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(entity.getOrder().getCode()));

        for (ShopScore s : list) {

            this.shopScoreMng.deleteById(s.getId());
        }

        this.orderReturnMng.update(entity);

        return myReturnOrder(request, model);
    }

    private WebErrors validateOrderView(Long orderId, ShopMember member, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (errors.ifNull(orderId, "orderId")) {

            return errors;
        }

        Order order = this.manager.findById(orderId);

        if (errors.ifNotExist(order, Order.class, orderId)) {

            return errors;
        }

        if (!order.getMember().getId().equals(member.getId())) {

            errors.noPermission(Order.class, orderId);

            return errors;
        }

        return errors;
    }

    public String getHistoryProductIds(HttpServletRequest request) {

        String str = null;

        Cookie[] cookie = request.getCookies();

        int num = cookie.length;

        for (int i = 0; i < num; i++) {

            if (cookie[i].getName().equals("shop_record")) {

                str = cookie[i].getValue();

                break;
            }
        }

        return str;
    }

    private WebErrors validateOrderReturnView(Long orderReturnId, ShopMember member, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (errors.ifNull(orderReturnId, "orderReturnId")) {

            return errors;
        }

        OrderReturn orderReturn = this.orderReturnMng.findById(orderReturnId);

        if (errors.ifNotExist(orderReturn, OrderReturn.class, orderReturnId)) {

            return errors;
        }

        if (!orderReturn.getOrder().getMember().getId().equals(member.getId())) {

            errors.noPermission(OrderReturn.class, orderReturnId);

            return errors;
        }

        return errors;
    }
}

