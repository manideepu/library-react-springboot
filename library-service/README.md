# Run SpringBoot
- cd library-service
-  mvn clean install
-  mvn spring-boot:run

# Swagger
- http://localhost:8080/swagger-ui/index.html

# H2 Console
- Open http://localhost:8080/h2-console
- Ensure that the JDBC URL is - jdbc:h2:mem:test
- User name:- sa
- Password :- Leave it empty

# User List

- The user list http://localhost:8080/v1/library/user/list
    - Integrated with the End point https://jsonplaceholder.org/users
    - Cached for 60 minutes
    - 5 Retry attempts 

# Sorting
- Backend sort is available in the endpoint - http://localhost:8080/v1/library/book/sortByTitle. However, its not called from React as its done in React itself.
