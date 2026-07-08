/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.UserDaoImpl;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author HYPE AMD
 */
public class UserController {
    private final UserDaoImpl userDao = new UserDaoImpl();
    
    public void create(User user) throws SQLException {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Please fill all field!");
            return;
        }
        
        userDao.create(user);
    }
    
    public int getExist(String email) {
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill 'Email' field!");
            return -1;
        }
        
        return userDao.getExist(email);
    }
    
    public String getPassword(String email) {
        return userDao.getPassword(email);
    }
    
}
