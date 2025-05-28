# Springboot Data Ingestor

## Overview

This project is a Spring Boot application designed to efficiently ingest large CSV files into a MySQL database using multi-threading, RabbitMQ messaging, and JPA batch inserts for scalable asynchronous processing.

---

## Features

- Automatic directory watcher scans for new CSV files on startup.
- CSV parsing and conversion into Java entities.
- Multi-threaded batch processing.
- RabbitMQ integration for decoupled asynchronous messaging.
- Efficient batch inserts into MySQL database using JPA.
- Monitoring via Spring Boot actuator.

---

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate ORM 6.x)
- RabbitMQ (AMQP protocol)
- MySQL database
- Maven build tool
- OpenCSV for CSV parsing
- HikariCP connection pooling
- Embedded Tomcat web server

---

## Project Structure & Setup

The project is organized in a modular package structure under `src/main/java/com/ingestor/` as follows:

com/ingestor/
├── config # Spring and RabbitMQ configuration classes
├── consumer # RabbitMQ consumers for processing messages
├── model # JPA entity classes (e.g., User.java)
├── producer # RabbitMQ producers to send batch messages
├── repository # Spring Data JPA repositories
├── services # Business logic and CSV processing services
├── watcher # Directory watcher to detect CSV files
└── SpringbootDataIngestorApplication.java # Main Spring Boot application entry


Additional folders:

- `src/main/resources/data/`  
  Place your CSV files here. The directory watcher scans this folder on startup.

- `src/main/resources/application.properties`  
  Configure database, RabbitMQ, batch sizes, and other properties here.

### How to Set Up

1. **Database:**
   - Create a MySQL database, for example, `data_ingestor_db`.
   - Set your database credentials and JDBC URL in `application.properties`.
   - Hibernate's `ddl-auto=update` will auto-create/update tables on startup.

2. **RabbitMQ:**
   - Ensure RabbitMQ server is running on `localhost` or the configured host.
   - Set RabbitMQ credentials and host in `application.properties`.
   - The app listens and sends messages via RabbitMQ queues.

3. **CSV Input Folder:**
   - Place CSV files in the folder specified by `csv.watch.dir` (default is `data/`).
   - On startup, the directory watcher picks up all `.csv` files and processes them.

4. **Build and Run:**
   - Build the project with Maven: `mvn clean install`
   - Run the Spring Boot app via your IDE or `java -jar target/your-app.jar`.

---

## How It Works

- On startup, **DirectoryWatcher** scans the configured CSV folder.
- CSV files are parsed into batches of `User` entities.
- Each batch is sent to RabbitMQ via producers.
- Consumers asynchronously receive messages and batch insert records into MySQL.
- This architecture enables scalable and efficient ingestion with decoupled processing.

---

## Troubleshooting

- **RabbitMQ Connection Errors:**  
  Verify RabbitMQ is running and accessible with the correct host, port, and credentials.

- **No Data in Database:**  
  Ensure JPA entities and repositories are correctly defined and scanned.  
  Confirm Hibernate schema generation and SQL logging for troubleshooting.

- **Application Hangs or Freezes:**  
  Check thread dumps and logs. Review RabbitMQ connectivity and batch processing logic.

- **Tables Not Created Automatically:**  
  Verify `spring.jpa.hibernate.ddl-auto=update` or `create` in properties.  
  Ensure your entity packages are included in component scanning.

---

## Customization & Extension

- Add or modify JPA entities and repositories.
- Extend CSV processing logic for different file types.
- Adjust thread pools and batch sizes for performance tuning.
- Add REST endpoints or UI for monitoring ingestion.
- Integrate other messaging or database solutions.

---

## Useful Commands

Maven clean and build commands to package the project.

Java command to run the packaged JAR.

Directory creation for CSV input files.


## Result in DB
![image](https://github.com/user-attachments/assets/5b3e2e76-bf83-456a-8ad5-67b76968f74c)

