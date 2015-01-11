package guda.shop.core.entity;

import guda.shop.biz.PathContants;
import guda.shop.core.entity.base.BaseWebsite;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class Website extends BaseWebsite {
    public static final String USER_BASE = "t";
    public static final String UPLOAD_PATH = "u";
    public static final String DEFAULT = "default";
    public static final String TPL_SUFFIX = ".html";
    public static final String TPL_PREFIX_SYS = "sys_";
    public static final String TPL_PREFIX_TAG = "tag_";
    private static final long serialVersionUID = 1L;

    public Website() {
    }

    public Website(Long paramLong) {
        super(paramLong);
    }

    public Website(Long paramLong, Global paramGlobal, String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, String paramString5, String paramString6, String paramString7, String paramString8, Integer paramInteger3, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14) {
        super(paramLong, paramGlobal, paramString1, paramString2, paramString3, paramString4, paramInteger1, paramInteger2, paramDate, paramBoolean1, paramBoolean2, paramString5, paramString6, paramString7, paramString8, paramInteger3, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14);
    }

    public String getTemplate(String paramString1, String paramString2, String paramString3, String paramString4) {
        StringBuilder localStringBuilder = getTemplateRelBuff().append("/").append(paramString1).append("/");
        if (!StringUtils.isBlank(paramString4)) {
            localStringBuilder.append(paramString4);
        }
        localStringBuilder.append(paramString2);
        if (!StringUtils.isBlank(paramString3)) {
            localStringBuilder.append("_").append(paramString3);
        }
        localStringBuilder.append(".html");
        return localStringBuilder.toString();
    }

    public String getUrl() {
        return "/";
    }

    public String getUploadRel(String paramString) {
        StringBuilder localStringBuilder = new StringBuilder(PathContants.UPLOAD_PATH);
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }

    public String getUploadUrls(String paramString) {
        StringBuilder localStringBuilder = getResBase().append(PathContants.UPLOAD_PATH);
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }

    public String getUploadUrl(String paramString) {
        StringBuilder localStringBuilder = getResBase();
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }


    public String getTemplateRel(String paramString) {
        StringBuilder localStringBuilder = getTemplateRelBuff();
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }

    public String getResBaseRel(String paramString) {
        StringBuilder localStringBuilder = getBase();
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }

    public String getResBaseUrl(String paramString) {
        StringBuilder localStringBuilder = getResBase();
        if (!StringUtils.isBlank(paramString)) {
            if (!paramString.startsWith("/"))
                localStringBuilder.append("/");
            localStringBuilder.append(paramString);
        }
        return localStringBuilder.toString();
    }


    private StringBuilder getBase() {
        return new StringBuilder("/").append("assets/global");
    }

    private StringBuilder getResBase() {
        return getUrlBuff(false).append(PathContants.ASSETS_GLOBAL);
    }

    public String getResBaseUrl() {
        return getResBase().toString();
    }

    public StringBuilder getTemplateRelBuff() {
        StringBuilder localStringBuilder = new StringBuilder(PathContants.FRONT_RELATIVE_PATH);
        return localStringBuilder;
    }

    public String getTemplateRel() {
        return getTemplateRel(null);
    }

    public StringBuilder getUrlBuff(boolean paramBoolean) {
        StringBuilder localStringBuilder = new StringBuilder();
        if ((paramBoolean) || (!getRelativePath().booleanValue())) {
            localStringBuilder = new StringBuilder("http://").append(getDomain());
            Integer localInteger = getGlobal().getPort();
            if ((localInteger != null) && (localInteger.intValue() != 80))
                localStringBuilder.append(":").append(localInteger);
        }
        if (getContextPath() != null)
            localStringBuilder.append(getContextPath());
        return localStringBuilder;
    }

    public String getResBaseRel() {
        return getBase().toString();
    }

    public String getUploadRel() {
        return getUploadRel(null);
    }

    public String getUploadUrl() {
        return getUploadUrl(null);
    }

    public String getTemplate(String paramString1, String paramString2) {
        return getTemplate(paramString1, paramString2, null, null);
    }

    public String getTplSys(String paramString1, String paramString2) {
        return getTemplate(paramString1, paramString2, null, null);
    }

    public String getTplTag(String paramString1, String paramString2, String paramString3) {
        return getTemplate(paramString1, paramString2, paramString3, null);
    }

    public String getContextPath() {
        String str = getGlobal().getContextPath();
        return StringUtils.isBlank(str) ? "" : str;
    }

    public Integer getPort() {
        return getGlobal().getPort();
    }

    public String[] getDomainAliasArray() {
        String str = getDomainAlias();
        if (!StringUtils.isBlank(str))
            return str.split(",");
        return null;
    }
}

