package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplateDemo1 {

    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new ComboPooledDataSource());
//        jdbcTemplate.exec ute("select * from account");
    }
}
