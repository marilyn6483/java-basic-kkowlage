import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class C3p0Demo1 {
    public static void main(String[] args) throws SQLException {
        // 1. 创建数据库连接池对象
        DataSource dataSource = new ComboPooledDataSource();
        // 2. 获取连接
        Connection connection = dataSource.getConnection();
        // 3.成功连接
        System.out.println(connection);
        //com.mchange.v2.c3p0.impl.NewProxyConnection@1055e4af
    }
}
