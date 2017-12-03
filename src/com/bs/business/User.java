package com.bs.business;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String type;
    private String addr1;
    private String addr2;
    private String salt;


    public User() {
        this.name = "";
        this.email = "";
        this.type = "";
        this.addr1 = "";
        this.addr2 = "";
        this.salt = "";
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    
}
