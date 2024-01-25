package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class BookingFailedException extends RuntimeException{
    public BookingFailedException(String message){
        super(RED_COLOR+message+RESET_COLOR);
    }
}
