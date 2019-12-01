
# REST-API 

### Technologies:
 - Java
 - Rest-Assured
 - TestNG
 - Maven
 
 
### Testing Approach
  - First of all, I read the documentation to understand all the requests.
  - I picked `\products` and `\services` to start. 
  - The documentation provides swagger to perform some manual requests and I also created some requests in Postman.
  - Generated JsonSchema based on documentation to validate the response request. 


### Why to use Rest-Assured?
  - Rest-assured allows to perform REST request using BDD format.
  - It is easy to understand the code.

  
### Run the test using Maven

```
mvn clean test -DsuiteXmlFile=suite-tests/suite.xml
```