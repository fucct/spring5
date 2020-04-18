package spring.dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQuery {
    private DataSource dataSource;

    public DbQuery(final DataSource datasource) {
        this.dataSource = datasource;
    }

    public int count(){
        try (Connection connection = dataSource.getConnection()){
            try(Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select count(*) from Member"))
            {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
