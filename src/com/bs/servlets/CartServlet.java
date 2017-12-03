package com.bs.servlets;

import com.bs.business.Tune;
import com.bs.business.Cart;
import com.bs.business.LineItem;
import com.bs.business.User;
import com.bs.constants.CommonConstants;
import com.bs.db.ProductDB;
import com.bs.util.MailUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("actionParam");
        System.out.println("action param:" + action);
        String url = null;
        String msg = "";
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(CommonConstants.USER_SESSION_KEY);
        try {

            if (action == null || userSession == null) {
                url = "/home.jsp";
            } else if (action.equals("shop")) {
                url = "/main.jsp";    // the home page of user.
            } else if (action.equals("cart")) {
                String productCode = request.getParameter("bookId");

                String quantityString = request.getParameter("quantity");
                String newProduct = request.getParameter("newItem");
                Cart cart = (Cart) session.getAttribute("cart");
                if (productCode != null) {
                    Tune book = (Tune) ProductDB.getTunes(Integer.parseInt(productCode), true, false, false).get(0);

                    if (cart == null) {
                        cart = new Cart();
                    }

                    //if the user enters a negative or invalid quantity,
                    //the quantity is automatically reset to 1.
                    int quantity;
                    try {
                        quantity = Integer.parseInt(quantityString);
                        if (quantity < 0) {
                            quantity = 1;
                        }
                    } catch (NumberFormatException nfe) {
                        quantity = 1;
                    }

                    LineItem lineItem = new LineItem();
                    lineItem.setProduct(book);
                    lineItem.setQuantity(quantity);
                    if (quantity > 0) {
                        cart.addItem(lineItem, newProduct);

                    } else if (quantity == 0) {
                        cart.removeItem(lineItem);
                    }
                    System.out.println("updated-Quantity:" + lineItem.getQuantity());
                }
                session.setAttribute("cart", cart);
                url = "/cart.jsp";
            } else if (action.equals("checkout")) {
                url = "/checkout.jsp";
            } else if (action.equals("payment")) {
                // To reduce the quantity
                Cart cart = (Cart) session.getAttribute("cart");
                request.setAttribute("orderConfrmNum", " ");
                request.setAttribute("cnfrmEmail", "");
                if (cart.getItems().size() > 0) {
                    ProductDB.updateTunesQuantity(cart.getItems());
                   long orderConfrmNum =  ProductDB.addTuneOrder(userSession,cart);
                    sendOrderConfirmationEmail(userSession, cart,orderConfrmNum );
                    
                    request.setAttribute("orderConfrmNum", orderConfrmNum);
                    request.setAttribute("cnfrmEmail", "Confirmation email has been sent to your email address " + userSession.getEmail()+".");
                }
                Cart cartForSummary = cart;
                session.setAttribute("cart", new Cart());
                request.setAttribute("cart", cartForSummary);
                url = "/paymentconfirmation.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  if (userSession != null) {
            ArrayList bList = ProductDB.getTunes(0, false, false, true);//retrieve latest 10 books.
            request.setAttribute("bList", bList);

            ArrayList categList = ProductDB.getCategories();
            request.setAttribute("categList", categList);
      //  }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private long sendOrderConfirmationEmail(User user, Cart cart, long orderNum) {
        String[] recipients = new String[]{user.getEmail()};
        String[] bccRecipients = new String[]{};
        
        String subject = "Books Order num "+orderNum+" Confirmation"  ;
        StringBuffer sb = new StringBuffer();
        sb.append("<table border='1' cellpadding='0'>");
        sb.append("<thead><tr>");
        sb.append("<th>Book</th>");
        sb.append("<th>Quantity</th>");
        sb.append("<th>Price per Book</th>");
        sb.append("<th>Amount</th>");
        sb.append("</tr></thead><tbody>");
        for (LineItem lineItem : cart.getItems()) {
            lineItem.getQuantity();
            Tune book = lineItem.getProduct();
            sb.append("<tr>");
            sb.append("<td>" + book.getTitle() + "</td>");
            sb.append("<td>" + lineItem.getQuantity() + "</td>");
            sb.append("<td>$" + book.getPrice() + "</td>");
            sb.append("<td>" + lineItem.getTotalCurrencyFormat() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody><tfoot><tr> <td colspan='3' align='right'>Total:</td> <td>" + cart.getTotalAmountFormat() + "</td></tr></tfoot>");
        sb.append("</table>");

        String messageBody = "<h2>Thank you, " + user.getName() + ".</h2>"
                + "<br/>" + "<p>Your order confirmation number is " + orderNum + ".</p>"
                + "<br/><h3>Order Summary:</h3><br/>" + sb.toString() + "<br/><br/> Thanks,<br/> ABC BookStore.";
        boolean isEmailSent = new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody, true);
        System.out.println("isEmailSent:" + isEmailSent);
        return orderNum;
    }

}
