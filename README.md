
# REST Number Processing Service

## Overview
This project implements a REST service using Java and Spring Boot that processes a given number according to the specified transformation rules. The service takes a numeric input, applies a series of operations, and returns the final computed result.

## Transformation Rules
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

## API Endpoint

### Process Number
**Endpoint:** `POST /process`

**Request:**
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
   curl -X POST http://localhost:8080/process -H "Content-Type: application/json" -d '{"number":43256791}'
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


