package guda.shop.common.developer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleGenerator
{
  private static final Logger _$20 = LoggerFactory.getLogger(ModuleGenerator.class);
  public static final String SPT = File.separator;
  public static final String ENCODING = "UTF-8";
  private Properties _$19 = new Properties();
  private String _$18;
  private String _$17;
  private File _$16;
  private File _$15;
  private File _$14;
  private File _$13;
  private File _$12;
  private File _$11;
  private File _$10;
  private File _$9;
  private File _$8;
  private File _$7;
  private File _$6;
  private File _$5;
  private File _$4;
  private File _$3;
  private File _$2;
  private File _$1;

  public ModuleGenerator(String paramString1, String paramString2)
  {
    this._$18 = paramString1;
    this._$17 = paramString2;
  }

  private void _$4()
  {
    try
    {
      _$20.debug("packName=" + this._$18);
      _$20.debug("fileName=" + this._$17);
      FileInputStream localFileInputStream = new FileInputStream(_$1(this._$18, this._$17));
      this._$19.load(localFileInputStream);
      String str1 = this._$19.getProperty("Entity");
      _$20.debug("entityUp:" + str1);
      if ((str1 == null) || (str1.trim().equals("")))
      {
        _$20.warn("Entity not specified, exit!");
        return;
      }
      String str2 = str1.substring(0, 1).toLowerCase() + str1.substring(1);
      _$20.debug("entityLow:" + str2);
      this._$19.put("entity", str2);
      if (_$20.isDebugEnabled())
      {
        Set localSet = this._$19.keySet();
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          _$20.debug(localObject + "=" + this._$19.get(localObject));
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  private void _$3()
  {
    String str1 = _$1(this._$19.getProperty("dao_impl_p"), this._$19.getProperty("Entity") + "DaoImpl.java");
    this._$16 = new File(str1);
    _$20.debug("daoImplFile:" + this._$16.getAbsolutePath());
    String str2 = _$1(this._$19.getProperty("dao_p"), this._$19.getProperty("Entity") + "Dao.java");
    this._$15 = new File(str2);
    _$20.debug("daoFile:" + this._$15.getAbsolutePath());
    String str3 = _$1(this._$19.getProperty("manager_p"), this._$19.getProperty("Entity") + "Mng.java");
    this._$14 = new File(str3);
    _$20.debug("managerFile:" + this._$14.getAbsolutePath());
    String str4 = _$1(this._$19.getProperty("manager_impl_p"), this._$19.getProperty("Entity") + "MngImpl.java");
    this._$13 = new File(str4);
    _$20.debug("managerImplFile:" + this._$13.getAbsolutePath());
    String str5 = _$1(this._$19.getProperty("action_p"), this._$19.getProperty("Entity") + "Act.java");
    this._$12 = new File(str5);
    _$20.debug("actionFile:" + this._$12.getAbsolutePath());
    String str6 = "WebContent/WEB-INF/" + this._$19.getProperty("config_sys") + "/" + this._$19.getProperty("config_entity") + "/";
    this._$11 = new File(str6 + "list.html");
    _$20.debug("pageListFile:" + this._$11.getAbsolutePath());
    this._$10 = new File(str6 + "edit.html");
    _$20.debug("pageEditFile:" + this._$10.getAbsolutePath());
    this._$9 = new File(str6 + "add.html");
    _$20.debug("pageAddFile:" + this._$9.getAbsolutePath());
  }

  private void _$2()
  {
    String str = this._$19.getProperty("template_dir");
    _$20.debug("tplPack:" + str);
    this._$8 = new File(_$1(str, "dao_impl.txt"));
    this._$7 = new File(_$1(str, "dao.txt"));
    this._$5 = new File(_$1(str, "manager_impl.txt"));
    this._$6 = new File(_$1(str, "manager.txt"));
    this._$4 = new File(_$1(str, "action.txt"));
    this._$3 = new File(_$1(str, "page_list.txt"));
    this._$1 = new File(_$1(str, "page_add.txt"));
    this._$2 = new File(_$1(str, "page_edit.txt"));
  }

  private static void _$1(File paramFile, String paramString)
    throws IOException
  {
    FileUtils.writeStringToFile(paramFile, paramString, "UTF-8");
  }

  private void _$1()
  {
    try
    {
      if ("true".equals(this._$19.getProperty("is_dao")))
      {
        _$1(this._$16, _$1(this._$8));
        _$1(this._$15, _$1(this._$7));
      }
      if ("true".equals(this._$19.getProperty("is_manager")))
      {
        _$1(this._$13, _$1(this._$5));
        _$1(this._$14, _$1(this._$6));
      }
      if ("true".equals(this._$19.getProperty("is_action")))
        _$1(this._$12, _$1(this._$4));
      if ("true".equals(this._$19.getProperty("is_page")))
      {
        _$1(this._$11, _$1(this._$3));
        _$1(this._$9, _$1(this._$1));
        _$1(this._$10, _$1(this._$2));
      }
    }
    catch (IOException localIOException)
    {
      _$20.warn("write file faild! " + localIOException.getMessage());
    }
  }

  private String _$1(File paramFile)
  {
    String str1 = null;
    try
    {
      str1 = FileUtils.readFileToString(paramFile, "UTF-8");
      Set localSet = this._$19.keySet();
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        String str2 = (String)localObject;
        String str3 = this._$19.getProperty(str2);
        str1 = str1.replaceAll("\\#\\{" + str2 + "\\}", str3);
      }
    }
    catch (IOException localIOException)
    {
      _$20.warn("read file faild. " + localIOException.getMessage());
    }
    return str1;
  }

  private String _$1(String paramString1, String paramString2)
  {
    _$20.debug("replace:" + paramString1);
    String str = paramString1.replaceAll("\\.", "/");
    _$20.debug("after relpace:" + str);
    return "src/" + str + "/" + paramString2;
  }

  public void generate()
  {
    _$4();
    _$3();
    _$2();
    _$1();
  }

  public static void main(String[] paramArrayOfString)
  {
    String str1 = "com.jeecms.common.developer.template";
    String str2 = "template.properties";
    new ModuleGenerator(str1, str2).generate();
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.developer.ModuleGenerator
 * JD-Core Version:    0.6.2
 */