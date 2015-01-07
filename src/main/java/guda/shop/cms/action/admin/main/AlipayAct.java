 package guda.shop.cms.action.admin.main;

 import guda.shop.cms.Alipay;
 import guda.shop.cms.entity.Logistics;
 import guda.shop.cms.entity.Order;
 import guda.shop.cms.entity.Payment;
 import guda.shop.cms.entity.PaymentPlugins;
 import guda.shop.cms.entity.Shipments;
 import guda.shop.cms.entity.Shipping;
 import guda.shop.cms.entity.ShopAdmin;
 import guda.shop.cms.manager.OrderMng;
 import guda.shop.cms.manager.PaymentPluginsMng;
 import guda.shop.cms.manager.ShipmentsMng;
 import guda.shop.cms.web.threadvariable.AdminThread;
 import java.util.HashMap;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 public class AlipayAct extends Alipay
 {

   @Autowired
   private OrderMng manager;

   @Autowired
   private PaymentPluginsMng paymentPluginsMng;

   @Autowired
   private ShipmentsMng shipmentMng;

   @RequestMapping({"/order/o_shipments.do"})
   public String shipments(Shipments bean, Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
   {
/*  35 */     Order order = this.manager.findById(id);
/*  36 */     ShopAdmin admin = AdminThread.get();
/*  37 */     bean.setShopAdmin(admin);
/*  38 */     bean.setIndent(order);
/*  39 */     if (order.getPayment().getType().byteValue() == 1) {
/*  40 */       if ((order.getShippingStatus().intValue() == 1) && (order.getStatus().intValue() == 2) && (order.getPaymentStatus().intValue() == 2)) {
/*  41 */         shipments(bean, order, id, response);
       }
     }
/*  44 */     else if ((order.getShippingStatus().intValue() == 1) && (order.getStatus().intValue() == 2)) {
/*  45 */       shipments(bean, order, id, response);
     }

/*  48 */     model.addAttribute("order", order);
/*  49 */     return "order/view";
   }

   public void shipments(Shipments bean, Order order, Long id, HttpServletResponse response) {
/*  53 */     Shipments shipments = this.shipmentMng.save(bean);
/*  54 */     order.setShippingStatus(Integer.valueOf(2));
/*  55 */     this.manager.updateByUpdater(order);
/*  56 */     this.manager.updateSaleCount(id);
/*  57 */     if (order.getPaymentCode() != null) {
/*  58 */       PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode(order.getPaymentCode());
/*  59 */       if ((!StringUtils.isBlank(order.getPaymentCode())) && (order.getPaymentCode().equals("alipayPartner")))
         try {
/*  61 */           alipay(paymentPlugins, order, shipments.getWaybill());
         } catch (Exception e) {
/*  63 */           e.printStackTrace();
         }
     }
   }

   private String alipay(PaymentPlugins paymentPlugins, Order order, String waybill)
     throws Exception
   {
/*  76 */     String trade_no = order.getTradeNo();

/*  80 */     String logistics_name = order.getShipping().getLogistics().getName();

/*  84 */     String invoice_no = waybill;

/*  86 */     String transport_type = order.getShipping().getLogisticsType();

/*  90 */     Map sParaTemp = new HashMap();
/*  91 */     sParaTemp.put("service", "send_goods_confirm_by_platform");
/*  92 */     sParaTemp.put("partner", paymentPlugins.getPartner());
/*  93 */     sParaTemp.put("_input_charset", "utf-8");
/*  94 */     sParaTemp.put("trade_no", trade_no);
/*  95 */     sParaTemp.put("logistics_name", logistics_name);
/*  96 */     sParaTemp.put("invoice_no", invoice_no);
/*  97 */     sParaTemp.put("transport_type", transport_type);

/* 100 */     String sHtmlText = buildRequest("", "", sParaTemp, paymentPlugins.getSellerKey());
/* 101 */     return sHtmlText;
   }
 }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AlipayAct
 * JD-Core Version:    0.6.2
 */