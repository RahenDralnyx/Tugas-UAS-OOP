/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Medicine {
   private String idMedicine;
   private String name;
   private double price;
   private int stock;

    public Medicine(String idMedicine, String name, double price, int stock) {
        this.idMedicine = idMedicine;
        this.price = price;
        this.name = name;
        this.stock = stock;
    }

    public String getIdMedicine() {
        return idMedicine;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
