/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.DoctorDaoImpl;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Doctor;

/**
 *
 * @author HYPE AMD
 */
public class DoctorController {
    private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();
    
    public void create(Doctor doctor) throws SQLException {
        if (!doctor.getName().matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Name must contain letters!");
            return;
        }
        
        doctorDao.create(doctor);
        JOptionPane.showMessageDialog(null, "Doctor created successfully!");
    }
    
    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }
    
    public List<Doctor> getSome(int limit, int offset) {
        return doctorDao.getSome(limit, offset);
    }
    
    public Doctor getById(String idDoctor) {
        return doctorDao.getById(idDoctor);
    }
    
    public void update(Doctor doctor, String idDoctor) throws SQLException {
        doctorDao.update(doctor, idDoctor);
    }
    
    public void delete(String idDoctor) throws SQLException {
        doctorDao.delete(idDoctor);
    }
    
    public int countDoctors() {
        return doctorDao.countDoctors();
    }
}
