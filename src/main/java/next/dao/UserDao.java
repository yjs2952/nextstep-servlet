package next.dao;

import next.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    public void insert(User user) throws SQLException {

        String query = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JdbcTemplate insertJdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
        };

        insertJdbcTemplate.update(query, pss);
    }

    public void update(User user) throws SQLException {

        String query = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";

        JdbcTemplate updateJdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());

            pstmt.executeUpdate();
        };

        updateJdbcTemplate.update(query, pss);
    }

    public List<User> findAll() throws SQLException {

        String sql = "SELECT userId, password, name, email FROM USERS";

        JdbcTemplate sjt = new JdbcTemplate();

        PreparedStatementSetter setter = preparedStatement -> {};

        RowMapper rowMapper = this::getUser;

        return sjt.query(sql, setter, rowMapper).stream()
                .map(o -> (User) o)
                .collect(Collectors.toList());
    }

    private User getUser(ResultSet rs) throws SQLException {
        return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email"));
    }

    public User findByUserId(String userId) throws SQLException {

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        JdbcTemplate sjt = new JdbcTemplate();

        PreparedStatementSetter setter = pstmt -> pstmt.setString(1, userId);

        RowMapper rowMapper = this::getUser;

        return (User) sjt.queryForObject(sql, setter, rowMapper);
    }
}
