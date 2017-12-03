package com.bs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bs.business.User;

public class UserDB {

    public static User getUser(String email, String password, boolean isValidating) {
        ConnectionPool pool = ConnectionPool.getInstance();

        Connection connection = pool.getConnection();
        System.out.println("Connection:" + connection);
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Email = ? And Password= ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            if (isValidating) {
                ps.setString(2, password);
            }
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setType(rs.getString("Type"));
                user.setAddr1(rs.getString("Address1"));
                user.setAddr2(rs.getString("Address2"));

            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String getSaltFromDB(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();

        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "select Salt from  User where Email =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();            
            if (rs.next()) {
                 return rs.getString(1);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int addUser(User user, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO User (Name,Email, Password, Salt , type, Address1, Address2) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, password);
            ps.setString(4, user.getSalt());
            ps.setString(5, user.getType());
            ps.setString(6, user.getAddr1());
            ps.setString(7, user.getAddr2());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public ArrayList<User> getUsers() {
        return null;
    }

}
