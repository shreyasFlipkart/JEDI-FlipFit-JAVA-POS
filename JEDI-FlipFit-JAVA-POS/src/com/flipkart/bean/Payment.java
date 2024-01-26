package com.flipkart.bean;

public class Payment {

    private String paymentId;
    private String amountPaid;
    private String customerId;
    private String bookingId;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentId() { return paymentId; }

    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getAmountPaid() { return amountPaid; }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCustomerId() { return customerId; }

    public void setCustomerId(String bookingId) { this.customerId = bookingId; }
}
