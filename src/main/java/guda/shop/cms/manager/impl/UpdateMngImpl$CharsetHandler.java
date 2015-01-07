 package guda.shop.cms.manager.impl;
/*     */*/ import jException;
/*     */ import org.apachelang.StringUtils;
/*     */ import org.apache.http.Htt/*     */ import org.apache.http.HttpResponse*/ import org.apache.http.StatusLine;
/*     */rg.apache.http.client.ClientProtocolException*/ import org.apache.http.client.ResponseHandler;
/*     */ imporche.http.util.EntityUtils;
/*     */ 
/*     */ class Updl$CharsetHandler
/*     */   implements ResponseHanng>
/*        */   private String charset;
/*     */
/* public UpdateMngImpl$CharsetHandler(UpdateMngIUpdateMngImp charset)
/*     */   {
/* 343 */   arset = cha    */   }
/*     */
/*     */   public String handleResponse(HttpResponse response) throws ClienException, IOException
/*     */   {
/* 348 */     S statusLine = getStatusLi349 */     if (statusLine.getStatusCode() >= 300) {
/* 350 */       return null;
/*     */     }
/* 352 */  tity entity = response.getEntity();
/* 353 */     if (entity != null) {
/* 354 */       if (!StringUtils.isBlank(this.charset)) {
/* 355 */         return EntityUring(entity, this.charset);
/*     */       }
/* 357 */       return EntityUtils.toString(entity);
/*     */     }
/*     */ 
/* 360 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.UpdateMngImpl.CharsetHandler
 * JD-Core Version:    0.6.2
 */