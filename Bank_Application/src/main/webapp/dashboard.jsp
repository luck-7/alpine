<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>2
<body>
    <!-- Check for Login Session -->
    <%
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <div class="container my-5">
        <h1 class="text-center">Welcome, <%= username %>!</h1>
        <hr>

        <!-- Fetch User Details from Database -->
        <% 
            String dbURL = "jdbc:mysql://localhost:3306/bankdb";
            String dbUser = "root";
            String dbPassword = "root";
            
            
            
            
            double balance = 0.0;
            

            try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
                String query = "SELECT balance FROM users WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            balance = rs.getDouble("balance");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<div class='alert alert-danger'>Error fetching user details: " + e.getMessage() + "</div>");
            }
        %>

        <!-- User Balance Section -->
        
        
        <div class="card mb-4">
            <div class="card-header">
                <h2>Account Details</h2>
            </div>
            <div class="card-body">
                <p><strong>Account Balance:</strong> $<%= String.format("%.2f", balance) %></p>
            </div>
        </div>

        <!-- Navigation Section -->
        <div class="row">
            <div class="col-md-4">
                <a href="Transaction.jsp" class="btn btn-primary btn-block w-100">Transaction</a>
            </div>
            <div class="col-md-4">
                <a href="withdraw.jsp" class="btn btn-success btn-block w-100">Withdraw Money</a>
            </div>
            <div class="col-md-4">
                <a href="transfer.jsp" class="btn btn-warning btn-block w-100">Self Transfer</a>
            </div>
        </div>
    </div>

    <!-- Logout -->
    <div class="text-center my-3">
        <a href="logout.jsp" class="btn btn-danger">Logout</a>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
