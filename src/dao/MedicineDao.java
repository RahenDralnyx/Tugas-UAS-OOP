/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Medicine;

/**
 *
 * @author HYPE AMD
 */
public interface MedicineDao {
    public void create(Medicine medicine) throws SQLException;
    public List<Medicine> getAll();
    public List<Medicine> getSome(int limit, int offset);
    public Medicine getById(String idMedicine);
    public void update(Medicine medicine, String idMedicine) throws SQLException;
    public void delete(String idMedicine) throws SQLException;
}
