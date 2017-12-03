package com.bs.db;

import java.sql.*;
import javax.sql.DataSource;//javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class ConnectionPool {
    
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStoreDB","root","");//?autoReconnect=true&user=root&password=1.Nbad_root");
        System.out.println("Connection object instance : " + con);
    }
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murachdb");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
           // return dataSource.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStoreDB","root","");//?autoReconnect=true&user=root&password=1.Nbad_root");//("jdbc:mysql://127.0.0.1:3306/BookStoreDB?autoReconnect=true&user=root&password=1.Nbad_root");
       System.out.println("connection:"+con);
       return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}