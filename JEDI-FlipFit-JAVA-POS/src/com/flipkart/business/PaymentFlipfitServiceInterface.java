package com.flipkart.business;

public interface PaymentFlipfitServiceInterface {
    public void makePayment(String userName, String gymCentreId, String bookingId);
    public void initiateRefund(String bookingId);
    public boolean verifyPayment(String paymentId);
}
