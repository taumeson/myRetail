# myRetail API - Nathaniel Engelsen
### Introduction
This myRetail API was created as part of a case study for a coding challenge. The goals were 

* Efficiency of development
* Efficiency of case review

Therefore the project has a handful of technologies and implementations that are not production-ready or fully complete and serve to be illustrative of their use. The goals of the case study are to create an API that meets the following criteria:

*	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON where {id} will be a number
    *	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793
    *	Example response: 
    ```json
    {
      "id": 13860428,
      "name": "The Big Lebowski (Blu-ray) (Widescreen)",
      "current_price": {
        "value": 13.49,
        "currency_code": "USD"
      }
    }
    ```
*	Performs an HTTP GET to retrieve the product name from an external API.
    * Example: http://redsky.target.com/v2/pdp/tcin/13860428
*	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.
*	*BONUS*: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.

### Core Technologies
<img src="https://spring.io/img/spring-by-pivotal-9066b55828deb3c10e27e609af322c40.png" alt="Spring Boot" width="250px" />

**Spring Boot**
Spring Boot was used as the basis of the REST API.  Spring is a premier MVC framework and toolset available for Java, and their Spring Boot initiative provides quickstart applications.  These application starters are perfectly suited not just to coding challenges but can be made production-ready with very little modifications. 
