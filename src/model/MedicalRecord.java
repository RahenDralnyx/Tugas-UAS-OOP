/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author user
 */
public class MedicalRecord {
    private String idMedicalRecord;
    private Animal animal;
    private Doctor doctor;
    private Medicine medicine;
    private int medicineQty;
    private String diagnose;
    private String suggest;
    private Date date;

    public MedicalRecord(String idMedicalRecord, Animal animal, Doctor doctor, Medicine medicine, int medicineQty, String diagnose, String suggest, Date date) {
        this.idMedicalRecord = idMedicalRecord;
        this.animal = animal;
        this.doctor = doctor;
        this.medicine = medicine;
        this.medicineQty = medicineQty;
        this.diagnose = diagnose;
        this.suggest = suggest;
        this.date = date;
    }
    

    public String getIdMedicalRecord() {
        return idMedicalRecord;
    }

    public Animal getAnimal() {
        return animal;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getMedicineQty() {
        return medicineQty;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public String getSuggest() {
        return suggest;
    }

    public Date getDate() {
        return date;
    }
}
