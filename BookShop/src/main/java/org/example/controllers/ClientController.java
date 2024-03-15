package org.example.controllers;
import org.example.main.Clients;
import org.example.repositories.ClientsRepository;

import java.util.List;

public class ClientController {
    private ClientsRepository clientsRepository;
    private PersonController personController;


    public ClientController(ClientsRepository clientsRepository, PersonController personController) {
        this.clientsRepository = clientsRepository;
        this.personController = personController;
    }

    public void createClient(int clientId, String fname, String lname,String bdate, String adress, String email) {
        personController.createPerson(clientId,fname,lname,bdate,adress,email);
    }

    public Clients findClientById(int clientId) {
        return clientsRepository.findById(clientId);
    }

    public List<Clients> viewAllClients() {
        return clientsRepository.findAll();
    }

    public void updateClient(int clientId, String fname, String lname,String bdate, String adress, String email) {
        Clients updatedclient = new Clients(clientId, fname, lname, bdate, adress, email);
        clientsRepository.update(updatedclient);
    }

    public void deleteClient(int clientId) {
        clientsRepository.delete(clientId);
    }
}
