package guda.shop.cms.action.front;

import guda.shop.cms.entity.*;
import guda.shop.cms.manager.*;
import guda.shop.cms.service.ShoppingSvc;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;
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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CartAct {
    private static final Logger log = LoggerFactory.getLogger(CartAct.class);
    private static final String SHOPPING_CART = "tpl.shoppingCart";
    private static final String CHECKOUT_SHIPPING = "tpl.checkoutShipping";

    @Autowired
    private OrderMng orderMng;

    @Autowired
    private ShoppingSvc shoppingSvc;

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ProductFashionMng productFashionMng;

    @Autowired
    private CartItemMng cartItemMng;

    @Autowired
    private CartMng cartMng;

    @Autowired
    private AddressMng addressMng;

    @Autowired
    private PaymentMng paymentMng;

    @Autowired
    private ShippingMng shippingMng;

    @Autowired
    private ShopMemberAddressMng shopMemberAddressMng;

    @Autowired
    private MemberCouponMng memberCouponMng;

    @Autowired
    private PopularityGroupMng popularityGroupMng;

    @Autowired
    private PopularityItemMng popularityItemMng;

    @RequestMapping({"/cart/shopping_cart.jspx"})
    public String shoppingCart(String backUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Website web = SiteUtils.getWeb(request);

        Cart cart = this.shoppingSvc.getCart(member, request, response);

        List popularityItems = null;

        if (cart != null) {

            popularityItems = this.popularityItemMng.getlist(cart.getId(), null);
        }

        model.addAttribute("cart", cart);

        if (!StringUtils.isBlank(backUrl)) {

            model.addAttribute("backUrl", backUrl);
        }

        model.addAttribute("popularityItems", popularityItems);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.shoppingCart", new Object[0]));
    }

    @RequestMapping({"/cart/add_orderItem.jspx"})
    public void addToCart(Long productId, Integer productAmount, Long fashId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        Website web = SiteUtils.getWeb(request);


        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        } else {

            Product product = this.productMng.findById(productId);

            if (fashId == null) {

                if (productAmount.intValue() > product.getStockCount().intValue()) {

                    json.put("status", 2);

                    json.put("error", "库存不足");
                } else {

                    Cart cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);

                    json.put("status", 1);

                    json.put("count", cart.getTotalItems());
                }
            } else {

                ProductFashion productFashion = this.productFashionMng.findById(fashId);

                if (productAmount.intValue() > productFashion.getStockCount().intValue()) {

                    json.put("status", 2);

                    json.put("error", productFashion.getAttitude() + "库存不足");
                } else {

                    Cart cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);

                    json.put("status", 1);

                    json.put("count", cart.getTotalItems());
                }
            }
        }

        log.info("add to cart productId={}, count={}", productId, productAmount);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/cart/add_popularity.jspx"})
    public void addToPopularity(Long popularityId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        Website web = SiteUtils.getWeb(request);


        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        } else if (getinventory(popularityId)) {

            Cart cart = null;

            for (Product product : this.popularityGroupMng.findById(popularityId).getProducts()) {

                cart = this.shoppingSvc.collectAddToCart(product, null, popularityId, 1, true, member, web, request, response);
            }

            this.popularityItemMng.save(cart, popularityId);

            json.put("status", 1);

            json.put("count", cart.getTotalItems());
        } else {

            json.put("status", 2);

            json.put("error", "库存不足");
        }


        ResponseUtils.renderJson(response, json.toString());
    }

    public boolean getinventory(Long popularityId) {

        for (Product product : this.popularityGroupMng.findById(popularityId).getProducts()) {

            if (1 > product.getStockCount().intValue()) {

                return false;
            }
        }

        return true;
    }

    @RequestMapping({"/cart/add_orderItem1.jspx"})
    public String orderAddToCart(Long orderId, Boolean isAdd, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        Website web = SiteUtils.getWeb(request);


        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Order order = this.orderMng.findById(orderId);

        Product product = null;

        Integer productAmount = Integer.valueOf(0);

        Long fashId = null;

        Cart cart = null;

        for (OrderItem item : order.getItems()) {

            product = item.getProduct();

            productAmount = item.getCount();

            if (item.getProductFash() != null) {

                fashId = item.getProductFash().getId();
            }

            cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);
        }

        return "redirect:shopping_cart.jspx";
    }

    @RequestMapping({"/cart/ajaxUpdateCartItem.jspx"})
    public void ajaxUpdateCartItem(Long cartItemId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        }

        CartItem cartItem = this.cartItemMng.findById(cartItemId);

        cartItem.setCount(count);

        cartItem.setScore(Integer.valueOf(cartItem.getProduct().getScore().intValue() * count.intValue()));

        this.cartItemMng.updateByUpdater(cartItem);

        log.info("update to cartItem cartItemId={}", cartItemId);

        json.put("status", 1);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/cart/ajaxDeleteCartItem.jspx"})
    public void ajaxDeleteCartItem(Long cartItemId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        }

        CartItem cartItem = this.cartItemMng.findById(cartItemId);

        Cart cart = cartItem.getCart();

        update(cart, cartItem);

        cart.getItems().remove(cartItem);

        this.cartMng.update(cart);


        log.info("delete to cartItem cartItemId={}", cartItemId);

        json.put("status", 1);

        ResponseUtils.renderJson(response, json.toString());
    }

    public void update(Cart cart, CartItem cartItem) {

        if (cartItem.getPopularityGroup() != null) {

            PopularityItem popularityItem = this.popularityItemMng.findById(cart.getId(), cartItem.getPopularityGroup().getId());

            if (popularityItem != null) {

                this.popularityItemMng.deleteById(popularityItem.getId());
            }

            if ((cart != null) && (cartItem.getPopularityGroup() != null)) {

                List<CartItem> list = this.cartItemMng.getlist(cart.getId(), cartItem.getPopularityGroup().getId());

                for (CartItem item : list) {

                    item.setPopularityGroup(null);

                    this.cartItemMng.updateByUpdater(item);
                }
            }
        }
    }

    @RequestMapping({"/cart/checkStockCount.jspx"})
    public void checkStockCount(Long productId, String productFashionId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        } else {

            Product product = this.productMng.findById(productId);

            if (productFashionId.equals("undefined")) {

                if (count.intValue() > product.getStockCount().intValue()) {

                    json.put("status", 2);

                    json.put("error", product.getName() + "该商品库存不足。");
                } else {

                    json.put("status", 1);
                }
            } else {

                ProductFashion productFashion = this.productFashionMng.findById(Long.valueOf(Long.parseLong(productFashionId)));

                if (count.intValue() > productFashion.getStockCount().intValue()) {

                    json.put("error", product.getName() + productFashion.getAttitude() + "该款式库存不足。");

                    json.put("status", 2);
                } else {

                    json.put("status", 1);
                }
            }
        }

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/cart/ajaxtotalDeliveryFee.jspx"})
    public void ajaxtotalDeliveryFee(Long deliveryMethod, Double weight, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        ShopMember member = MemberThread.get();

        JSONObject json = new JSONObject();

        if (member == null) {

            json.put("status", 0);
        }

        Shipping shipping = this.shippingMng.findById(deliveryMethod);


        Double freight = shipping.calPrice(weight);

        json.put("status", 1);

        json.put("freight", freight);

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/cart/checkout_shipping.jspx"})
    public String shippingInput(Long[] cart2Checkbox, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopMember member = MemberThread.get();

        if (member == null) {

            return "redirect:../login.jspx";
        }

        Cart cart = this.shoppingSvc.getCart(member.getId());

        if (cart == null) {

            return "redirect:shopping_cart.jspx";
        }

        List<PopularityItem> popularityItems = null;

        Double popularityPrice = Double.valueOf(0.0D);

        if (cart != null) {

            popularityItems = this.popularityItemMng.getlist(cart.getId(), null);

            for (PopularityItem popularityItem : popularityItems) {

                popularityPrice = Double.valueOf(popularityPrice.doubleValue() + popularityItem.getPopularityGroup().getPrice().doubleValue() * popularityItem.getCount().intValue());
            }
        }


        Set items = getItems(cart2Checkbox, cart);

        Double price = getPrice(items);


        List splist = this.shippingMng.getList(web.getId(), true);


        List smalist = this.shopMemberAddressMng.getList(member.getId());


        List plist = this.addressMng.getListById(null);


        List paylist = this.paymentMng.getList(Long.valueOf(1L), true);

        model.addAttribute("memberCouponlist", this.memberCouponMng.getList(member.getId(), new BigDecimal(price.doubleValue())));

        model.addAttribute("items", getItems(cart2Checkbox, cart));

        model.addAttribute("smalist", smalist);

        model.addAttribute("plist", plist);

        model.addAttribute("paylist", paylist);

        model.addAttribute("splist", splist);

        model.addAttribute("popularityPrice", popularityPrice);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.checkoutShipping", new Object[0]));
    }

    public Set<CartItem> getItems(Long[] cart2Checkbox, Cart cart) {

        Set items = new HashSet();

        if (cart2Checkbox != null) {

            for (Long id : cart2Checkbox)

                items.add(this.cartItemMng.findById(id));
        } else {

            items = cart.getItems();
        }

        return items;
    }

    public Double getPrice(Set<CartItem> items) {

        Double price = Double.valueOf(0.0D);

        for (CartItem item : items) {

            if (item.getProductFash() != null)

                price = Double.valueOf(price.doubleValue() + item.getProductFash().getSalePrice().doubleValue() * item.getCount().intValue());
            else {

                price = Double.valueOf(price.doubleValue() + item.getProduct().getSalePrice().doubleValue() * item.getCount().intValue());
            }
        }

        return price;
    }
}

