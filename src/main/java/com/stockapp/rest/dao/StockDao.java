package com.stockapp.rest.dao;

import com.stockapp.rest.model.Stock;
import com.stockapp.rest.model.Stocks;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class StockDao {
    private static Stocks stocks = new Stocks();

    static {
        stocks.getStockList().add(new Stock(1, "Tata Motors", 149.50, new Timestamp(1569950608)));
        stocks.getStockList().add(new Stock(2, "State Bank of India", 180.00, new Timestamp(1569950700)));
        stocks.getStockList().add(new Stock(3, "Infosys Limited", 550.20, new Timestamp(1569950728)));
        stocks.getStockList().add(new Stock(4, "TV 18 Broadcast", 82.10, new Timestamp(1569950751)));
        stocks.getStockList().add(new Stock(5, "ABN AMRO", 865.40, new Timestamp(1569950786)));
    }

    public Stocks getAllStocks() {
        return stocks;
    }

    public void addStock(Stock stock) {
        stocks.getStockList().add(stock);
    }

    public Stock getStockBasedOnId(int id) {
        Stock resultStock = null;
        for (Stock stock : stocks.getStockList()) {
            if (stock.getId() == id) {
                resultStock = stock;
                break;
            }
        }
        return resultStock;
    }

    public void updateStock(Stock stockToUpdate) {
        if (stocks.getStockList() != null && containsStock(stockToUpdate.getId())) {
            for (Stock stock : stocks.getStockList()) {
                if (stock.getId() == stockToUpdate.getId()) {
                    stock.setCurrentPrice(stockToUpdate.getCurrentPrice());
                    break;
                }
            }
        }
    }

    public boolean containsStock(int id) {
        boolean check = false;
        for (Stock stock : stocks.getStockList()) {
            if (stock.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }
}
