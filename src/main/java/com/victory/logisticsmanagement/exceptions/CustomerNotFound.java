package com.victory.logisticsmanagement.exceptions;

public class CustomerNotFound extends RuntimeException{

    public CustomerNotFound(String message){
        super(message);
    }
    public CustomerNotFound(String message,Throwable cause){
        super(message,cause);
    }
    public CustomerNotFound(Throwable cause){
        super(cause);
    }

}
