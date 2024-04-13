# Event Management System

## Java SpringBoot Project

This project is built using Java with the Spring Boot framework and Maven for dependency management. 
The choice of Java and Spring Boot provides a robust and mature ecosystem for building enterprise-grade 
applications. Spring Boot simplifies the setup and development of Spring applications by providing
defaults and auto-configuration for many features, reducing the amount of boilerplate code.

The database used is MySql. This was chosen because The `eventmanagementsystem` project uses MySQL due 
to its ability to handle structured data and complex queries, its open-source nature, scalability, 
compatibility with major platforms and languages, robust security, and high performance under heavy loads.

The `eventmanagementsystem` project, built with Java, Spring Boot, and Maven, uses MySQL for its 
ability to manage structured data, complex queries, and high-load situations. Key challenges included
dependency management, addressed with Maven, and data security, mitigated by MySQL's robust security 
measures. A specific difficulty was parsing JSON objects in the calculation of the `disCalculation` 
API and `weather` API. This was due to the complex and nested structure of the JSON responses. 
To overcome this, custom deserialization was implemented to accurately map the JSON data to Java
objects, ensuring the correct extraction and processing of the required data.



### Prerequisites

- Java 17
- Maven 4.0.0
- MySql 8.0.26

### Steps

1. Clone the repository: `git clone [Your Repository URL]`
2. Navigate to the project directory: `cd [Your Project Directory]`
3. Install dependencies: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

To add dependencies in `pom.xml`, include the `<dependency>` tag within
`<dependencies>`. Specify the `groupId`, `artifactId`, and `version` 
(if needed) for each dependency. Dependencies include Spring Boot devtools, 
Lombok, Spring Boot starter test, web, OpenCSV, Data JPA, MySQL Connector, 
and org.json.

## API Endpoints

### Endpoint 1

- URL:** `/events/find`
- Method:** `GET`
- Request Format:** This endpoint is used to find events based on latitude, longitude, and date.
  Request Parameters:
  latitude: The latitude of the location.
  longitude: The longitude of the location.
  date: The date for which to find events. The format is yyyy-mm-dd.
- Response Format:** A list of ResponseDto objects, each containing event_name, city_name, date, weather, and distance_km.
- Description:** This endpoint finds events based on the provided latitude, longitude, and date. 
- It calculates the distance between the location and each event, retrieves the weather information 
- for the event date, and returns the results in a list of ResponseDto objects.

### Endpoint 2

- URL:** `/events/create`
- Method:** `POST`
- Request Format: This endpoint is used to create a new event.
  Request Body:
  {
    "eventName": "Event Name",
    "cityName": "City Name",
    "latitude": 12.3456,
    "longitude": 78.9101,
    "date": "yyyy-mm-dd"
  }
- Description:** This endpoint creates a new event with the provided event name, city name, latitude, 
- longitude, and date.
