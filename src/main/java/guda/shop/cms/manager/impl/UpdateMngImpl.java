package guda.shop.cms.manager.impl;

import guda.shop.cms.manager.UpdateMng;
iimport guda.shopcommon.web.springmvc.RealPathResolver;
imimport guda.shopore.entity.Website;
impimport guda.shopre.manager.LogMng;
import com.jspgou.core.manager.WebsiteMng;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;

public class UpdateMngImpl
  implements UpdateMng
{
  public static final String UPDATE_PATH = ".zip";
  private String _$4;

  @Autowired
  private RealPathResolver _$3;

  @Autowired
  private LogMng _$2;

  @Autowired
  private WebsiteMng _$1;

  public void update()
  {
    this._$4 = (this._$3.get("/") + "update" + System.getProperty("file.separator"));
    long l = 1296000000L;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(localSimpleDateFormat.format(new Date()));
      Timer localTimer = new Timer();
      localTimer.schedule(new PlainTimerTask(), localDate, l);
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
  }

  public String getRestart()
  {
    String str1 = "/WEB-INF/config/jdbc.properties";
    String str5 = null;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(this._$3.get(str1));
      Properties localProperties = new Properties();
      localProperties.load(localFileInputStream);
      String str2 = localProperties.getProperty("jdbc.url");
      String[] arrayOfString = str2.split("[?]");
      String str3 = localProperties.getProperty("jdbc.username");
      String str4 = localProperties.getProperty("jdbc.password");
      Connection localConnection = getConn(arrayOfString[0], str3, str4);
      Statement localStatement = localConnection.createStatement();
      ResultSet localResultSet = localStatement.executeQuery("select * from jc_core_website ;");
      localResultSet.next();
      str5 = localResultSet.getString("restart");
      localStatement.close();
      localConnection.close();
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str5;
  }

  public Connection getConn(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Class.forName("com.mysql.jdbc.Driver");
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    String str = paramString1 + "?user=" + paramString2 + "&password=" + paramString3 + "&characterEncoding=utf8";
    Connection localConnection = DriverManager.getConnection(str);
    return localConnection;
  }

  public class PlainTimerTask extends TimerTask
  {
    public PlainTimerTask()
    {
    }

    public void run()
    {
      Website localWebsite = UpdateMngImpl.this._$1.findById(Long.valueOf(1L));
      String str1 = "http://update.jeecms.com/update.jhtml?version=" + getVersion() + "&domain=" + localWebsite.getDomain() + "&name=" + localWebsite.getName();
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      IllIlllIlllIIllI localIllIlllIlllIIllI = new IllIlllIlllIIllI(UpdateMngImpl.this, "UTF-8");
      try
      {
        HttpGet localHttpGet = new HttpGet(new URI(str1));
        String str2 = (String)localDefaultHttpClient.execute(localHttpGet, localIllIlllIlllIIllI);
        if (!StringUtils.isBlank(str2))
        {
          StringReader localStringReader = new StringReader(str2);
          InputSource localInputSource = new InputSource(localStringReader);
          SAXBuilder localSAXBuilder = new SAXBuilder();
          Document localDocument = localSAXBuilder.build(localInputSource);
          Element localElement = localDocument.getRootElement();
          List localList = localElement.getChildren();
          for (int i = 0; i < localList.size(); i++)
          {
            localElement = (Element)localList.get(i);
            String str3 = localElement.getChild("versions").getText();
            String str4 = localElement.getChild("updatepackage").getText();
            String str5 = localElement.getChild("updatelog").getText();
            download(str4, str3);
            UpdateMngImpl.this._$2.save(str3, str5);
          }
        }
      }
      catch (URISyntaxException localURISyntaxException)
      {
      }
      catch (ClientProtocolException localClientProtocolException)
      {
      }
      catch (IOException localIOException)
      {
      }
      catch (JDOMException localJDOMException)
      {
      }
    }

    public void download(String paramString1, String paramString2)
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpGet localHttpGet = new HttpGet(paramString1);
      try
      {
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
        StatusLine localStatusLine = localHttpResponse.getStatusLine();
        if (localStatusLine.getStatusCode() == 200)
        {
          String str = UpdateMngImpl.this._$4 + paramString2 + ".zip";
          File localFile = new File(str);
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          InputStream localInputStream = localHttpResponse.getEntity().getContent();
          byte[] arrayOfByte = new byte[1024];
          int i = 0;
          while ((i = localInputStream.read(arrayOfByte)) != -1)
            localFileOutputStream.write(arrayOfByte, 0, i);
          localFileOutputStream.flush();
          localFileOutputStream.close();
          unZipFiles(localFile, UpdateMngImpl.this._$4 + paramString2 + System.getProperty("file.separator"));
          Install(paramString2);
          replace(paramString2);
        }
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        localClientProtocolException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      finally
      {
        localDefaultHttpClient.getConnectionManager().shutdown();
      }
    }

    public void unZipFiles(File paramFile, String paramString)
      throws IOException
    {
      File localFile = new File(paramString);
      if (!localFile.exists())
        localFile.mkdirs();
      ZipFile localZipFile = new ZipFile(paramFile);
      Enumeration localEnumeration = localZipFile.getEntries();
      while (localEnumeration.hasMoreElements())
      {
        ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
        String str1 = localZipEntry.getName();
        InputStream localInputStream = localZipFile.getInputStream(localZipEntry);
        String str2 = (paramString + str1).replaceAll("\\*", "/");
        Object localObject;
        if (0 < str2.lastIndexOf('/'))
        {
          localObject = new File(str2.substring(0, str2.lastIndexOf('/')));
          if (!((File)localObject).exists())
            ((File)localObject).mkdirs();
          if (new File(str2).isDirectory());
        }
        else
        {
          localObject = new FileOutputStream(str2);
          byte[] arrayOfByte = new byte[1024];
          int i;
          while ((i = localInputStream.read(arrayOfByte)) > 0)
            ((OutputStream)localObject).write(arrayOfByte, 0, i);
          localInputStream.close();
          ((OutputStream)localObject).close();
        }
      }
    }

    public void Install(String paramString)
    {
      String str1 = "/WEB-INF/config/jdbc.properties";
      String str2 = "/update/" + paramString + "/db/update-to-" + paramString + ".sql";
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(UpdateMngImpl.this._$3.get(str1));
        Properties localProperties = new Properties();
        localProperties.load(localFileInputStream);
        String str3 = localProperties.getProperty("jdbc.url");
        String[] arrayOfString = str3.split("[?]");
        String str4 = localProperties.getProperty("jdbc.username");
        String str5 = localProperties.getProperty("jdbc.password");
        List localList = readSql(UpdateMngImpl.this._$3.get(str2));
        updateWebsite(arrayOfString[0], str4, str5);
        createTable(arrayOfString[0], str4, str5, localList);
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localFileNotFoundException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }

    public void updateWebsite(String paramString1, String paramString2, String paramString3)
      throws Exception
    {
      Connection localConnection = UpdateMngImpl.this.getConn(paramString1, paramString2, paramString3);
      Statement localStatement = localConnection.createStatement();
      String str = "update jc_core_website set version = '4.5'";
      localStatement.executeUpdate(str);
      str = "update jc_core_website set restart = '1'";
      localStatement.executeUpdate(str);
      localStatement.close();
      localConnection.close();
    }

    public void replace(String paramString)
    {
      String str = UpdateMngImpl.this._$4 + paramString + System.getProperty("file.separator") + "ROOT" + ".zip";
      File localFile = new File(str);
      try
      {
        unZipFiles(localFile, UpdateMngImpl.this._$3.get("/"));
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }

    public void createTable(String paramString1, String paramString2, String paramString3, List<String> paramList)
      throws Exception
    {
      Connection localConnection = UpdateMngImpl.this.getConn(paramString1, paramString2, paramString3);
      Statement localStatement = localConnection.createStatement();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localStatement.execute(str);
      }
      localStatement.close();
      localConnection.close();
    }

    public List<String> readSql(String paramString)
      throws Exception
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(paramString), "GBK"));
      ArrayList localArrayList = new ArrayList();
      StringBuilder localStringBuilder = new StringBuilder();
      String str = null;
      while ((str = localBufferedReader.readLine()) != null)
        if ((!str.startsWith("/*")) && (!str.startsWith("#")) && (!StringUtils.isBlank(str)))
          if (str.endsWith(";"))
          {
            localStringBuilder.append(str);
            localStringBuilder.setLength(localStringBuilder.length() - 1);
            localArrayList.add(localStringBuilder.toString());
            localStringBuilder.setLength(0);
          }
          else
          {
            localStringBuilder.append(str);
          }
      localBufferedReader.close();
      return localArrayList;
    }

    public String getVersion()
    {
      String str1 = "/WEB-INF/config/jdbc.properties";
      String str5 = null;
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(UpdateMngImpl.this._$3.get(str1));
        Properties localProperties = new Properties();
        localProperties.load(localFileInputStream);
        String str2 = localProperties.getProperty("jdbc.url");
        String[] arrayOfString = str2.split("[?]");
        String str3 = localProperties.getProperty("jdbc.username");
        String str4 = localProperties.getProperty("jdbc.password");
        Connection localConnection = UpdateMngImpl.this.getConn(arrayOfString[0], str3, str4);
        Statement localStatement = localConnection.createStatement();
        ResultSet localResultSet = localStatement.executeQuery("select * from jc_core_website ;");
        localResultSet.next();
        str5 = localResultSet.getString("version");
        localStatement.close();
        localConnection.close();
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localFileNotFoundException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return str5;
    }
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.UpdateMngImpl
 * JD-Core Version:    0.6.2
 */