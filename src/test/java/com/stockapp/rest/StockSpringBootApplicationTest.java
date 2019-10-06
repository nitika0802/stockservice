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

@Test
    public void testGetStockListSuccess() throws URISyntaxException{
        final String baseURL = "http://localhost:" + randomServerPort + "/api/stocks";
        URI uri = new URI(baseURL);
        String result = this.testRestTemplate.getForObject(uri,String.class);
        String assertString = "{\"stockList\":[{\"id\":1,\"name\":\"Tata Motors\",\"currentPrice\":149.5,\"lastUpdate\":\"1970-01-19T04:05:50.608+0000\"},{\"id\":2,\"name\":\"State Bank of India\",\"currentPrice\":180.0,\"lastUpdate\":\"1970-01-19T04:05:50.700+0000\"},{\"id\":3,\"name\":\"Infosys Limited\",\"currentPrice\":550.2,\"lastUpdate\":\"1970-01-19T04:05:50.728+0000\"},{\"id\":4,\"name\":\"TV 18 Broadcast\",\"currentPrice\":82.1,\"lastUpdate\":\"1970-01-19T04:05:50.751+0000\"},{\"id\":5,\"name\":\"ABN AMRO\",\"currentPrice\":865.4,\"lastUpdate\":\"1970-01-19T04:05:50.786+0000\"}]}";
        Assert.assertEquals(assertString,result);
}

@Test
    public void testGetStockSuccess() throws URISyntaxException{
        final String baseURL = "http://localhost:" + randomServerPort + "/api/stocks/1";
        URI uri = new URI(baseURL);
        String result = this.testRestTemplate.getForObject(uri,String.class);
        System.out.println(result);
}
}
