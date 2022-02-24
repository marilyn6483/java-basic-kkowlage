import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo2 {

    public static List<Major> getResult(ResultSet resultSet) {
        List<Major> result = new ArrayList<Major>();

        while (true) {
            try {
                if (!resultSet.next()) break;
                else {
                    Major major = new Major();
                    int majorId = resultSet.getInt(1);
                    String majorName = resultSet.getString(2);
                    major.setMajorId(majorId);
                    major.setMajorName(majorName);
                    result.add(major);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///eesy", "root", "root");
            stmt = conn.createStatement();
            String sql = "select * from major";
            resultSet = stmt.executeQuery(sql);
            System.out.println(resultSet);
            // ResultSet是结果集对象的封装
            // 使用next方法遍历对象
//            resultSet.next();
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            float money = resultSet.getFloat(3);
//            System.out.println(id);
//            System.out.println(name);
//            System.out.println(money);
            List<Major> res = getResult(resultSet);
            System.out.println(res);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
