package guda.shop.cms.lucene;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductInfo;
import guda.shop.common.file.lucene.MoneyTools;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class LuceneProduct
        implements ProductInfo {
    public static final String ID = "id";
    public static final String WEBSITE_ID = "websiteId";
    public static final String CATEGORY_ID_ARRAY = "categoryIdArray";
    public static final String CATEGORY_NAME_ATTRY = "categoryNameArray";
    public static final String BRAND_NAME = "brandName";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String URL = "url";
    public static final String DETAIL_IMG_URL = "detailImgUrl";
    public static final String LIST_IMG_URL = "listImgUrl";
    public static final String COVER_IMG_URL = "coverImgUrl";
    public static final String MIN_IMG_URL = "minImgUrl";
    public static final String MARKET_PRICE = "marketPrice";
    public static final String SALE_PRICE = "salePrice";
    public static final String KEYWORD_ARRAY = "keywordArray";
    public static final String TAG_ARRAY = "tagArray";
    public static final String CREATE_TIME = "createTime";
    public static final String[] QUERY_FIELD = {"name", "categoryNameArray",
            "brandName", "description"};
    public static final BooleanClause.Occur[] QUERY_FLAGS = {
            BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
    private static final Logger log = LoggerFactory.getLogger(LuceneProduct.class);
    private Long id;
    private Long websiteId;
    private Collection<Long> categoryIdArray;
    private Collection<String> categoryNameArray;
    private String brandName;
    private String name;
    private String url;
    private String description;
    private String detailImgUrl;
    private String listImgUrl;
    private String minImgUrl;
    private Double marketPrice;
    private Double salePrice;
    private Collection<String> keywordArray;
    private Collection<String> tagArray;
    private Date createTime;
    private String coverImgUrl;

    public static Query createQuery(String queryString, Long webId, Long ctgId, Date start, Date end, Analyzer analyzer)
            throws org.apache.lucene.queryParser.ParseException {

        BooleanQuery bq = new BooleanQuery();


        if (!StringUtils.isBlank(queryString)) {

            Query q = MultiFieldQueryParser.parse(Version.LUCENE_30, queryString,
                    QUERY_FIELD, QUERY_FLAGS, analyzer);

            bq.add(q, BooleanClause.Occur.MUST);
        }


        if (webId != null) {

            Query q = new TermQuery(new Term("websiteId", webId.toString()));

            bq.add(q, BooleanClause.Occur.MUST);
        }

        if (ctgId != null) {

            Query q = new TermQuery(new Term("categoryIdArray", ctgId.toString()));

            bq.add(q, BooleanClause.Occur.MUST);
        }

        if ((start != null) || (end != null)) {

            String startDate = null;

            String endDate = null;

            if (start != null) {

                startDate = DateTools.dateToString(start, Resolution.DAY);
            }

            if (end != null) {

                endDate = DateTools.dateToString(end, Resolution.DAY);
            }

            Query q = new TermRangeQuery("createTime", startDate, endDate, true, true);

            bq.add(q, BooleanClause.Occur.MUST);
        }

        return bq;
    }

    public static Pagination getResult(Searcher searcher, TopDocs docs, int pageNo, int pageSize)
            throws CorruptIndexException, IOException {

        List list = new ArrayList(pageSize);

        ScoreDoc[] hits = docs.scoreDocs;

        int endIndex = pageNo * pageSize;

        int len = hits.length;

        if (endIndex > len) {

            endIndex = len;
        }

        for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {

            Document d = searcher.doc(hits[i].doc);

            list.add(createProduct(d));
        }

        return new Pagination(pageNo, pageSize, docs.totalHits, list);
    }

    public static Document createDocument(Product p) {

        Document doc = new Document();

        doc.add(new Field("id", p.getId().toString(), Store.YES,
                Index.NO));

        doc.add(new Field("websiteId", p.getWebsite().getId().toString(),
                Store.YES, Index.NOT_ANALYZED));

        for (Long id : p.getCategoryIdArray()) {

            doc.add(new Field("categoryIdArray", id.toString(),
                    Store.YES, Index.NOT_ANALYZED));
        }


        doc.add(new Field("name", p.getName(), Store.YES,
                Index.ANALYZED));

        for (String name : p.getCategoryNameArray()) {

            doc.add(new Field("categoryNameArray", name, Store.YES,
                    Index.ANALYZED));
        }

        if (!StringUtils.isBlank(p.getMdescription())) {

            doc.add(new Field("description", p.getMdescription(), Store.YES,
                    Index.ANALYZED));
        }

        if (!StringUtils.isBlank(p.getBrandName())) {

            doc.add(new Field("brandName", p.getBrandName(), Store.YES,
                    Index.ANALYZED));
        }


        doc.add(new Field("url", p.getUrl(), Store.YES, Index.NO));


        if (!StringUtils.isBlank(p.getDetailImgUrl())) {

            doc.add(new Field("detailImgUrl", p.getDetailImgUrl(),
                    Store.YES, Index.NO));
        }

        if (!StringUtils.isBlank(p.getListImgUrl())) {

            doc.add(new Field("listImgUrl", p.getListImgUrl(), Store.YES,
                    Index.NO));
        }

        if (!StringUtils.isBlank(p.getCoverImgUrl())) {

            doc.add(new Field("coverImgUrl", p.getCoverImgUrl(), Store.YES,
                    Index.NO));
        }

        if (!StringUtils.isBlank(p.getMinImgUrl())) {

            doc.add(new Field("minImgUrl", p.getMinImgUrl(), Store.YES,
                    Index.NO));
        }


        doc.add(new Field("marketPrice", MoneyTools.moneyToString(
                p.getMarketPrice()), Store.YES, Index.NOT_ANALYZED));

        doc.add(new Field("salePrice",
                MoneyTools.moneyToString(p.getPrice()), Store.YES,
                Index.NOT_ANALYZED));


        doc.add(new Field("createTime", DateTools.dateToString(
                p.getCreateTime(), Resolution.MILLISECOND),
                Store.YES, Index.NOT_ANALYZED
        ));


        Collection<String> keywords = p.getKeywordArray();

        for (String keyword : keywords) {

            doc.add(new Field("keywordArray", keyword, Store.YES,
                    Index.ANALYZED));
        }

        Collection<String> tags = p.getTagArray();

        for (String tag : tags) {

            doc.add(new Field("tagArray", tag, Store.YES,
                    Index.ANALYZED));
        }

        return doc;
    }

    public static LuceneProduct createProduct(Document d) {

        LuceneProduct p = new LuceneProduct();

        List<Fieldable> list = d.getFields();


        for (Fieldable f : list) {

            String name = f.name();

            if (name.equals("keywordArray"))
                p.addToKeyworeds(f.stringValue());

            else if (name.equals("tagArray"))
                p.addToTags(f.stringValue());

            else if (name.equals("id"))
                p.setId(Long.valueOf(f.stringValue()));

            else if (name.equals("websiteId"))
                p.setWebsiteId(Long.valueOf(f.stringValue()));

            else if (name.equals("categoryIdArray"))
                p.addToCategoryIds(Long.valueOf(f.stringValue()));

            else if (name.equals("createTime"))
                try {

                    p.setCreateTime(DateTools.stringToDate(f.stringValue()));
                } catch (java.text.ParseException e) {

                    log.error("lucene date format faild", e);
                }

            else if (name.equals("name"))
                p.setName(f.stringValue());

            else if (name.equals("categoryNameArray"))
                p.addToCategoryNames(f.stringValue());

            else if (name.equals("brandName"))
                p.setBrandName(f.stringValue());

            else if (name.equals("description"))
                p.setDescription(f.stringValue());

            else if (name.equals("url"))
                p.setUrl(f.stringValue());

            else if (name.equals("detailImgUrl"))
                p.setDetailImgUrl(f.stringValue());

            else if (name.equals("listImgUrl"))
                p.setListImgUrl(f.stringValue());

            else if (name.equals("coverImgUrl"))
                p.setCoverImgUrl(f.stringValue());

            else if (name.equals("minImgUrl"))
                p.setMinImgUrl(f.stringValue());

            else if (name.equals("marketPrice"))
                p.setMarketPrice(MoneyTools.stringToMoney(f.stringValue()));

            else if (name.equals("salePrice")) {

                p.setSalePrice(MoneyTools.stringToMoney(f.stringValue()));
            }
        }


        return p;
    }

    public void addToKeyworeds(String keyword) {

        if (this.keywordArray == null) {

            this.keywordArray = new ArrayList();
        }

        this.keywordArray.add(keyword);
    }

    public void addToTags(String tag) {

        if (this.tagArray == null) {

            this.tagArray = new ArrayList();
        }

        this.tagArray.add(tag);
    }

    public void addToCategoryNames(String categoryName) {

        if (this.categoryNameArray == null) {

            this.categoryNameArray = new ArrayList();
        }

        this.categoryNameArray.add(categoryName);
    }

    public void addToCategoryIds(Long categoryId) {

        if (this.categoryIdArray == null) {

            this.categoryIdArray = new ArrayList();
        }

        this.categoryIdArray.add(categoryId);
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getDetailImgUrl() {

        return this.detailImgUrl;
    }

    public void setDetailImgUrl(String detailImgUrl) {

        this.detailImgUrl = detailImgUrl;
    }

    public String getListImgUrl() {

        return this.listImgUrl;
    }

    public void setListImgUrl(String listImgUrl) {

        this.listImgUrl = listImgUrl;
    }

    public String getCoverImgUrl() {

        return this.coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {

        this.coverImgUrl = coverImgUrl;
    }

    public String getMinImgUrl() {

        return this.minImgUrl;
    }

    public void setMinImgUrl(String minImgUrl) {

        this.minImgUrl = minImgUrl;
    }

    public Double getMarketPrice() {

        return this.marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {

        this.marketPrice = marketPrice;
    }

    public Double getSalePrice() {

        return this.salePrice;
    }

    public void setSalePrice(Double salePrice) {

        this.salePrice = salePrice;
    }

    public String getUrl() {

        return this.url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public Long getWebsiteId() {

        return this.websiteId;
    }

    public void setWebsiteId(Long websiteId) {

        this.websiteId = websiteId;
    }

    public Date getCreateTime() {

        return this.createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public String getBrandName() {

        return this.brandName;
    }

    public void setBrandName(String brandName) {

        this.brandName = brandName;
    }

    public Collection<String> getKeywordArray() {

        return this.keywordArray;
    }

    public void setKeywordArray(Collection<String> keywordArray) {

        this.keywordArray = keywordArray;
    }

    public Collection<String> getTagArray() {

        return this.tagArray;
    }

    public void setTagArray(Collection<String> tagArray) {

        this.tagArray = tagArray;
    }

    public Collection<String> getCategoryNameArray() {

        return this.categoryNameArray;
    }

    public void setCategoryNameArray(Collection<String> categoryNameArray) {

        this.categoryNameArray = categoryNameArray;
    }

    public Collection<Long> getCategoryIdArray() {

        return this.categoryIdArray;
    }

    public void setCategoryIdArray(Collection<Long> categoryIdArray) {

        this.categoryIdArray = categoryIdArray;
    }
}

