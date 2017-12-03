/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.servlets;

import com.bs.business.Tune;
import com.bs.business.Category;
import com.bs.business.User;
import com.bs.constants.CommonConstants;
import com.bs.db.ProductDB;
import com.bs.db.UserDB;
import com.bs.util.PasswordUtil;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BooksAddUpdateController", urlPatterns = {"/BooksAddUpdateController"})
public class BooksAddUpdateController extends HttpServlet {

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

        try {
            HttpSession session = req.getSession();
            //  User userSession = (User) session.getAttribute(CommonConstants.USER_SESSION_KEY);
            User adminSession = (User) session.getAttribute(CommonConstants.ADMIN_SESSION_KEY);
            System.out.println("adminSession:" + adminSession);
            if (action == null) {
                url = "/home.jsp";
            } else if (action.equalsIgnoreCase(CommonConstants.ADD_CATEGORY)) {
                if (adminSession != null) {
                    String categName = req.getParameter("categName");
                    String desc = req.getParameter("description");
                    msg = ProductDB.addCategory(categName, desc);
                    url = "/admin.jsp";

                } else {
                    url = "/login.jsp";
                }

            } else if (action.equalsIgnoreCase(CommonConstants.EDIT_CATEGORY) || action.equalsIgnoreCase(CommonConstants.UPDATE_CATEGORY)) {
                if (adminSession != null) {
                    Category c = new Category();
                    c.setCategCode(Integer.parseInt(req.getParameter(CommonConstants.CATEGCODE)));
                    c.setCategName(req.getParameter("categName"));
                    c.setDescription(req.getParameter("description"));
                    if (action.equalsIgnoreCase(CommonConstants.EDIT_CATEGORY)) {
                        req.setAttribute("categ", c);
                        url = "/updateCategory.jsp";
                    } else {
                        msg = ProductDB.updateCategory(c);
                        url = "/editcategory.jsp";
                    }

                } else {
                    url = "/login.jsp";
                }

            } else if (action.equalsIgnoreCase(CommonConstants.ADD_BOOK)) {
                if (adminSession != null) {
                    String tempPath = req.getServletContext().getRealPath("/") + "\\images";
                    System.out.println("DOes temppath exists: " + new File(tempPath).exists());
                    new File(tempPath).mkdirs();
                    System.out.println("DOes temppath exists, 2nd check: " + new File(tempPath).exists());
                    MultipartRequest mpr = new MultipartRequest(req, tempPath);
                    File imgFile = mpr.getFile("image");
                    System.out.println(imgFile.getName());
                    Tune book = new Tune();
                    book.setTitle(mpr.getParameter("title").trim());
                    book.setAuthor(mpr.getParameter("author").trim());
                    book.setCategCode(Integer.parseInt(mpr.getParameter("category")));
                    book.setCover(imgFile.getName());
                    book.setIsbn(mpr.getParameter("isbn").trim());
                    book.setQuantity(Integer.parseInt(mpr.getParameter("quantity")));
                    book.setPrice(Double.parseDouble(mpr.getParameter("price")));
                    book.setPublisher(mpr.getParameter("publisher"));
                    book.setEdition(mpr.getParameter("edition").trim());
                    book.setDescritpion(mpr.getParameter("description"));
                    book.setPrice(Double.parseDouble(mpr.getParameter("price")));
                    book.setQuantity(Integer.parseInt(mpr.getParameter("quantity")));

                    msg = ProductDB.addTune(book);
                    System.out.println(msg);
//                msg = StudyDB.addStudy(study, count, f);
//                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
//                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                    url = "/admin.jsp";

                } else {
                    url = "/login.jsp";
                }
            } else if (action.equalsIgnoreCase(CommonConstants.EDIT_SEL_TUNE) || action.equalsIgnoreCase(CommonConstants.EDIT_TUNE)) {
                if (adminSession != null) {
                    if (action.equalsIgnoreCase(CommonConstants.EDIT_SEL_TUNE)) {
                        String bookId = req.getParameter(CommonConstants.BOOKID).trim();
                        Tune book = (Tune) ProductDB.getTunes(Integer.parseInt(bookId), true, false, false).get(0);
                        ArrayList cList = ProductDB.getCategories();
                        req.setAttribute("cList", cList);
                        req.setAttribute("book", book);
                        url = "/updateBook.jsp";
                    } else {
                        String tempPath = req.getServletContext().getRealPath("/") + "\\images";
                        System.out.println("DOes temppath exists: " + new File(tempPath).exists());
                        new File(tempPath).mkdirs();
                        System.out.println("DOes temppath exists, 2nd check: " + new File(tempPath).exists());
                        MultipartRequest mpr = new MultipartRequest(req, tempPath);
                        File imgFile = mpr.getFile("image");
                       System.out.println(imgFile);
                        Tune book = new Tune();
                        book.setbookId(Integer.parseInt(mpr.getParameter(CommonConstants.BOOKID).trim()));
                        book.setTitle(mpr.getParameter("title").trim());
                        book.setAuthor(mpr.getParameter("author").trim());
                        book.setCategCode(Integer.parseInt(mpr.getParameter("category")));
                        if (imgFile != null) {
                            book.setCover(imgFile.getName());
                        } else {
                            book.setCover(mpr.getParameter("oldImage"));
                        }
                        book.setIsbn(mpr.getParameter("isbn").trim());
                        book.setQuantity(Integer.parseInt(mpr.getParameter("quantity")));
                        book.setPrice(Double.parseDouble(mpr.getParameter("price")));
                        book.setPublisher(mpr.getParameter("publisher"));
                        book.setEdition(mpr.getParameter("edition").trim());
                        book.setDescritpion(mpr.getParameter("description"));
                        book.setPrice(Double.parseDouble(mpr.getParameter("price")));
                        book.setQuantity(Integer.parseInt(mpr.getParameter("quantity")));

                        msg = ProductDB.updateTune(book);
                        System.out.println(msg);
//                msg = StudyDB.addStudy(study, count, f);
//                ArrayList listOfStudies = StudyDB.getStudies(user.getEmail());
//                req.setAttribute(CommonConstants.STUDY_LIST, listOfStudies);
                        url = "/admin.jsp";
                    }

                } else {
                    url = "/login.jsp";
                }
            } else {
                //  url = "/home.jsp";
            }

            if (adminSession != null) {
                ArrayList categList = ProductDB.getCategories();
                req.setAttribute("categList", categList);
            }
            req.setAttribute("msg", msg);
//            if (url == null) {
//                url = "/home.jsp";
//            }
//            // resp.sendRedirect("login.jsp");
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
