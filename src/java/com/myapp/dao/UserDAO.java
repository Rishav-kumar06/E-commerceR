/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.dao;

import com.myapp.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static List<User> users = new ArrayList<>();

    // register new user
    public boolean registerUser(User user) {
        users.add(user);
        return true;
    }

    // login user
    public User loginUser(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}