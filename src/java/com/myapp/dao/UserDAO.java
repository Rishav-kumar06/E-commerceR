package com.myapp.dao;

import com.myapp.model.User;
import com.myapp.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(User user) {
        // Try inserting with role column; if DB doesn't have role column, fallback to insert without role
        String sqlWithRole = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        String sqlWithoutRole = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sqlWithRole, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getRole() != null ? user.getRole() : "USER");
                int affected = ps.executeUpdate();
                if (affected == 0) return false;
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
                return true;
            } catch (SQLException e) {
                // Possibly 'role' column doesn't exist — try fallback
                System.err.println("registerUser: insert with role failed, trying fallback. Reason: " + e.getMessage());
                try (PreparedStatement ps2 = conn.prepareStatement(sqlWithoutRole, Statement.RETURN_GENERATED_KEYS)) {
                    ps2.setString(1, user.getName());
                    ps2.setString(2, user.getEmail());
                    ps2.setString(3, user.getPassword());
                    int affected = ps2.executeUpdate();
                    if (affected == 0) return false;
                    try (ResultSet rs = ps2.getGeneratedKeys()) {
                        if (rs.next()) {
                            user.setId(rs.getInt(1));
                        }
                    }
                    return true;
                } catch (SQLException ex2) {
                    System.err.println("registerUser fallback failed: " + ex2.getMessage());
                    ex2.printStackTrace();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password) {
        // Try selecting role if it exists; otherwise fallback to select without role
        String sqlWithRole = "SELECT id, name, email, password, role FROM users WHERE email = ? AND password = ? LIMIT 1";
        String sqlWithoutRole = "SELECT id, name, email, password FROM users WHERE email = ? AND password = ? LIMIT 1";

        try (Connection conn = DBConnectionUtil.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sqlWithRole)) {
                ps.setString(1, email);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
                        return user;
                    }
                }
            } catch (SQLException e) {
                // role column might not exist; fallback
                System.err.println("loginUser: select with role failed, trying fallback. Reason: " + e.getMessage());
                try (PreparedStatement ps2 = conn.prepareStatement(sqlWithoutRole)) {
                    ps2.setString(1, email);
                    ps2.setString(2, password);
                    try (ResultSet rs2 = ps2.executeQuery()) {
                        if (rs2.next()) {
                            User user = new User();
                            user.setId(rs2.getInt("id"));
                            user.setName(rs2.getString("name"));
                            user.setEmail(rs2.getString("email"));
                            user.setPassword(rs2.getString("password"));
                            // role will be null -> User.getRole() defaults to USER
                            return user;
                        }
                    }
                } catch (SQLException ex2) {
                    System.err.println("loginUser fallback failed: " + ex2.getMessage());
                    ex2.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean emailExists(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        String sql = "SELECT 1 FROM users WHERE email = ? LIMIT 1";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
