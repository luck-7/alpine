package com.bank.servlet;





import com.bank.dao.TransactionDAO;
import com.bank.dto.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/TransactionServlet")

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String transactionType = request.getParameter("transactionType"); // "deposit" or "withdraw"
        double amount = Double.parseDouble(request.getParameter("amount"));
        String username = request.getParameter("username");

        // Create a new Transaction object
        Transaction transaction = new Transaction();
        transaction.setUserId(getUserIdByUsername(username)); // Implement this method to fetch userId from the username
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);

        // Process the transaction using TransactionDAO
        TransactionDAO transactionDAO = new TransactionDAO();
        boolean transactionSuccess = transactionDAO.performTransaction(transaction);

        if (transactionSuccess) {
            // Update the balance of the user (either deposit or withdraw)
            boolean balanceUpdated = TransactionDAO.updateBalance(username, amount, transactionType.equalsIgnoreCase("deposit"));
            if (balanceUpdated) {
                request.setAttribute("transactionStatus", "Transaction successful!");
            } else {
                request.setAttribute("transactionStatus", "Balance update failed.");
            }
        } else {
            request.setAttribute("transactionStatus", "Transaction failed.");
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("Transaction.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        // Fetch the user's balance using the DAO
        int userId = getUserIdByUsername(username); // Implement this method to fetch userId from the username
        TransactionDAO transactionDAO = new TransactionDAO();
        double balance = transactionDAO.getBalance(userId);

        // Set balance attribute in request scope
        request.setAttribute("balance", balance);

        // Forward the request to the JSP page
        request.getRequestDispatcher("Transaction.jsp").forward(request, response);
    }

    // Helper method to fetch userId from the username (implement this method to fetch data from DB)
    private int getUserIdByUsername(String username) {
        // Logic to fetch userId from the database using username
        return 1; // Placeholder value, should fetch from DB
    }
}
