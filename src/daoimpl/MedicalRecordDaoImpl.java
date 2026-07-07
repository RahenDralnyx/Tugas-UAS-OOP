/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.MedicalRecordDao;
import java.sql.SQLException;
import java.util.List;
import model.MedicalRecord;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Animal;
import model.Doctor;
import model.Medicine;

/**
 *
 * @author HYPE AMD
 */
public class MedicalRecordDaoImpl implements MedicalRecordDao {
    private Connection conn;
    
    public MedicalRecordDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public String create(MedicalRecord medicalRecord) throws SQLException {
        String generatedId = null;
        try {
            String sql = "INSERT INTO medical_records (id,id_animal,id_doctor,id_medicine,medicine_qty,diagnose,suggest,date) VALUES (0,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, medicalRecord.getAnimal().getIdAnimal());
            pst.setString(2, medicalRecord.getDoctor().getIdDoctor());
            pst.setString(3, medicalRecord.getMedicine().getIdMedicine());
            pst.setInt(4, medicalRecord.getMedicineQty());
            pst.setString(5, medicalRecord.getDiagnose());
            pst.setString(6, medicalRecord.getSuggest());
            pst.setDate(7, medicalRecord.getDate() != null ? medicalRecord.getDate() : new Date(System.currentTimeMillis()));
            pst.executeUpdate();
            
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                    generatedId = keys.getString(1);
                }
            }
            
            // fall back: re-read id_code of the row we just inserted, in case
            // id_code is a generated/derived column rather than the raw auto-increment id
            if (generatedId != null) {
                String checkSql = "SELECT id_code FROM medical_records WHERE id=?";
                PreparedStatement checkPst = conn.prepareStatement(checkSql);
                checkPst.setString(1, generatedId);
                ResultSet rs = checkPst.executeQuery();
                if (rs.next()) {
                    generatedId = rs.getString("id_code");
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return generatedId;
    }
    
    @Override
    public List<MedicalRecord> getAll() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM medical_records";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idMedicalRecord = rs.getString("id_code");
                String idAnimal = rs.getString("id_animal");
                String idDoctor = rs.getString("id_doctor");
                String idMedicine = rs.getString("id_medicine");
                int medicineQty = rs.getInt("medicine_qty");
                String diagnose = rs.getString("diagnose");
                String suggest = rs.getString("suggest");
                Date date = rs.getDate("date");
                
                medicalRecords.add(new MedicalRecord(idMedicalRecord,new Animal(idAnimal,"",0,0,null,null),new Doctor(idDoctor,"","","",0),new Medicine(idMedicine,"",0,0),medicineQty,diagnose,suggest,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> getSome(int limit, int offset) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM medical_records ORDER BY id DESC LIMIT "+String.valueOf(limit)+" OFFSET "+String.valueOf(offset);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idMedicalRecord = rs.getString("id_code");
                String idAnimal = rs.getString("id_animal");
                String idDoctor = rs.getString("id_doctor");
                String idMedicine = rs.getString("id_medicine");
                int medicineQty = rs.getInt("medicine_qty");
                String diagnose = rs.getString("diagnose");
                String suggest = rs.getString("suggest");
                Date date = rs.getDate("date");
                
                medicalRecords.add(new MedicalRecord(idMedicalRecord,new Animal(idAnimal,"",0,0,null,null),new Doctor(idDoctor,"","","",0),new Medicine(idMedicine,"",0,0),medicineQty,diagnose,suggest,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicalRecords;
    }
    
    @Override
    public MedicalRecord getById(String idMedicalRecord) {
        MedicalRecord medicalRecord = null;
        
        try {
            String sql = "SELECT * FROM medical_records WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idMedicalRecord);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id_code");
                String idAnimal = rs.getString("id_animal");
                String idDoctor = rs.getString("id_doctor");
                String idMedicine = rs.getString("id_medicine");
                int medicineQty = rs.getInt("medicine_qty");
                String diagnose = rs.getString("diagnose");
                String suggest = rs.getString("suggest");
                Date date = rs.getDate("date");
                
                medicalRecord = new MedicalRecord(id,new Animal(idAnimal,"",0,0,null,null),new Doctor(idDoctor,"","","",0),new Medicine(idMedicine,"",0,0),medicineQty,diagnose,suggest,date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicalRecord;
    }
    
    @Override
    public void update(MedicalRecord medicalRecord, String idMedicalRecord) throws SQLException {
        try {
            String sql = "UPDATE medical_records SET id_animal=?, id_doctor=?, id_medicine=?, medicine_qty=?, diagnose=?, suggest=?, date=? WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, medicalRecord.getAnimal().getIdAnimal());
            pst.setString(2, medicalRecord.getDoctor().getIdDoctor());
            pst.setString(3, medicalRecord.getMedicine().getIdMedicine());
            pst.setInt(4, medicalRecord.getMedicineQty());
            pst.setString(5, medicalRecord.getDiagnose());
            pst.setString(6, medicalRecord.getSuggest());
            pst.setDate(7, medicalRecord.getDate());
            pst.setString(8, idMedicalRecord);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public void delete(String idMedicalRecord) throws SQLException {
        try {
            String sql = "DELETE FROM medical_records WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idMedicalRecord);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
