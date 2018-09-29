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

Based on these requirements, I created an application with the following hallmarks:
1. Quick creation / starter packages
2. Dependency injection
3. HATEOAS
4. Quick usage of logging, instrumentation, serialization, dependency management, etc.

### Core Technologies
<img src="https://spring.io/img/spring-by-pivotal-9066b55828deb3c10e27e609af322c40.png" alt="Spring Boot" width="250px" />

**Spring Boot**

Spring Boot was used as the basis of the REST API.  Spring is a premier MVC framework and toolset available for Java, and their Spring Boot initiative provides quickstart applications.  These application starters are perfectly suited not just to coding challenges but can be made production-ready with very few modifications. Spring Boot provides configuration and automation, including autowiring, a web server, dependencies, testing tools, etc.  Their tagline is representative of the change they bring to application creation: *It's kind of like magic.*

<img src="https://webassets.mongodb.com/_com_assets/cms/MongoDB-Logo-5c3a7405a85675366beb3a5ec4c032348c390b3f142f5e6dddf1d78e2df5cb5c.png" alt="MongoDB" width="250px" />

**MongoDB**

MongoDB was used as the nosql data store required for storing price information. MongoDB is tremendously fast and highly scalable and ready for production usage.  Admittedly they had speed bumps early on, but they have made a real name for themselves and have found a place in one of the next big ecosystems - the MEAN stack.

<img src="https://swagger.io/swagger/media/assets/images/swagger_logo.svg" alt="Swagger" width="250px" />

**Swagger**

Swagger is a major player in the API documentation and testing space.  By creating "easy-to-use" configuration files and annotations Swagger enables thousands of developers to document and test their APIs every day throughout the world. Swagger has donated their specifications to the Open API Initiative as the Open API Specification to enable every developer to have tools for their platform. I use the Swagger UI component to provide web-based access to the application's REST API.

<img src="https://www.vojtechruzicka.com/static/springfox-20a04815f6523b4b8790562ec88a6c60-a3725.jpg" alt="Springfox" width="250px" />

**Springfox**

Springfox bridges the gap between Swagger and Spring.  It provides annotations that are Swagger and OAS compatible that our Swagger UI components can use to provide usable functionality via a generated webpage.

<img src="https://maven.apache.org/images/maven-logo-black-on-white.png" alt="Maven" width="250px" />

**Maven**

Maven is used as our dependency and project management tool. Java has a long line of fantastic tools in this space and Maven is one of the best.

<img src="http://decimal4j.org/img/d4j-perf-overview.png" alt="Decimal4j" width="250px" />

**Decimal4j**

In order to work with currencies I prefer to work with fixed-point numeric values instead of floating-point. Java has subpar experiences with fixed-point numbers, so I went with one of the industry standard implementations. Decimal4j provides a wealth of fixed-point data types and arithmetic with fast performance. 

### Getting Started
1. Install JDK with a version >= 10.0.2
2. Install the Spring Tool Suite (STS) from https://spring.io/tools/sts/all. This application was created on version 3.9.6.
3. Update your machine environment variables and classpath within STS
4. Install MongoDB Community Server from https://www.mongodb.com/download-center#atlas. This application has been tesed on version 4.0.2
  * Either install MongoDB as a service, or remember to run it before testing
5. Clone the repository from https://github.com/taumeson/myRetail.git or git@github.com:taumeson/myRetail.git

Note: The following dependencies have been added to the pom.xml relative to a standard spring boot application. They are present in the repository as of September 29, 2018:
```xml
		<dependency>
		    <groupId>org.mongodb</groupId>
		    <artifactId>mongodb-driver-sync</artifactId>
		    <version>3.8.2</version>
		</dependency>
		<dependency>
		    <groupId>io.springfox</groupId>
		    <artifactId>springfox-swagger2</artifactId>
		    <version>2.9.2</version>
		</dependency>
		<dependency>
		    <groupId>io.springfox</groupId>
		    <artifactId>springfox-swagger-ui</artifactId>
		    <version>2.8.0</version>
		    <scope>compile</scope>
		</dependency>
		<dependency>
		    <groupId>org.decimal4j</groupId>
		    <artifactId>decimal4j</artifactId>
		    <version>1.0.3</version>
		</dependency>
```

In order these are a MongoDB driver, Springfox annotations, Swagger UI, and Decimal4j.

6. Import the project into your STS workspace. It will automatically download required JARs from the internet based on your pom.xml

Once the maven dependencies have been updated you're able to test and run the application.

### Testing with Maven
This project was built on Windows PCs.  The CLI wrapper for Maven on Windows is mvnw.cmd, which is executable and included with the project.  To run the junit tests execute:

```
mvnw test
```

### Running the Application with Spring Boot
The spring-boot goal for Maven makes it incredibly simple to run the project:

```
mvnw spring-boot:run
```

## Evaluating the project with Swagger
Swagger UI is the component that I've chosen to provide a self-contained external endpoint for testing.  It's a tremendous tool that is used in production for an astounding variety of APIs.  To get started, run the application and navigate to:

http://localhost:8080/swagger-ui.html

<img src="https://user-images.githubusercontent.com/113290/46247770-c4ae2e80-c3d5-11e8-8782-e8d8ab505be0.PNG" alt="swagger landing page" width="800px" />

#### Testing GET functionality

1. Expand the "Product Management" section to expose the available functions - a single GET function and a single PUT

<img src="https://user-images.githubusercontent.com/113290/46247771-c546c500-c3d5-11e8-87fa-5ed7b2d9fb8f.PNG" alt="our product management api functions" width="800px" />

2. From here, expand the GET function and click on "Try it out". You will expose the ability to modify parameters and execute the function

<img src="https://user-images.githubusercontent.com/113290/46247765-c4ae2e80-c3d5-11e8-82bb-59f107ad0a68.PNG" alt="our GET function" width="800px" />

3. Enter a product identifier and click "Execute".  Use an example identifier from above, or use one of 13860427, 13860428, 13860429. Swagger will execute the GET API and return the response below under "Responses". It will also give you more information on alternative means of calling the function via command line.

<img src="https://user-images.githubusercontent.com/113290/46247767-c4ae2e80-c3d5-11e8-9ac8-1f8e3730c3ac.PNG" alt="GET function request/response" width="800px" />

4. Evaluate the response

#### Testing PUT functionality

1. Copy a GET function response into your clipboard. We will use an updated version of this payload as our "product" parameter
2. Expand the PUT function and click on "Try it out"
3. Paste the product JSON into the "product" parameter textbox, and copy/paste the id into the id textbox

<img src="https://user-images.githubusercontent.com/113290/46247768-c4ae2e80-c3d5-11e8-9899-f6a33a16187b.PNG" alt="our PUT function" width="800px" />

4. Click "Execute" and evaluate the response

#### Investigate the models

Swagger also includes the ability to annotate models, but for this case study I only included the default functionality where Swagger uses reflection on the models that are required as request/response parameters. To evaluate these models, contract the "Product Management" section and expand the "Models" section

<img src="https://user-images.githubusercontent.com/113290/46247769-c4ae2e80-c3d5-11e8-8bc6-71c90f12fba9.PNG" alt="our models" width="800px" />
