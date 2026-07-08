/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.MedicalRecord;

/**
 *
 * @author HYPE AMD
 */
public interface MedicalRecordDao {
    public String create(MedicalRecord medicalRecord) throws SQLException;
    public List<MedicalRecord> getAll();
    public List<MedicalRecord> getSome(int limit, int offset);
}
