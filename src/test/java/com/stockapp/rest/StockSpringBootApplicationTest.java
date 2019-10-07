package com.stockapp.rest;

import com.stockapp.rest.dao.StockDao;
import com.stockapp.rest.model.Stock;
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

    /**
     * This is the test method for validating if add stock operation is successfully happening
     * @throws URISyntaxException
     */
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

    /**
     * This is the test method which validates that update stock action is successful
     * @throws URISyntaxException
     */
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

    /**
     * This is a test method which validates get stock list action
     * @throws URISyntaxException
     */
    @Test
    public void testGetStockListSuccess() throws URISyntaxException{
        final String baseURL = "http://localhost:" + randomServerPort + "/api/stocks";
        URI uri = new URI(baseURL);
        String result = this.testRestTemplate.getForObject(uri,String.class);
}

    /**
     * This is a test method which validates the get stock based on ID action
     * @throws URISyntaxException
     */
    @Test
    public void testGetStockSuccess() throws URISyntaxException{
        final String baseURL = "http://localhost:" + randomServerPort + "/api/stocks/1";
        URI uri = new URI(baseURL);
        String result = this.testRestTemplate.getForObject(uri,String.class);
}
}
