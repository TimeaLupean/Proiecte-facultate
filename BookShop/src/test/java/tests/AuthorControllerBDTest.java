package tests;
import org.example.BD_Controller.AuthorControllerBD;
import org.example.BD_Repository.AuthorRepositoryBD;
import org.example.main.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControllerBDTest {

    private AuthorControllerBD authorControllerBD;
    private AuthorRepositoryBDMock authorRepositoryBDMock;

    @BeforeEach
    void setUp() {
        authorRepositoryBDMock = new AuthorRepositoryBDMock();
        authorControllerBD = new AuthorControllerBD(authorRepositoryBDMock);
    }

    @Test
    void saveIntoDB_ValidAuthor_SuccessfullySaved() {
        Author author = new Author(1, "John", "Doe", "2000-01-01", "123 Main St");
        authorControllerBD.saveIntoDB(author);

        assertTrue(authorRepositoryBDMock.saved);
    }


    @Test
    void loadFromDB_ExistingAuthors_ReturnsAuthorList() {
        List<Author> loadedAuthors = authorControllerBD.loadFromDB();

        assertEquals(2, loadedAuthors.size());
    }

    @Test
    void findById_ExistingAuthorId_ReturnsAuthor() {
        int authorId = 1;
        Author expectedAuthor = new Author(authorId, "John", "Doe", "2000-01-01", "123 Main St");
        authorRepositoryBDMock.setAuthorToReturn(expectedAuthor);
        Author foundAuthor = authorControllerBD.findById(authorId);

        assertNotNull(foundAuthor);
        assertEquals(expectedAuthor, foundAuthor);
    }

    @Test
    void findByName_ExistingAuthorName_ReturnsAuthor() {
        String firstName = "John";
        String lastName = "Doe";
        Author expectedAuthor = new Author(1, firstName, lastName, "2000-01-01", "123 Main St");
        authorRepositoryBDMock.setAuthorToReturn(expectedAuthor);
        Author foundAuthor = authorControllerBD.findByName(firstName, lastName);

        assertNotNull(foundAuthor);
        assertEquals(expectedAuthor, foundAuthor);
    }

    @Test
    void delete_ExistingAuthorId_SuccessfullyDeleted() {
        int authorId = 1;
        authorControllerBD.delete(authorId);

        assertTrue(authorRepositoryBDMock.deleted);
        assertEquals(authorId, authorRepositoryBDMock.deletedAuthorId);
    }

    public class AuthorRepositoryBDMock extends AuthorRepositoryBD {
        boolean saved = false;
        boolean deleted = false;
        int deletedAuthorId;
        Author authorToReturn;

        @Override
        public void saveIntoDB(Author author) {
            saved = true;
        }

        @Override
        public Author createAuthorFromResultSet(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("AuthorID");
            String fname = resultSet.getString("FirstName");
            String lname = resultSet.getString("LastName");
            String bdate = resultSet.getString("BirthDate");
            String address = resultSet.getString("Address");

            authorToReturn = new Author(id, fname, lname, bdate, address);
            return authorToReturn;
        }

        @Override
        public List<Author> loadFromDB() {
            List<Author> authors = new ArrayList<>();
            authors.add(new Author(1, "John", "Doe", "2000-01-01", "123 Main St"));
            authors.add(new Author(2, "Jane", "Smith", "1995-05-15", "456 Oak St"));
            return authors;
        }

        @Override
        public Author findByID(int id) {
            return authorToReturn;
        }

        @Override
        public Author findByName(String fname, String lname) {
            return authorToReturn;
        }

        @Override
        public void delete(int id) {
            deleted = true;
            deletedAuthorId = id;
        }
        
        public void setAuthorToReturn(Author author) {
            authorToReturn = author;
        }
    }
}
