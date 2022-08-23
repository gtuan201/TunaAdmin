package com.example.tunashopadmin.model;

import java.util.List;

public class Order {
    String id,uid,fullname,address,shopname,totalprice,phoneNumber,purchaseMethod,status,date,time,timeCompleteOrder;

    public Order() {
    }

    public Order(String id, String uid, String fullname, String address, String shopname, String totalprice, String phoneNumber, String purchaseMethod, String status, String date, String time, String timeCompleteOrder) {
        this.id = id;
        this.uid = uid;
        this.fullname = fullname;
        this.address = address;
        this.shopname = shopname;
        this.totalprice = totalprice;
        this.phoneNumber = phoneNumber;
        this.purchaseMethod = purchaseMethod;
        this.status = status;
        this.date = date;
        this.time = time;
        this.timeCompleteOrder = timeCompleteOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(String purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeCompleteOrder() {
        return timeCompleteOrder;
    }

    public void setTimeCompleteOrder(String timeCompleteOrder) {
        this.timeCompleteOrder = timeCompleteOrder;
    }
}
