package com.example.tunashopadmin.model;

public class Chat {
    String id,imgReceiver, nameReceiver,lastMessage,timeLastMessage,uidReceiver;

    public Chat() {
    }

    public Chat(String id, String imgReceiver, String nameReceiver, String lastMessage, String timeLastMessage, String uidReceiver) {
        this.id = id;
        this.imgReceiver = imgReceiver;
        this.nameReceiver = nameReceiver;
        this.lastMessage = lastMessage;
        this.timeLastMessage = timeLastMessage;
        this.uidReceiver = uidReceiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgReceiver() {
        return imgReceiver;
    }

    public void setImgReceiver(String imgReceiver) {
        this.imgReceiver = imgReceiver;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getTimeLastMessage() {
        return timeLastMessage;
    }

    public void setTimeLastMessage(String timeLastMessage) {
        this.timeLastMessage = timeLastMessage;
    }

    public String getUidReceiver() {
        return uidReceiver;
    }

    public void setUidReceiver(String uidReceiver) {
        this.uidReceiver = uidReceiver;
    }
}
