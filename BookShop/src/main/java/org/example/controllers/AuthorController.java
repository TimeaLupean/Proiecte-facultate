package org.example.controllers;

import org.example.main.Author;
import org.example.repositories.AuthorRepository;

import java.util.List;

public class AuthorController {
    private AuthorRepository authorRepository;
    private PersonController personController;

    public AuthorController(AuthorRepository authorRepository, PersonController personController) {
        this.authorRepository = authorRepository;
        this.personController = personController;
    }

    public void createAauthor(int authorId, String firstName, String lastName, String birthDate,String address) {
        personController.createPerson(authorId, firstName, lastName, birthDate, address,null);
    }


    public Author findAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }

    public Author findAuthorByName(String firstname, String lastname){return authorRepository.findbyName(firstname,lastname);}

    public List<Author> viewAllAuthors() {
        return authorRepository.findAll();
    }

    public void updateAuthor(int authorId, String firstName, String lastName, String birthDate,String address) {
        Author updatedAuthor = new Author(authorId, firstName, lastName, birthDate, address);
        authorRepository.update(updatedAuthor);
    }

    public void deleteAuthor(int authorId) {
        authorRepository.delete(authorId);
    }


}
