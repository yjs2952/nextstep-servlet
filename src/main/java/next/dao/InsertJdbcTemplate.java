package next.dao;

import next.model.User;

import java.sql.SQLException;

public class InsertJdbcTemplate {
    public void insert(User user, UserDao userDao) throws SQLException {
        userDao.insert(user);
    }
}
