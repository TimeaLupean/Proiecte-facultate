package tests;

import org.example.main.Shipping;
import org.example.repositories.ShippingRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ShippingRepositoryTest {

    @Test
    void findByIdTest_expected() {
        Shipping expectedShipping = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(expectedShipping);

        Shipping actualShipping = shippingRepository.findById(1);

        assertEquals(expectedShipping, actualShipping, "Failed to find shipping by Id");
    }

    @Test
    void findByIdTest_unexpected() {
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        Shipping actualShipping = shippingRepository.findById(1);

        assertNull(actualShipping, "Found unexpected shipping by Id");
    }

    @Test
    void findAllTest_expected() {
        Shipping shipping1 = new Shipping(1, "123 Street, City", "Express");
        Shipping shipping2 = new Shipping(2, "456 Avenue, Town", "Standard");

        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(shipping1);
        shippingRepository.save(shipping2);

        assertEquals(2, shippingRepository.findAll().size(), "Failed to find all shippings");
    }

    @Test
    void findAllTest_unexpected() {
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());

        assertNull(shippingRepository.findAll(), "Found unexpected shippings");
    }

    @Test
    void saveTest_expected() {
        Shipping shipping = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        boolean saved = shippingRepository.save(shipping);

        assertTrue(saved, "Failed to save the shipping");
    }

    @Test
    void saveTest_unexpected() {
        Shipping shipping = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(shipping);

        boolean savedAgain = shippingRepository.save(shipping);

        assertFalse(savedAgain, "Saved the shipping again unexpectedly");
    }

    @Test
    void updateTest_expected() {
        Shipping initialShipping = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(initialShipping);

        Shipping updatedShipping = new Shipping(1, "789 Boulevard, Village", "Standard");
        boolean updated = shippingRepository.update(updatedShipping);

        assertTrue(updated, "Failed to update the shipping");
    }

    @Test
    void updateTest_unexpected() {
        Shipping initialShipping = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(initialShipping);

        Shipping updatedShipping = new Shipping(2, "789 Boulevard, Village", "Standard");
        boolean updated = shippingRepository.update(updatedShipping);

        assertFalse(updated, "Updated non-existing shipping unexpectedly");
    }

    @Test
    void deleteTest_expected() {
        Shipping shippingToDelete = new Shipping(1, "123 Street, City", "Express");
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());
        shippingRepository.save(shippingToDelete);

        boolean deleted = shippingRepository.delete(1);

        assertTrue(deleted, "Failed to delete shipping");
        assertNull(shippingRepository.findById(1), "Shipping still exists after deletion");
    }

    @Test
    void deleteTest_unexpected() {
        ShippingRepository shippingRepository = new ShippingRepository(new ArrayList<>());

        boolean deleted = shippingRepository.delete(1);

        assertFalse(deleted, "Deleted non-existing shipping unexpectedly");
    }
}
