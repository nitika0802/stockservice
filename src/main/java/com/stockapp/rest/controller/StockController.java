package com.stockapp.rest.controller;

import com.stockapp.rest.dao.StockDao;
import com.stockapp.rest.model.Stock;
import com.stockapp.rest.model.Stocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/stocks")
public class StockController {
    @Autowired
    private StockDao stockDao;

    @GetMapping(path = "", produces = "application/json")
    public Stocks getStocks(){
        return stockDao.getAllStocks();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Stock getStock(@PathVariable int id){
        return stockDao.getStockBasedOnId(id);
    }
}

