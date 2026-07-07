/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Doctor;

/**
 *
 * @author HYPE AMD
 */
public interface DoctorDao {
    public void create(Doctor doctor) throws SQLException;
    public List<Doctor> getAll();
    public List<Doctor> getSome(int limit, int offset);
    public Doctor getById(String idDoctor);
    public void update(Doctor doctor, String idDoctor) throws SQLException;
    public void delete(String idDoctor) throws SQLException;
}
