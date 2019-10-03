package com.stockapp.rest.controller;

import com.stockapp.rest.dao.StockDao;
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
        return stockDao.getAllStocks();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Stock getStock(@PathVariable int id){
        return stockDao.getStockBasedOnId(id);
    }
    @PostMapping(path="",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> addStock(@RequestBody Stock stock){
        int id = stockDao.getAllStocks().getStockList().size() + 1;
        stock.setId(id);
        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
        stock.setLastUpdate(lastUpdate);
        stockDao.addStock(stock);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path="/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> updateStock(@PathVariable int id, @RequestBody Stock stock){
        Stock stock1 = stockDao.getStockBasedOnId(id);
        stock1.setCurrentPrice(stock.getCurrentPrice());
        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
        stock1.setLastUpdate(lastUpdate);
        stockDao.updateStock(stock1);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}

