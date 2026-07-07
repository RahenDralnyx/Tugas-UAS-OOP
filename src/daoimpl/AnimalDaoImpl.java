/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.AnimalDao;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Client;
import model.Animal;

/**
 *
 * @author HYPE AMD
 */
public class AnimalDaoImpl implements AnimalDao {
    private Connection conn;

    public AnimalDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void create(Animal animal) throws SQLException {
        try {
            String sql = "INSERT INTO animals (id,id_client,type,age,weight,gender) VALUES (0,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, animal.getClient() != null ? animal.getClient().getIdClient() : "");
            pst.setString(2, animal.getType());
            pst.setInt(3, animal.getAge());
            pst.setDouble(4, animal.getWeight());
            pst.setBoolean(5, animal.getGender());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public List<Animal> getAll() {
        List<Animal> animals = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM animals";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idAnimal = rs.getString("id_code");
                String type = rs.getString("type");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");
                Boolean gender = rs.getBoolean("gender");
                String idClient = rs.getString("id_client");
                
                animals.add(new Animal(idAnimal,type,age,weight,gender,new Client(idClient, "", "", "", null)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return animals;
    }
    
    @Override
    public List<Animal> getSome(int limit, int offset) {
        List<Animal> animals = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM animals ORDER BY id DESC LIMIT "+String.valueOf(limit)+" OFFSET "+String.valueOf(offset);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idAnimal = rs.getString("id_code");
                String type = rs.getString("type");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");
                Boolean gender = rs.getBoolean("gender");
                String idClient = rs.getString("id_client");
                
                animals.add(new Animal(idAnimal,type,age,weight,gender,new Client(idClient, "", "", "", null)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return animals;
    }
    
    @Override
    public Animal getById(String idAnimal) {
        Animal animal = null;
        
        try {
            String sql = "SELECT * FROM animals WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idAnimal);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id_code");
                String type = rs.getString("type");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");
                Boolean gender = rs.getBoolean("gender");
                String idClient = rs.getString("id_client");
                
                animal = new Animal(id,type,age,weight,gender,new Client(idClient, "", "", "", null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return animal;
    }
    
    @Override
    public List<Animal> getByClientId(String idClient) {
        List<Animal> animals = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM animals WHERE id_client=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idClient);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idAnimal = rs.getString("id_code");
                String type = rs.getString("type");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");
                Boolean gender = rs.getBoolean("gender");
                
                animals.add(new Animal(idAnimal,type,age,weight,gender,new Client(idClient, "", "", "", null)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return animals;
    }
    
    @Override
    public void update(Animal animal, String idAnimal) throws SQLException {
        try {
            String sql = "UPDATE animals SET type=?, age=?, weight=?, gender=?, id_client=? WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, animal.getType());
            pst.setInt(2, animal.getAge());
            pst.setDouble(3, animal.getWeight());
            pst.setBoolean(4, animal.getGender());
            pst.setString(5, animal.getClient() != null ? animal.getClient().getIdClient() : "");
            pst.setString(6, idAnimal);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public void delete(String idAnimal) throws SQLException {
        try {
            String sql = "DELETE FROM animals WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idAnimal);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public int countAnimals() {
    int total = 0;

        try {
            String sql = "SELECT COUNT(*) FROM animals";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return total;
    }
}
