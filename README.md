[Build Status](http://jenkins.arjunsreepad.com/buildStatus/icon?job=To+Do+List&build=5 "http://jenkins.arjunsreepad.com/job/To%20Do%20List/5/")

# To Do List Web application using Spring Boot and MySQL/H2 In memory DB

A simple Todo list application using Spring Boot with the following options:

- Thymeleaf template for the rendering.
- Spring boot JPA for backend
- MYSQL/H2 is a DB

## Getting Started

### Using Mysql
 - Create a database in your MySQL instance or create a free db instance on remotemysql.com 
 - Update the application.properties file in the `src/main/resources` folder  by uncommenting the MYSQL related changes and comment out the h2 related configaration
 - Update the URL, DB schema, username and password for your MySQL instance.

### Using H2 DB
- No changes required
 
### Installation
- Clone the repo
- run the below command and launch application in 8080 port
> mvnw install spring-boot:run
- Open a web browser to http://localhost:8080


### Screenshots
![Alt text](https://github.com/arjunsreepad/spboot-mysql-h2-webapp/raw/master/screenshot/app%20launch.png "App Launch")


![Alt text](https://raw.githubusercontent.com/arjunsreepad/spboot-mysql-h2-webapp/master/screenshot/app%20after%20update.png "App after update")
