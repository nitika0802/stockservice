package com.stockapp.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class StockSpringBootApplication {

    public static void main(String args[]){
        SpringApplication.run(StockSpringBootApplication.class,args);
    }
}
