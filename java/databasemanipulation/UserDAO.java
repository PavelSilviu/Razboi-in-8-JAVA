package databasemanipulation;

import java.sql.*;

public class UserDAO {
    public void initUserDB() throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "create table users ( id int, name varchar2(50), password int)")) {
            pstmt.executeUpdate();
        }
    }

    public void create(int idParam, String nameParam, int passParam) throws SQLException {
        Connection con = Database.getConnection();
        String st = "insert into users (id, name, password) values (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(
                st)) {
            pstmt.setInt(1, idParam);
            pstmt.setString(2, nameParam);
            pstmt.setInt(3, passParam);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from users where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public String findPassById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select password from users where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : "0";
        }
    }
}
