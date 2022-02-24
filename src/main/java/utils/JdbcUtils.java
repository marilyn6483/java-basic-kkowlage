package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

    private static String user;
    private static String password;
    private static String url;
    private static DataSource ds;
    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
//            URL resource = classLoader.getResource("jdbc.properties");
//            Properties pro = new Properties();
            try {
                // 不这样写
//                pro.load(new FileInputStream(resource.getPath()));
//                url = pro.getProperty("url");
//                user = pro.getProperty("user");
//                password = pro.getProperty("password");
                ds = new ComboPooledDataSource();
                // 配置文件必须交c3p0-config.xml
                // 空参，自动到classpath目录下面加载“c3p0-config.xml”配置文件---配置文件的存储位置和名称必须是这样，且使用“默认配置”
            } catch (Exception e) {
                e.printStackTrace();
            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static Connection getConnection() throws SQLException {

        return ds.getConnection();

    }

    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void closeR() {

    }

    public static void main(String[] args) throws SQLException {
        JdbcUtils.getConnection();
    }
}
