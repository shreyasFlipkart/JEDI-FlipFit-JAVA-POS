package com.flipkart.business;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.GymCentreDAO;
import com.flipkart.bean.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.flipkart.constants.Constants.GREEN_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
import static com.flipkart.constants.Constants.*;

public class PaymentFlipfitImplService implements PaymentFlipfitServiceInterface {
    public static List<Payment> payments = new ArrayList<>();
    public static List<String> refunds = new ArrayList<>();
    public void makePayment(String userName, String gymCentreId, String bookingId){
        System.out.println(YELLOW_COLOR+"\nPayment initiated"+RESET_COLOR);
        Payment payment = new Payment();
        CustomerDAO customerDAO = new CustomerDAO();
        GymCentreDAO gymCentreDAO = new GymCentreDAO();
        payment.setPaymentId(String.valueOf(UUID.randomUUID()));
        payment.setCustomerId(customerDAO.getCustomerById(userName).getUserName());
        payment.setAmountPaid(String.valueOf(gymCentreDAO.getGymCentreByCentreId(gymCentreId).getPrice()));
        payment.setBookingId(bookingId);
        payments.add(payment);
        System.out.println(GREEN_COLOR + "Payment Successfull" + RESET_COLOR);
//        return true;
    }

    public void initiateRefund(String bookingId){
        System.out.println(YELLOW_COLOR+"\nInitiating refund..."+RESET_COLOR);
        payments.stream()
                .filter(payment -> payment.getBookingId().equals(bookingId) && !refunds.contains(bookingId))
                .findFirst()
                .ifPresent(payment -> {
                    System.out.println(GREEN_COLOR + "Refund successful for bookingId:" + RESET_COLOR + bookingId + "\n");
                    refunds.add(bookingId);
                });
//        return true;
    }

    public boolean verifyPayment(String paymentId){
        return payments.stream()
                .anyMatch(payment -> payment.getPaymentId().equals(paymentId));

    }
}
