package guda.shop.common.hibernate3;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import java.util.*;
import java.util.Map.Entry;

public class Finder {
    public static final String ROW_COUNT = "select count(*) ";
    public static final String FROM = "from";
    public static final String DISTINCT = "distinct";
    public static final String HQL_FETCH = "fetch";
    public static final String ORDER_BY = "order";
    private StringBuilder _$13;
    private List<String> _$12;
    private List<Object> _$11;
    private List<Type> _$10;
    private List<String> _$9;
    private List<Collection<Object>> _$8;
    private List<Type> _$7;
    private List<String> _$6;
    private List<Object[]> _$5;
    private List<Type> _$4;
    private int _$3 = 0;
    private int _$2 = 0;
    private boolean _$1 = false;

    protected Finder() {
        this._$13 = new StringBuilder();
    }

    protected Finder(String paramString) {
        this._$13 = new StringBuilder(paramString);
    }

    public static Finder create() {
        return new Finder();
    }

    public static Finder create(String paramString) {
        return new Finder(paramString);
    }

    public static void main(String[] paramArrayOfString) {
        Finder localFinder = create("select distinct p FROM BookType join fetch p");
        System.out.println(localFinder.getRowCountHql());
        System.out.println(localFinder.getOrigHql());
    }

    public Finder append(String paramString) {
        this._$13.append(paramString);
        return this;
    }

    public String getOrigHql() {
        return this._$13.toString();
    }

    public String getRowCountHql() {
        String str1 = this._$13.toString();
        int i = str1.toLowerCase().indexOf("from");
        String str2 = str1.substring(0, i);
        str1 = str1.substring(i);
        String str3 = str1.replace("fetch", "");
        int j = str3.indexOf("order");
        if (j > 0)
            str3 = str3.substring(0, j);
        return _$1(str2) + str3;
    }

    public int getFirstResult() {
        return this._$3;
    }

    public void setFirstResult(int paramInt) {
        this._$3 = paramInt;
    }

    public int getMaxResults() {
        return this._$2;
    }

    public void setMaxResults(int paramInt) {
        this._$2 = paramInt;
    }

    public boolean isCacheable() {
        return this._$1;
    }

    public void setCacheable(boolean paramBoolean) {
        this._$1 = paramBoolean;
    }

    public Finder setParam(String paramString, Object paramObject) {
        return setParam(paramString, paramObject, null);
    }

    public Finder setParam(String paramString, Object paramObject, Type paramType) {
        _$9().add(paramString);
        _$8().add(paramObject);
        _$7().add(paramType);
        return this;
    }

    public Finder setParams(Map<String, Object> paramMap) {
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext()) {
            Entry localEntry = (Entry) localIterator.next();
            setParam((String) localEntry.getKey(), localEntry.getValue());
        }
        return this;
    }

    public Finder setParamList(String paramString, Collection<Object> paramCollection, Type paramType) {
        _$6().add(paramString);
        _$5().add(paramCollection);
        _$4().add(paramType);
        return this;
    }

    public Finder setParamList(String paramString, Collection<Object> paramCollection) {
        return setParamList(paramString, paramCollection, null);
    }

    public Finder setParamList(String paramString, Object[] paramArrayOfObject, Type paramType) {
        _$3().add(paramString);
        _$2().add(paramArrayOfObject);
        _$1().add(paramType);
        return this;
    }

    public Finder setParamList(String paramString, Object[] paramArrayOfObject) {
        return setParamList(paramString, paramArrayOfObject, null);
    }

    public Query setParamsToQuery(Query paramQuery) {
        int i;
        if (this._$12 != null)
            for (i = 0; i < this._$12.size(); i++)
                if (this._$10.get(i) == null)
                    paramQuery.setParameter((String) this._$12.get(i), this._$11.get(i));
                else
                    paramQuery.setParameter((String) this._$12.get(i), this._$11.get(i), (Type) this._$10.get(i));
        if (this._$9 != null)
            for (i = 0; i < this._$9.size(); i++)
                if (this._$7.get(i) == null)
                    paramQuery.setParameterList((String) this._$9.get(i), (Collection) this._$8.get(i));
                else
                    paramQuery.setParameterList((String) this._$9.get(i), (Collection) this._$8.get(i), (Type) this._$7.get(i));
        if (this._$6 != null)
            for (i = 0; i < this._$6.size(); i++)
                if (this._$4.get(i) == null)
                    paramQuery.setParameterList((String) this._$6.get(i), (Object[]) this._$5.get(i));
                else
                    paramQuery.setParameterList((String) this._$6.get(i), (Object[]) this._$5.get(i), (Type) this._$4.get(i));
        return paramQuery;
    }

    public Query createQuery(Session paramSession) {
        Query localQuery = setParamsToQuery(paramSession.createQuery(getOrigHql()));
        if (getFirstResult() > 0)
            localQuery.setFirstResult(getFirstResult());
        if (getMaxResults() > 0)
            localQuery.setMaxResults(getMaxResults());
        if (isCacheable())
            localQuery.setCacheable(true);
        return localQuery;
    }

    private String _$1(String paramString) {
        if (paramString.indexOf("select") == -1)
            return "select count(*) ";
        return paramString.replace("select", "select count(") + ") ";
    }

    private List<String> _$9() {
        if (this._$12 == null)
            this._$12 = new ArrayList();
        return this._$12;
    }

    private List<Object> _$8() {
        if (this._$11 == null)
            this._$11 = new ArrayList();
        return this._$11;
    }

    private List<Type> _$7() {
        if (this._$10 == null)
            this._$10 = new ArrayList();
        return this._$10;
    }

    private List<String> _$6() {
        if (this._$9 == null)
            this._$9 = new ArrayList();
        return this._$9;
    }

    private List<Collection<Object>> _$5() {
        if (this._$8 == null)
            this._$8 = new ArrayList();
        return this._$8;
    }

    private List<Type> _$4() {
        if (this._$7 == null)
            this._$7 = new ArrayList();
        return this._$7;
    }

    private List<String> _$3() {
        if (this._$6 == null)
            this._$6 = new ArrayList();
        return this._$6;
    }

    private List<Object[]> _$2() {
        if (this._$5 == null)
            this._$5 = new ArrayList();
        return this._$5;
    }

    private List<Type> _$1() {
        if (this._$4 == null)
            this._$4 = new ArrayList();
        return this._$4;
    }
}

