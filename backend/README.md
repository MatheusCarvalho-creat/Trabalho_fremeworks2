# Filmes Backend

Spring Boot backend with:
- Entities: User, Movie (User -> Movie relation)
- Authentication: JWT (endpoints /api/auth/register and /api/auth/login)
- CRUD for Movies: /api/movies
- Swagger UI available at /swagger-ui.html (when running)

Configuration:
- application.properties uses H2 in-memory by default for local testing.
- Override SPRING_DATASOURCE_URL, USER, PASS and JWT_SECRET for production/Aiven.

Build:
- mvn clean package
- java -jar target/filmes-backend-0.0.1-SNAPSHOT.jar
