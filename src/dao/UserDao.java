/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import model.User;

/**
 *
 * @author HYPE AMD
 */
public interface UserDao {
    public void create(User user) throws SQLException;
    public int getExist(String email);
    public String getPassword(String email);
}
