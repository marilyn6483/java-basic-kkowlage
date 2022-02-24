import com.sun.org.apache.xpath.internal.operations.Or;
import entity.Order;
import org.junit.Test;
import utils.JdbcUtils;

import java.lang.reflect.Field;
import java.sql.*;

public class OrderForQuery {

    /**
     * 通用查询操作
     * @return order
     * 注意：表的列名和Java类的属性名不一致的情况下，查询语句需要使用别名，获取查询结果的列名的时候获取列的别名
     */
    public Order orderForQuery(String sql, Object... args) {
        Order order = new Order();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtils.getConnection();

            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int colCount = resultSetMetaData.getColumnCount();
            if (resultSet.next()) {
                for (int i = 0; i < colCount; i++) {
                    Object colValue = resultSet.getObject(i + 1);
                    // 获取类的别名
                    String colName = resultSetMetaData.getColumnLabel(i + 1);
                    System.out.println(colName);
                    // 反射获取名称为colName的字段
                    Field field = order.getClass().getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(order, colValue);
                }
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            JdbcUtils.close(statement, connection);
        }


        return order;
    }

    @Test
    public void testQuery2() {
        String sql = "select order_id as orderId, order_name as orderName, order_date as orderDate from t_order where order_id = ?";
        System.out.println(orderForQuery(sql, 7));

    }

    @Test
    public void testQuery1() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "select order_id as orderId, order_name as orderName, order_date as orderDate from t_order where order_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        // 填充占位符
        statement.setObject(1, 6);
        ResultSet resultSet = statement.executeQuery();
        Order order = new Order();
        if (resultSet.next()) {
            int orderId = (Integer) resultSet.getObject(1);
            String orderName = (String) resultSet.getObject(2);
            Date orderDate = (Date) resultSet.getObject(3);
            order.setOrderId(orderId);
            order.setOrderName(orderName);
            order.setOrderDate(orderDate);
            System.out.println(order);
        }

        JdbcUtils.close(statement, connection);
    }

}
