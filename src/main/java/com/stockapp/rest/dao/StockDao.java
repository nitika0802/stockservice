package com.stockapp.rest.dao;

import com.stockapp.rest.model.Stock;
import com.stockapp.rest.model.Stocks;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class StockDao {
    private static Stocks stocks = new Stocks();

    //This block adds 5 stocks to the stock list in memory, upon initialization
    static {
        stocks.getStockList().add(new Stock(1, "Tata Motors", 149.50, new Timestamp(System.currentTimeMillis())));
        stocks.getStockList().add(new Stock(2, "State Bank of India", 180.00, new Timestamp(System.currentTimeMillis())));
        stocks.getStockList().add(new Stock(3, "Infosys Limited", 550.20, new Timestamp(System.currentTimeMillis())));
        stocks.getStockList().add(new Stock(4, "TV 18 Broadcast", 82.10, new Timestamp(System.currentTimeMillis())));
        stocks.getStockList().add(new Stock(5, "ABN AMRO", 865.40, new Timestamp(System.currentTimeMillis())));
    }

    /**
     * This method fetches the list of stocks available
     * @return Stocks object
     */
    public Stocks getAllStocks() {
        return stocks;
    }

    /**
     * This method adds the Stock object passed as a parameter to the stock list created in memory
     * @param stock
     */
    public void addStock(Stock stock) {
        stocks.getStockList().add(stock);
    }

    /**
     * Returns stock object based on the stock id, from the stock list
     * @param id
     * @return Stock object
     */
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

    /**
     * This method updates the CurrentPrice of the stock based on the Stock object passed as a parameter
     * @param stockToUpdate
     */
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
