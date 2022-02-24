import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {

    public static void main(String[] args) throws Exception {
        // 1. 导入jar包
        // 2. 注册驱动，将MySQL的driver类加载进入内存
        Class.forName("com.mysql.jdbc.Driver");
        // 3. 类被加载后会执行下面一段静态代码块，注册驱动
        /*
        * static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
        * */
        // 获取连接对象 驱动管理对象 DriverManager
        // 功能1：注册驱动
        // 功能2：创建连接
        // connection对象的作用
        // 1.获取执行sql对象
        // 2.管理事务 开启 提交 回滚
        // 3.
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "root");
        // 需要执行的sql语句
        String sql1 = "update account set money = 10000 where id = 1";
        String sql2 = "insert into account (name, money) values ('cc', '2000')";
        String sql3 = "delete from account where name = 'cc'";
        String sql4 = "update account set money = 10000 where id = 1";
        // 获取执行sql的对象， 执行sql的对象
        // Statement对象：用于执行静态sql语句，并返回其结果对象
        // 执行语句的方法：executeUpdate -> 返回受影响的行数，int类型
        //               executeQuery -> 执行查询语句，返回结果集 ResultSet
        Statement statement = connection.createStatement();
//        Thread.sleep(10000);

        int count = statement.executeUpdate(sql3);
        System.out.println(count);
        // 释放资源
        statement.close();
        connection.close();

        //ResultSet 结果集

        // PreparedStatement




    }
}
