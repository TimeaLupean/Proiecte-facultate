package org.example.repositories;

import org.example.main.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherRepository {

    private List<Publisher> publishers = new ArrayList<>();

    public PublisherRepository(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Publisher findById(int targetPublisherId) {
        for (Publisher publisher : publishers) {
            if (publisher.getPublisher_id() == targetPublisherId) {
                return publisher;
            }
        }
        System.out.println("Could not find publisher with Id:"+targetPublisherId);
        return null;
    }

    public List<Publisher> findAll() {
        if(publishers.isEmpty()){
            System.out.println("There is no publisher");
            return null;
        }
        return publishers;
    }

    public boolean save(Publisher publisher) {
        boolean saved = false;
        for (Publisher item : publishers) {
            if (publisher.getPublisher_id() == item.getPublisher_id()) {
                System.out.println("publisher already exists");
                return saved;
            }

        }
        publishers.add(publisher);
        for (Publisher item : publishers) {
            if (publisher.getPublisher_id() == item.getPublisher_id())
                saved = true;
        }

        return saved;

    }

    public boolean update(Publisher updatedPublisher) {
        boolean updated = false;
        for (Publisher publisher : publishers) {
            if (publisher.getPublisher_id() == updatedPublisher.getPublisher_id()) {
                publisher.setName(updatedPublisher.getName());
                publisher.setAddress(updatedPublisher.getAddress());
                publisher.setFiscal_code(updatedPublisher.getFiscal_code());
                publisher.setBooks(updatedPublisher.getBooks());
                updated = true;
                break;
            }

        }
        if (updated == false)
            System.out.println("publisher was not updated");

        return updated;
    }


    public boolean delete(int targetPublisherId) {

        boolean deleted = false;
        Publisher publisherToRemove = null;
        for (Publisher publisher : publishers) {
            if (publisher.getPublisher_id() == targetPublisherId) {
                publisherToRemove = publisher;
                break;
            }
        }

        if (publisherToRemove == null) {
            System.out.println("Payment method does not exist");
            deleted = false;
        }

        if (publisherToRemove != null) {
            publishers.remove(publisherToRemove);
            deleted = true;

        }
        return deleted;


    }
}
