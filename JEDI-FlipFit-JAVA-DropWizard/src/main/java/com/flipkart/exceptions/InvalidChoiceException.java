package com.flipkart.exceptions;

import java.io.InvalidObjectException;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class InvalidChoiceException extends InvalidObjectException {
    public InvalidChoiceException(){
        super(RED_COLOR+"Please enter valid choice"+RESET_COLOR);
    }
}
