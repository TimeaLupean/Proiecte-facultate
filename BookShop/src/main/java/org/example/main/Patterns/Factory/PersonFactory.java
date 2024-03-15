package org.example.main.Patterns.Factory;

import org.example.main.Author;
import org.example.main.Clients;

public class PersonFactory {
    public Author createAuthor(int id, String firstName, String lastName, String birthDate, String address) {
        return new Author(id, firstName, lastName, birthDate, address);
    }
    public Clients createClient(int id, String firstName, String lastName, String birthDate, String address, String email) {
        return new Clients(id, firstName, lastName, birthDate, address, email);
    }
}
