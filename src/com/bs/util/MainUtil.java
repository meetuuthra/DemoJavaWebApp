/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.util;

/**
 *
 * @author pc
 */
public class MainUtil {

    public static void main(String[] args) {
        String[] recipients = new String[]{"azs9015@gmail.com"};
        String[] bccRecipients = new String[]{};
        String subject = "Books Order Confirmation";
        String messageBody = "Test Mail from codesstore.blogspot.com";

        new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody, false);
    }
}
