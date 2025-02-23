
# REST Number Processing & Ticketing Service

## Overview
This project implements two REST services using Java and Spring Boot:

1. **Number Processing Service**
   - Accepts a numeric input and applies a series of transformations before returning the final computed result.

2. **Ticketing System Service**
   - Manages a queue system by generating sequential ticket numbers, retrieving the current ticket, and deleting tickets from the queue.

---

## Number Processing Service

### Transformation Rules
The service performs the following sequential operations on the input number:

1. **Shift digits less than or equal to 3 one position to the right:**  
   Example: `43256791` → `45326791`
2. **Multiply all digits 8 and 9 by 2:**  
   Example: `45326791` → `453267181`
3. **Remove all digits 7:**  
   Example: `453267181` → `45326181`
4. **Count even digits and divide the number by this count, rounding down:**  
   Example: `45326181 / 4 = 11331545`

For input `43256791`, the final result is `11331545`.

### API Endpoint

#### Process Number
- **Endpoint:** `POST /numbers`
- **Request Body:**
  ```json
  {
    "number": 43256791
  }
```

**Response:**
```json
{
  "result": 11331545
}
```
# Ticketing System Service

This service simulates a ticketing system for a branch office. The system issues numbered tickets 
with a timestamp and a queue position, retrieves the current ticket, and also allows removal 
of the last active ticket.

## Ticket Flow Example

Let's assume the following tickets are currently active:

- **Ticket 1245**, issued **2017-09-01 15:22**, queue position **0**
- **Ticket 1246**, issued **2017-09-01 15:42**, queue position **1**
- **Ticket 1250**, issued **2017-09-01 16:32**, queue position **2**

### Generating a New Ticket

A request to create a new ticket returns something like:

- **Ticket 1251**, issued **2017-09-01 19:20**, queue position **3**

### Getting the Current Ticket

Retrieving the current ticket (the one with the smallest queue position, typically **0**) might return:

- **Ticket 1245**, issued **2017-09-01 15:22**, queue position **0**

### Deleting the Current Ticket

After deleting the ticket with queue position **0**, the remaining tickets update their positions:

- **Ticket 1246**, issued **2017-09-01 15:42**, queue position **0**
- **Ticket 1250**, issued **2017-09-01 16:32**, queue position **1**
- **Ticket 1251**, issued **2017-09-01 19:20**, queue position **2**

## Ticketing System Endpoints

### Create Ticket
**`POST /tickets`**  
**Response**: Returns the newly created ticket with its ID, timestamp, and queue order.

### Get Ticket
**`GET /tickets/{ticketId}`**  
**Response**: Returns the ticket information (ID, timestamp, order in queue).

### Delete Ticket
**`DELETE /tickets/{ticketId}`**  
**Response**: Removes the specified ticket from the queue and reorders the remaining tickets. If the ticket is successfully deleted, returns a **204 No Content** status.


## Technologies Used
- Java 17
- Spring Boot
- Maven/Gradle
- REST API

## Setup & Run

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repository.git
   cd your-repository
   ```
2. Build the project:
   ```sh
   mvn clean install   # For Maven
   # OR
   ./gradlew build     # For Gradle
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run   # For Maven
   # OR
   ./gradlew bootRun     # For Gradle
   ```
4. Test the API using a tool like Postman or `curl`:
   ```sh
   curl -X POST http://localhost:8080/numbers -H "Content-Type: application/json" -d '{"number":43256791}'
   curl -X POST http://localhost:8080/tickets
   curl -X GET http://localhost:8080/tickets/1245
   curl -X DELETE http://localhost:8080/tickets/1245
   ```
   
## Testing
- Unit tests are implemented using JUnit and Spring Boot Test framework.
- Run tests with:
  ```sh
  mvn test   # For Maven
  # OR
  ./gradlew test   # For Gradle
  ```

## License
This project is licensed under the MIT License.


