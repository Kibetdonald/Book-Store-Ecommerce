# Book-Store-Ecommerce
This project is a microservices-based ecommerce shop built using Spring Boot. The application consists of several microservices that work together to provide a seamless shopping experience for customers.

### Microservices
- API Gateway: Acts as the entry point to Bookify, handling incoming requests and routing them to the appropriate microservice. 
- Discovery Server: Handles service registration and discovery, allowing microservices to register themselves and providing a centralized registry for service communication.
- Config Server: Centralizes configuration management for all microservices, storing their configuration files and providing dynamic retrieval at runtime.
- Order Service: Manages customer orders, from placement to processing. Integrates with payment gateways for secure payment processing.
- Product Catalog Service: Manages the catalog of books available for sale, allowing customers to browse, search, and view book details.
- Authentication Service: Handles user authentication and authorization, managing user accounts, registration, login, and access token generation.

### Technologies Used
 - Spring Boot
 - Netflix Eureka
 - Spring Cloud Config
 - Spring Cloud Gateway
 - Spring Security
 - PostgreSQL

### Proposed System Architecture
<img src="https://github.com/Kibetdonald/Book-Store-Ecommerce/assets/50916200/cbc61490-253a-46b9-941b-c95b8e70b8fe.JPG" width="90%" />

