package com.bs.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<LineItem> items;
    private double totalAmount = 0;

    public Cart() {
        items = new ArrayList<LineItem>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item, String newItem) {
        String code = "" + item.getProduct().getbookId();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (("" + lineItem.getProduct().getbookId()).equals(code)) {
                if (newItem == null) {
                    double oldTotal = lineItem.getTotal();
                    lineItem.setQuantity(quantity);
                    setTotalAmount(totalAmount + (lineItem.getTotal() - oldTotal));
                    return;
                } else {
                    return;
                }
            }
        }
        items.add(item);
        setTotalAmount(totalAmount + item.getTotal());
    }

    public void removeItem(LineItem item) {
        String code = "" + item.getProduct().getbookId();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (("" + lineItem.getProduct().getbookId()).equals(code)) {
                setTotalAmount(totalAmount - lineItem.getTotal());
                items.remove(i);
                return;
            }
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getTotalAmountFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.totalAmount);
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
