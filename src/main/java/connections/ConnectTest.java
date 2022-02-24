package connections;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectTest {

    //获取数据库连接1
    @Test
    public void testConnection1() throws SQLException{
        // mysql的实现类对象
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/myemployees";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        Connection connection = driver.connect(url, info);

    }

    // 获取数据库连接方式2
    @Test
    public void testConnection2() throws ClassNotFoundException{
        // 反射获取Driver的实现类对象
        Class.forName("com.mysql.jdbc.Driver");




    }
}
