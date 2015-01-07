/*     */ package guda.shop.manager.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.ResponseHandler;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ 
/*     */ class UpdateMngImpl$CharsetHandler
/*     */   implements ResponseHandler<String>
/*     */ {
/*     */   private String charset;
/*     */ 
/*     */   public UpdateMngImpl$CharsetHandler(UpdateMngImpl paramUpdateMngImpl, String charset)
/*     */   {
/* 343 */     this.charset = charset;
/*     */   }
/*     */ 
/*     */   public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
/*     */   {
/* 348 */     StatusLine statusLine = response.getStatusLine();
/* 349 */     if (statusLine.getStatusCode() >= 300) {
/* 350 */       return null;
/*     */     }
/* 352 */     HttpEntity entity = response.getEntity();
/* 353 */     if (entity != null) {
/* 354 */       if (!StringUtils.isBlank(this.charset)) {
/* 355 */         return EntityUtils.toString(entity, this.charset);
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