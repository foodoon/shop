 package guda.shop.cms.action.admin;

 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 public class FrameAct
 {
   @RequestMapping({"/frame/config_main.do"})
   public String configMain(ModelMap model)
   {
/*  14 */     return "frame/config_main";
   }

   @RequestMapping({"/frame/config_left.do"})
   public String configLeft(ModelMap model) {
/*  19 */     return "frame/config_left";
   }

   @RequestMapping({"/frame/config_right.do"})
   public String configRight(ModelMap model) {
/*  24 */     return "frame/config_right";
   }

   @RequestMapping({"/frame/category_main.do"})
   public String categoryMain(ModelMap model) {
/*  29 */     return "frame/category_main";
   }

   @RequestMapping({"/frame/channel_main.do"})
   public String channelMain(ModelMap model) {
/*  34 */     return "frame/channel_main";
   }

   @RequestMapping({"/frame/article_main.do"})
   public String articleMain(ModelMap model) {
/*  39 */     return "frame/article_main";
   }

   @RequestMapping({"/frame/product_main.do"})
   public String productMain(ModelMap model) {
/*  44 */     return "frame/product_main";
   }

   @RequestMapping({"/frame/template_main.do"})
   public String templateMain(ModelMap model) {
/*  49 */     return "frame/template_main";
   }

   @RequestMapping({"/frame/resource_main.do"})
   public String resourceMain(ModelMap model) {
/*  54 */     return "frame/resource_main";
   }

   @RequestMapping({"/frame/member_main.do"})
   public String memberMain(ModelMap model) {
/*  59 */     return "frame/member_main";
   }

   @RequestMapping({"/frame/member_left.do"})
   public String memberLeft(ModelMap model) {
/*  64 */     return "frame/member_left";
   }

   @RequestMapping({"/frame/member_right.do"})
   public String memberRight(ModelMap model) {
/*  69 */     return "frame/member_right";
   }

   @RequestMapping({"/frame/order_main.do"})
   public String orderMain(ModelMap model) {
/*  74 */     return "frame/order_main";
   }

   @RequestMapping({"/frame/order_left.do"})
   public String orderLeft(ModelMap model) {
/*  79 */     return "frame/order_left";
   }

   @RequestMapping({"/frame/order_right.do"})
   public String orderRight(ModelMap model) {
/*  84 */     return "frame/order_right";
   }

   @RequestMapping({"/frame/assistant_main.do"})
   public String pageMain(ModelMap model) {
/*  89 */     return "frame/assistant_main";
   }

   @RequestMapping({"/frame/assistant_left.do"})
   public String pageLeft(ModelMap model) {
/*  94 */     return "frame/assistant_left";
   }

   @RequestMapping({"/frame/assistant_right.do"})
   public String pageRight(ModelMap model) {
/*  99 */     return "frame/assistant_right";
   }

   @RequestMapping({"/frame/marketing_main.do"})
   public String marketingMain(ModelMap model) {
/* 104 */     return "frame/marketing_main";
   }

   @RequestMapping({"/frame/marketing_right.do"})
   public String marketingRight(ModelMap model) {
/* 109 */     return "frame/marketing_right";
   }

   @RequestMapping({"/frame/marketing_left.do"})
   public String marketingLeft(ModelMap model) {
/* 114 */     return "frame/marketing_left";
   }
 }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.FrameAct
 * JD-Core Version:    0.6.2
 */