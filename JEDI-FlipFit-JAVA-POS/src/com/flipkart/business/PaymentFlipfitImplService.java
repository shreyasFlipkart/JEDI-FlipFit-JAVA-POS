package com.flipkart.business;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.GymCentreDAO;
import com.flipkart.bean.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentFlipfitImplService implements PaymentFlipfitServiceInterface {
    public static List<Payment> payments = new ArrayList<>();
    public static List<String> refunds = new ArrayList<>();
    public void makePayment(String userName, String gymCentreId, String bookingId){
        System.out.println("\nPayment initiated");
        Payment payment = new Payment();
        CustomerDAO customerDAO = new CustomerDAO();
        GymCentreDAO gymCentreDAO = new GymCentreDAO();
        payment.setPaymentId(String.valueOf(UUID.randomUUID()));
        payment.setCustomerId(customerDAO.getCustomerById(userName).getUserName());
        payment.setAmountPaid(String.valueOf(gymCentreDAO.getGymCentreByCentreId(gymCentreId).getPrice()));
        payment.setBookingId(bookingId);
        payments.add(payment);
        System.out.println("Payment Successfull");
//        return true;
    }

    public void initiateRefund(String bookingId){
        System.out.println("\nInitiating refund...");
        for(Payment payment: payments){
            if(payment.getBookingId().equals(bookingId) && !refunds.contains(bookingId)){
                System.out.println("Refund successfull for bookingId:" + bookingId);
                refunds.add(bookingId);
            }
        }
//        return true;
    }

    public boolean verifyPayment(String paymentId){
        for(Payment payment: payments){
            if(payment.getPaymentId().equals(paymentId)){
                return true;
            }
        }
        return false;
    }
}
