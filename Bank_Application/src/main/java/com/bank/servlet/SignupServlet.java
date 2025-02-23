package com.bank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// Annotation to map the servlet

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from signup form
    	
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Validate input
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Database operation
        try {
            // Load JDBC driver (if not already loaded)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection (update URL, username, and password as per your database)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "root");

            // Check if the username or email already exists
            String checkUserQuery = "SELECT * FROM users WHERE username = ? OR email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
            checkStmt.setString(1, username);
            checkStmt.setString(2, email);
            if (checkStmt.executeQuery().next()) {
                request.setAttribute("errorMessage", "Username or email already exists.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }

            // Insert the user into the database
            String insertQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); // In a real application, hash the password before storing
            insertStmt.setString(3, email);

            int rows = insertStmt.executeUpdate();

            if (rows > 0) {
                request.setAttribute("successMessage", "Signup successful. You can now login.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Signup failed. Please try again.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }

            // Close resources
            insertStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
