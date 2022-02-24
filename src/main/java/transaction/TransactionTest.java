package transaction;

import org.junit.Test;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    /**
     * 使用jdbc测试事务
     * */
    @Test
    public void testTx1() {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            String sql1 = "update account set money = money - ? where name = ?";
            String sql2 = "update account set money = money + ? where name = ?";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement1.setObject(1, 500);
            preparedStatement2.setObject(1, 500);
            preparedStatement1.setObject(2, "aa");
            preparedStatement2.setObject(2, "bb");

            preparedStatement1.executeUpdate();
            int i = 0 / 0;
            preparedStatement2.executeUpdate();

            connection.commit();
        } catch (Exception throwables) {
            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
            }
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement1, connection);
            JdbcUtils.close(preparedStatement2, null);
        }
        // 开启事务




    }
}
