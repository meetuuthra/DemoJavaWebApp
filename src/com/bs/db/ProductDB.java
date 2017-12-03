package com.bs.db;

import com.bs.business.Tune;
import com.bs.business.Cart;
import com.bs.business.Category;
import com.bs.business.LineItem;
import com.bs.business.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import nbad.business.QuestionReport;
//import nbad.business.Study;
//import nbad.business.User;
//import nbad.constants.CommonConstants;

public class ProductDB {

    public static String addCategory(String categName, String desc) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "insert into Category(cName, cDescription) values(?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, categName);
            ps.setString(2, desc);
            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "New Category addedd successfully.";
            } else {
                return "Unable to add new Category.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "Duplicate entry: Category name already exists.";
            } else {
                return e.getMessage();
            }
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String updateCategory(Category c) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "update Category set cName =?, cDescription=? where categoryCode = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, c.getCategName());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getCategCode());
            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "Category updated successfully.";
            } else {
                return "Unable to update Category.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "Duplicate entry: Category name already exists.";
            } else {
                return e.getMessage();
            }
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String addTune(Tune tune) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            //    FileInputStream fis = new FileInputStream(file);
//'image',
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            System.out.println("sqlDate:" + sqlDate);
            String query = "insert into Book (categoryCode,title, author,coverName,"
                    + " isbn, quantity,price,publisher,edition,description, addedOn) "
                    + " values(?, ?, ?, ?,?, ?, ?, ?,?, ?,?) "; //imgFile,published
            ps = connection.prepareStatement(query);
            ps.setInt(1, tune.getCategCode());
            ps.setString(2, tune.getTitle());
            ps.setString(3, tune.getAuthor());
            ps.setString(4, tune.getCover());
            ps.setString(5, tune.getIsbn());
            ps.setInt(6, tune.getQuantity());
            ps.setDouble(7, tune.getPrice());
            ps.setString(8, tune.getPublisher());
            ps.setString(9, tune.getEdition());
            ps.setString(10, tune.getDescritpion());
            ps.setDate(11, sqlDate);
//            ps.setString(11, null);
            //            ps.setBinaryStream(12, fis, (int) file.length());
            //          ps.setString(11, );
            //ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "New book addedd successfully.";
            } else {
                return "Unable to add new tune.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
                return "Duplicate entry: Tune name already exists.";
            } else {
                return e.getMessage();
            }
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String updateTune(Tune tune) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "update Book set categoryCode=?, title=?, author=?, coverName=?, "
                    + " isbn=?, quantity=?, price=?, publisher=?, edition=?, description=? where bookId = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, tune.getCategCode());
            ps.setString(2, tune.getTitle());
            ps.setString(3, tune.getAuthor());
            ps.setString(4, tune.getCover());
            ps.setString(5, tune.getIsbn());
            ps.setInt(6, tune.getQuantity());
            ps.setDouble(7, tune.getPrice());
            ps.setString(8, tune.getPublisher());
            ps.setString(9, tune.getEdition());
            ps.setString(10, tune.getDescritpion());
            ps.setInt(11, tune.getbookId());

            int isReportAdded = ps.executeUpdate();
            System.out.println(isReportAdded);
            if (isReportAdded > 0) {
                return "Tune details updated successfully.";
            } else {
                return "Unable to update Tune details.";
            }
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate")) {
//                return "Duplicate entry: Tune name already exists.";
                return e.getMessage();
            } else {
                return e.getMessage();
            }
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList getCategories() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "select * from Category order by cName";
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList cList = new ArrayList();
            while (rs.next()) {
                Category c = new Category();
                c.setCategCode(rs.getInt("categoryCode"));
                c.setCategName(rs.getString("cName"));
                System.out.println("cat names:" + rs.getString(1));
                c.setDescription(rs.getString("cDescription"));
                cList.add(c);
                
            }
            return cList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList getTunes(int id, boolean byTid, boolean byCid, boolean byDateLatest) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode order  by B.categoryCode";
            if (byDateLatest) {
                query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode order by B.addedOn desc limit 10";
            }
            if (byTid) {
                query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode  and B.bookId=?  order  by B.categoryCode";
            }
            if (byCid) {
                query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode  and B.categoryCode=?  order  by B.categoryCode";
            }
            ps = connection.prepareStatement(query);
            if (byTid || byCid) {
                ps.setInt(1, id);
            }
            ResultSet rs = ps.executeQuery();
            ArrayList tList = new ArrayList();
            while (rs.next()) {
                Tune b = new Tune();
                b.setCategName(rs.getString("cName"));
                b.setbookId(rs.getInt("bookId"));
                b.setCategCode(rs.getInt("categoryCode"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCover(rs.getString("coverName"));
                b.setIsbn(rs.getString("isbn"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPrice(rs.getDouble("price"));
                b.setPublisher(rs.getString("publisher"));
                b.setEdition(rs.getString("edition"));
                b.setDescritpion(rs.getString("description"));

                tList.add(b);

            }
            return tList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList searchTunes(String searchbox, int cid, boolean allCategories) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode and B.title like ? order by B.title";

            if (!allCategories) {
                query = "select C.cName, B.* from Book B, Category C where B.categoryCode = C.categoryCode and  B.title like ? and B.categoryCode=? order by B.title";
            }
            System.out.println(cid);
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + searchbox.trim() + "%");
            if (!allCategories) {
                ps.setInt(2, cid);
            }
            ResultSet rs = ps.executeQuery();
            ArrayList bList = new ArrayList();
           
            while (rs.next()) {
                Tune b = new Tune();
                b.setCategName(rs.getString("cName"));
                b.setbookId(rs.getInt("bookId"));
                b.setCategCode(rs.getInt("categoryCode"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCover(rs.getString("coverName"));
                b.setIsbn(rs.getString("isbn"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPrice(rs.getDouble("price"));
                b.setPublisher(rs.getString("publisher"));
                b.setEdition(rs.getString("edition"));
                b.setDescritpion(rs.getString("description"));

                bList.add(b);
                System.out.println("bList:" + bList);

            }
            return bList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void updateTunesQuantity(ArrayList<LineItem> items) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            for (LineItem lineItem : items) {
                String query = "update Book B set B.quantity = (B.quantity-?) where B.bookId =?";

                ps = connection.prepareStatement(query);
                ps.setInt(1, lineItem.getQuantity());
                ps.setInt(2, lineItem.getProduct().getbookId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static long addTuneOrder(User userSession, Cart cart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            long orderNum = System.currentTimeMillis();
            ArrayList<LineItem> items = cart.getItems();

            String query = "insert into Invoice(email, orderNum, orderTotal) values(?,?,?)";

            ps = connection.prepareStatement(query);
            ps.setString(1, userSession.getEmail());
            ps.setString(2, orderNum + "");
            ps.setDouble(3, cart.getTotalAmount());
            ps.executeUpdate();
            for (LineItem lineItem : items) {
                Tune book = lineItem.getProduct();
                String qry = "insert into orderItems(orderNum,bid,quantity,price,amount) values(?,?,?,?,?)";
                ps = connection.prepareStatement(qry);
                ps.setString(1, orderNum + "");
                ps.setInt(2, book.getbookId());
                ps.setInt(3, lineItem.getQuantity());
                ps.setDouble(4, book.getPrice());
                ps.setDouble(5, lineItem.getTotal());
                ps.executeUpdate();
            }
            return orderNum;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {

            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
}
