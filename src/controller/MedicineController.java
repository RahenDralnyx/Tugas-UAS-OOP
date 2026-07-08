/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.MedicineDaoImpl;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Medicine;

/**
 *
 * @author HYPE AMD
 */
public class MedicineController {
    private final MedicineDaoImpl medicineDao = new MedicineDaoImpl();
    
    public void create(Medicine medicine) throws SQLException {
        if (!medicine.getName().matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Name must contain letters!");
            return;
        }
        
        medicineDao.create(medicine);
        JOptionPane.showMessageDialog(null, "Medicine created successfully!");
    }
    
    public List<Medicine> getAll() {
        return medicineDao.getAll();
    }
    
    public List<Medicine> getSome(int limit, int offset) {
        return medicineDao.getSome(limit, offset);
    }
    
    public Medicine getById(String idMedicine) {
        return medicineDao.getById(idMedicine);
    }
    
    public void update(Medicine medicine, String idMedicine) throws SQLException {
        medicineDao.update(medicine, idMedicine);
    }
    
    public void delete(String idMedicine) throws SQLException {
        medicineDao.delete(idMedicine);
    }
    
}
