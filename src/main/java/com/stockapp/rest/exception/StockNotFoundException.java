package com.stockapp.rest.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(int id){
        super("Stock Id not found" + id);
    }
    public StockNotFoundException(){
        super("Stocks not found");
    }
}
