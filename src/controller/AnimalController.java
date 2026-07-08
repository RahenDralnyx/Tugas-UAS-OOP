/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.AnimalDaoImpl;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Animal;

/**
 *
 * @author HYPE AMD
 */
public class AnimalController {
    private final AnimalDaoImpl animalDao = new AnimalDaoImpl();
    
    public void create(Animal animal) throws SQLException {
        // cek tipe
        if (!animal.getType().matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Type must contain letters!");
            return;
        }
        
        animalDao.create(animal);
        JOptionPane.showMessageDialog(null, "Animal created successfully!");
    }
    
    public List<Animal> getAll() {
        return animalDao.getAll();
    }
    
    public List<Animal> getSome(int limit, int offset) {
        return animalDao.getSome(limit, offset);
    }
    
    public Animal getById(String idAnimal) {
        return animalDao.getById(idAnimal);
    }
    
    
    public void update(Animal animal, String idAnimal) throws SQLException {
        animalDao.update(animal, idAnimal);
    }
    
    public void delete(String idAnimal) throws SQLException {
        animalDao.delete(idAnimal);
    }
    
    public int countAnimals() {
        return animalDao.countAnimals();
    }
}
