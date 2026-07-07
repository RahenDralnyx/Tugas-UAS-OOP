/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Animal;
import model.Client;

/**
 *
 * @author HYPE AMD
 */
public interface ClientDao {
    public void create(Client client) throws SQLException;
    public List<Client> getAll();
    public List<Client> getSome(int limit, int offset);
    public Client getById(String idClient);
    public void update(Client client, String idClient) throws SQLException;
    public void delete(String idClient) throws SQLException;
}
