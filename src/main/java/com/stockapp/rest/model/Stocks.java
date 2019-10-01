package com.stockapp.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Stocks {
    private List<Stock> stockList;

    public List<Stock> getStockList(){
        if(stockList == null){
            stockList = new ArrayList<>();
        }
        return stockList;
    }

    public void setStockList(List<Stock> stockList){
        this.stockList = stockList;
    }

}
