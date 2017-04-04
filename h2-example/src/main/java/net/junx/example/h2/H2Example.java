  
package net.junx.example.h2;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
 
/** 
 * @author	junx
 * @date	2015年11月26日上午9:51:30 
 * @since 	JDK 1.8
 */

public class H2Example {
    public static void main(String[] args) throws Exception {
//        embedded();
        server();
    }

    private static void server() throws SQLException, ClassNotFoundException {
        Server tcpServer = Server.createTcpServer("-tcpPort","9067","-tcpAllowOthers").start();
        Server webServer = Server.createWebServer("-webPort","9068","-webAllowOthers").start();  
     
        String url = "jdbc:h2:tcp://localhost:9067/~/h2/test2;DB_CLOSE_ON_EXIT=FALSE;MODE=ORACLE;AUTO_RECONNECT=TRUE";
        String username = "sa";
        String password = "";
        String driver = "org.h2.Driver";
        
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stat = conn.createStatement();
        stat.execute("CREATE TABLE TEMP(ID INT)");
        stat.close();
        conn.close();
    }

    private static void embedded() throws Exception {
        String url = "jdbc:h2:~/h2/test1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL;AUTO_RECONNECT=TRUE";
        String username = "sa";
        String password = "";
        String driver = "org.h2.Driver";
        
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stat = conn.createStatement();
        stat.execute("CREATE TABLE TEMP(ID INT)");
        stat.close();
        conn.close();
    }
}