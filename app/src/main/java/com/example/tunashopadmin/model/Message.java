package com.example.tunashopadmin.model;

public class Message {
    String message,time,date,senderID;

    public Message() {
    }

    public Message(String message, String time, String date, String senderID) {
        this.message = message;
        this.time = time;
        this.date = date;
        this.senderID = senderID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
}
