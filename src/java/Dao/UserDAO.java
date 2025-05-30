/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.User;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());  // In production, hash the password
            ps.setString(3, user.getEmail());
            System.out.println("Executing insert for: " + user.getUsername() + ", " + user.getEmail());
            int rowCount = ps.executeUpdate();
            System.out.println("Rows inserted: " + rowCount);
            return rowCount > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry error
            System.out.println("Duplicate entry: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            e.printStackTrace(); // 👈 This will reveal the real reason if the insert fails
            return false;
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        }
        return null;
    }
}