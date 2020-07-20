package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {

        String query = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());

                pstmt.executeUpdate();
            }
        };

        insertJdbcTemplate.execute(query);
    }

    void update(User user) throws SQLException {

        String query = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";

        JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());

                pstmt.executeUpdate();
            }
        };

        updateJdbcTemplate.execute(query);
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate sjt = new SelectJdbcTemplate() {
            @Override
            void setValues(PreparedStatement preparedStatement) throws SQLException {

            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }
        };

            // TODO 구현 필요함.
        return new ArrayList<>();
    }

    public User findByUserId(String userId) throws SQLException {

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        SelectJdbcTemplate sjt = new SelectJdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                User user = null;
                if (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }
                return user;
            }
        };

        return (User) sjt.queryForObject(sql);
    }
}
