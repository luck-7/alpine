package com.bank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.TransactionDAO;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Ensure user is logged in
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        double withdrawAmount = Double.parseDouble(request.getParameter("amount"));
        double currentBalance = 0.0;

        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/bankdb";
        String dbUser = "root";
        String dbPassword = "root";
        
        
        

        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            // Fetch the current balance
            String balanceQuery = "SELECT balance FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(balanceQuery)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        currentBalance = rs.getDouble("balance");
                    } else {
                        request.setAttribute("errorMessage", "User not found.");
                        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Ensure sufficient balance
            if (withdrawAmount > currentBalance) {
                request.setAttribute("errorMessage", "Insufficient balance.");
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                return;
            }

            // Create a TransactionDAO object
            TransactionDAO transactionDAO = new TransactionDAO();

            // Update balance by deducting the withdraw amount
            boolean isUpdated = TransactionDAO.updateBalance(username, withdrawAmount, false);

            if (isUpdated) {
                // Successfully updated balance
                request.setAttribute("successMessage", "Withdrawal successful.");
            } else {
                // Failed to update balance
                request.setAttribute("errorMessage", "Transaction failed. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred.");
        }

        // Forward back to the withdraw page
        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
    }
}



