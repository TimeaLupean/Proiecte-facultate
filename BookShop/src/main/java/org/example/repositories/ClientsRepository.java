package org.example.repositories;

import org.example.main.Clients;

import java.util.ArrayList;
import java.util.List;


public class ClientsRepository {
    private List<Clients> clients = new ArrayList<>();

    public ClientsRepository(List<Clients> clients) {
        this.clients = clients;
    }

    public ClientsRepository(){}

    public Clients findById(int targetClientId) {
        for (Clients client : clients) {
            if (client.getClient_id() == targetClientId) {
                return client;
            }
        }
        System.out.println("Could not find Client with Id: " + targetClientId);
        return null;
    }

    public List<Clients> findAll() {
        if (clients.isEmpty()) {
            System.out.println("There are no Clients");
            return null;
        }
        return clients;
    }


    public boolean save(Clients client) {
        boolean saved = false;
        for (Clients item : clients) {
            if (client.getClient_id() == item.getClient_id()) {
                System.out.println("Client already exists");
                return saved;
            }

        }
        clients.add(client);
        for (Clients item : clients) {
            if (client.getClient_id() == item.getClient_id())
                saved = true;
        }

        return saved;
    }

    public boolean update(Clients updatedclient) {
        boolean updated = false;
        for (Clients client : clients) {
            if (client.getClient_id() == updatedclient.getClient_id()) {
                client.setFirstName(updatedclient.getFirstName());
                client.setLastName(updatedclient.getLastName());
                client.setBirth_date(updatedclient.getBirth_date());
                client.setAddress(updatedclient.getAddress());
                client.setEmail(updatedclient.getEmail());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Client was not updated");

        return updated;
    }

    public boolean delete(int targetClientId) {
        boolean deleted = false;
        Clients clientToRemove = null;
        for (Clients client : clients) {
            if (client.getClient_id() == targetClientId) {
                clientToRemove = client;
                break;
            }
        }
        if (clientToRemove == null) {
            System.out.println("Client does not exist");
            deleted = false;
        }
        if (clientToRemove != null) {
            clients.remove(clientToRemove);
            deleted = true;
        }
        return deleted;


    }
}