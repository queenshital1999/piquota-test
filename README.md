# piquota-test
Stock and inventory management app
Clone the Repository: Clone the repository containing the code for the Inventory Management System.
->git clone <repository-url>

Navigate to the Project Directory:
->cd inventory-management-system

Configure the Database:->
Open the application.properties file located in src/main/resources.
Update the database connection properties such as URL, username, and password according to your local database setup.

Build the Project:->
Execute the following Maven command to build the project:
->mvn clean install

Run the Application:->
Run the following command to start the application:
->mvn spring-boot:run

Access the APIs:->
Once the application is running, you can access the APIs using a REST client like Postman or cURL.
Base URL for accessing endpoints locally: http://localhost:8080/inventory

Insert Inventory:-> Send a POST request to /inventory/insert with JSON body containing inventory details.
Get All Inventories:-> Send a GET request to /inventory/all.
Get Inventory by ID:-> Send a GET request to /inventory/{id} where {id} is the ID of the inventory.
Update Inventory:-> Send a PUT request to /inventory/{id} with JSON body containing updated inventory details.
Delete Inventory:->Send a DELETE request to /inventory/{id} where {id} is the ID of the inventory you want to delete.

Testing:->
Test the functionality of the services by sending requests to the respective endpoints using the provided APIs.

===========================================================================================================================

for stock 

Steps to Run the Services Locally:
Clone the Repository: 
Clone the repository containing the code for the Stock Management System.
->git clone <repository-url>

Navigate to the Project Directory:
->cd stock-management-system

Configure the Database:
Open the application.properties file located in src/main/resources.
Update the database connection properties such as URL, username, and password according to your local database setup.

Build the Project:
Execute the following Maven command to build the project:
mvn clean install

Run the Application:
Run the following command to start the application:
mvn spring-boot:run

Access the APIs:
Once the application is running, you can access the APIs using a REST client like Postman or cURL.
Base URL for accessing endpoints locally: http://localhost:8081/stocks

Add Stock:-> Send a POST request to /stocks/add with JSON body containing stock details.
Get All Stocks:-> Send a GET request to /stocks/all.
Get Stock by ID:-> Send a GET request to /stocks/{id} where {id} is the ID of the stock.
Update Stock:-> Send a PUT request to /stocks/{id} with JSON body containing updated stock details.
Delete Stock:-> Send a DELETE request to /stocks/{id} where {id} is the ID of the stock you want to delete.

Testing:
Test the functionality of the services by sending requests to the respective endpoints using the provided APIs
