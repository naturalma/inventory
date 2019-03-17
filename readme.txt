1. Technologies Involved:
- Spring boot
- Thymeleaf
- JUnit
- Ajax
- Maven
- Github

2. Additional Features
- Result Searching and Sorting
- Category Validation rule maintenance

3. Highlights
- In fact I do not have past experiences working with Spring Boot and Thymeleaf. I took this opportunity to pick them up as they are indeed useful and easy to deploy a web application.
- Due to time constraint, I do not use any database to store the inventory data and category validation rules. Instead, they were held in memory in my program. However, it will be easy to switch to database or other persistence level by writing and instanciating another CategoryManager and InventoryManager, other part does not need to change.

4. quick steps to run and test:
open command line window, navigate to the project root folder, type following command:
mvn clean install
mvn spring-boot:run

open browser:
Inventory page:
http:localhost:8080/

Category validation rule maintenance page:
http:localhost:8080/admin