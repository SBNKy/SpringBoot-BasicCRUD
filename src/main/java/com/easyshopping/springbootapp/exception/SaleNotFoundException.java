package com.easyshopping.springbootapp.exception;

public class SaleNotFoundException extends RuntimeException {

    public SaleNotFoundException(String message) {
        super(message);
    }

    public SaleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaleNotFoundException(Throwable cause) {
        super(cause);
    }
}
