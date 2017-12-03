package com.bs.business;

import java.beans.*;
import java.io.Serializable;

public class Tune implements Serializable {

    private int bookId;
    private int categCode;
    private String categName;
    private String title;
    private String titleShort;
    private String author;
    private String authorShort;
    private String cover;
    private String isbn;
    private String publisher;
    private String published;
    private String edition;
    private String descritpion;
    private String descriptionShort;
    private double price;
    private String priceString;
    private String priceFirst;
    private String priceLast;
    private int quantity;

    public Tune() {
        categName = "";
        title = "";
        author = "";
        cover = "";
        isbn = "";
        publisher = "";
        edition = "";
        descritpion = "";
        published = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title.length() > 13) {
            setTitleShort(title.substring(0, 13) + "...");
        }
        else{
            setTitleShort(title);
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        if (author.length() > 13) {
            setAuthorShort(author.substring(0, 13) + "...");
        }
        else{
            setAuthorShort(author);
        }
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
        if(descritpion.length() > 80){
            setDescriptionShort(descritpion.substring(0,80)+ "...");
        }
        else{
            setDescriptionShort(descritpion);
        }
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        setPriceString(this.price + "");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getbookId() {
        return bookId;
    }

    public void setbookId(int bookId) {
        this.bookId = bookId;
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

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        if (priceString.length() == 1) {
            setPriceFirst("0" + priceString);
            setPriceLast("00");
        } else if (priceString.length() == 2) {
            setPriceFirst(priceString);
            setPriceLast("00");
        } else if (priceString.length() == 3) {
            setPriceFirst(priceString.substring(0, 2));
            setPriceLast("00");
        } else if (priceString.length() == 4) {
            setPriceFirst(priceString.substring(0, 2));
            setPriceLast(priceString.substring(3) + "0");
        } else if (priceString.length() == 5) {
            setPriceFirst(priceString.substring(0, 2));
            setPriceLast(priceString.substring(3));
        }

        this.priceString = getPriceFirst() + "." + getPriceLast();
    }

    public String getPriceFirst() {
        return priceFirst;
    }

    public void setPriceFirst(String priceFirst) {
        this.priceFirst = priceFirst;
    }

    public String getPriceLast() {
        return priceLast;
    }

    public void setPriceLast(String priceLast) {
        this.priceLast = priceLast;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getAuthorShort() {
        return authorShort;
    }

    public void setAuthorShort(String authorShort) {
        this.authorShort = authorShort;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

}
