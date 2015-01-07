package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.KeyWordDao;
iimport guda.shopcms.entity.KeyWord;
import guda.shop.cms.manager.KeyWordMng;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyWordMngImpl
  implements KeyWordMng
{

  @Autowired
  private KeyWordDao _$1;

  @Transactional(readOnly=true)
  public List<KeyWord> getAllList()
  {
    List localList = this._$1.getAllList();
    return localList;
  }

  @Transactional(readOnly=true)
  public KeyWord findById(Integer paramInteger)
  {
    KeyWord localKeyWord = this._$1.findById(paramInteger);
    return localKeyWord;
  }

  public List<KeyWord> findKeyWord(Integer paramInteger)
  {
    return this._$1.findKeyWord(paramInteger);
  }

  public KeyWord save(String paramString)
  {
    List localList = getKeyWordContent(paramString);
    KeyWord localKeyWord = null;
    if (localList.isEmpty())
    {
      localKeyWord = new KeyWord();
      localKeyWord.setKeyword(paramString);
      localKeyWord.setTimes(Integer.valueOf(1));
      this._$1.save(localKeyWord);
    }
    else
    {
      localKeyWord = (KeyWord)localList.iterator().next();
      localKeyWord.setTimes(Integer.valueOf(localKeyWord.getTimes().intValue() + 1));
    }
    return localKeyWord;
  }

  public List<KeyWord> getKeyWordContent(String paramString)
  {
    return this._$1.getKeyWordContent(paramString);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.KeyWordMngImpl
 * JD-Core Version:    0.6.2
 */