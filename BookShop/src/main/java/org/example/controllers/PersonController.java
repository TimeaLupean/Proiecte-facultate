package org.example.controllers;

import org.example.main.Author;
import org.example.main.Clients;
import org.example.main.Patterns.Factory.PersonFactory;
import org.example.repositories.AuthorRepository;
import org.example.repositories.ClientsRepository;

public class PersonController {

    private AuthorRepository authorRepository;
    private ClientsRepository clientsRepository;
    private PersonFactory personFactory;


    public PersonController( PersonFactory personFactory, AuthorRepository authorRepository, ClientsRepository clientsRepository) {
        this.personFactory = personFactory;
        this.authorRepository = authorRepository;
        this.clientsRepository = clientsRepository;
    }

    public void createPerson(int personId, String fname, String lname, String bdate, String adress, String email) {
        if (email == null || email.isEmpty()) {
            Author author = personFactory.createAuthor(personId, fname, lname, bdate, adress);
            authorRepository.save(author);
        } else {
            Clients client = personFactory.createClient(personId, fname, lname, bdate, adress, email);
            clientsRepository.save(client);
        }

    }

}