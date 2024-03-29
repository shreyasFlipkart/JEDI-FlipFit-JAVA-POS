package com.flipkart.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    private static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Disable lenient parsing

        try {
            Date date = dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isFutureDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date inputDate = dateFormat.parse(dateStr);
            Date currentDate = new Date();
            return inputDate.after(currentDate);
        } catch (ParseException e) {
        }
        return true;
    }

    public static boolean isDateValid(String dateStr) {
        return isValidDate(dateStr) && !isFutureDate(dateStr);
    }


    public boolean isTimeValid(String time) {
        String regex = "/^(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)$/gm";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public boolean isEmailValid(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPhoneValid(String phone) {
        if(phone.length() != 10) return false;
        String regex = "(0|91)?[6-9][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean isCardValid(String cardNumber){
        return (cardNumber.length()==12);
    }

    public boolean isPanValid(String panNumber){
        String regex = "^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(panNumber);
        return matcher.matches();
    }

    public boolean isGstValid(String gstNumber){
        String regex = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gstNumber);
        return matcher.matches();
    }

}