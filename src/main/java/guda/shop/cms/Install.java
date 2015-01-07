package guda.shop.cms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Install
{
  public static final String UTF8 = "UTF-8";

  public static void dbXml(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    throws Exception
  {
    String str = FileUtils.readFileToString(new File(paramString1));
    str = StringUtils.replace(str, "DB_HOST", paramString2);
    str = StringUtils.replace(str, "DB_PORT", paramString3);
    str = StringUtils.replace(str, "DB_NAME", paramString4);
    str = StringUtils.replace(str, "DB_USER", paramString5);
    str = StringUtils.replace(str, "DB_PASSWORD", paramString6);
    FileUtils.writeStringToFile(new File(paramString1), str);
  }

  public static Connection getConn(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    Class.forName("com.mysql.jdbc.Driver");
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    String str = "jdbc:mysql://" + paramString1 + ":" + paramString2 + "/" + paramString3 + "?user=" + paramString4 + "&password=" + paramString5 + "&characterEncoding=utf8";
    Connection localConnection = DriverManager.getConnection(str);
    return localConnection;
  }

  public static void webXml(String paramString1, String paramString2)
    throws Exception
  {
    FileUtils.copyFile(new File(paramString1), new File(paramString2));
  }

  public static void createDb(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    Class.forName("com.mysql.jdbc.Driver");
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    String str1 = "jdbc:mysql://" + paramString1 + ":" + paramString2 + "?user=" + paramString4 + "&password=" + paramString5 + "&characterEncoding=UTF8";
    Connection localConnection = DriverManager.getConnection(str1);
    Statement localStatement = localConnection.createStatement();
    String str2 = "drop database if exists " + paramString3;
    localStatement.execute(str2);
    str2 = "create database " + paramString3 + " CHARACTER SET UTF8";
    localStatement.execute(str2);
    localStatement.close();
    localConnection.close();
  }

  public static void changeDbCharset(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws Exception
  {
    Connection localConnection = getConn(paramString1, paramString2, paramString3, paramString4, paramString5);
    Statement localStatement = localConnection.createStatement();
    String str = "ALTER DATABASE " + paramString3 + " CHARACTER SET UTF8";
    localStatement.execute(str);
    localStatement.close();
    localConnection.close();
  }

  public static void createTable(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList)
    throws Exception
  {
    Connection localConnection = getConn(paramString1, paramString2, paramString3, paramString4, paramString5);
    Statement localStatement = localConnection.createStatement();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      System.out.println(str);
      localStatement.execute(str);
    }
    localStatement.close();
    localConnection.close();
  }

  public static void updateConfig(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    throws Exception
  {
    Connection localConnection = getConn(paramString1, paramString2, paramString3, paramString4, paramString5);
    Statement localStatement = localConnection.createStatement();
    String str = "update jc_core_website set domain='" + paramString6 + "'";
    localStatement.executeUpdate(str);
    str = "update jc_core_global set context_path='" + paramString7 + "',port=" + paramString8;
    localStatement.executeUpdate(str);
    localStatement.close();
    localConnection.close();
  }

  public static List<String> readSql(String paramString)
    throws Exception
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(paramString), "UTF-8"));
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
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.Install
 * JD-Core Version:    0.6.2
 */