package com.flipkart.business;

/**
 * Interface defining the contract for Flipfit services related to payment operations.
 */

public interface PaymentFlipfitServiceInterface {
    /**
     * Method to make payment to confirm the slot booking
     * @param userName
     * @param gymCentreId
     * @param bookingId
     */
    public void makePayment(String userName, String gymCentreId, String bookingId);
    /**
     * Method to initiate refund for the cancelled slot
     * @param bookingId
     */
    public void initiateRefund(String bookingId);
    /**
     * Method to verify the payment status
     * @param paymentId
     * @return boolean value of the payment status(True or False)
     */
    public boolean verifyPayment(String paymentId);
}
