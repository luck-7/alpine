# Alpine - Bank Application

Alpine is a web-based banking application developed using J2EE and JDBC. It allows users to perform essential banking operations such as account management, transactions, and balance inquiries.

## Features

- User authentication (Login/Registration)
- Deposit and Withdraw money
- Fund transfer between accounts
- Account statement generation
- Admin dashboard for account management

## Technologies Used

- **Backend:** J2EE (Servlets, JSP), JDBC (Java Database Connectivity)
- **Database:** MySQL
- **Frontend:** HTML, CSS, JavaScript, Bootstrap, JSP
- **Web Server:** Apache Tomcat

## Installation and Setup

### Prerequisites

Ensure you have the following installed:

- JDK 8 or later
- Apache Tomcat Server
- MySQL Database
- Any IDE (Eclipse/IntelliJ IDEA/NetBeans)

### Steps to Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/luck-7/alpine.git
   ```

2. **Import the project** into your preferred IDE.

3. **Configure the database:**
   - Create a MySQL database named `bank_db`.
   - Execute the SQL script located in `database/bank_db.sql` to set up the tables.

4. **Update database credentials:**
   - Modify `database.properties` with your MySQL credentials.

5. **Deploy the project** on Apache Tomcat.

6. **Start the server** and access the application at:
   ```
   http://localhost:8080/BankApplication
   ```

## Usage

1. **Register/Login** as a user.
2. **Manage accounts:**
   - View balance
   - Deposit money
   - Withdraw money
   - Transfer funds
3. **Admin Access:**
   - Manage users
   - Manage accounts

## Contribution

1. **Fork the repository.**
2. **Create a new branch** for your feature.
3. **Commit your changes** and push them to GitHub.
4. **Create a pull request** for review.

## License

This project is licensed under the **MIT License**.

## Contact

For any queries, contact: **negiarnab2002@gmail.com**

