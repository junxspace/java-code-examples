/**
 * @ProjectName : java-code-snippet
 * @FileName : JDBCSnippet.java
 * @PackageName : net.junx.codesnippet
 * @Date : Nov 8, 201610:28:42 AM
 * @Author : junX (junxspace@hotmail.com)
 */
package net.junx.codesnippet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author junX yangwenjun@jianzhimao.com
 * @date Nov 8, 2016 10:28:42 AM <br/>
 * @version
 * @since JDK 1.8
 */
public class JDBCSnippet {

  public static void main(String[] args) throws Exception {
    String driverClass = "oracle.jdbc.driver.OracleDriver";
    Connection con;
    
    String url = "<db.url>";
    String userName = "<db.user>";
    String password = "<db.password>";
    
    
    Class.forName(driverClass);

    con = DriverManager.getConnection(url, userName, password);
    PreparedStatement ps = con.prepareStatement("SELECT SYSDATE FROM DUAL");
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
      // do the thing you do
    }
    rs.close();
    ps.close();
  }
}
