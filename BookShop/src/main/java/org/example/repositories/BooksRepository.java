package org.example.repositories;

import org.example.main.Books;

import java.util.ArrayList;
import java.util.List;


public class BooksRepository {
    private List<Books> books = new ArrayList<>();

    public BooksRepository(List<Books> books) {
        this.books = books;
    }

    public Books findById(int bookId) {
        for (Books book : books) {
            if (book.getBook_id() == bookId) {
                return book;
            }
        }
        System.out.println("Could not find book with Id:" + bookId);
        return null;
    }


    public List<Books> findAll() {
        if(books.isEmpty()){
            System.out.println("No book was found");
            return null;
        }

        return books;
    }


    public boolean save(Books book) {
        boolean saved = false;
        for (Books book1 : books) {
            if (book1.getBook_id() == book1.getBook_id()) {
                System.out.println("Book already exists");
                return saved;
            }

        }
        books.add(book);
        for (Books book1 : books) {
            if (book1.getBook_id() == book1.getBook_id())
                saved = true;
        }

        return saved;
    }


    public boolean update(Books book) {
        boolean updated = false;
        for (Books book1 : books) {
            if (book1.getBook_id() == book.getBook_id()) {
                book1.setTitle(book.getTitle());
                book1.setAuthor(book.getAuthor());
                book1.setCategory(book.getCategory());
                book1.setPrice(book.getPrice());
                book1.setPublishing_year(book.getPublishing_year());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Book was not updated");
        return updated;
    }


    public boolean delete(int booksId) {
        boolean deleted = false;
        Books bookMethodToRemove = null;
        for (Books book : books) {
            if (book.getBook_id() == booksId) {
                bookMethodToRemove = book;
                break;
            }
        }

        if (bookMethodToRemove == null) {
            System.out.println("Bppk does not exist");
            deleted = false;
        }
        if (bookMethodToRemove != null) {
            books.remove(bookMethodToRemove);
            deleted = true;
        }
        return deleted;
    }
}