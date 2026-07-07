/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Animal;

/**
 *
 * @author HYPE AMD
 */
public interface AnimalDao {
    public void create(Animal animal) throws SQLException;
    public List<Animal> getAll();
    public List<Animal> getSome(int limit, int offset);
    public Animal getById(String idAnimal);
    public List<Animal> getByClientId(String idClient);
    public void update(Animal animal, String idAnimal) throws SQLException;
    public void delete(String idAnimal) throws SQLException;
}
