/*     */ package guda.shop.cms.lucene;
/*     */ 
/*     */ import guda.shop.cms.entity.Product;
/*     */ import guda.shop.cms.entity.ProductInfo;
/*     */ import guda.shop.common.file.lucene.MoneyTools;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.core.entity.Website;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.document.DateTools;
/*     */ import org.apache.lucene.document.DateTools.Resolution;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.document.Field.Index;
/*     */ import org.apache.lucene.document.Field.Store;
/*     */ import org.apache.lucene.document.Fieldable;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.queryParser.MultiFieldQueryParser;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.Searcher;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ import org.apache.lucene.search.TermRangeQuery;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.util.Version;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class LuceneProduct
/*     */   implements ProductInfo
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(LuceneProduct.class);
/*     */   public static final String ID = "id";
/*     */   public static final String WEBSITE_ID = "websiteId";
/*     */   public static final String CATEGORY_ID_ARRAY = "categoryIdArray";
/*     */   public static final String CATEGORY_NAME_ATTRY = "categoryNameArray";
/*     */   public static final String BRAND_NAME = "brandName";
/*     */   public static final String NAME = "name";
/*     */   public static final String DESCRIPTION = "description";
/*     */   public static final String URL = "url";
/*     */   public static final String DETAIL_IMG_URL = "detailImgUrl";
/*     */   public static final String LIST_IMG_URL = "listImgUrl";
/*     */   public static final String COVER_IMG_URL = "coverImgUrl";
/*     */   public static final String MIN_IMG_URL = "minImgUrl";
/*     */   public static final String MARKET_PRICE = "marketPrice";
/*     */   public static final String SALE_PRICE = "salePrice";
/*     */   public static final String KEYWORD_ARRAY = "keywordArray";
/*     */   public static final String TAG_ARRAY = "tagArray";
/*     */   public static final String CREATE_TIME = "createTime";
/* 239 */   public static final String[] QUERY_FIELD = { "name", "categoryNameArray", 
/* 240 */     "brandName", "description" };
/*     */ 
/* 241 */   public static final BooleanClause.Occur[] QUERY_FLAGS = { 
/* 242 */     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD, 
/* 243 */     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
/*     */   private Long id;
/*     */   private Long websiteId;
/*     */   private Collection<Long> categoryIdArray;
/*     */   private Collection<String> categoryNameArray;
/*     */   private String brandName;
/*     */   private String name;
/*     */   private String url;
/*     */   private String description;
/*     */   private String detailImgUrl;
/*     */   private String listImgUrl;
/*     */   private String minImgUrl;
/*     */   private Double marketPrice;
/*     */   private Double salePrice;
/*     */   private Collection<String> keywordArray;
/*     */   private Collection<String> tagArray;
/*     */   private Date createTime;
/*     */   private String coverImgUrl;
/*     */ 
/*     */   public static Query createQuery(String queryString, Long webId, Long ctgId, Date start, Date end, Analyzer analyzer)
/*     */     throws org.apache.lucene.queryParser.ParseException
/*     */   {
/*  47 */     BooleanQuery bq = new BooleanQuery();
/*     */ 
/*  49 */     if (!StringUtils.isBlank(queryString)) {
/*  50 */       Query q = MultiFieldQueryParser.parse(Version.LUCENE_30, queryString, 
/*  51 */         QUERY_FIELD, QUERY_FLAGS, analyzer);
/*  52 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*     */ 
/*  55 */     if (webId != null) {
/*  56 */       Query q = new TermQuery(new Term("websiteId", webId.toString()));
/*  57 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*  59 */     if (ctgId != null) {
/*  60 */       Query q = new TermQuery(new Term("categoryIdArray", ctgId.toString()));
/*  61 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*  63 */     if ((start != null) || (end != null)) {
/*  64 */       String startDate = null;
/*  65 */       String endDate = null;
/*  66 */       if (start != null) {
/*  67 */         startDate = DateTools.dateToString(start, Resolution.DAY);
/*     */       }
/*  69 */       if (end != null) {
/*  70 */         endDate = DateTools.dateToString(end, Resolution.DAY);
/*     */       }
/*  72 */       Query q = new TermRangeQuery("createTime", startDate, endDate, true, true);
/*  73 */       bq.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*  75 */     return bq;
/*     */   }
/*     */ 
/*     */   public static Pagination getResult(Searcher searcher, TopDocs docs, int pageNo, int pageSize)
/*     */     throws CorruptIndexException, IOException
/*     */   {
/*  81 */     List list = new ArrayList(pageSize);
/*  82 */     ScoreDoc[] hits = docs.scoreDocs;
/*  83 */     int endIndex = pageNo * pageSize;
/*  84 */     int len = hits.length;
/*  85 */     if (endIndex > len) {
/*  86 */       endIndex = len;
/*     */     }
/*  88 */     for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
/*  89 */       Document d = searcher.doc(hits[i].doc);
/*  90 */       list.add(createProduct(d));
/*     */     }
/*  92 */     return new Pagination(pageNo, pageSize, docs.totalHits, list);
/*     */   }
/*     */ 
/*     */   public static Document createDocument(Product p)
/*     */   {
/* 103 */     Document doc = new Document();
/* 104 */     doc.add(new Field("id", p.getId().toString(), Store.YES,
/* 105 */       Index.NO));
/* 106 */     doc.add(new Field("websiteId", p.getWebsite().getId().toString(), 
/* 107 */       Store.YES, Index.NOT_ANALYZED));
/* 108 */     for (Long id : p.getCategoryIdArray()) {
/* 109 */       doc.add(new Field("categoryIdArray", id.toString(), 
/* 110 */         Store.YES, Index.NOT_ANALYZED));
/*     */     }
/*     */ 
/* 113 */     doc.add(new Field("name", p.getName(), Store.YES,
/* 114 */       Index.ANALYZED));
/* 115 */     for (String name : p.getCategoryNameArray()) {
/* 116 */       doc.add(new Field("categoryNameArray", name, Store.YES,
/* 117 */         Index.ANALYZED));
/*     */     }
/* 119 */     if (!StringUtils.isBlank(p.getMdescription())) {
/* 120 */       doc.add(new Field("description", p.getMdescription(), Store.YES,
/* 121 */         Index.ANALYZED));
/*     */     }
/* 123 */     if (!StringUtils.isBlank(p.getBrandName())) {
/* 124 */       doc.add(new Field("brandName", p.getBrandName(), Store.YES,
/* 125 */         Index.ANALYZED));
/*     */     }
/*     */ 
/* 128 */     doc.add(new Field("url", p.getUrl(), Store.YES, Index.NO));
/*     */ 
/* 130 */     if (!StringUtils.isBlank(p.getDetailImgUrl())) {
/* 131 */       doc.add(new Field("detailImgUrl", p.getDetailImgUrl(), 
/* 132 */         Store.YES, Index.NO));
/*     */     }
/* 134 */     if (!StringUtils.isBlank(p.getListImgUrl())) {
/* 135 */       doc.add(new Field("listImgUrl", p.getListImgUrl(), Store.YES,
/* 136 */         Index.NO));
/*     */     }
/* 138 */     if (!StringUtils.isBlank(p.getCoverImgUrl())) {
/* 139 */       doc.add(new Field("coverImgUrl", p.getCoverImgUrl(), Store.YES,
/* 140 */         Index.NO));
/*     */     }
/* 142 */     if (!StringUtils.isBlank(p.getMinImgUrl())) {
/* 143 */       doc.add(new Field("minImgUrl", p.getMinImgUrl(), Store.YES,
/* 144 */         Index.NO));
/*     */     }
/*     */ 
/* 147 */     doc.add(new Field("marketPrice", MoneyTools.moneyToString(
/* 148 */       p.getMarketPrice()), Store.YES, Index.NOT_ANALYZED));
/* 149 */     doc.add(new Field("salePrice", 
/* 150 */       MoneyTools.moneyToString(p.getPrice()), Store.YES,
/* 151 */       Index.NOT_ANALYZED));
/*     */ 
/* 153 */     doc.add(new Field("createTime", DateTools.dateToString(
/* 154 */       p.getCreateTime(), Resolution.MILLISECOND),
/* 155 */       Store.YES, Index.NOT_ANALYZED));
/*     */ 
/* 157 */     Collection keywords = p.getKeywordArray();
/* 158 */     for (String keyword : keywords) {
/* 159 */       doc.add(new Field("keywordArray", keyword, Store.YES,
/* 160 */         Index.ANALYZED));
/*     */     }
/* 162 */     Collection tags = p.getTagArray();
/* 163 */     for (String tag : tags) {
/* 164 */       doc.add(new Field("tagArray", tag, Store.YES,
/* 165 */         Index.ANALYZED));
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
/* 176 */       String name = f.name();
/* 177 */       if (name.equals("keywordArray"))
/* 178 */         p.addToKeyworeds(f.stringValue());
/* 179 */       else if (name.equals("tagArray"))
/* 180 */         p.addToTags(f.stringValue());
/* 181 */       else if (name.equals("id"))
/* 182 */         p.setId(Long.valueOf(f.stringValue()));
/* 183 */       else if (name.equals("websiteId"))
/* 184 */         p.setWebsiteId(Long.valueOf(f.stringValue()));
/* 185 */       else if (name.equals("categoryIdArray"))
/* 186 */         p.addToCategoryIds(Long.valueOf(f.stringValue()));
/* 187 */       else if (name.equals("createTime"))
/*     */         try {
/* 189 */           p.setCreateTime(DateTools.stringToDate(f.stringValue()));
/*     */         } catch (java.text.ParseException e) {
/* 191 */           log.error("lucene date format faild", e);
/*     */         }
/* 193 */       else if (name.equals("name"))
/* 194 */         p.setName(f.stringValue());
/* 195 */       else if (name.equals("categoryNameArray"))
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
/* 208 */         p.setCoverImgUrl(f.stringValue());
/* 209 */       else if (name.equals("minImgUrl"))
/* 210 */         p.setMinImgUrl(f.stringValue());
/* 211 */       else if (name.equals("marketPrice"))
/* 212 */         p.setMarketPrice(MoneyTools.stringToMoney(f.stringValue()));
/* 213 */       else if (name.equals("salePrice")) {
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
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/* 292 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/* 296 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 300 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 304 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getDescription() {
/* 308 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 312 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getDetailImgUrl() {
/* 316 */     return this.detailImgUrl;
/*     */   }
/*     */ 
/*     */   public void setDetailImgUrl(String detailImgUrl) {
/* 320 */     this.detailImgUrl = detailImgUrl;
/*     */   }
/*     */ 
/*     */   public String getListImgUrl() {
/* 324 */     return this.listImgUrl;
/*     */   }
/*     */ 
/*     */   public void setListImgUrl(String listImgUrl) {
/* 328 */     this.listImgUrl = listImgUrl;
/*     */   }
/*     */   public String getCoverImgUrl() {
/* 331 */     return this.coverImgUrl;
/*     */   }
/*     */ 
/*     */   public void setCoverImgUrl(String coverImgUrl) {
/* 335 */     this.coverImgUrl = coverImgUrl;
/*     */   }
/*     */ 
/*     */   public String getMinImgUrl() {
/* 339 */     return this.minImgUrl;
/*     */   }
/*     */ 
/*     */   public void setMinImgUrl(String minImgUrl) {
/* 343 */     this.minImgUrl = minImgUrl;
/*     */   }
/*     */ 
/*     */   public Double getMarketPrice() {
/* 347 */     return this.marketPrice;
/*     */   }
/*     */ 
/*     */   public void setMarketPrice(Double marketPrice) {
/* 351 */     this.marketPrice = marketPrice;
/*     */   }
/*     */ 
/*     */   public Double getSalePrice() {
/* 355 */     return this.salePrice;
/*     */   }
/*     */ 
/*     */   public void setSalePrice(Double salePrice) {
/* 359 */     this.salePrice = salePrice;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 363 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 367 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public Long getWebsiteId() {
/* 371 */     return this.websiteId;
/*     */   }
/*     */ 
/*     */   public void setWebsiteId(Long websiteId) {
/* 375 */     this.websiteId = websiteId;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime() {
/* 379 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 383 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public String getBrandName() {
/* 387 */     return this.brandName;
/*     */   }
/*     */ 
/*     */   public void setBrandName(String brandName) {
/* 391 */     this.brandName = brandName;
/*     */   }
/*     */ 
/*     */   public Collection<String> getKeywordArray() {
/* 395 */     return this.keywordArray;
/*     */   }
/*     */ 
/*     */   public void setKeywordArray(Collection<String> keywordArray) {
/* 399 */     this.keywordArray = keywordArray;
/*     */   }
/*     */ 
/*     */   public Collection<String> getTagArray() {
/* 403 */     return this.tagArray;
/*     */   }
/*     */ 
/*     */   public void setTagArray(Collection<String> tagArray) {
/* 407 */     this.tagArray = tagArray;
/*     */   }
/*     */ 
/*     */   public Collection<String> getCategoryNameArray() {
/* 411 */     return this.categoryNameArray;
/*     */   }
/*     */ 
/*     */   public void setCategoryNameArray(Collection<String> categoryNameArray) {
/* 415 */     this.categoryNameArray = categoryNameArray;
/*     */   }
/*     */ 
/*     */   public Collection<Long> getCategoryIdArray() {
/* 419 */     return this.categoryIdArray;
/*     */   }
/*     */ 
/*     */   public void setCategoryIdArray(Collection<Long> categoryIdArray) {
/* 423 */     this.categoryIdArray = categoryIdArray;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneProduct
 * JD-Core Version:    0.6.2
 */