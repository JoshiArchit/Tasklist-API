# TaskList
TaskList is a lightweight REST API for managing tasks, built using Spring Boot and JPA. It's designed as a beginner-friendly project for understanding RESTful APIs and the MVC architecture.


## Dependencies & Project Setup 
Use the [spring initializer](https://start.spring.io/) to create a new project with the following dependencies and add any additional ones:
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Spring Postgres

Or use this link to get the exact configuration : [Project Configuration](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.4.4&packaging=jar&jvmVersion=24&groupId=com.archit&artifactId=tasklist_api&name=tasklist_api&description=Backend%20REST%20Api%20for%20Tasklist%20application&packageName=com.archit.tasklist_api&dependencies=web,data-jpa,postgresql,lombok,h2)

## Deploy
Deployment instructions coming soon...
### Environment variables and confidential parameters
- Use the .env.example file to find the required environment variables and create a .env file (don't forget to add it to .gitignore!)
- The environment variables are also used in the application.properties and docker-compose file.
- For local development you can add it in the Run Configurations in IntelliJ.

## Maven Command Cheatsheet (Windows)
- `./mvnw install` — Build the project
- `./mvnw spring-boot:run` — Run the project