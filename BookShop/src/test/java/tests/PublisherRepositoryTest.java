package tests;


import org.example.main.Publisher;
import org.example.repositories.PublisherRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherRepositoryTest {

    @Test
    void findByIdTest_expected() {
        Publisher publisher1 = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        publisherRepository.save(publisher1);

        Publisher expectedPublisher = publisher1;
        Publisher actualPublisher = publisherRepository.findById(1);

        assertEquals(expectedPublisher, actualPublisher, "Failed to find the publisher by Id");
    }

    @Test
    void findByIdTest_unexpected() {
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        Publisher actualPublisher = publisherRepository.findById(2);
        assertNull(actualPublisher, "Found publisher with unexpected Id");
    }

    @Test
    void findAllTest_expected() {
        Publisher publisher1 = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        Publisher publisher2 = new Publisher(2, "Publisher 2", "Address 2", 67890,new ArrayList<>());

        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        List<Publisher> foundPublishers = publisherRepository.findAll();

        assertEquals(2, foundPublishers.size());
        assertTrue(foundPublishers.contains(publisher1));
        assertTrue(foundPublishers.contains(publisher2));
    }

    @Test
    void findAllTest_unexpected() {
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        List<Publisher> foundPublishers = publisherRepository.findAll();
        assertNull(foundPublishers, "Found unexpected publishers");
    }

    @Test
    void saveTest_expected() {
        Publisher publisher = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        boolean saved = publisherRepository.save(publisher);

        assertTrue(saved, "Failed to save the publisher");
        List<Publisher> foundPublishers = publisherRepository.findAll();
        assertEquals(1, foundPublishers.size());
        assertEquals(publisher, foundPublishers.get(0));
    }

    @Test
    void saveTest_unexpected() {
        Publisher publisher1 = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher(1, "Updated Publisher", "Updated Address", 54321,new ArrayList<>());
        boolean saved = publisherRepository.save(publisher2);

        assertFalse(saved, "Saved the publisher with duplicate Id");
    }

    @Test
    void updateTest_expected() {
        Publisher publisher1 = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        publisherRepository.save(publisher1);

        Publisher updatedPublisher = new Publisher(1, "Updated Publisher", "Updated Address", 54321,new ArrayList<>());
        boolean updated = publisherRepository.update(updatedPublisher);

        assertTrue(updated, "Failed to update the publisher");

    }

    @Test
    void updateTest_unexpected() {
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        Publisher publisher = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        boolean updated = publisherRepository.update(publisher);
        assertFalse(updated, "Updated a non-existing publisher");
    }

    @Test
    void deleteTest_expected() {
        Publisher publisher1 = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        Publisher publisher2 = new Publisher(2, "Publisher 2", "Address 2", 67890,new ArrayList<>());

        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        boolean deleted = publisherRepository.delete(1);
        assertTrue(deleted, "Failed to delete the publisher");

        Publisher foundPublisher = publisherRepository.findById(1);
        assertNull(foundPublisher, "Publisher not deleted successfully");
    }

    @Test
    void deleteTest_unexpected() {
        PublisherRepository publisherRepository = new PublisherRepository(new ArrayList<>());
        Publisher publisher = new Publisher(1, "Publisher 1", "Address 1", 12345,new ArrayList<>());
        publisherRepository.save(publisher);

        boolean deleted = publisherRepository.delete(2);
        assertFalse(deleted, "Deleted a non-existing publisher");
    }
}

