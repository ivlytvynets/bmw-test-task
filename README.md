# bmw-test-task
## Task
Your task is to implement a REST endpoint testing software in java 8+. Any other framework or technology can be used. The suggested database is PostgreSQL.

Your code has to call the following endpoint, and handle all the data you will get back in the response.

https://jsonplaceholder.typicode.com/users
## Technologies
1. Java 17
2. Spring Boot Framework 2.6.6
3. PostgreSQL 14.0.2

## How to run?
1. Install JDK.
2. Install PostgreSQL server.
3. Create a user with the name 'postgres' and the password 'password'.
4. Create a database with name 'bmw' and set user with name 'postgres' as owner.
5. Clone project.
6. Run the following commands:
- `mvn clean package`
- `java -jar target/bmw-test-task-0.0.1-SNAPSHOT.jar`
7. Open `logs/spring.log` to see logs.
