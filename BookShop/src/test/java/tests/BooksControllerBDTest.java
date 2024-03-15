package tests;

import org.example.BD_Controller.AuthorControllerBD;
import org.example.BD_Controller.BooksControllerBD;
import org.example.BD_Controller.CategoryControllerBD;
import org.example.BD_Repository.AuthorRepositoryBD;
import org.example.BD_Repository.BooksRepositoryDB;
import org.example.BD_Repository.CategoryRepositoryBD;
import org.example.SqlServer;
import org.example.main.Author;
import org.example.main.Books;
import org.example.main.Category;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class BooksControllerBDTest {

    private SqlServer sqlServer = new SqlServer();
    private BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
    private BooksControllerBD booksControllerBD = new BooksControllerBD(booksRepositoryDB);
    private AuthorRepositoryBD authorRepositoryBD = new AuthorRepositoryBD(sqlServer);
    private AuthorControllerBD authorControllerBD = new AuthorControllerBD(authorRepositoryBD);
    private CategoryRepositoryBD categoryRepositoryBD = new CategoryRepositoryBD(sqlServer);
    private CategoryControllerBD categoryControllerBD = new CategoryControllerBD(categoryRepositoryBD);

    @Test
    public void testLoadFromDB() {
        Author author = authorControllerBD.findById(1);
        Category category = categoryControllerBD.findById(2);
        Books book1 = new Books(6, "Book1", 2022,author, 30, category);
        Books book2 = new Books(7, "Book2", 2023, author, 40, category);
        Books book3 = new Books(8, "Book3", 2021, author, 30, category);
        booksControllerBD.saveIntoDB(book1);
        booksControllerBD.saveIntoDB(book2);
        booksControllerBD.saveIntoDB(book3);

        List<Books> loadedBooks = booksControllerBD.loadFromDB();
        assertEquals(9, loadedBooks.size());
        assertBookExistsInList(book1, loadedBooks);
        assertBookExistsInList(book2, loadedBooks);
        assertBookExistsInList(book3, loadedBooks);



    }
    private void assertBookExistsInList(Books expectedBook, List<Books> actualBooks) {
        assertTrue(
                actualBooks.stream().anyMatch(actualBook ->
                        actualBook.getBook_id() == expectedBook.getBook_id() &&
                                Objects.equals(actualBook.getTitle(), expectedBook.getTitle())),
                "Book not found in loadedBooks: " + expectedBook
        );
    }


}