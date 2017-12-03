package com.bs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {
    
    /*  This code uses SHA-256. If this algorithm isn't available to you,
        you can try a weaker level of encryption such as SHA-128.
    */    
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {        
       MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }        
        return sb.toString(); 
//        return DigestUtils.sha256Hex(password);
    }
    
    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    public static String hashAndSaltPassword(String password)
            throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password + salt);
    }
    
    public static void checkPasswordStrength(String password) throws Exception {
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password cannot be empty.");
        } else if (password.length() < 8) {
            throw new Exception("Password is to short. " +
                    "Must be at least 8 characters long.");
        }
    }

    public static boolean validatePassword(String password) {
        try {
            checkPasswordStrength(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /*  This code tests the functionality of this class.
    */    
    public static void main(String[] args) {
        try {
//            System.out.println("Hash for 'sesame':\n"
//                    + hashPassword("sesame"));
//            System.out.println("Random salt:\n"
//                    + getSalt());
//            System.out.println("Salted hash for 'sesame':\n"
//                    + hashAndSaltPassword("sesame"));     
String salt = getSalt();
            System.out.println("salt:" + salt);
for(int i=0; i<10; i++){
    System.out.println(hashPassword("Admin" + salt));
}
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
        
        try {
            checkPasswordStrength("sesame1776");
            System.out.println("Password is valid.");
            System.out.println("pwd: " + DigestUtils.sha256Hex("sesame1776"));;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}