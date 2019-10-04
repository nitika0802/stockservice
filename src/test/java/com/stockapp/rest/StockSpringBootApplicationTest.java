package com.stockapp.rest;

import com.stockapp.rest.controller.StockController;
import com.stockapp.rest.dao.StockDao;
import com.stockapp.rest.model.Stock;
import com.stockapp.rest.model.Stocks;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockSpringBootApplicationTest {
@Autowired
    private TestRestTemplate testRestTemplate;
@LocalServerPort
    int randomServerPort;
@Autowired
    private StockDao stockDao;

@Test
    public void testAddStockSuccess() throws URISyntaxException{
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/stocks";
        URI uri = new URI(baseUrl);
        Stock stock = new Stock(6,"PNB Bank",190.23, new Timestamp(System.currentTimeMillis()));
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST","true");
        HttpEntity<Stock> request = new HttpEntity<>(stock,headers);
        ResponseEntity<String> result = this.testRestTemplate.postForEntity(uri,request,String.class);
        Assert.assertEquals(201,result.getStatusCodeValue());
}

@Test
    public void testUpdateStockSuccess() throws URISyntaxException{
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/stocks/1";
        URI uri = new URI(baseUrl);
        Stock stock = new Stock(1,182.40);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST","true");
        HttpEntity<Stock> request = new HttpEntity<>(stock,headers);
        this.testRestTemplate.put(uri,request);
        double currentPrice = stockDao.getStockBasedOnId(1).getCurrentPrice();
        Assert.assertEquals(182.40,currentPrice,0);
}
}
