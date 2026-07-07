/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Animal {
    private String idAnimal;
    private Client client;
    private String type;
    private int age;
    private double weight;
    private Boolean gender;
    
    public Animal() {
        idAnimal = null;
        client = null;
        type = null;
        age = 0;
        weight = 0;
        gender = null;
    }

    public Animal(String idAnimal, String type, int age, double weight, Boolean gender, Client client) {
        this.idAnimal = idAnimal;
        this.type = type;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.client = client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public Client getClient() {
        return client;
    }

   

}


