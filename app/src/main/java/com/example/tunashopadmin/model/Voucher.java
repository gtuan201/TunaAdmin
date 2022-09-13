package com.example.tunashopadmin.model;

public class Voucher {
    String id, status,nameVoucher,subject,timeStart,timeCancel,amount,percent,minTotalPrice,maxOfPercent,type;

    public Voucher() {
    }

    public Voucher(String id, String status, String nameVoucher, String subject, String timeStart, String timeCancel, String amount, String percent, String minTotalPrice, String maxOfPercent, String type) {
        this.id = id;
        this.status = status;
        this.nameVoucher = nameVoucher;
        this.subject = subject;
        this.timeStart = timeStart;
        this.timeCancel = timeCancel;
        this.amount = amount;
        this.percent = percent;
        this.minTotalPrice = minTotalPrice;
        this.maxOfPercent = maxOfPercent;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeCancel() {
        return timeCancel;
    }

    public void setTimeCancel(String timeCancel) {
        this.timeCancel = timeCancel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getMinTotalPrice() {
        return minTotalPrice;
    }

    public void setMinTotalPrice(String minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public String getMaxOfPercent() {
        return maxOfPercent;
    }

    public void setMaxOfPercent(String maxOfPercent) {
        this.maxOfPercent = maxOfPercent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
