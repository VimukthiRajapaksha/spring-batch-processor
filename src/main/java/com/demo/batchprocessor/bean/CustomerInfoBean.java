package com.demo.batchprocessor.bean;


public class CustomerInfoBean {

    private String cardNumber;
    private String mobileNumber;
    private String email;
    private String expDate;
    private String status;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerInfoBean{" +
                "cardNumber='" + cardNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", expDate='" + expDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public CustomerInfoBean() {
    }

    public CustomerInfoBean(String cardNumber, String mobileNumber, String email) {
        this.cardNumber = cardNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
}
