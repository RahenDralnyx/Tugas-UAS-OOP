/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoimpl;

import config.DBConnection;
import dao.ClientDao;
import java.sql.SQLException;
import java.util.List;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HYPE AMD
 */
public class ClientDaoImpl implements ClientDao {
    private Connection conn;
    
    public ClientDaoImpl() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void create(Client client) throws SQLException {
        try {
            String sql = "INSERT INTO clients (id,name,address,telephone) VALUES (0,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, client.getName());
            pst.setString(2, client.getAddress());
            pst.setString(3, client.getTelephone());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public List<Client> getAll() {
        ArrayList<Client> clients = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM clients";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idClient = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                
                clients.add(new Client(idClient,name,address,telephone,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }
    
    @Override
    public List<Client> getSome(int limit, int offset) {
        ArrayList<Client> clients = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM clients ORDER BY id DESC LIMIT "+String.valueOf(limit)+" OFFSET "+String.valueOf(offset);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String idClient = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                
                clients.add(new Client(idClient,name,address,telephone,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }
    
    @Override
    public Client getById(String idClient) {
        Client client = null;
        
        try {
            String sql = "SELECT * FROM clients WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idClient);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id_code");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String telephone = rs.getString("telephone");
                
                client = new Client(id,name,address,telephone,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return client;
    }
    
    @Override
    public void update(Client client, String idClient) throws SQLException {
        try {
            String sql = "UPDATE clients SET name=?, address=?, telephone=? WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, client.getName());
            pst.setString(2, client.getAddress());
            pst.setString(3, client.getTelephone());
            pst.setString(4, client.getIdClient());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public void delete(String idClient) throws SQLException {
        try {
            String sql = "DELETE FROM clients WHERE id_code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idClient);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public int countClients() {
    int total = 0;

        try {
            String sql = "SELECT COUNT(*) FROM clients";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

             if (rs.next())  {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            }

        return total;
    }
}
