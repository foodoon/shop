 package guda.shop.cms.lucene;
/*     */*/ import gcms.entity.Product;
/*     */ iimport guda.shopy.ProductInfo;
/*     */ imimport guda.shop.mmon.fi.MoneyTools;
/*     */ impimport guda.shop.mon.page.Pagina    */ import com.jspgou.core.entity.Website;
/*   rt java.io.IOException;
/*     */ import java.utist;
/*     */ import java.util.Collect   */ import java.util.Date;
/*     */ava.util.List;
/*     */ import org.apans.lang.StringUtils;
/*     */ imapache.lucene.analysis.Analyzer;
 import org.apache.lucene.document.DateTools;
/*     *org.apache.lucene.document.DateTools.Resolution;
/*   rt org.apache.lucene.document.Document;
/*     */ imporche.lucene.document.Field;
/*     */ import org.apache.lucene.docud.Index;
/*     */ import org.apache.lucene.document.Fe;
/*     */ import org.apache.lucene.document.Fiel     */ import org.apache.lucene.index.CorruptIndexExcept   */ import org.apache.lucene.index.Term;
/*     */ impoache.lucene.queryParser.MultiFieldQueryParser;
/*     *org.apache.lucene.search.BooleanClause.Occur;
/*     */ import o.lucene.search.BooleanQuery;
/*     */ import o.lucene.search.Query;
/*     */ import org.apache.lucene.search.ScoreD  */ import org.apache.lucene.search.Searcher;
/*     */ importhe.lucene.search.TermQuery;
/*     */ import org.apache.arch.TermRangeQuery;
/*     */ import org.apache.arch.TopDocs;
/*     */ import org.apache.lucene.uti;
/*     */ import org.slf4j.Logger;
/*     */ impor4j.LoggerFactory;
/*     */
/*     */ public class Luct
/*     */   implements ProductInfo
/*     */ {
/*  43 ate static final Logger log = LoggerFactory.getLoggProduct.class);
/*     */   public static final S= "id";
/*     */   public static fng WEBSITE_ID = "websiteId";
/*     */   ptic final SEGORY_ID_ARRAY = "categoryIdArray";
/  public static final String CATEGOTTRY = "categoryNameArray";
/*     */   public static final String BRAND_NAME = "brandName";
/*     */  tatic final String NAME = "name";
/*     */   publ final String DESCRIPTION = "description";
/*     */   public sta String URL = "url";
/*     */   public static final String DETAIL_IMG_URL = "Url";
/*     */   public static final String LIST_IMG_URL = "listImgUrl";
/*     *c static final String COVER_IMG_URL = "coverImgUrl";
/*     */   atic final String MIN_IMG_URL = "minImgUrl";
/*     */ static final String MARKET_PRICE = "marketPrice";
/*     */   publifinal String SALE_PRICE = "salePrice";
/*     */   ptic final String KEYWORD_ARRAY = "keywordArray";
/*     */   public statString TAG_ARRAY = "tagArray";
/*     */   public static final StrinTIME = "createTime";
/* 239 */   public static final String[] QUERY_FIname", "categoryNameArray",
/* 240 */     "brandName", "descripti     */
/* 241 */   public static final BooleanClause.Occur[] QUERY_
/* 242 */     BooleanClause.Occur.SHOULD, BooleanClause.Occur.S* 243 */     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
   private Long id;
/*     */   private Long websiteId;
/*     ate Collection<Long> categoryIdArray;
/*     */   private Collection<String> categoryNameArray;
/*     */   private String brandName;
/*     */   private String name;
/*     */   private String url;/   private String description;
/*     */   private String detailImgUrl;
/*     */   private String listImgUrl;
/*     */   private String minImgUrl;
/*     */   private Double marketPrice;
/*     */   private Double salePric */   private Collection<StrirdArray;
/*     */   private Collectg> tagArray;
/*     */   private Date createTime;
/*  rivate String coverImgUrl;
/*     */
/*     */   public sry createQuery(String queryString, LonLong ctgId, Date start, Date end, analyzer)
/*     */     throws e.lucene.queryParser.ParseException
/*
/*  47 */     BooleanQuery bq = new Bool);
/*     */ 
/*  49 */     if (!Stringlank(queryString)) {
/*  50 */       QMultiFieldQueryParser.parse(Version.LUCEeryString,
         QUERY_FIY_FLAGS, analyzer);
/*  52 */       bq.add(q, Booleancur.MUST);
/*     */     }
/*     */ 
/*  55 */  bId != null) {
/*  56 */       Query ermQuery(new Term("websiteId", webId.toS;
/*  57 */.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*  59 */     if (ctgId != null) {
/*  60 */       Query q = new TermQuery(newtegoryIdArray", ctgId.toString()));
/*  61 */       bq.add(q, Bool.Occur.MUST);
/*     */     }
/*  63 */     if ((start != null) ||null)) {
/*  64 */       String startDate = null;
/*  65 */       String endDate = null;
/*  66 */       if (start != null) {
/*  67 */         startDate = DateTools.dateToString(start, DateTools.Resolution.DAY);
/*     */       }
/*  69 */       if (end != nul70 */         enateTools.dateToString(end, DateTools.Resolution.DAY);
/*     */       }
/*  72 */       Query q = new TermRangeQuery("createTime", startDate, endDate, true, true);
/*  73 */       bBooleanClause.Occur.MUST);
/*     */     }
/*  75 */     return bq;
/*     */   }
/*     */ 
/*     */   public static Pagination getResult(Searcher searcher, TopDocs docs, int pageNo, int pag     */     throws CorruptIndexException, IOException
/*     */   {
/*  81 */     List list = new ArrayList(pageSize);
/*  82 */     ScoreDoc[] hits = docs.scoreDocs;
/*  83 */     int endIndex = pageNo * pageSize;
/*  84 */     int len = hits.length;
/*  85 */     if (endIn) {
/*  86 */       endIndex = len;
/*     */     }
/*  88 */     for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
/*  89 */ ument d = searcher.doc(hits[i].doc);
/*  90 */       list.add(createProduct(d));
/*     */     }
/*  92 */     return new Pagination(pageNo, pageSize, docs.totalHi;
/*     */   }
/*     */ 
/*     */   puic Document crent(Product  */   {
/* 103 */     Document doc = new Document();
/* 104 */     doc.add(new Field("id", p.getId().toStield.Store.YES,
/* 105 */       Field.Index.NO));
/* 10doc.add(new Field("websiteId", p.getWebsite().getId().toString(),
/* 107 */       Field.Store.YES, Field.Index.NOT_ANALYZED));
/* 108 */     for (Long id : p.getCategoryIdArray()) {
/* 109 */       doc.add(new Field("categoryIdArray", id.toString(), 
/* 110 */     .Store.YES, Field.Index.NOT_ANALYZED));
/*     */     }
/*     */ 
/* 113 */     doc.add(new Field("name", p.getName(), Field.Store.YES, 
/* 114 */       Field.Index.ANALYZED));
/* 115 */  tring name : p.getCategoryNameArray()) {
/* 116 */       doc.add(new Field("categoryNameArrayField.Store.YE7 */       ndex.ANALYZED));
/*     */     }
/* 119 */     if (!StringUtik(p.getMdescription())) {
/* 120 */       doc.add(new Field("description", p.getMdescription(), Field.Store.YES,
/* 121 */         Field.Index.ANALYZED));
/*     */     }
/* 123 */     if (!StringUtils.isBlank(p.getBrandName())) {
/* 124 */       doc.add(new Field("brandName", p.getBrandName(), Field.Store.YES,
/* 125 */         Field.Index.ANALYZED));
/*     */     }
/*     */ 
/* 128 */     doc.add(new Field("url", p.getUrl(), Field.Store.YES, Field.Index.NO));
/*     */ 
/* 130 */     if (!ls.isBlank(p.getUrl())) {
/* 131 */       doc.add(new Field("detailImgUrl", p.getDetailImgUrl(), 
/* 132 */         Field.Store.YES, Field.Index.NO));
/*     */     }
/* 134 */     if (!StringUtils.isBlank(p.getListImgUrl())) {
/* 135 */       doc.add(new Field("listImgUrl", p.getListImgUrl(), Field.Store.YES,
/* 136   Field.Index.NO));
/*     */     }
/* 138 */     if (!StringUtils.isBlank(p.getCoverImgUrl())) {
/* 139 */       doc.add(new Field("coverImgUrl", p.getCoverImgUrl(), Field.Store.YES, 
/* 140 */         Field.);
/*     */     }
/* 142 */     if (!StringUtils.isBlank(p.getMinImgUrl())) {
/* 143 */       doc.add(new Field("minImgUrl", p.getMinImgUrl(), Field.Store.YES, 
/* 144 */         Field.Index.NO));
/*   }
/*     */ 
/    doc.add(new Field("marketPrice", MoneyTools.moneyToString(
/* 148 */       p.getMarketPrice()Store.YES, Field.Index.NOT_ANALYZED));
/* 149 */     doc.add(new Field("salePrice",
/* 150 */       MoneyTools.moneyToString(p.getPrice()), Field.Store.YES, 
/* 151 */       Field.Index.NOT_ANALYZED  */
/* 153 */     doc.add(new Field("createTime", DateTools.dateToString(
/* 154 */       p.getCreateTime(), DateTools.Resolution.MILLISECOND), 
/* 155 */       Field.Store.YES, Field.Index.NOT_AN
/*     */ 
/* 157 */     Collection keywords = p.getKeywordArray();
/* 158 */     for (String keyword : keywords) {
/* 159 */       doc.add(new Field("keywordArray", keyword, Field.Store.YES,
/* 160   Field.Index.ANALYZED));
/*     */     }
/* 162 */     Collection tags = p.getTagArray();
/* 163 */     for (String tag : tags) {
/* 164 */       doc.add(new Field("tagArray", tag, Field.Store.Y65 */         Fi.ANALYZED));
/*     */     }
/* 167 */     return doc;
/*     */   }
/*     */ 
/*     */   public static LuceneProduct createProduct(Document d)
/*     */   {
/* 172 */     LuceneProduct p = new LuceneProduct();
/* 173 */     List list = d.getFields();
/*     */ 
/* 175 */     for (Fieldable f : list) {
/* 176 */       String name();
/* 177 */       if (name.equals("keywordArray"))
/* 178 */         p.addToKeyworeds(f.stringValue());
/* 179 */       else if (name.equals("tagArray"))
/* 180 */         p.addToTags(f.stringValue());
/* 181 else if (name.equals("id"))
/* 182 */         p.setId(Long.valueOf(f.stringValue()));
/* 183 */       else if (name.equals("websiteId"))
/* 184 */         p.setWebsiteId(Long.valueOf(f.stringValue()));
/* 185 */       else if (name.eqegoryIdArray"))
/* 186 */         p.addToCategoryIds(Long.valueOf(f.stringValue()));
/* 187 */       else if (name.equals("createTime"))
/*     */         try {
/* 189 */           p.setCreateTime(DateTools.stringToDingValue()));
/*     */         } catch (jParseException191 */     .error("lucene date format faild", e);
/*     */         }
/* 193 else if (name.equals("name"))
/* 194 */         p.setName(f.stringValue());
/* 195 */       else if (name.eqegoryNameArray"))
/* 196 */         p.addToCategoryNames(f.stringValue());
/* 197 */       else if (name.equals("brandName"))
/* 198 */         p.setBrandName(f.stringValue());
/* 199 */       else if (name.equals("description"))
/* 200 */         p.setDescription(f.stringValue());
/* 201 */       else if (name.equals("url"))
/* 202 */         p.setUrl(f.stringValue());
/* 203 */       else if (name.equals("detailImgUrl"))
/* 204 */         p.setDetailImgUrl(f.stringValue());
/* 205 */       else if (name.equals("listImgUrl"))
/* 206 */         p.setListImgUrl(f.stringValue());
/* 207 */       else if (name.equals("coverImgUrl"))
/* 208 */         p.setCoverImgUrl(f.stringValue() */       else if (name.equals("minImgUrl"))
/* 210 */         p.setMinImgUrl(f.stringValue());
/* 211 else if (name.equals("marketPrice"))
/* 212 */         p.setMarketPrice(MoneyTools.stringToMoney(f.stringValue()));
/*     else if (name.equals("salePrice")) {
/* 214 */         p.setSalePrice(MoneyTools.stringToMoney(f.stringValue()));
/*     */       }
/*     */     }
/*     */ 
/* 218 */     return p;
/*     */   }
/*     */ 
/*     */   public void addToKeyworeds(String keyword)
/*     */   {
/* 246 */     if (this.keywordArray == null) {
/* 247 */       this.keywordArray = new ArrayList();
/*     */     }
/* 249 */     this.keywordArray.add(keyword);
/*     */   }
/*     */ 
/*     */   public void addToTags(String tag) {
/* 253 */     if (this.tagArray == null) {
/* 254 */       this.tagArray = new ArrayList();
/*     */     }
/* 256 */     this.tagArray.add(tag);
/*     */   }
/*     */ 
/*     */   public void addToCategoryNames(String categoryName) {
/* 260 */     if (this.categoryNameArray == null) {
/* 261 */       this.categoryNameArray = new ArrayList();
/*     */     }
/* 263 */     this.categoryNameArray.add(categoryName);
/*     */   }
/*     */ 
/*     */   public void addToCategoryIds(Long categoryId) {
/* 267 */     if (this.categoryIdArray == null) {
/* 268 */       this.categoryIdArray = new ArrayList();
/*     */     }
/* 270 */     this.categoryIdArray.add(categoryId);
/*     */   }/
/*     */   pubgetId()
/*     *292 */     return this.id;
/*     *    */
/*    lic void seid) {
/* 296 */     this.id = id;
/*     */   }
/*       */   public String getName() {
/* 300 */     return this.name;
/*     */   }
/*     */
/*     */   public void tring name) {
/* 304 */     this.name = name;
/*     */   }
/*/*     */   pung getDescr{
/* 308 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 312 */    cription = description;
/*     */   }
/*     */ 
/*   blic String gegUrl() {
/*   return this.detailImgUrl;
/*     */   }
/*     */ 
/*     */   public void setDetailImgUrl(String detailImgUrl) {
/* 320 */     this.detailImgUrl = detailImgUrl;
/*     */   */
/*     */   public String getListImgUrl() {
/* 324 */     return thgUrl;
/*     *    */
/* public void setListImgUrl(String listImgUrl) {
/* 328 */     this.listImgUrl = listImgUrl;
/*     */   }
/*     */   public String getCoverImgUrl() {
/* 331 */     re.coverImgUrl;
/*     */   }
/*     */ 
/*     */   public void setCo(String coverI/* 335 */  overImgUrl = coverImgUrl;
/*    *     */
/*     */   public String getMinIm/* 339 */     is.minImgUr */   }
/*     */ 
/*     */   public void setMinImgUrl(String minImg 343 */     thUrl = minIm    */   }
/*     */
/*     */   public Double getMarketPrice() {
/*   return this.ce;
/*          */
/*     */   public void setMarketPrice(Double marketPrice) {
/* 351 */.marketPrice =ice;
/*    *     */
/*     */   public Double getSalePrice() {
/* 355 */     return this.saleP    */   }
/* *     */   id setSalePrice(Double salePrice) {
/* 359 */     this.salePrice = salePrice;
/*     */   }
/*     */ 
/*  ublic String g
/* 363 */ n this.url;
/*     */   }
/*     */
/*     */   public void setUrl(String url) {
/* 3 this.url = ur */   }
/* *     */   public Long getWebsiteId() {
/* 371 */     return this.websiteId;
/*     */   }
/*     */
/*     */ void setWebsi websiteId) */     this.websiteId = websiteId;
/*     */   }
/*     */
/*     */   public DaateTime() {
/*   return teTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 383 */     teTime = creat     */   }
/*     */ 
/*     */   public String getBrandName() {
/* 387 */     returandName;
/*
/*     */ /   public void setBrandName(String brandName) {
/* 391 */     this.brandName = brandName;
/*     */   }
/*/*     */   puection<StriywordArray() {
/* 395 */     return this.keywordArray;
/*     */   }
/*     */ 
   public voidrdArray(Coltring> keywordArray) {
/* 399 */     this.keywordArray = keywordArray;
/*     */   }
/*     */
/* public Collectg> getTagAr* 403 */     return this.tagArray;
/*     */   }
/*     */ 
/*     */   public void ay(Collection<agArray) {
     this.tagArray = tagArray;
/*     */   }
/*     */
/*     */   public Collection<String> getCategoryNa {
/* 411 */   this.categray;
/*     */   }
/*     */ 
/*     */   public void setCategoryNameArray(Colleing> categoryN {
/* 415 *s.categoryNameArray = categoryNameArray;
/*     */   }
/*     */ 
/*     */   public Collection<LonegoryIdArray() */     retcategoryIdArray;
/*     */   }
/*     */ 
/*     */   public void seIdArray(Collec> categoryI
/* 423 */     this.categoryIdArray = categoryIdArray;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneProduct
 * JD-Core Version:    0.6.2
 */