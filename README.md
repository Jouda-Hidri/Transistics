# Transistics
A REST service to manage constant time calculations with thread safety.

Based on transactions received from a `POST` request, statistics can be retrieved with a `GET` request.
Statistics are updated everytime a new transaction is created and everytime the life duration of a transaction is over (60s).

A `TimerTask` is created everytime a new transaction is created, so that statistics are recalculated when the remaining life duration of this transaction is over.

Calculations are done in O(1).     
The concurrent access to statistics is thread safe.


## Requirements

Java version : 1.8         
Maven version : 3.5.0

## Run the project

````
cd path/to/folder

// Clone the project
git clone https://github.com/Jouda-Hidri/Transistics.git

// Build and test the application
mvn clean install

// Test the application
mvn test

// Run
mvn spring-boot:run

// Add a transaction (replace timestamp value with a recent one)
curl -d '{"amount": 50, "timestamp": 1518943207299}' -H "Content-Type: application/json" -X POST http://localhost:8080/transactions

// Get statistics
curl http://localhost:8080/statistics
````

