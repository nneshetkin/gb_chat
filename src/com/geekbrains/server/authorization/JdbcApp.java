package com.geekbrains.server.authorization;
import java.sql.*;
//HW2
public class JdbcApp {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        stmt = connection.createStatement();
    }
    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  static String auth(String login,String pass) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login = ? and password=?");
        ps.setString(1, login);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }

    }

}

