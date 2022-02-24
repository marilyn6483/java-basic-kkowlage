import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Demo2 {

    public static void main(String[] args) throws SQLException {
        // 1. 创建数据库连接池对象
        DataSource dataSource = new ComboPooledDataSource();
        // 2. 获取连接
        for (int i = 1; i <= 6; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println(i + ": " + connection);
        }
// 验证最大连接数
//        1: com.mchange.v2.c3p0.impl.NewProxyConnection@1055e4af
//        2: com.mchange.v2.c3p0.impl.NewProxyConnection@e6ea0c6
//        3: com.mchange.v2.c3p0.impl.NewProxyConnection@5577140b
//        4: com.mchange.v2.c3p0.impl.NewProxyConnection@67f89fa3
//        5: com.mchange.v2.c3p0.impl.NewProxyConnection@277c0f21
//        Exception in thread "main" java.sql.SQLException: An attempt by a client to checkout a Connection has timed out.
//        at com.mchange.v2.sql.SqlUtils.toSQLException(SqlUtils.java:106)
//        at com.mchange.v2.sql.SqlUtils.toSQLException(SqlUtils.java:65)

        // 3.成功连接

        //com.mchange.v2.c3p0.impl.NewProxyConnection@1055e4af
    }
}
