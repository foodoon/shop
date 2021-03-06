package guda.shop.core.tpl;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract interface TplManager {
    public abstract List<? extends Tpl> getListByPrefix(String paramString);

    public abstract List<String> getNameListByPrefix(String paramString);

    public abstract List<? extends Tpl> getChild(String paramString);

    public abstract void save(String paramString1, String paramString2, boolean paramBoolean);

    public abstract void save(String paramString, MultipartFile paramMultipartFile);

    public abstract Tpl get(String paramString);

    public abstract void update(String paramString1, String paramString2);

    public abstract void rename(String paramString1, String paramString2);

    public abstract int delete(String[] paramArrayOfString);
}

