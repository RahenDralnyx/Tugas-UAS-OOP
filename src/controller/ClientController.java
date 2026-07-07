/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daoimpl.ClientDaoImpl;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Client;

/**
 *
 * @author HYPE AMD
 */
public class ClientController {
    private final ClientDaoImpl clientDao = new ClientDaoImpl();
    
    public void create(Client client) throws SQLException {
        if (!client.getName().matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Name must contain letters!");
            return;
        }
        
        clientDao.create(client);
        JOptionPane.showMessageDialog(null, "Client created successfully!");
    }
    
    public List<Client> getAll() {
        return clientDao.getAll();
    }
    
    public List<Client> getSome(int limit, int offset) {
        return clientDao.getSome(limit, offset);
    }
    
    public Client getById(String idClient) {
        return clientDao.getById(idClient);
    }
    
    public void update(Client client, String idClient) throws SQLException {
        clientDao.update(client, idClient);
    }
    
    public void delete(String idClient) throws SQLException {
        clientDao.delete(idClient);
    }
}
