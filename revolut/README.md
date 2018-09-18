# RESTFull API
Design and implement a RESTful API (including data model and the backing implementation) for money
transfers between accounts.

### Purpose
This project was developed by [Bahadir Sezgun](https://www.linkedin.com/in/bsezgun/)
to demonstrate how to transfer money between accounts invoked by another internal system/service call.

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
    - To transfer money : 
  > http://localhost:8080/bank/transfer/{toAccountId}/{fromAccountId}/{amount}
  > Request Type : POST
  > Return Type  : JSON
  > Url Parameters
    - toAccountId : Bank Account Id of the receiver (Type:BigDecimal)
    - fromAccountId : Bank Account Id of the sender (Type:BigDecimal)
    - amount : Amount of transferring money (Type:BigDecimal)

### Running Tests
This project uses [Maven](https://maven.apache.org/).
To run tests, simply run "mvn clean test".

### Practice Exercises
To practice BDD and automation, try the following exercises:

1. The assertion for the "results for ___ are shown" step checks only the page title.
   Add more comprehensive assertions to strengthen the test.
2. Add a new scenario to search Google for images.
3. Add a new scenario to perform Google searches directly using URL query parameters.
4. Add a new feature for basic tests against Wikipedia using a new page object class,
   a new step definition class, and dependency injection.
   * Picking a language from the home page.
   * Searching for articles.
   * Verifying that embedded links navigate to the correct articles.
   * Viewing page history.
5. Make it possible to choose the web browser using a properties file.
   Classes to read the properties file and construct the appropriate web driver
   should be put in the framework package.
6. Write a new feature for basic service-level testing.
   Use [REST Assured](http://rest-assured.io/) to hit a few endpoints from
   [JSONPlaceholder](https://jsonplaceholder.typicode.com/).
7. Create separate test runners that partition the set of features using tags.
8. Add logging to the tests with [SLF4J](https://www.slf4j.org/) or
   [Extent Reports](http://extentreports.com/).
