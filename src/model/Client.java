/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

public class Client extends Person {
    private String idClient;
    private ArrayList<Animal> animals;
    
    
    public Client(String idClient, String name, String address, String telephone, ArrayList<Animal> animals) {
        super(name, address, telephone);
        this.idClient = idClient;
        if (animals != null) {
            this.animals = new ArrayList<>(animals);
        }
    }

    public String getIdClient() {
        return idClient;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
    
}
