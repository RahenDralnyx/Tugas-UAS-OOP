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
import model.Client;

/**
 *
 * @author HYPE AMD
 */
public class MedicalRecordDaoImpl implements MedicalRecordDao {
    private Connection conn;

    // Query dasar dipakai bersama oleh getAll, getSome, dan getById
    // supaya JOIN-nya cukup ditulis sekali saja di sini.
    private static final String BASE_SELECT =
        "SELECT mr.*, "
        + "a.type AS animal_type, a.age, a.weight, a.gender, "
        + "c.id_code AS client_id, c.name AS client_name, c.address AS client_address, c.telephone AS client_telephone, "
        + "d.name AS doctor_name, d.address AS doctor_address, d.telephone AS doctor_telephone, d.price AS doctor_price, "
        + "m.name AS medicine_name, m.price AS medicine_price, m.stock AS medicine_stock "
        + "FROM medical_records mr "
        + "JOIN animals a ON mr.id_animal = a.id_code "
        + "JOIN clients c ON a.id_client = c.id_code "
        + "JOIN doctors d ON mr.id_doctor = d.id_code "
        + "JOIN medicines m ON mr.id_medicine = m.id_code";

    // Mengubah satu baris ResultSet (hasil BASE_SELECT) menjadi objek MedicalRecord.
    // Dipakai bersama supaya logic mapping-nya juga tidak diulang-ulang.
    private MedicalRecord mapRow(ResultSet rs) throws SQLException {
        String idMedicalRecord = rs.getString("id_code");
        String idAnimal = rs.getString("id_animal");
        String idDoctor = rs.getString("id_doctor");
        String idMedicine = rs.getString("id_medicine");
        int medicineQty = rs.getInt("medicine_qty");
        String diagnose = rs.getString("diagnose");
        String suggest = rs.getString("suggest");
        Date date = rs.getDate("date");

        Client client = new Client(rs.getString("client_id"), rs.getString("client_name"), rs.getString("client_address"), rs.getString("client_telephone"), null);
        Animal animal = new Animal(idAnimal, rs.getString("animal_type"), rs.getInt("age"), rs.getDouble("weight"), rs.getBoolean("gender"), client);
        Doctor doctor = new Doctor(idDoctor, rs.getString("doctor_name"), rs.getString("doctor_address"), rs.getString("doctor_telephone"), rs.getDouble("doctor_price"));
        Medicine medicine = new Medicine(idMedicine, rs.getString("medicine_name"), rs.getDouble("medicine_price"), rs.getInt("medicine_stock"));

        return new MedicalRecord(idMedicalRecord, animal, doctor, medicine, medicineQty, diagnose, suggest, date);
    }

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
            
        } catch (SQLException e) {
            throw e;
        }
        return generatedId;
    }
    
    @Override
    public List<MedicalRecord> getAll() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        
        try {
            PreparedStatement pst = conn.prepareStatement(BASE_SELECT);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                medicalRecords.add(mapRow(rs));
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
            String sql = BASE_SELECT + " ORDER BY mr.id DESC LIMIT " + limit + " OFFSET " + offset;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                medicalRecords.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicalRecords;
    }
}
