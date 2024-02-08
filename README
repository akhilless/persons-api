The project implements Persons API. The database is Postgresql provided by a Docker container.
## Steps to run
First start the database by running the following command
```docker compose up -d```
Then start the Spring boot application:
```./mvnw spring-boot:run```
It will listen to incoming requests on port 8080. Then you can do requests like this one to create a new Person:
```curl -X POST http://localhost:8080/persons -H 'Content-Type: application/json'  -d '{"firstName": "John", "lastName": "Doe", "birthDate": "2000-12-24", "currentAddress": "Amsterdam, somestreet 483"}'```
The following request retrieves the created person:
```curl 'http://localhost:8080/persons?lastName=Doe&firstName=John'```

The tests are run using the following command:
``` ./mvnw test```