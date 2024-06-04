# UserCycleRecords

Microservice application consisting of User Service, Cycle Service & 
## Getting Started

To get the Cycle Service up and running, follow these steps:

### Prerequisites

- Docker (will use `docker-compose up --build` to run the services)

## Installation

### Using Docker Compose

To quickly get all the services up and running together, you can use Docker Compose. Make sure you have Docker Compose installed on your system.

1. Clone the repository:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```bash
    cd <project-directory>
    ```

3. Build and start the services using Docker Compose:

    ```bash
    docker-compose up --build
    ```

This command will build Docker images for each service (if not already built) and start containers for all services defined in the `docker-compose.yml` file. Once the services are up and running, you can access them using the respective endpoints.


## Swagger Endpoints

The Cycle Service provides the following Swagger endpoints for API documentation:

- **User Service:** [http://localhost:8080/user/swagger-ui/index.html](http://localhost:8080/user/swagger-ui/index.html)
- **Cycle Service:** [http://localhost:8080/cycle/swagger-ui/index.html](http://localhost:8080/cycle/swagger-ui/index.html)
- **Daily Usage Service:** [http://localhost:8080/dailyUsage/swagger-ui/index.html](http://localhost:8080/cycle/swagger-ui/index.html)
- **Eureka Server:** [http://localhost:8761](http://localhost:8761)

## Service Design

The API Gateway serves as a centralized entry point for all client requests within a microservices architecture, providing a unified interface to interact with multiple backend services. It acts as a reverse proxy, routing requests to the appropriate microservice based on predefined routing rules.

Eureka Server, on the other hand, plays a crucial role in service discovery and registration within the microservices ecosystem. It maintains a registry of available services and their instances, allowing services to dynamically locate and communicate with each other.

Feign Client simplifies the process of making HTTP requests between microservices by providing a declarative interface for defining RESTful clients. It abstracts away the complexity of low-level HTTP communication and integrates seamlessly with service discovery mechanisms like Eureka Server, facilitating communication between microservices in a transparent and efficient manner.

### API Design

#### Get the current cycle daily usage for a given customer
- **GET /dailyUsage/current:** Retrieves the current cycle daily usage for a given customer.
    - Input Parameters: `userId`, `mdn`
    - Output: List of `{date, daily usage}`
#### Get the cycle history of a given mdn
- **GET /cycle/history:** Retrieves the cycle history of a given mobile number.
    - Input Parameters: `userId`, `mdn`
    - Output: List of `{cycleId, startDate, endDate}`
#### Create a new user
- **POST /user/:** Creates a new user with the provided details.
    - Input Parameters: `firstName`, `lastName`, `email`, `password`
    - Output: `{id, firstName, lastName, email}`
#### Update existing user profile
- **PUT /user/{userId}:** Updates an existing user's profile information.
    - Input Parameters: `firstName`, `lastName`, `email`
    - Output: `{id, firstName, lastName, email}`

### Future Improvements:

#### Caching Strategies: 
Implement caching mechanisms (e.g., Redis or Memcached) at the API layer to cache frequently accessed data and reduce the load on the database. Cache invalidation strategies should be carefully designed to ensure data consistency.

#### Rate Limiting and Throttling: 
Introduce rate limiting and throttling mechanisms in the API gateway to prevent abuse and protect against denial-of-service (DoS) attacks. Rate limiting can help maintain system stability by limiting the number of requests allowed per user or client.

#### Asynchronous Processing: 
Evaluate the possibility of implementing asynchronous processing for long-running or resource-intensive operations. Asynchronous processing can improve system responsiveness and scalability by offloading tasks to background workers or message queues.

#### Replication and Sharding DB:
Replication ensures high availability and data redundancy by maintaining multiple copies of data across different servers. Sharding enhances scalability and performance by distributing large datasets across multiple servers, allowing the system to handle higher throughput and larger data volumes.

#### Command Query Responsibility Segregation (CQRS)
Separate read and write operations into distinct services to improve performance, scalability, and security(DailyUsage Query Service, DailyUsage Command Service). This separation allows for optimized handling of queries and commands, leading to more efficient and maintainable systems.

#### Improve other services 
Add additional functionalities like authentication, authorization, logging, extra validation, extra custom error handling, Monitoring and metrics, Circuit Breaker, Additional end to end Testing and CI/CD.