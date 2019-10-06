package com.stockapp.rest.controller;

import com.stockapp.rest.dao.StockDao;
import com.stockapp.rest.exception.StockAddException;
import com.stockapp.rest.exception.StockNotFoundException;
import com.stockapp.rest.exception.StockUpdateException;
import com.stockapp.rest.model.Stock;
import com.stockapp.rest.model.Stocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/api/stocks")
public class StockController {
    @Autowired
    private StockDao stockDao;

    @GetMapping(path = "", produces = "application/json")
    public Stocks getStocks(){
        try{
            return stockDao.getAllStocks();
        }
        catch (Exception ex){
            throw new StockNotFoundException();
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Stock getStock(@PathVariable int id){
        try {
            return stockDao.getStockBasedOnId(id);
        }
        catch (Exception ex){
            throw new StockNotFoundException(id);
        }
    }

    @PostMapping(path="",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> addStock(@RequestBody Stock stock){
        try{
            int id = stockDao.getAllStocks().getStockList().size() + 1;
            stock.setId(id);
            Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
            stock.setLastUpdate(lastUpdate);
            stockDao.addStock(stock);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        catch (Exception ex){
            throw new StockAddException(stock.getId());
        }
    }

    @PutMapping(path="/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> updateStock(@PathVariable int id, @RequestBody Stock stock){
        try{
            Stock stock1 = stockDao.getStockBasedOnId(id);
            stock1.setCurrentPrice(stock.getCurrentPrice());
            Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
            stock1.setLastUpdate(lastUpdate);
            stockDao.updateStock(stock1);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock1.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        catch (Exception ex){
            throw new StockUpdateException(id);
        }
    }
}

