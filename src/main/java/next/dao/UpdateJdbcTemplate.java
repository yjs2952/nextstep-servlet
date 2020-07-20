package next.dao;

import next.model.User;

import java.sql.SQLException;

public class UpdateJdbcTemplate {
    public void update(User user, UserDao userDao) throws SQLException {
        userDao.update(user);
    }
}
