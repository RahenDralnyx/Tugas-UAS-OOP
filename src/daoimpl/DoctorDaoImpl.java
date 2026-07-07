/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.DoctorDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Doctor;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HYPE AMD
 */
public class DoctorDaoImpl implements DoctorDao {
    private Connection conn;
    
    public DoctorDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void create(Doctor doctor) throws SQLException {
        try {
            String sql = "INSERT INTO doctors (id,name,address,telephone,price) VALUES (0,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, doctor.getName());
            pst.setString(2, doctor.getAddress());
            pst.setString(3, doctor.getTelephone());
            pst.setDouble(4, doctor.getPrice());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM doctors";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idDoctor = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                double price = rs.getDouble("price");
                
                doctors.add(new Doctor(idDoctor,name,address,telephone,price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctors;
    }
    
    @Override
    public List<Doctor> getSome(int limit, int offset) {
        List<Doctor> doctors = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM doctors ORDER BY id DESC LIMIT "+String.valueOf(limit)+" OFFSET "+String.valueOf(offset);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idDoctor = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                double price = rs.getDouble("price");
                
                doctors.add(new Doctor(idDoctor,name,address,telephone,price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctors;
    }
    
    @Override
    public Doctor getById(String idDoctor) {
        Doctor doctor = null;
        
        try {
            String sql = "SELECT * FROM doctors WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idDoctor);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                double price = rs.getDouble("price");
                
                doctor = new Doctor(id,name,address,telephone,price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctor;
    }
    
    @Override
    public void update(Doctor doctor, String idDoctor) throws SQLException {
        try {
            String sql = "UPDATE doctors SET name=?, address=?, telephone=?, price=? WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, doctor.getName());
            pst.setString(2, doctor.getAddress());
            pst.setString(3, doctor.getTelephone());
            pst.setDouble(4, doctor.getPrice());
            pst.setString(5, doctor.getIdDoctor());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public void delete(String idDoctor) throws SQLException {
        try {
            String sql = "DELETE FROM doctors WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idDoctor);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
