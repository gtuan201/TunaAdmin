package com.example.tunashopadmin.model;

public class User {
    String imgUrl,shopManage,uid,typeUser,name,phone;
    String online;

    public User() {
    }

    public User(String imgUrl, String shopManage, String uid, String typeUser, String name, String phone, String online) {
        this.imgUrl = imgUrl;
        this.shopManage = shopManage;
        this.uid = uid;
        this.typeUser = typeUser;
        this.name = name;
        this.phone = phone;
        this.online = online;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShopManage() {
        return shopManage;
    }

    public void setShopManage(String shopManage) {
        this.shopManage = shopManage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
