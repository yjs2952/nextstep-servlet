package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class JdbcTemplate {

    List<Object> query(String sql, PreparedStatementSetter setter, RowMapper rowMapper) throws SQLException {

        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
        ) {
            setter.values(pstmt);

            rs = pstmt.executeQuery();

            List<Object> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rowMapper.mapRow(rs));
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return null;
    }

    Object queryForObject(String sql, PreparedStatementSetter setter, RowMapper rowMapper) throws SQLException {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ){

            setter.values(pstmt);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return null;
    }

    void update(String query, PreparedStatementSetter setter) throws SQLException {
        try (
                Connection con = ConnectionManager.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query);){
            setter.values(pstmt);
        }
    }
}
