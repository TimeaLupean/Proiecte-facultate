package tests;

import org.example.controllers.AuthorController;
import org.example.controllers.PersonController;
import org.example.main.Author;
import org.example.main.Clients;
import org.example.main.Patterns.Factory.PersonFactory;
import org.example.repositories.AuthorRepository;
import org.example.repositories.ClientsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AuthorControllerTest {
    private AuthorController authorController;
    private PersonController personController;


    @BeforeEach
    public void setUp() {
        AuthorRepository mockRepository = new MockAuthorRepository();
        ClientsRepository mockClientRepo = new MockClientsRepository();
        PersonFactory personFactory = new PersonFactory();
        personController = new PersonController(personFactory,mockRepository,mockClientRepo);
        authorController = new AuthorController(mockRepository,personController);
    }

    @Test
    public void testCreateAuthor() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        Author createdAuthor = authorController.findAuthorById(1);
        assertNotNull(createdAuthor);
        assertEquals("John", createdAuthor.getFirstName());
        assertEquals("Doe", createdAuthor.getLastName());
        assertEquals("2000-01-01", createdAuthor.getBirth_date());
        assertEquals("123 Main St", createdAuthor.getAddress());
    }

    @Test
    public void testFindAuthorById() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        Author foundAuthor = authorController.findAuthorById(1);
        assertNotNull(foundAuthor);
        assertEquals("John", foundAuthor.getFirstName());
        assertEquals("Doe", foundAuthor.getLastName());
        assertEquals("2000-01-01", foundAuthor.getBirth_date());
        assertEquals("123 Main St", foundAuthor.getAddress());
    }

    @Test
    public void testFindAuthorByName() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        Author foundAuthor = authorController.findAuthorByName("John", "Doe");
        assertNotNull(foundAuthor);
        assertEquals("John", foundAuthor.getFirstName());
        assertEquals("Doe", foundAuthor.getLastName());
        assertEquals("2000-01-01", foundAuthor.getBirth_date());
        assertEquals("123 Main St", foundAuthor.getAddress());
    }

    @Test
    public void testViewAllAuthors() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        authorController.createAauthor(2, "Jane", "Smith", "1995-05-15", "456 Oak St");
        List<Author> allAuthors = authorController.viewAllAuthors();
        assertNotNull(allAuthors);
        assertEquals(2, allAuthors.size());
    }

    @Test
    public void testUpdateAuthor() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        authorController.updateAuthor(1, "Updated", "Author", "2000-01-01", "456 New St");
        Author updatedAuthor = authorController.findAuthorById(1);
        assertNotNull(updatedAuthor);
        assertEquals("Updated", updatedAuthor.getFirstName());
        assertEquals("Author", updatedAuthor.getLastName());
        assertEquals("2000-01-01", updatedAuthor.getBirth_date());
        assertEquals("456 New St", updatedAuthor.getAddress());
    }

    @Test
    public void testDeleteAuthor() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        authorController.deleteAuthor(1);
        Author deletedAuthor = authorController.findAuthorById(1);
        assertNull(deletedAuthor);
    }

    @Test
    public void testCreateAuthorWithDuplicateId() {
        authorController.createAauthor(1, "John", "Doe", "2000-01-01", "123 Main St");
        try {
            authorController.createAauthor(1, "Jane", "Smith", "1995-05-15", "456 Oak St");
        } catch (IllegalArgumentException e) {
            System.out.println("An author already exists with this id.");
        }
    }

    @Test
    public void testFindNonexistentAuthorById() {
        Author foundAuthor = authorController.findAuthorById(999);
        assertNull(foundAuthor);
    }

    @Test
    public void testFindNonexistentAuthorByName() {
        Author foundAuthor = authorController.findAuthorByName("Nonexistent", "Author");
        assertNull(foundAuthor);
    }

    @Test
    public void testUpdateNonexistentAuthor() {
        try {
            authorController.updateAuthor(999, "Updated", "Author", "2000-01-01", "456 New St");
        } catch (IllegalArgumentException e) {
            System.out.println("Cn not update a nonexistent author");
        }
    }

    @Test
    public void testDeleteNonexistentAuthor() {
        try {
            authorController.deleteAuthor(999);
        } catch (IllegalArgumentException e) {
            System.out.println("Can not delete a nonexistent author");
        }
    }

    private static class MockClientsRepository extends ClientsRepository{
        private final List<Clients> clients = new ArrayList<>();
        public MockClientsRepository(){}

        @Override
        public Clients findById(int clientId){
            for (Clients client : clients) {
                if (client.getClient_id() == clientId) {
                    return client;
                }
            }
            return null;
        }

        @Override
        public List<Clients> findAll() {
            return new ArrayList<>(clients);
        }

        @Override
        public boolean save(Clients client){ return clients.add(client);}

        @Override
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
            return updated;
        }

        @Override
        public boolean delete(int clientId) {
            return clients.removeIf(client -> client.getClient_id() == clientId);
        }

    }
    private static class MockAuthorRepository extends AuthorRepository {
        private final List<Author> authors = new ArrayList<>();

        public MockAuthorRepository() {}

        @Override
        public List<Author> findAll() {
            return new ArrayList<>(authors);
        }

        @Override
        public Author findById(int authorId) {
            for (Author author : authors) {
                if (author.getAuthor_id() == authorId) {
                    return author;
                }
            }
            return null;
        }


        @Override
        public Author findbyName(String firstName, String lastName) {
            for (Author author : authors) {
                if (author.getFirstName().equals(firstName) && author.getLastName().equals(lastName)) {
                    return author;
                }
            }
            return null;
        }

        @Override
        public boolean save(Author author) {
            return authors.add(author);
        }

        @Override
        public boolean update(Author author) {
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getAuthor_id() == author.getAuthor_id()) {
                    authors.set(i, author);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean delete(int authorId) {
            return authors.removeIf(author -> author.getAuthor_id() == authorId);
        }
    }
}
