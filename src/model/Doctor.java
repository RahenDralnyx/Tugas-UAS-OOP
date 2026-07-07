/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Doctor extends Person {
    private String idDoctor;
    private double price;
    
    public Doctor(String idDoctor, String name, String address, String telephone, double price) {
        super(name, address, telephone);
        this.idDoctor = idDoctor;
        this.price = price;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public double getPrice() {
        return price;
    }
}
