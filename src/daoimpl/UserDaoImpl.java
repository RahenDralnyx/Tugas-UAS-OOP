/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author HYPE AMD
 */
public class UserDaoImpl implements UserDao {
    private Connection conn;

    public UserDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void create(User user) throws SQLException {
        try {
            String sql = "INSERT INTO users (email,password) VALUES (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public int getExist(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return 1;
                }
            }
            return 0;
        } catch (SQLException ex) {
            return 2;
        }
    }
    
    @Override
    public String getPassword(String email) {
        String password = "";
        
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String user = rs.getString("email");
                String pass = rs.getString("password");
                
                if (user.equals(email)) {
                    password = pass;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return password;
    }
}
