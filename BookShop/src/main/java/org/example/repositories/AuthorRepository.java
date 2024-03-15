package org.example.repositories;

import org.example.main.Author;

import java.util.ArrayList;
import java.util.List;


public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public AuthorRepository(List<Author> authors) {
        this.authors = authors;
    }
    public AuthorRepository() {

    }

    public Author findById(int authorId){
        for (Author author : authors) {
            if (author.getAuthor_id() == authorId) {
                return author;
            }
        }
        System.out.println("Could not find author with Id:" + authorId);
        return null;
    }

    public Author findbyName(String firstname, String lastname){
        for (Author author : authors) {
            if (author.getFirstName() == firstname && author.getLastName() == lastname) {
                return author;
            }
        }
        System.out.println("Could not find this author");
        return null;
    }

    public List<Author> findAll(){
        if(authors.isEmpty()){
            System.out.println("No authors found");
            return null;
        }

        return authors;
    }

    public boolean save(Author author){
        boolean saved = false;
        for (Author author1 : authors) {
            if (author.getAuthor_id() == author1.getAuthor_id()) {
                System.out.println("Author already exists");
                return saved;
            }

        }
        authors.add(author);
        for (Author author1 : authors) {
            if (author.getAuthor_id() == author1.getAuthor_id())
                saved = true;
        }

        return saved;
    }
    public boolean update(Author author){
        boolean updated = false;
        for (Author authorr : authors) {
            if (authorr.getAuthor_id() == author.getAuthor_id()) {
                author.setFirstName(authorr.getFirstName());
                author.setLastName(authorr.getLastName());
                author.setBirth_date(authorr.getBirth_date());
                author.setAddress(authorr.getAddress());
                updated = true;
                break;
            }
        }
        if(updated == false)
            System.out.println("Author was not updated");
        return updated;
    }
    public boolean delete(int authorId){
        boolean deleted = false;
        Author authorToRemove = null;
        for (Author author : authors) {
            if (author.getAuthor_id() == authorId) {
                authorToRemove = author;
                break;
            }
        }
        if (authorToRemove == null) {
            System.out.println("Author was not found");
            deleted = false;
        }

        if (authorToRemove != null) {
            authors.remove(authorToRemove);
            deleted = true;
        }

        return deleted;
    }

}
