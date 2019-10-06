package com.stockapp.rest.exception;

public class StockUpdateException extends RuntimeException {
    public StockUpdateException(int id){
        super("Cannot update stock with id " + id);
    }
}
