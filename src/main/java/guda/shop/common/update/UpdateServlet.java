package guda.shop.common.update;

import guda.shop.cms.manager.UpdateMng;
import guda.shop.common.web.springmvc.RealPathResolver;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class UpdateServlet extends HttpServlet {
    public void init() {
        Install();
    }

    public void Install() {
        ServletContext localServletContext = getServletContext();
        WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(localServletContext);
        RealPathResolver localRealPathResolver = (RealPathResolver) localWebApplicationContext.getBean("realPathResolver");
        UpdateMng localUpdateMng = (UpdateMng) localWebApplicationContext.getBean("updateMng");
        localUpdateMng.update();
        String str1 = "/WEB-INF/config/jdbc.properties";
        try {
            FileInputStream localFileInputStream = new FileInputStream(localRealPathResolver.get(str1));
            Properties localProperties = new Properties();
            localProperties.load(localFileInputStream);
            String str2 = localProperties.getProperty("jdbc.url");
            String[] arrayOfString = str2.split("[?]");
            String str3 = localProperties.getProperty("jdbc.username");
            String str4 = localProperties.getProperty("jdbc.password");
            createTable(arrayOfString[0], str3, str4);
        } catch (FileNotFoundException localFileNotFoundException) {
            localFileNotFoundException.printStackTrace();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public void createTable(String paramString1, String paramString2, String paramString3)
            throws Exception {
        Connection localConnection = getConn(paramString1, paramString2, paramString3);
        Statement localStatement = localConnection.createStatement();
        localStatement.execute("UPDATE jc_core_website SET restart = '0';");
        localStatement.close();
        localConnection.close();
    }

    public Connection getConn(String paramString1, String paramString2, String paramString3)
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String str = paramString1 + "?user=" + paramString2 + "&password=" + paramString3 + "&characterEncoding=utf8";
        Connection localConnection = DriverManager.getConnection(str);
        return localConnection;
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.update.UpdateServlet
 * JD-Core Version:    0.6.2
 */