package com.bank.dao;

import com.bank.dto.*; 

import com.bank.connection.*;
import java.sql.*;

public class UserDAO {
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, full_name, email, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Connection_factory.requestConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setDouble(5, user.getBalance());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 


  
    }
    



