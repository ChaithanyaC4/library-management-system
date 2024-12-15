# library-management-system
1:LIBRARY MANAGEMENT SYSTEM
This is a simple Library Management System built with Spring Boot. The system allows users to manage books and track their borrowing activity. Key functionalities include:

User Management: Users can sign up, log in, update their details, and view their profile.
Book Management: Admins or authorized users can add, update, and delete books. Users can borrow or return books based on availability.
Key Features:
User Signup: Users can create an account by providing their name, email, and password.
Login: Users can log in with their credentials (email and password).
Book Borrowing: Users can borrow available books from the library. The stock is updated when a book is borrowed.
Book Returning: Users can return borrowed books. The stock is updated accordingly.
Book Management (Admin): Authorized users can add, update, and delete books from the system.
Session Validation: Ensures user sessions are valid during operations.
Main Components:
User: Represents a library user with details like name, email, password, and a list of borrowed books.
Book: Represents a book with details such as title, author, genre, and stock.
UserService: Manages user operations such as creating, updating, deleting, and logging in.
BookService: Handles book-related operations like borrowing, returning, and managing book details.
UserController: Handles user-related API endpoints like sign-up, login, and profile update.
BookController: Manages book-related API endpoints for borrowing, returning, adding, updating, and deleting books.
Technologies Used:
Spring Boot: For building the backend and API services.
H2 Database: In-memory database for storing user and book data.
REST API: Exposes endpoints for interacting with users and books.

INSTRUCTIONS TO SET UP AND RUN THE LIBRARY MANAGEMENT SYSTEM
Pre-requisites:
Java Development Kit (JDK): Ensure you have JDK 11 or higher installed. You can download it from the official JDK website.
Maven: The project uses Maven as a build tool. Make sure Maven is installed. If not, follow the installation guide on Maven's official website.
IDE: You can use any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse).
Steps to Set Up the Project:
Clone the Repository:

Clone the project repository to your local machine using the following command:
bash
Copy code
git clone <repository_url>
Replace <repository_url> with the URL of your project repository.
Navigate to the Project Directory:

Open a terminal or command prompt and navigate to the project directory:
bash
Copy code
cd <project_directory>
Build the Project:

Use Maven to build the project. Run the following command in the terminal:
bash
Copy code
mvn clean install
This command will download the necessary dependencies and package the application.
Configure Application Properties:

The project uses an in-memory H2 database, so no external database setup is required.
However, you can configure other settings (like logging, data source, etc.) in the src/main/resources/application.properties file if needed.
Run the Application:

To start the Spring Boot application, run the following command:
bash
Copy code
mvn spring-boot:run
Alternatively, you can run the application from your IDE by running the LibraryManagementApplication class.
Access the Application:

Once the application starts, it will be available at:
arduino
Copy code
http://localhost:8080
You can access the various endpoints (e.g., /users, /books) using a tool like Postman or curl.
Testing the Application:
You can use Postman or any API testing tool to interact with the API. Below are some sample API endpoints:

User Sign-up (POST):

css
Copy code
POST /users/signup
Request Body: { "name": "John Doe", "email": "john@example.com", "password": "password123" }
User Login (POST):

bash
Copy code
POST /users/login?email=john@example.com&password=password123
Borrow a Book (POST):

bash
Copy code
POST /books/borrow?userId=1&bookId=2
Return a Book (POST):

kotlin
Copy code
POST /books/return?userId=1&bookId=2
H2 Database Console (Optional):
The project uses an in-memory H2 database, which can be accessed via the H2 Console. To enable it, ensure the following is in the application.properties file:
properties
Copy code
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
Then, you can access the H2 console at:
bash
Copy code
http://localhost:8080/h2-console
Login with the following credentials:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password
