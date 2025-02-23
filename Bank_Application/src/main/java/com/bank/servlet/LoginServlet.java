package com.bank.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database configuration
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bankdb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get user credentials from the login form
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (isValidUser(username, password)) {
                // Create a session for the logged-in user
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                // Redirect to the dashboard
                
               
            } else {
                // Invalid credentials, show an error message
                out.println("<h3 style='color:red'>Invalid email or password. Please try again.</h3>");
                request.getRequestDispatcher("login.jsp").include(request, response);
                
            }
        }
    }

    private boolean isValidUser(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to validate user credentials
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(query);
           
			ps.setString(1, username);
            ps.setString(2, password);

            // Execute the query
            rs = ps.executeQuery();

            // If a record is found, user credentials are valid
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
