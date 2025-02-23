package com.bank.dao;


import java.sql.*; 


import com.bank.connection.Connection_factory;
import com.bank.dto.Transaction;

public class TransactionDAO {

    public boolean performTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, transaction_type, amount) VALUES (?, ?, ?)";
        try (Connection conn = Connection_factory.requestConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transaction.getUserId());
            ps.setString(2, transaction.getTransactionType());
            ps.setDouble(3, transaction.getAmount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   

        // Method to update the user's balance (either deposit or withdraw)
        public static boolean updateBalance(String username, double amount, boolean isDeposit) {
            

            // SQL to update balance
            String updateBalanceQuery = isDeposit ?
                    "UPDATE users SET balance = balance + ? WHERE username = ?" :
                    "UPDATE users SET balance = balance - ? WHERE username = ?";

            try (Connection conn = Connection_factory.requestConnection();
                 PreparedStatement ps = conn.prepareStatement(updateBalanceQuery)) {

                ps.setDouble(1, amount);
                ps.setString(2, username);

                int rowsUpdated = ps.executeUpdate();
                return rowsUpdated > 0; // If balance update is successful
            } catch (SQLException e) {
                e.printStackTrace();
                return false; // If there is an issue during the transaction
            }
        }
    


    public double getBalance(int userId) {
        String sql = "SELECT balance FROM users WHERE user_id = ?";
        try (Connection conn = Connection_factory.requestConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

  
}

