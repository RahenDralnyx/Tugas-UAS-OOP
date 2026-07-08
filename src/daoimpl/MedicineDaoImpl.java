/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.MedicineDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HYPE AMD
 */
public class MedicineDaoImpl implements MedicineDao {
    private Connection conn;
    
    public MedicineDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void create(Medicine medicine) throws SQLException {
        try {
            String sql = "INSERT INTO medicines (id,name,price,stock) VALUES (0,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, medicine.getName());
            pst.setDouble(2, medicine.getPrice());
            pst.setInt(3, medicine.getStock());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public List<Medicine> getAll() {
        List<Medicine> medicines = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM medicines";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idMedicine = rs.getString("id_code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                
                medicines.add(new Medicine(idMedicine,name,price,stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicines;
    }
    
    @Override
    public List<Medicine> getSome(int limit, int offset) {
        List<Medicine> medicines = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM medicines ORDER BY id DESC LIMIT "+String.valueOf(limit)+" OFFSET "+String.valueOf(offset);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idMedicine = rs.getString("id_code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                
                medicines.add(new Medicine(idMedicine,name,price,stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicines;
    }
    
    @Override
    public Medicine getById(String idMedicine) {
        Medicine medicine = null;
        
        try {
            String sql = "SELECT * FROM medicines WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idMedicine);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id_code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                
                medicine = new Medicine(id,name,price,stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicine;
    }
    
    @Override
    public void update(Medicine medicine, String idMedicine) throws SQLException {
        try {
            String sql = "UPDATE medicines SET name=?, price=?, stock=? WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, medicine.getName());
            pst.setDouble(2, medicine.getPrice());
            pst.setInt(3, medicine.getStock());
            pst.setString(4, idMedicine);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public void delete(String idMedicine) throws SQLException {
        try {
            String sql = "DELETE FROM medicines WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idMedicine);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
