package com.example.tunashopadmin.model;

public class Coffee {
    String urlImg, coffeeName, note, size, category,id,ice,quantity,totalPrice;

    public Coffee() {
    }

    public Coffee(String urlImg, String coffeeName, String note, String size, String category, String id, String ice, String quantity, String totalPrice) {
        this.urlImg = urlImg;
        this.coffeeName = coffeeName;
        this.note = note;
        this.size = size;
        this.category = category;
        this.id = id;
        this.ice = ice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
