package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    void values(PreparedStatement preparedStatement) throws SQLException;
}