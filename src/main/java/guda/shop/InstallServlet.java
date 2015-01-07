package guda.shop;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InstallServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    String str1 = paramHttpServletRequest.getParameter("dbHost");
    String str2 = paramHttpServletRequest.getParameter("dbPort");
    String str3 = paramHttpServletRequest.getParameter("dbName");
    String str4 = paramHttpServletRequest.getParameter("dbUser");
    String str5 = paramHttpServletRequest.getParameter("dbPassword");
    String str6 = paramHttpServletRequest.getParameter("isCreateDb");
    String str7 = paramHttpServletRequest.getParameter("isCreateTable");
    String str8 = paramHttpServletRequest.getParameter("isInitData");
    String str9 = paramHttpServletRequest.getParameter("domain");
    String str10 = paramHttpServletRequest.getParameter("cxtPath");
    String str11 = paramHttpServletRequest.getParameter("port");
    String str12 = paramHttpServletRequest.getParameter("dbFileName");
    String str13 = paramHttpServletRequest.getParameter("initFileName");
    String str14 = "/WEB-INF/config/jdbc.properties";
    String str15 = "/install/config/web.xml";
    String str16 = "/WEB-INF/web.xml";
    try
    {
      if ("true".equals(str6))
        Install.createDb(str1, str2, str3, str4, str5);
      else
        Install.changeDbCharset(str1, str2, str3, str4, str5);
      if ("true".equals(str7))
      {
        str17 = getServletContext().getRealPath(str12);
        localObject = Install.readSql(str17);
        Install.createTable(str1, str2, str3, str4, str5, (List)localObject);
      }
      if ("true".equals(str8))
      {
        str17 = getServletContext().getRealPath(str13);
        localObject = Install.readSql(str17);
        Install.createTable(str1, str2, str3, str4, str5, (List)localObject);
      }
      Install.updateConfig(str1, str2, str3, str4, str5, str9, str10, str11);
      String str17 = getServletContext().getRealPath(str14);
      Install.dbXml(str17, str1, str2, str3, str4, str5);
      Object localObject = getServletContext().getRealPath(str15);
      String str18 = getServletContext().getRealPath(str16);
      Install.webXml((String)localObject, str18);
    }
    catch (Exception localException)
    {
      throw new ServletException("install failed!", localException);
    }
    RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher("/install/install_setup.jsp");
    localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.InstallServlet
 * JD-Core Version:    0.6.2
 */