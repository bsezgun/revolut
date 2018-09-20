# RESTFull API
Design and implement a RESTful API (including data model and the backing implementation) for money
transfers between accounts.

### Purpose
This project uses [Maven](https://maven.apache.org/) and developed by [Bahadir Sezgun](https://www.linkedin.com/in/bsezgun/) to demonstrate how to transfer money between accounts invoked by another internal system/service call.

### Version
This project uses:
- Eclipse Oxygen
- Maven 2
- Java 1.8
- Jetty Server 9.3.10.v20160621
- Jersey 2.15
- Hibernate 5.3.6
- H2 In Memory Database 1.4
- JUnit 4.12
 
### Rest End Points
> To transfer money : You can transfer money from the account to another account
  - http://localhost:8080/revolut/bank/transfer/{accountType}/{toAccountId}/{fromAccountId}/{amount}
  -  Request Type : POST
  -  Return Type  : JSON
  -  Url Parameters
     - accountType : Account Type (USD:1,EURO:2) (Type:BigDecimal)
     - toAccountId : Bank Account Id of the receiver (Type:BigDecimal)
     - fromAccountId : Bank Account Id of the sender (Type:BigDecimal)
     - amount : Amount of transferring money (Type:BigDecimal)
###
> To deposit account : This end point for the test-purpose. You can deposit the account. If the system not found the account than the system will create account automatically.  
  -  http://localhost:8080/revolut/bank/deposit/{accountType}/{toAccountId}/{deposit} 
  -  Request Type : POST
  -  Return Type  : JSON
  -  Url Parameters
      - accountType : Account Type (USD:1,EURO:2) (Type:BigDecimal)
      - toAccountId : Bank Account Id of the receiver (Type:BigDecimal) 
      - deposit : Amount of transferring money (Type:BigDecimal)

###
> To retrieve account :Also, this end point for the testing purposes. You can view account details by this end point. 
  -  http://localhost:8080/revolut/bank/account/{accountType}/{accountId} 
  -  Request Type : GET
  -  Return Type  : JSON
  -  Url Parameters
      	- accountType : Account Type (USD:1,EURO:2) (Type:BigDecimal)
    	- accountId : Bank Account Id  (Type:BigDecimal)  
    
### Package Application
This project uses [Maven](https://maven.apache.org/).
To create single executable application (revolut.jar), simply run "mvn clean install".

### Running Application
{path Of the java 1.8}/java -jar revolut.jar

### Running Tests
This project uses [Maven](https://maven.apache.org/).
To run tests, simply run "mvn clean test".

### Project Structure and Information
	
![alt text](https://github.com/bsezgun/revolut/blob/master/revolut/src/main/resources/imgs/project_structure.PNG)

##### src/main	
> com.revolut.money.transfer package
- RevolutApp (Main Class)
- This is the main class of the project. it defined that this is the main class of the application in the pom.xml {at the build part}.
	- When it is starts than, 
		1. It loads properties from application.properties file
		2. It starts to Jersey server. Loads Rest Controllers under 'com.revolut.money.transfer.controller' package.
		3. It creates Test Accounts for test purpose
		    
> com.revolut.money.transfer.controller package
- The Rest Controller Classes under this package. Please look at the Rest End Points section
		
> com.revolut.money.transfer.entity package
- The Entity Classes under this package. Abstract factory pattern used for the entities in case of different type of accounts add in the future.

> com.revolut.money.transfer.facade package
- The business rules control Classes under this package. Factory pattern used for the classes in case of different type of accounts rules control in the future.
		
> com.revolut.money.transfer.repository package
- The repository (database executions) Classes under this package. Singleton pattern used for the classes. These classes are immutable classes.
			
> com.revolut.money.transfer.service package
- The business service (database executions) classes under this package. Factory pattern used for the classes. When request received by the Rest Controller, it calls the related service business by accountType via AccountType interface.
		
> com.revolut.money.transfer.util package
- The utility class and response class  under this package. Utility class is responsible for the final variables. These variables use for response status or can be change project startup behavior.
		 
##### src/main/resources
> Configuration files under this folder. 
1. hibernate.cfg.xml, hibernate.properties are for the H2 Database configuration 
2. application.properties is for application rules configuration
	
#####  src/test
> revolut package
- All test classes under this package. JUnit test framework is used for testing.	
- To run tests, simply run "mvn clean test".	

## UML Diagrams
### Class Diagram
![alt text](https://github.com/bsezgun/revolut/blob/master/revolut/src/main/resources/imgs/class_diagram.jpg)
### Sequence Diagram
![alt text](https://github.com/bsezgun/revolut/blob/master/revolut/src/main/resources/imgs/sequence_diagram.jpg)
