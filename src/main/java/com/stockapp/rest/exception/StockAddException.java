package com.stockapp.rest.exception;

public class StockAddException extends RuntimeException {
    public StockAddException(int id){
        super("Cannot add stock with id " + id);
    }
}
