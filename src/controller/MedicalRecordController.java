/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.MedicalRecordDaoImpl;
import java.sql.SQLException;
import java.util.List;
import model.MedicalRecord;

/**
 *
 * @author HYPE AMD
 */
public class MedicalRecordController {
    private final MedicalRecordDaoImpl medicalRecordDao = new MedicalRecordDaoImpl();
    
    public String create(MedicalRecord medicalRecord) throws SQLException {
        return medicalRecordDao.create(medicalRecord);
    }
    
    public List<MedicalRecord> getAll() {
        return medicalRecordDao.getAll();
    }
    
    public List<MedicalRecord> getSome(int limit, int offset) {
        return medicalRecordDao.getSome(limit, offset);
    }
    
}
