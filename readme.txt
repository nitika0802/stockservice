This application manages Stock operations. Upon initialization, it creates a list of stocks with some predefined data.

Operations include --
GET list of stocks
GET stock based on ID
POST add a stock
PUT update a stock

Request Mappings --
GET --
http://localhost:8080/api/stocks -- To get list of stocks in json format
GET --
http://localhost:8080/api/stocks/id -- To get a stock based on id parameter
POST --
http://localhost:8080/api/stocks -- To add a stock object
Body :
{
	"name":"$name",
	"currentPrice":"$currentPrice"
}
Headers : Content-Type application/json
PUT --
http://localhost:8080/api/stocks/id -- To update stock object for id param
Body:
{
	"currentPrice":"762.30"
}
Headers : Content-Type application/json

Running the application --

To run the application, Go to StockSpringBootApplication class. Right click and Run the application. It is a SpringBoot application which does not require an external server to run.
Once the application is up and running, you will be able to access the Rest endpoints mentioned above.

You can also run the application using the traditional method of building the war file and deploying it to an external server.

To run the tests, Go to StockSpringBootApplicationTest class. Right click and Run the application.

The Stock list is created with the pre-existing stocks --
1, "Tata Motors", 149.50
2, "State Bank of India", 180.00
3, "Infosys Limited", 550.20
4, "TV 18 Broadcast", 82.10
5, "ABN AMRO", 865.40


