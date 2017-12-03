package com.bs.business;

import java.beans.*;
import java.io.Serializable;

public class Category implements Serializable {
    private int categCode;
    private String categName;
    private String description;

    public Category() {
        categName = "";
        description = "";
    }

    public int getCategCode() {
        return categCode;
    }

    public void setCategCode(int categCode) {
        this.categCode = categCode;
    }

    public String getCategName() {
        return categName;
    }

    public void setCategName(String categName) {
        this.categName = categName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
