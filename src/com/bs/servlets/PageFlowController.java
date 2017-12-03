/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.servlets;

import com.bs.business.Tune;
import com.bs.business.Category;
import com.bs.business.LineItem;
import com.bs.business.User;
import com.bs.constants.CommonConstants;
import com.bs.db.ProductDB;
import com.bs.db.UserDB;
import com.bs.util.MailUtil;
import com.bs.util.PasswordUtil;
import static com.bs.util.PasswordUtil.hashPassword;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
@WebServlet(name = "PageFlowController", urlPatterns = {"/PageFlowController"})
public class PageFlowController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("actionParam");
        System.out.println("action param:" + action);
        String url = null;
        String msg = "";
        ArrayList bList = null;
        String pageHeading ="NewlyAdded Tunes";//"BestSelling Books";

        try {
            HttpSession session = req.getSession();
            User userSession = (User) session.getAttribute(CommonConstants.USER_SESSION_KEY);
            User adminSession = (User) session.getAttribute(CommonConstants.ADMIN_SESSION_KEY);
            String adminLinkSel = "";
            if (action == null) {
            		System.out.println("Came to render home page");
                url = "/home.jsp";
            } else if (action.equalsIgnoreCase("home")) {
                if (userSession != null) {
                    url = "/main.jsp";
                } else if (adminSession != null) {
                    url = "/admin.jsp";
                } else {
                    url = "/home.jsp";
                }
            } else if (action.equalsIgnoreCase("about")) {
                if (userSession != null) {
                    url = "/about.jsp";
                } else if (adminSession != null) {
                    url = "/admin.jsp";
                } else {
                    url = "/about.jsp";
                }
            } else if (action.equalsIgnoreCase("contact")) {
                url = "/contactus.jsp";
            } /*else if (action.equalsIgnoreCase("query")) {
                //String name = req.getParameter("name");
                //String comments = req.getParameter("comments");
                //String email = req.getParameter("email");
                //long queryNum = sendEmail(name, email, comments);
                //req.setAttribute("queryNum", queryNum);
                //System.out.println("queryNum" + queryNum);
                url = "/contactcnfrm.jsp";

            } */else if (action.equalsIgnoreCase("login")) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                //To retrieve salt from DB and create hashedsaltpassword to validate.
                String salt = UserDB.getSaltFromDB(email);
                System.out.println("Salt:" + salt);
                User user = null;
                if (salt != null) {
                    System.out.println("Login:password + salt:" + password + salt);
                    String saltedAndHashedPassword = PasswordUtil.hashPassword(password + salt);
                    System.out.println("saltedAndHashedPassword:" + saltedAndHashedPassword);
                    user = UserDB.getUser(email, saltedAndHashedPassword, true);
                    System.out.println("user:" + user);
                }
                if (user == null) {
                    msg = "ERROR: Invalid login Id or password.";
                    url = "/login.jsp";
                } else {
                    if (user.getType().equalsIgnoreCase(CommonConstants.TYPE_BUYER)) {
                        url = "/main.jsp";
                        session.setAttribute(CommonConstants.USER_SESSION_KEY, user);
                    } else {
                        url = "/admin.jsp";
                        session.setAttribute(CommonConstants.ADMIN_SESSION_KEY, user);
                    }

                }

            } else if (action.equalsIgnoreCase("goToLogin")) {
                url = "/login.jsp";
            } else if (action.equalsIgnoreCase("goToSignUp")) {
                url = "/signup.jsp";
            } else if (action.equalsIgnoreCase("logout")) {

                if (userSession != null || adminSession != null) {
                    session.removeAttribute(CommonConstants.USER_SESSION_KEY);
                    session.removeAttribute(CommonConstants.ADMIN_SESSION_KEY);
                    msg="Successfully logged you out.";
                    url = "/login.jsp";

                }
            } else if (action.equalsIgnoreCase("create")) {
                String email = req.getParameter("email");
                String name = req.getParameter("name");
                String password = req.getParameter("password");
                String confirm_password = req.getParameter("confirm_password");
                String addr1 = req.getParameter("addr1");
                String addr2 = req.getParameter("addr2");
                if (password.length() < 8) {
                    msg = "ERROR: Password is to short. Must be at least 8 characters long.";
                    url = "/signup.jsp";
                } else if (!password.equals(confirm_password)) {
                    msg = "ERROR: Password doesn't match confirm password.";
                    url = "/signup.jsp";
                } else {
                    // hash and salt password
                    String salt = "";
                    String saltedAndHashedPassword;

                    salt = PasswordUtil.getSalt();
                    System.out.println("salt:" + salt);
                    System.out.println("Reg:password + salt:" + password + salt);
                    saltedAndHashedPassword = PasswordUtil.hashPassword(password + salt);
                    System.out.println(saltedAndHashedPassword);

                    User user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setAddr1(addr1);
                    user.setAddr2(addr2);
                    user.setType("Buyer");
                    user.setSalt(salt);

                    int isNewUser = UserDB.addUser(user, saltedAndHashedPassword);
                    //System.out.println("isnew:" + isNewUser);
                    if (isNewUser == 0) {
                        msg = "ERROR: Account for this email id already exists.";
                        url = "/signup.jsp";
                    } else {
                        url = "/main.jsp";
                        session.setAttribute(CommonConstants.USER_SESSION_KEY, user);
                    }

                }
            } else if (action.equalsIgnoreCase(CommonConstants.GOTO_ADD_CATEGORY)) {
                if (adminSession != null) {
                    url = "/addcategory.jsp";
                    adminLinkSel = CommonConstants.GOTO_ADD_CATEGORY;
                    
                } else {
                    url = "/home.jsp";
                }
            } else if (action.equalsIgnoreCase(CommonConstants.GOTO_EDIT_CATEGORY)) {
                if (adminSession != null) {
                    url = "/editcategory.jsp";
                    ArrayList categList = ProductDB.getCategories();
                    adminLinkSel = CommonConstants.GOTO_EDIT_CATEGORY;
                    req.setAttribute("categList", categList);
                } else {
                    url = "/home.jsp";
                }
            } else if (action.equalsIgnoreCase(CommonConstants.GOTO_ADD_TUNE)) {
                if (adminSession != null) {
                    ArrayList cList = ProductDB.getCategories();
                    req.setAttribute("cList", cList);
                    adminLinkSel = CommonConstants.GOTO_ADD_TUNE;
                    url = "/addbook.jsp";
                } else {
                    url = "/home.jsp";
                }
            } else if (action.equalsIgnoreCase(CommonConstants.GOTO_EDIT_TUNE)) {
                if (adminSession != null) {
                    adminLinkSel = CommonConstants.GOTO_EDIT_TUNE;
                    String categCode = req.getParameter(CommonConstants.CATEGCODE);
                    ArrayList booksList = null;
                    if (categCode == null) {
                        booksList = ProductDB.getTunes(0, false, false, false);
                        pageHeading = "Update Books";
                    } else {
                        String categName = req.getParameter("categName");
                        booksList = ProductDB.getTunes(Integer.parseInt(categCode), false, true, false);
                       pageHeading = categName+" Tunes";
                    }
                    req.setAttribute("categCodeSelected", categCode);
                    req.setAttribute("pageHeading", pageHeading);
                    req.setAttribute("booksList", booksList);
                    url = "/editBookSelection.jsp";
                } else {
                    url = "/home.jsp";
                }
            } else if (action.equalsIgnoreCase(CommonConstants.SEARCH_BY_CATEG)) {

                if (userSession != null) {
                    url = "/main.jsp";
                } else {
                    url = "/home.jsp";
                }
                String categoryName = req.getParameter("categName");                                
                pageHeading = categoryName + " category Tunes...";
                String categCode = req.getParameter(CommonConstants.CATEGCODE);
                bList = ProductDB.getTunes(Integer.parseInt(categCode), false, true, false);
                req.setAttribute("categCodeSelected", categCode);
//                    url = "/main.jsp";
                if (bList == null) {
                    msg = "Search Error.";
                    bList = new ArrayList();
                }
                if (bList != null && bList.size() == 0) {
                    msg = "No result found.";
                }
//                } else {
//                    url = "/home.jsp";
//                }
            } else if (action.equalsIgnoreCase(CommonConstants.SEARCH_BOX)) {
                
                pageHeading = "Search Result...";
                if (userSession != null) {
                    url = "/main.jsp";
                } else {
                    url = "/home.jsp";
                }
                String searchbox = req.getParameter("searchbox");
                String category = req.getParameter("category");
                if (category.equalsIgnoreCase("All")) {
                    bList = ProductDB.searchTunes(searchbox, 0, true);
                } else {
                    bList = ProductDB.searchTunes(searchbox, Integer.parseInt(category), false);
                }
                if (bList == null) {
                    msg = "Search Error.";
                    bList = new ArrayList();
                }
                if (bList != null && bList.size() == 0) {
                    msg = "No result found.";
                }

//                    url = "/main.jsp";
//                } else {
//                    url = "/login.jsp";
//                }
            } else if (action.equalsIgnoreCase(CommonConstants.SELECT_TUNE)) {
//                if (userSession != null) {
                    String tid = req.getParameter(CommonConstants.BOOKID);
                    Tune tune = (Tune) ProductDB.getTunes(Integer.parseInt(tid), true, false, false).get(0);
                    req.setAttribute("book", tune);
                    url = "/productdetail.jsp";
//                } else {
//                    url = "/login.jsp";
//                }
            } else {

                url = "/home.jsp";
            }
            req.setAttribute("msg", msg);
            if (url == null) {
                url = "/home.jsp";
            }
            //Rechecking session for user/admin session Id. Because just after login it would be null initially.
            userSession = (User) session.getAttribute(CommonConstants.USER_SESSION_KEY);
            adminSession = (User) session.getAttribute(CommonConstants.ADMIN_SESSION_KEY);

            if (userSession != null || adminSession != null) {
                if (userSession != null && bList == null) {
                    bList = ProductDB.getTunes(0, false, false, true);//retrieve latest 10 books.
                    req.setAttribute("bList", bList);
                } else {
                    req.setAttribute("bList", bList);
                }
                ArrayList categList = ProductDB.getCategories();
                req.setAttribute("categList", categList);
            }
            if (bList == null) {
               // bList = ProductDB.getBooks(0, false, false, true);
            }//retrieve latest 10 books.
            System.out.println("adminLinkSel:" + adminLinkSel);
            req.setAttribute("adminLinkSel",adminLinkSel );
            req.setAttribute("bList", bList);
            ArrayList categList = ProductDB.getCategories();
            req.setAttribute("categList", categList);
            req.setAttribute("pageHeading", pageHeading);

            getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long sendEmail(String name, String email, String comments) {
        String[] recipients = new String[]{"abcbookstorenbad@gmail.com"};
        String[] bccRecipients = new String[]{};
        long num = System.currentTimeMillis();
        String subject = "New Inquiry - " + num;

        String messageBody = "<h2>From: " + name + "</h2>"
                + "<br/><h3>Email address: " + email + "</h3>"
                + "<br/><h3>Comments:</h3><p>" + comments + "</p>";
        boolean isEmailSent = new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody, true);
        System.out.println("isEmailSent:" + isEmailSent);

        recipients = new String[]{email};
        subject = "Regarding your query - " + num;
        messageBody = "Hi," + name + "<br/>"
                + "<p>Thank you for submitting a query, we will try to answer your query as soon as possible.</p>"
                + "<br/> Thanks, <br/> ABC BookStore.";
        isEmailSent = new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody, true);
        return num;
    }

}
