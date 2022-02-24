import org.junit.Test;
import utils.JdbcUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo3 {
    public Major queryForMajor(String sql, Object...args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        // 填充占位符
        for (int i = 0; i < args.length; i++) {
            // 从i开始设置
            statement.setObject(i+1, args[i]);
        }
        // 执行sql并获取结果集
        ResultSet resultSet = statement.executeQuery();
        // 获取结果集的元数据
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int colCount = resultSetMetaData.getColumnCount();
        Major major = new Major();
        if (resultSet.next()) {
            for (int i = 0; i < colCount; i++) {
                Object colValue = resultSet.getObject(i + 1);
                String colName = resultSetMetaData.getColumnName(i + 1);
                // 反射获取
                Class majorClass = major.getClass();
                Field field = majorClass.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(major, colValue);
            }
        }
        JdbcUtils.close(statement, connection);
        return major;
    }

    @Test
    public void commonQueryForMajorTest() {
        String querySql = "select majorId from major where majorId = ?";
        try {
            Major major = queryForMajor(querySql, 2);
            System.out.println(major);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testUpdate() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String updateSql = "UPDATE major SET majorname = ? WHERE majorid = ? ";
        PreparedStatement statement = connection.prepareStatement(updateSql);
        statement.setObject(1, "html4");
        statement.setObject(2, "2");
        statement.execute();
        connection.close();
    }

    @Test
    public void testQuery() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String querySql = "select * from major";
        PreparedStatement statement = connection.prepareStatement(querySql);
        ResultSet resultSet = statement.executeQuery(querySql);
        List<Major> majorList = new ArrayList();
        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            String majorName = resultSet.getString(2);
            Major major = new Major();
            major.setMajorId(id);
            major.setMajorName(majorName);
            majorList.add(major);
        }

        for (int i = 0; i < majorList.size(); i++) {
            System.out.println(majorList.get(i));
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("java.sql.DriverManager");
        // Driver类被加载的时候，已经注册了驱动了
        Class.forName("com.mysql.jdbc.Driver");
//        static {
//            try {
//                DriverManager.registerDriver(new Driver());
//            } catch (SQLException var1) {
//                throw new RuntimeException("Can't register driver!");
//            }
//        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy",  "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println();
            while (resultSet.next()) {
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
