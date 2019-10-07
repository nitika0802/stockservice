package com.stockapp.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class StockSpringBootApplication {

    /**
     * This is the main method which is the execution point of the SpringBootApplication. It runs the application on an Apache Tomcat Server at port 8080
     */
    public static void main(String args[]){
        SpringApplication.run(StockSpringBootApplication.class,args);
    }
}
